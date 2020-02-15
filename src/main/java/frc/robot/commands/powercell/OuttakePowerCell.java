package frc.robot.commands.powercell;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Pushes a power cell out of the intake mechanism of the 
 * power cell manipulator.
 */
public class OuttakePowerCell extends Command {

    public OuttakePowerCell() {

        requires(Robot.powerCellManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.powerCellManipulator.outtake();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.powerCellManipulator.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
