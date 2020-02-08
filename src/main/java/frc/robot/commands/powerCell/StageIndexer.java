package frc.robot.commands.powerCell;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Pushes the power cells down slightly in order to give the flywheel room.
 */
public class StageIndexer extends TimedCommand {

    public StageIndexer() {
        super(0.5);
        requires(Robot.powerCellManipulator);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.powerCellManipulator.indexerDown();
    }

    // Called once after timeout
    @Override
    protected void end() {
        Robot.powerCellManipulator.stopIndexer();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
