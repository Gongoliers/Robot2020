package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drivetrain.DriveDistance;
import frc.robot.commands.powerCell.EjectPowerCellHigh;
import frc.robot.commands.powerCell.IntakePowerCell;

/**
 * Condition: Starts the match lined up with the POWER PORT.
 * Condition: 3 POWER CELLS are preloaded and not touching the flywheel.
 * 
 * Shoots 3 preloaded POWER CELLS at the high goal.
 * Backs up to collect 3 more POWER CELLS from the TRENCH RUN.
 * Moves forwards again to shoot 3 new POWER CELLS at the high goal.
 */
public class AutoShootCollectShoot extends CommandGroup {

    public AutoShootCollectShoot() {
        addSequential(new EjectPowerCellHigh(), 3.5);
        addParallel(new DriveDistance(17), 4);
        addSequential(new IntakePowerCell(), 4);
        addSequential(new DriveDistance(-17), 4);
        addSequential(new EjectPowerCellHigh(), 3.5);
    }
}
