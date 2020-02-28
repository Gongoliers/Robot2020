package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Turns the Limelight green targeting lights to full brightness.
 */
public class EnableLimelightTargeting extends InstantCommand {

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.vision.setDriverMode(true);
    }

}
