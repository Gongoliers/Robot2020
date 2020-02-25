package frc.robot.commands.powercell;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Stops the shooter mechanism of the power cell manipulator subsystem.
 */
public class StopShooter extends InstantCommand {

    public StopShooter() {

        requires(Robot.powerCellManipulator);

    }

    // Called once when this command runs
    @Override
    protected void initialize() {
        Robot.powerCellManipulator.stopShooter();
        Robot.powerCellManipulator.stopFeeder();
    }

}
