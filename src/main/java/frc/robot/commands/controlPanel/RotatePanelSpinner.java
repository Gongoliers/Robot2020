package frc.robot.commands.controlpanel;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Rotates the spinner until driver stops it
 */
public class RotatePanelSpinner extends Command {

    public RotatePanelSpinner() {

        requires(Robot.controlPanelManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.controlPanelManipulator.rotate();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.controlPanelManipulator.stopSpinner();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
