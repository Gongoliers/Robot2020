package frc.robot.commands;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * TODO
 */
public class StopDrivetrain extends InstantCommand {

    public StopDrivetrain() {

        requires(Robot.drivetrain);

    }

    // Called once when this command runs
    @Override
    protected void initialize() {
    }

}
