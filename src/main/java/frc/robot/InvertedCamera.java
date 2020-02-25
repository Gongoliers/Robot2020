package frc.robot;

import com.thegongoliers.input.vision.TargetingCamera;

/**
 * Inverts a targeting camera for when a camera needs to be mounted on the rear
 * of the robot.
 */
public class InvertedCamera implements TargetingCamera {

    private TargetingCamera baseCamera;

    public InvertedCamera(TargetingCamera camera) {
        baseCamera = camera;
    }

    @Override
    public boolean hasTarget() {
        return baseCamera.hasTarget();
    }

    @Override
    public double getHorizontalOffset() {
        return -baseCamera.getHorizontalOffset();
    }

    @Override
    public double getVerticalOffset() {
        return baseCamera.getVerticalOffset();
    }

    @Override
    public double getTargetArea() {
        return baseCamera.getTargetArea();
    }

}
