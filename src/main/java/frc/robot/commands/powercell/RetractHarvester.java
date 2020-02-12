package frc.robot.commands.powercell;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Retracts the pistons in order to un-deploy the power cell harvester.
 */
public class RetractHarvester extends InstantCommand {

    public RetractHarvester() {
        requires(Robot.powerCellManipulator);
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.powerCellManipulator.retract();
        Robot.drivetrain.removeEnforcedMaxVoltage(Robot.powerCellManipulator);
        Robot.oi.setLeftRumble(false);
    }

}
