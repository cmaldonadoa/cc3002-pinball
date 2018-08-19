package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import static gui.Config.*;

/**
 * This class is used to control de movement of the flippers.
 *
 * @author Cristobal Maldonado
 */
public class PlayerControl extends Component {
    private double vel;
    private double rotation;
    private PhysicsComponent physics;

    @Override
    public void onUpdate(double tpf) {
        if (vel < 0) {
            rotation = getEntity().getRotation();
            if (rotation > -FLIPPER_INITIAL_ANGLE) {
                physics.setAngularVelocity(vel);
            }
            else {
                physics.setAngularVelocity(0);
            }
        }
        if (vel > 0) {
            rotation = getEntity().getRotation();
            if (rotation < FLIPPER_INITIAL_ANGLE) {
                physics.setAngularVelocity(vel);
            }
            else {
                physics.setAngularVelocity(0);
            }
        }
    }

    public void leftUp() {
        vel = -FLIPPER_INITIAL_ANGLE * 0.65;
    }

    public void leftDown() {
        vel = FLIPPER_INITIAL_ANGLE * 0.65;
    }

    public void rightUp() {
        vel = FLIPPER_INITIAL_ANGLE * 0.65;
    }

    public void rightDown() {
        vel = -FLIPPER_INITIAL_ANGLE * 0.65;
    }
}
