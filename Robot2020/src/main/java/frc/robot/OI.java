package frc.robot;

import java.util.function.BooleanSupplier;

import com.thegongoliers.hardware.Hardware;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.drivetrain.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private static final int DRIVER_JOYSTICK_PORT = 0;
    private static final int MANIPULATOR_XBOX_PORT = 1;

    private Joystick driverJoystick;
    private XboxController xboxController;

    public OI() {

        driverJoystick = new Joystick(DRIVER_JOYSTICK_PORT);
        xboxController = new XboxController(MANIPULATOR_XBOX_PORT);

        //// Driver Joystick setup

        Button driverTrigger = new JoystickButton(driverJoystick, 1);
        driverTrigger.whenPressed(new SetTurboDrivetrain(true));
        driverTrigger.whenReleased(new SetTurboDrivetrain(false));

        Button driveStickMoved = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Math.abs(driverJoystick.getY()) > 0.3 || Math.abs(driverJoystick.getZ()) > 0.1;
            }
        });
        driveStickMoved.whenPressed(new DrivetrainOperatorContol());

        //// TODO: Manipulator Xbox Controller setup

    }

    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    //// joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}
