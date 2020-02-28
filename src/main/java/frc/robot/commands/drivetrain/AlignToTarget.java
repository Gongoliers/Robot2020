package frc.robot.commands.drivetrain;

import com.thegongoliers.commands.AlignTargetCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.DisableLimelightTargeting;
import frc.robot.commands.EnableLimelightTargeting;

public class AlignToTarget extends CommandGroup {

    public AlignToTarget() {
        addSequential(new EnableLimelightTargeting());
        addSequential(new AlignTargetCommand(Robot.drivetrain, Robot.drivetrain.getModularDrivetrain(), 0, 0));
        addSequential(new DisableLimelightTargeting());
    }

}
