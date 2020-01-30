package frc.robot.commands.powerCell;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Stops all active motors and retracts pistons in the power cell manipulator.
 */
public class ResetPowerCellManipulator extends CommandGroup {

    public ResetPowerCellManipulator() {
        addSequential(new StopPowerCellManipulator());
        addSequential(new RetractHarvester());
    }
}
