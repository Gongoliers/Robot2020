package frc.robot.commands.powerCell;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

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
        Robot.drivetrain.enforceMaxVoltage(Drivetrain.NORMAL_VOLTAGE);
    }

}
