package frc.robot.commands.controlPanel;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * retracts piston that was previously deployed
 */
public class RetractPanelSpinner extends InstantCommand {

    public RetractPanelSpinner() {
        requires(Robot.controlPanelManipulator);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
      Robot.controlPanelManipulator.retract();
  }

}
