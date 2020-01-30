package frc.robot.commands.drivetrain;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Stops the motors of the drivetrain.
 */
public class StopDrivetrain extends InstantCommand {

    public StopDrivetrain() {

        requires(Robot.drivetrain);

    }

    // Called once when this command runs
    @Override
    protected void initialize() {
        Robot.drivetrain.stop();
    }

}
