package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Turns the Limelight green targeting lights to full brightness.
 */
public class LimelightTargetingMode extends Command {

    public LimelightTargetingMode() {
        requires(Robot.vision);
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.vision.setDriverMode(false);
    }

    @Override
    protected void end() {
        Robot.vision.setDriverMode(true);
    }

    @Override
    protected boolean isFinished() {
        return false; // driver will stop manually
    }

}
