package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Disables the onboard compressor.
 */
public class DisableCompressor extends InstantCommand {
    public DisableCompressor() {
        super();
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.compressor.stop();
    }

}
