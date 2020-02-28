package frc.robot.commands.powercell;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Feeds the ball through the power cell feeder for 1/2 second.
 */
public class FeedPowerCell extends TimedCommand {
    public FeedPowerCell() {
        super(0.5);
        requires(Robot.powerCellManipulator);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.oi.quickRumble(true);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.powerCellManipulator.feedBallsToShooter();
    }

    // Called once after timeout
    @Override
    protected void end() {
        Robot.powerCellManipulator.stopFeeder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
