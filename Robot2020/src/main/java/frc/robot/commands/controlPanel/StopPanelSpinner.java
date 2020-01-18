package frc.robot.commands.controlPanel;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * TODO
 */
public class StopPanelSpinner extends InstantCommand {

    public StopPanelSpinner() {

        requires(Robot.controlPanelManipulator);

    }

    // Called once when this command runs
    @Override
    protected void initialize() {
    }

}
