package frc.robot.commands;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * TODO
 */
public class StopIndexer extends InstantCommand {

    public StopIndexer() {

        requires(Robot.powerCellManipulator);

    }

    // Called once when this command runs
    @Override
    protected void initialize() {
    }

}
