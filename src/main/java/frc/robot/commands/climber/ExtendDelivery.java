package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Extends the arm that deploys the climber hook to the shield generator switch.
 */
public class ExtendDelivery extends Command {

    public ExtendDelivery() {

        requires(Robot.climber);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.oi.setLeftRumble(true);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.climber.extendDelivery();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.climber.isDeliveryAtTop();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.climber.stopDelivery();
        Robot.oi.setLeftRumble(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
