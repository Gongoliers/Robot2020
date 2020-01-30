package frc.robot.commands.climber;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Stops the delivery motor in the climber subsystem.
 */
public class StopClimberDelivery extends InstantCommand {

    public StopClimberDelivery() {

        requires(Robot.climber);

    }

    // Called once when this command runs
    @Override
    protected void initialize() {
        Robot.climber.stopDelivery();
    }

}
