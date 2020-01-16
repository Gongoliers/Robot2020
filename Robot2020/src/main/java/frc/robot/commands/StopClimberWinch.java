package frc.robot.commands;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * TODO
 */
public class StopClimberWinch extends InstantCommand {

    public StopClimberWinch() {

        requires(Robot.climber);

    }

    // Called once when this command runs
    @Override
    protected void initialize() {
    }

}
