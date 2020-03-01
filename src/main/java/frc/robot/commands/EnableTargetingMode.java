package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Enables Limelight targeting mode and waits for half a second.
 */
public class EnableTargetingMode extends TimedCommand {
    public EnableTargetingMode() {
        super(0.5);
        requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.vision.setDriverMode(false);
    }
}
