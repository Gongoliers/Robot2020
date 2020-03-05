package frc.robot.commands.powercell;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Extends the pistons in order to deploy the power cell harvester.
 */
public class DeployHarvester extends InstantCommand {

    public DeployHarvester() {
        requires(Robot.powerCellManipulator);
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.powerCellManipulator.deploy();
        Robot.drivetrain.addEnforcedMaxVoltage(Robot.powerCellManipulator, 10.0);
        Robot.oi.setLeftRumble(true);
    }

}
