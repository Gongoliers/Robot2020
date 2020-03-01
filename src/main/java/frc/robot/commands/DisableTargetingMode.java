package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Turns the Limelight green targeting lights off and returns to driver mode.
 */
public class DisableTargetingMode extends InstantCommand {

    public DisableTargetingMode() {
        requires(Robot.vision);
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.vision.setDriverMode(true);
    }

}
