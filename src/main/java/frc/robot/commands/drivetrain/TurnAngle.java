package frc.robot.commands.drivetrain;

import com.thegongoliers.commands.FollowPathCommand;
import com.thegongoliers.paths.SimplePath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

/**
 * Turns the robot to a particular angle and then stops.
 */
public class TurnAngle extends CommandGroup {

    public TurnAngle(double angle) {

        var path = new SimplePath();
        path.addRotation(angle);
        addSequential(new FollowPathCommand(Robot.drivetrain, Robot.drivetrain.getModularDrivetrain(), path));

    } 
}
