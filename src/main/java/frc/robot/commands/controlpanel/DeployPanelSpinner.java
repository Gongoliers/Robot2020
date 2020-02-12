package frc.robot.commands.controlpanel;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Deploy pistons to spin control panel
 */
public class DeployPanelSpinner extends InstantCommand {
    
    public DeployPanelSpinner() {
        requires(Robot.controlPanelManipulator);
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.controlPanelManipulator.deploy();
        Robot.drivetrain.addEnforcedMaxVoltage(Robot.controlPanelManipulator, 4.0);
        Robot.oi.setRightRumble(true);
    }

}
