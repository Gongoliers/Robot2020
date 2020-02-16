package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drivetrain.DriveDistance;
import frc.robot.commands.powercell.ShootPowerCellLow;

/**
 * Drives straight forwards to the low goal of the POWER PORT
 * and delivers the three preloaded POWER CELLs.
 */
public class AutoLowGoal extends CommandGroup {

    public AutoLowGoal() {
        addSequential(new DriveDistance(12), 7);
        addSequential(new ShootPowerCellLow(), 7);
    }
}
