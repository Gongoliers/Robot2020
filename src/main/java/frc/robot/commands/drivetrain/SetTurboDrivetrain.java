package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Sets the drivetrain to either turbo mode or precision mode.
 */
public class SetTurboDrivetrain extends InstantCommand {

    private boolean enabled;

    public SetTurboDrivetrain(boolean enabled) {
        requires(Robot.drivetrain);
        this.enabled = enabled;
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.drivetrain.setTurboEnabled(enabled);
    }

}
