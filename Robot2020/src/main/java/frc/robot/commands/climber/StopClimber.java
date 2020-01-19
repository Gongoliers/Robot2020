package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Stops all the motors in the climber subsystem.
 */
public class StopClimber extends CommandGroup {

    public StopClimber() {

        addSequential(new StopClimberDelivery());
        addSequential(new StopClimberWinch());

    }
}
