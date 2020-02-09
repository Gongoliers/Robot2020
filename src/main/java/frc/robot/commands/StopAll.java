package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climber.StopClimber;
import frc.robot.commands.controlpanel.StopPanelSpinner;
import frc.robot.commands.drivetrain.StopDrivetrain;
import frc.robot.commands.powercell.StopPowerCellManipulator;

public class StopAll extends CommandGroup {
    /**
     * Stops all motors and subsystems.
     */
    public StopAll() {

        addParallel(new StopDrivetrain());
        addParallel(new StopClimber());
        addParallel(new StopPowerCellManipulator());
        addParallel(new StopPanelSpinner());

    }
}
