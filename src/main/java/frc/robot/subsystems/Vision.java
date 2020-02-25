package frc.robot.subsystems;

import com.thegongoliers.input.vision.LimelightCamera;
import com.thegongoliers.input.vision.TargetingCamera;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.InvertedCamera;

/**
 * The Vision subsystem is used for driver cameras and for automated target
 * alignment.
 */
public class Vision extends Subsystem {

    private TargetingCamera targetingCamera;

    public Vision() {
        targetingCamera = new InvertedCamera(new LimelightCamera());
    }

    public TargetingCamera getTargetingCamera() {
        return targetingCamera;
    }

    @Override
    public void initDefaultCommand() {
        // intentionally empty
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putNumber("Angle to Target", getAngleToTarget());
        SmartDashboard.putBoolean("Target Found?", isTargetFound());
    }

    public double getAngleToTarget() {
        return targetingCamera.getHorizontalOffset();
    }

    public double getTargetArea() {
        return targetingCamera.getTargetArea();
    }

    public boolean isTargetFound() {
        return targetingCamera.hasTarget();
    }

}
