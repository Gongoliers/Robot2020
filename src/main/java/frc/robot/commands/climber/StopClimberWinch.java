package frc.robot.commands.climber;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Stops the winch motor in the climber subsystem.
 */
public class StopClimberWinch extends InstantCommand {

    public StopClimberWinch() {

        requires(Robot.climber);

    }

    // Called once when this command runs
    @Override
    protected void initialize() {
        Robot.climber.stopWinch();
    }

}
