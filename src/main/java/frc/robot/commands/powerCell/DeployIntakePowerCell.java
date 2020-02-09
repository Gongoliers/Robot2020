package frc.robot.commands.powercell;

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
