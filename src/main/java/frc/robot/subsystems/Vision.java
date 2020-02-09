package frc.robot.subsystems;

import com.thegongoliers.input.vision.LimelightCamera;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
