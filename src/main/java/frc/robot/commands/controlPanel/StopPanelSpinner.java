package frc.robot.commands.controlpanel;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Stops the panel spinner motor from running.
 */
public class StopPanelSpinner extends InstantCommand {

    public StopPanelSpinner() {
        requires(Robot.controlPanelManipulator);
    }

    // Called once when this command runs
    @Override
    protected void initialize() {
        Robot.controlPanelManipulator.stopSpinner();
    }

}
