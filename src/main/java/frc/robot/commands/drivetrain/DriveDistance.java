package frc.robot.commands.drivetrain;

import com.thegongoliers.commands.FollowPathCommand;
import com.thegongoliers.paths.SimplePath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

/**
 * Drives forwards for a particular distance, then stops.
 */
public class DriveDistance extends CommandGroup {

    public DriveDistance(double distance) {

        var path = new SimplePath();
        path.addStraightAway(distance);
        addSequential(new FollowPathCommand(Robot.drivetrain, Robot.drivetrain.getModularDrivetrain(), path));

    } 
}
