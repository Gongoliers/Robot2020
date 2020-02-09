package frc.robot.commands.controlpanel;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Rotates the spinner until driver stops it
 */
public class RotatePanelSpinner3Times extends Command {

    public RotatePanelSpinner3Times() {

        requires(Robot.controlPanelManipulator);

    }

    private double startingDistance;

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        startingDistance = Robot.controlPanelManipulator.getSpinnerDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.controlPanelManipulator.rotate();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.controlPanelManipulator.getSpinnerDistance() >= (startingDistance+3); // TODO tune
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
