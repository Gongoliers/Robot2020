package frc.robot.commands.powerCell;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Stops the indexer mechanism of the power cell manipulator.
 */
public class StopIndexer extends InstantCommand {

    public StopIndexer() {

        requires(Robot.powerCellManipulator);

    }

    // Called once when this command runs
    @Override
    protected void initialize() {
        Robot.powerCellManipulator.stopIndexer();
    }

}
