package frc.robot.subsystems;

import com.thegongoliers.input.vision.LimelightCamera;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

    private LimelightCamera targetingCamera;

    public Vision() {

        targetingCamera = new LimelightCamera();

    }

    public LimelightCamera getTargetingCamera() {
        return targetingCamera;
    }

    @Override
    public void initDefaultCommand() {
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // TODO: Add getters for angle to target, target area, and is target found

}
