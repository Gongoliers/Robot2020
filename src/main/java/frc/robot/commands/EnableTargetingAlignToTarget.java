package frc.robot.commands;

import com.thegongoliers.commands.AlignTargetCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class EnableTargetingAlignToTarget extends CommandGroup {

    public EnableTargetingAlignToTarget() {
        addSequential(new EnableTargetingMode());
        addSequential(new AlignTargetCommand(Robot.drivetrain, Robot.drivetrain.getModularDrivetrain(), 0, 0));
    }

}
