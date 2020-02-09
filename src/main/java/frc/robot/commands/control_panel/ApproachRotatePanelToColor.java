package frc.robot.commands.controlpanel;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Approaches the control panel and then spins it to the specific FMS color.
 */
public class ApproachRotatePanelToColor extends CommandGroup {

    public ApproachRotatePanelToColor() {
        addSequential(new ApproachControlPanel(), 2);
        addSequential(new RotatePanelSpinnerToColor());
    }
}
