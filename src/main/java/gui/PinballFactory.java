package gui;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.ColorComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;

import static gui.Config.*;
import static gui.PinballType.*;

/**
 * This class is used to create any element of the game.
 *
 * @author Cristobal Maldonado
 */
public class PinballFactory implements EntityFactory {
    @Spawns("Left Flipper")
    public Entity newLeftFlipper(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return Entities.builder()
                .type(FLIPPER)
                .at(GAME_WIDTH /2 - 25 - FLIPPER_WIDTH, HEIGHT - 75 - FLIPPER_HEIGHT)
                .viewFromNodeWithBBox(new Rectangle(FLIPPER_WIDTH, FLIPPER_HEIGHT, Color.LIGHTCORAL))
                .rotate(FLIPPER_INITIAL_ANGLE)
                .with(physics, new CollidableComponent(true))
                .with(new PlayerControl())
                .build();
    }

    @Spawns("Right Flipper")
    public Entity newRightFlipper(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return Entities.builder()
                .type(FLIPPER)
                .at(GAME_WIDTH /2 + 25, HEIGHT - 75 - FLIPPER_HEIGHT)
                .viewFromNodeWithBBox(new Rectangle(FLIPPER_WIDTH, FLIPPER_HEIGHT, Color.LIGHTCORAL))
                .rotate(-FLIPPER_INITIAL_ANGLE)
                .with(physics, new CollidableComponent(true))
                .with(new PlayerControl())
                .build();
    }

    @Spawns("GameBackground")
    public Entity newBackground1(SpawnData data) {
        return Entities.builder()
                .viewFromNode(new Rectangle(GAME_WIDTH, HEIGHT, Color.BLACK))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    @Spawns("ScoreBackground")
    public Entity newBackground2(SpawnData data) {
        return Entities.builder()
                .at(GAME_WIDTH, 0)
                .viewFromNode(new Rectangle(WIDTH - GAME_WIDTH, HEIGHT, Color.LIGHTSEAGREEN))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    @Spawns("Ball")
    public Entity newBall(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(0.7f).density(0.7f));

        return Entities.builder()
                .type(BALL)
                .at(GAME_WIDTH /2 - 2 * BALL_RADIUS + 100,  70)
                .bbox(new HitBox("Ball", BoundingShape.circle(BALL_RADIUS)))
                .viewFromNode(new Circle(BALL_RADIUS, Color.WHITE))
                .with(physics, new CollidableComponent(true))
                .build();
    }

    @Spawns("Drop Target")
    public Entity newDropTarget(SpawnData data) {
        double angle = new Random().nextDouble() * 360;

        return Entities.builder()
                .type(TARGET)
                .from(data)
                .viewFromNodeWithBBox(new Rectangle(50, 10, Color.BLUEVIOLET))
                .rotate(angle)
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .with("color", Color.BLUEVIOLET)
                .build();
    }

    @Spawns("Spot Target")
    public Entity newSpotTarget(SpawnData data) {
        double angle = new Random().nextDouble() * 360;

        return Entities.builder()
                .type(TARGET)
                .from(data)
                .viewFromNodeWithBBox(new Rectangle(50, 10, Color.RED))
                .rotate(angle)
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .with("color", Color.RED)
                .build();
    }

    @Spawns("Kicker Bumper")
    public Entity newKickerBumper(SpawnData data) {
        return Entities.builder()
                .type(BUMPER)
                .from(data)
                .bbox(new HitBox("Kicker", BoundingShape.circle(26)))
                .viewFromNode(new Circle(26, Color.SADDLEBROWN))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .with("color", Color.SADDLEBROWN)
                .build();
    }

    @Spawns("Pop Bumper")
    public Entity newPopBumper(SpawnData data) {
        return Entities.builder()
                .type(BUMPER)
                .from(data)
                .bbox(new HitBox("Pop", BoundingShape.circle(26)))
                .viewFromNodeWithBBox(new Circle(26, Color.CYAN))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .with("color", Color.CYAN)
                .build();
    }

    @Spawns("ScreenBounds")
    public Entity newScreenBounds(SpawnData data) {
        Entity bounds = Entities.makeScreenBounds(100);
        bounds.setType(WALL);
        bounds.addComponent(new CollidableComponent(true));
        return bounds;
    }

    @Spawns("Wall")
    public Entity newWall(SpawnData data) {
        return Entities.builder()
                .type(WALL)
                .at(GAME_WIDTH, 0)
                .viewFromNodeWithBBox(new Rectangle(10, HEIGHT, Color.GOLD))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .build();
    }
}
