package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.StopAll;
import frc.robot.commands.climber.ExtendDelivery;
import frc.robot.commands.climber.RaiseWinch;
import frc.robot.commands.climber.RetractDelivery;
import frc.robot.commands.controlpanel.DeployPanelSpinner;
import frc.robot.commands.controlpanel.RetractPanelSpinner;
import frc.robot.commands.controlpanel.RotatePanelSpinner3Times;
import frc.robot.commands.drivetrain.DriveDistance;
import frc.robot.commands.powercell.DeployIntakePowerCell;
import frc.robot.commands.powercell.ResetPowerCellManipulator;
import frc.robot.commands.powercell.ShootPowerCellHigh;

/**
 * Runs a test on every component of every subsystem. To be run before matches
 * to check the status of the robot.
 */
public class FullSystemCheck extends CommandGroup {

  public FullSystemCheck() {
      addSequential(new DriveDistance(12));
      addSequential(new DriveDistance(-12));
      
      addSequential(new StopAll());

      addSequential(new DeployPanelSpinner());
      addSequential(new RotatePanelSpinner3Times());
      addSequential(new RetractPanelSpinner());

      addSequential(new StopAll());

      addSequential(new DeployIntakePowerCell(), 3);
      addSequential(new ResetPowerCellManipulator());
      addSequential(new ShootPowerCellHigh(), 3);

      addSequential(new StopAll());

      addSequential(new ExtendDelivery());
      addSequential(new RetractDelivery());
      addSequential(new RaiseWinch(), 0.25);

      addSequential(new StopAll());
  }

}
