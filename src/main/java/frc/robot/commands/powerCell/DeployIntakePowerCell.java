package frc.robot.commands.powerCell;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Add your docs here.
 */
public class DeployIntakePowerCell extends CommandGroup {

    public DeployIntakePowerCell() {
        addSequential(new DeployHarvester());
        addSequential(new IntakePowerCell());
    }
}
