package frc.robot.commands.drivetrain;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * Allows the driver to control movement using a joystick / controller.
 */
public class DrivetrainOperatorContol extends Command {

    public DrivetrainOperatorContol() {

        requires(Robot.drivetrain);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double speed = -OI.driverJoystick.getY();
        double rotation = OI.driverJoystick.getZ();

        Robot.drivetrain.arcadeDrive(speed, rotation);
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
