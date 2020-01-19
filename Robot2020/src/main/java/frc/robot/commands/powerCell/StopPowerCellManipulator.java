package frc.robot.commands.powerCell;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Stop all parts and motors of the power cell manipulator.
 */
public class StopPowerCellManipulator extends CommandGroup {


    public StopPowerCellManipulator() {

        addSequential(new StopIndexer());
        addSequential(new StopIntake());
        addSequential(new StopShooter());
 
    } 
}
