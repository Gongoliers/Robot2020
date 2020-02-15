package frc.robot.commands.powercell;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Stops the intake mechanism of the power cell manipulator subsystem.
 */
public class StopIntake extends InstantCommand {

    public StopIntake() {
        requires(Robot.powerCellManipulator);
    }

    // Called once when this command runs
    @Override
    protected void initialize() {
        Robot.powerCellManipulator.stopIntake();
    }

}
