package frc.robot.commands.powerCell;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * TODO
 */
public class StopIntake extends InstantCommand {

    public StopIntake() {

        requires(Robot.powerCellManipulator);

    }

    // Called once when this command runs
    @Override
    protected void initialize() {
    }

}
