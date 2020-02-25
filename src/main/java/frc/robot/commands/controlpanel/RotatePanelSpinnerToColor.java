package frc.robot.commands.controlpanel;

import java.util.HashMap;

import com.thegongoliers.input.gameMessages.GameSpecificMessage2020;
import com.thegongoliers.input.gameMessages.GameSpecificMessage2020.ColorAssignment;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * Fetches the necessary ROTATE CONTROL, then spins the CONTROL PANEL
 * to the necessary color and stops.
 */
public class RotatePanelSpinnerToColor extends Command {

    private ColorAssignment targetColor;
    private double targetDistance;

    public RotatePanelSpinnerToColor() {
        requires(Robot.controlPanelManipulator);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        ColorAssignment fmsColor = new GameSpecificMessage2020().getColorAssignment();

        // adjust for the fact that our sensor is two spaces off from the field's sensor
        switch (fmsColor) {
        case Blue:
            targetColor = ColorAssignment.Red; // when the field sees blue, our robot sees red
            break;
        case Red:
            targetColor = ColorAssignment.Blue; // when the field sees red, our robot sees blue
            break;
        case Yellow: 
            targetColor = ColorAssignment.Green; // when the field sees yellow, our robot sees green
            break;
        case Green:
            targetColor = ColorAssignment.Yellow; // when the field sees green, our robot sees yellow
            break;
        default:
            targetColor = ColorAssignment.Unknown;
            break;
        }

        SmartDashboard.putString("Control Panel FMS Color", fmsColor.name());
        SmartDashboard.putString("Control Panel Target Color", targetColor.name());
        
        ColorAssignment currentColor = Robot.controlPanelManipulator.getColor();

        // blue yellow red green (left to right)
        HashMap<ColorAssignment, Integer> colorMap = new HashMap<>();
        colorMap.put(ColorAssignment.Unknown, Integer.MIN_VALUE);
        colorMap.put(ColorAssignment.Blue, 0);
        colorMap.put(ColorAssignment.Yellow, 1);
        colorMap.put(ColorAssignment.Red, 2);
        colorMap.put(ColorAssignment.Green, 3);

        int currentIndex = colorMap.get(currentColor);
        int targetIndex = colorMap.get(targetColor);

        int leftDistance;
        int rightDistance;

        if (currentIndex >= targetIndex) {
            leftDistance = currentIndex - targetIndex;
            rightDistance = 4 + targetIndex - currentIndex;
        } else {
            leftDistance = 4 + currentIndex - targetIndex;
            rightDistance = targetIndex - currentIndex;
        }

        if (rightDistance <= leftDistance) {
            targetDistance = rightDistance;
        } else {
            targetDistance = -leftDistance;
        }

        Robot.controlPanelManipulator.resetSpinnerDistance();
    }

    // private boolean atDistance = false;

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // if (Robot.controlPanelManipulator.isSpinnerAtDistance() || atDistance) {
        //     atDistance = true;
        //     Robot.controlPanelManipulator.slowRotate(targetDistance > 0);
        // } else {
        //     Robot.controlPanelManipulator.setSpinnerDistance(targetDistance);
        // }

        Robot.controlPanelManipulator.slowRotate(targetDistance > 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.controlPanelManipulator.getColor() == targetColor;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.controlPanelManipulator.stopSpinner();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
