package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.*;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;

import controller.Game;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.gameelements.Hittable;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.Table;

import static com.almasb.fxgl.app.DSLKt.*;
import static gui.Config.*;
import static gui.PinballType.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This class runs the game in a window, and updates it for every event.
 *
 * @author Cristobal Maldonado
 */
public class PinballApp extends GameApplication {
    private Game game;
    private Entity flipperL, flipperR, ball;
    private boolean ballInGame;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(WIDTH);
        settings.setHeight(HEIGHT);
        settings.setTitle("Pinball");
        settings.setVersion("1.0");
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("Score", 0);
        vars.put("Balls", 0);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new PinballFactory());

        ballInGame = false;
        spawnWalls();
        spawnBackground();
        spawnFlippers();

        game = new Game();
        getGameState().setValue("Balls", game.getBalls());
    }

    private void spawnBackground() {
        spawn("GameBackground");
        spawn("ScoreBackground");
    }

    private void spawnFlippers() {
        flipperL = spawn("Left Flipper");
        flipperR = spawn("Right Flipper");;
    }

    private void spawnBall() {
        ball = spawn("Ball");
    }

    private void spawnWalls() {
        spawn("ScreenBounds");
        spawn("Wall");
    }

    private void spawnTable() {
        Table table = game.createTable("new table", 4, 0.3,4,2);
        game.setTable(table);
        for (Bumper bumper : table.getBumpers()) {
            double x = new Random().nextDouble() * (GAME_WIDTH - 100) + 50;
            double y = new Random().nextDouble() * (HEIGHT - 250) + 50;
            if (bumper.isKickerBumper()) {
                spawn("Kicker Bumper", new SpawnData(x, y).put("hittable", bumper));
            }
            else {
                spawn("Pop Bumper", new SpawnData(x, y).put("hittable", bumper));
            }

        }

        for (Target target : table.getTargets()) {
            double x = new Random().nextDouble() * GAME_WIDTH - 50;
            double y = new Random().nextDouble() * HEIGHT - 50;
            if (target.isDropTarget()) {
                spawn("Drop Target", new SpawnData(x, y).put("hittable", target));
            }
            else {
                spawn("Spot Target", new SpawnData(x, y).put("hittable", target));
            }

        }
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addInputMapping(new InputMapping("Left Flipper", KeyCode.A));
        input.addInputMapping(new InputMapping("Right Flipper", KeyCode.D));

        input.addAction(new UserAction("New Ball") {
            @Override
            protected void onActionBegin() {
                if (game.getBalls() > 0 && game.getTable().isPlayableTable() && !ballInGame) {
                    spawnBall();
                    ballInGame = true;
                }
            }
        }, KeyCode.SPACE);

        input.addAction(new UserAction("New Table") {
            @Override
            protected  void onActionBegin() {
                if (!ballInGame) {
                    if (game.getTable().isPlayableTable()) {
                        List<Entity> targets = getGameWorld().getEntitiesByType(TARGET);
                        List<Entity> bumpers = getGameWorld().getEntitiesByType(BUMPER);
                        for (Entity target : targets) {
                            target.removeFromWorld();
                        }
                        for (Entity bumper : bumpers) {
                            bumper.removeFromWorld();
                        }
                    }
                    spawnTable();
                }
            }
        }, KeyCode.N);
    }

    @OnUserAction(name = "Left Flipper", type = ActionType.ON_ACTION)
    public void moveLeftUp() {
        flipperL.getComponent(PlayerControl.class).leftUp();
    }

    @OnUserAction(name = "Left Flipper", type = ActionType.ON_ACTION_END)
    public void moveLeftDown() {
        flipperL.getComponent(PlayerControl.class).leftDown();
    }

    @OnUserAction(name = "Right Flipper", type = ActionType.ON_ACTION)
    public void moveRightUp() {
        flipperR.getComponent(PlayerControl.class).rightUp();
    }

    @OnUserAction(name = "Right Flipper", type = ActionType.ON_ACTION_END)
    public void moveRightDown() {
        flipperR.getComponent(PlayerControl.class).rightDown();
    }

    @Override
    protected void initUI() {
        Text textScore = getUIFactory().newText("Score:", Color.BLACK, 30);
        Text intScore = getUIFactory().newText("", Color.BLACK, 30);
        Text textLives = getUIFactory().newText("Balls:", Color.BLACK, 30);
        Text intLives = getUIFactory().newText("", Color.BLACK, 30);

        textScore.setTranslateX(GAME_WIDTH + 50);
        textScore.setTranslateY(50);

        intScore.setTranslateX(GAME_WIDTH + 50);
        intScore.setTranslateY(90);
        intScore.textProperty().bind(getGameState().intProperty("Score").asString());

        textLives.setTranslateX(GAME_WIDTH + 50);
        textLives.setTranslateY(150);

        intLives.setTranslateX(GAME_WIDTH + 50);
        intLives.setTranslateY(190);
        intLives.textProperty().bind(getGameState().intProperty("Balls").asString());

        getGameScene().addUINodes(textScore, intScore, textLives, intLives);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BALL, WALL) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox boxBall, HitBox boxWall) {
                if(boxWall.getName().equals("BOT")) {
                    ball.removeComponent(PhysicsComponent.class);
                    ball.removeFromWorld();
                    ballInGame = false;
                    game.dropBall();
                    getGameState().setValue("Balls", game.getBalls());
                }
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BALL, BUMPER) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity hittable, HitBox boxBall, HitBox boxHittable) {
                Bumper bumper = hittable.getObject("hittable");
                if (bumper.isUpgraded()) {
                    changeColor(hittable, Color.YELLOW);
                }
                else {
                    changeColor(hittable, hittable.getObject("color"));
                }

                if (bumper.isKickerBumper()) {
                    play("kicker.wav");
                }
                else {
                    play("pop.wav");
                }
                bonusHandler(hittable);
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BALL, TARGET) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity hittable, HitBox boxBall, HitBox boxHittable) {
                Target target = hittable.getObject("hittable");
                if (target.isActive()) {
                    if (target.isDropTarget()) {
                        play("drop.wav");
                    }
                    else {
                        play("spot.wav");
                    }
                    changeColor(hittable, new Color(0.35,0.35,0.35,1));
                }
                bonusHandler(hittable);
            }
        });
    }

    public void updateUI(Entity hittable) {
        Hittable h = hittable.getObject("hittable");
        h.hit();
        getGameState().setValue("Balls", game.getBalls());
        getGameState().setValue("Score", game.getScore());

        PhysicsComponent ballPhysics = ball.getComponent(PhysicsComponent.class);
        ballPhysics.setLinearVelocity(1.5 * ballPhysics.getVelocityX(), 1.5 * ballPhysics.getVelocityY());
    }

    public void changeColor(Entity hittable, Color newColor) {
        Node node = hittable.getView().getNodes().get(0);
        if (hittable.getType().equals(BUMPER)) {
            Circle circ = (Circle) node;
            circ.setFill(newColor);
            hittable.setView(circ);
        }

        else {
            Rectangle rect = (Rectangle) node;
            rect.setFill(newColor);
            hittable.setView(rect);
        }
    }

    public void bonusHandler(Entity hittable) {
        int oldEB = game.getExtraBallBonus().timesTriggered();
        int oldJP = game.getJackPotBonus().timesTriggered();
        int oldDT = game.getDropTargetBonus().timesTriggered();
        updateUI(hittable);
        int newEB = game.getExtraBallBonus().timesTriggered();
        int newJP = game.getJackPotBonus().timesTriggered();
        int newDT = game.getDropTargetBonus().timesTriggered();

        if (newEB + newJP + newDT > oldEB + oldJP + oldDT) {
            play("bonus.wav");
        }

        if (newDT > oldDT) {
            List<Entity> bumpers = getGameWorld().getEntitiesByType(BUMPER);
            List<Entity> targets = getGameWorld().getEntitiesByType(TARGET);

            for (Entity bumper : bumpers) {
                changeColor(bumper, Color.YELLOW);
            }

            for (Entity target : targets) {
                changeColor(target, target.getObject("color"));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
