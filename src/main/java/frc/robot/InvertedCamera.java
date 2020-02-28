package frc.robot;

import com.thegongoliers.input.vision.LimelightCamera;

/**
 * Inverts a Limelight camera for when a camera needs to be mounted on the rear
 * of the robot.
 */
public class InvertedCamera extends LimelightCamera {

    @Override
    public double getHorizontalOffset() {
        return -super.getHorizontalOffset();
    }

}
