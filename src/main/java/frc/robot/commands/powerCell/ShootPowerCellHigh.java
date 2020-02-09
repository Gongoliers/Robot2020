package frc.robot.commands.powercell;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootPowerCellHigh extends CommandGroup {
    /**
     * Stages the power cells in the indexer, then shoots for the high goal(s).
     */
    public ShootPowerCellHigh() {
        addSequential(new StageIndexer());
        addSequential(new EjectPowerCellHigh());
    }
}
