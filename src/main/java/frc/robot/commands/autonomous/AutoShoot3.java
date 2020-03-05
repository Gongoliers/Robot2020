package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.powercell.ShootPowerCellHigh;

/**
 * Condition: Starts the match lined up with the POWER PORT.
 * Condition: 3 POWER CELLS are preloaded and not touching the flywheel.
 * 
 * Shoots 3 preloaded balls at the high goal.
 * Backs up off the INITIATION LINE.
 */
public class AutoShoot3 extends CommandGroup {

    public AutoShoot3(double delay) {
        addSequential(new WaitCommand(delay));
        addSequential(new ShootPowerCellHigh(), 7);
        addSequential(new Drive(-0.75), 2.5);
    }

    public AutoShoot3() {
        this(0);
    }
}
