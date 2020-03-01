package frc.robot.commands.climber;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Retracts the arm that deploys the climber hook to
 * the shield generator switch.
 */
public class RetractDelivery extends TimedCommand {

    public RetractDelivery() {
        super(0.5);
        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.oi.setRightRumble(true);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.climber.retractDelivery();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.climber.stopDelivery();
        Robot.oi.setRightRumble(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
