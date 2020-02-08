package frc.robot.commands.powerCell;

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
        Robot.drivetrain.enforceMaxVoltage(6.0);
    }

}
