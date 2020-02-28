package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Enables the onboard compressor.
 */
public class EnableCompressor extends InstantCommand {
    public EnableCompressor() {
        super();
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.compressor.start();
    }

}
