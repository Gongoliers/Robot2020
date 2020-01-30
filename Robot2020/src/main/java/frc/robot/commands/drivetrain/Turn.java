package frc.robot.commands.drivetrain;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Turns the robot at a particular speed until stopped/interrupted.
 * Turns right if speed is positive or left if speed is negative.
 */
public class Turn extends Command {

    private double speed;

    public Turn(double speed) {

        requires(Robot.drivetrain);
        this.speed = speed;

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.drivetrain.arcadeDrive(0, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
