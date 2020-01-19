package frc.robot.commands.climber;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts the arm that deploys the climber hook to
 * the shield generator switch.
 */
public class RetractDelivery extends Command {

    public RetractDelivery() {

        requires(Robot.climber);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.climber.retractDelivery();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.climber.isDeliveryAtBottom();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.climber.stopDelivery();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
