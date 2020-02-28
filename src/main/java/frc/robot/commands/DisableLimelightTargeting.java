package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Turns off the Limelight's green targeting lights.
 */
public class DisableLimelightTargeting extends InstantCommand {

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.vision.setDriverMode(true);
    }

}
