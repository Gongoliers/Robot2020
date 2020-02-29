package frc.robot.commands.controlpanel;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ControlPanelManipulator;

public class ApproachControlPanel extends Command {
    private static final double DRIVING_FORWARD_SPEED = .2; // TODO: Tune this value

    public ApproachControlPanel() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.drivetrain.arcadeDrive(DRIVING_FORWARD_SPEED, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.controlPanelManipulator.getProximity() >= ControlPanelManipulator.DISTANCE_FROM_SENSOR_TO_PANEL;
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
