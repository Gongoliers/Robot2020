package frc.robot.commands.controlpanel;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Approaches the control panel and then spins it three times.
 */
public class ApproachRotatePanel3Times extends CommandGroup {

    public ApproachRotatePanel3Times() {
        addSequential(new ApproachControlPanel(), 2);
        addSequential(new RotatePanelSpinner3Times());
    }
}
