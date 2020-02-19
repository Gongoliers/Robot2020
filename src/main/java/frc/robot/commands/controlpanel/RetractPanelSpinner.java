package frc.robot.commands.controlpanel;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * retracts piston that was previously deployed
 */
public class RetractPanelSpinner extends InstantCommand {

    public RetractPanelSpinner() {
        requires(Robot.controlPanelManipulator);
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.controlPanelManipulator.stopSpinner();
        Robot.controlPanelManipulator.retract();
        Robot.drivetrain.removeEnforcedMaxVoltage(Robot.controlPanelManipulator);
        Robot.oi.setRightRumble(false);
    }

}
