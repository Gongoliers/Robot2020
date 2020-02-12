package frc.robot;

import java.util.function.BooleanSupplier;

import com.thegongoliers.commands.AlignTargetCommand;
import com.thegongoliers.hardware.Hardware;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.DPadButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.StopAll;
import frc.robot.commands.controlpanel.*;
import frc.robot.commands.climber.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.powercell.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    /**
     * When this is set to true, the Xbox controller will be used for 
     * both driving and manipulation.  When false, the controls will be
     * split between a joystick and a controller.
     */
    private static final boolean SINGLE_DRIVER_MODE = false;
    private static final double RUMBLE_INTENSITY = 0.3;

    private static final int DRIVER_JOYSTICK_PORT = 0;
    private static final int MANIPULATOR_XBOX_PORT = 1;

    public Joystick driverJoystick;
    public XboxController xboxController;

    /**
     * The driver joystick controls the drivetrain with the Y and Z axis.
     * Button 1 on the joystick will enable turbo mode while held.
     * Button 11 on the joystick will stop all active commands when pressed.
     * 
     * The manipulator Xbox controller controlls all manipulator subsystems.
     * The menu/start buttons (small buttons in the center) will stop all.
     * The colored ABXY buttons control the power cell manipulator:
     * * A = Start Intake
     * * X = Start Shooting High
     * * B = Start Shooting Low
     * * Y = Start Reverse Intake / Outtake
     * The bumpers and triggers control the control panel manipulator:
     * * Right Bumper = Deploy Panel Spinner
     * * Left Bumper = Retract Panel Spinner
     * * Right Trigger = Spin/Rotate Panel to FMS Color
     * * Left Trigger = Spin/Rotate Panel Three Times
     * The DPAD buttons control the climber subsystem:
     * * Left = Raise Winch
     * * Right = Stop Winch
     * * Down = Retract/Lower Delivery
     * * Up =  Extend/Raise Delivery
     */
    public OI() {

        driverJoystick = new Joystick(DRIVER_JOYSTICK_PORT);
        xboxController = new XboxController(MANIPULATOR_XBOX_PORT);

        //// -- Driver Joystick setup

        Button driverTrigger = new JoystickButton(driverJoystick, 1);
        driverTrigger.whenReleased(new SetTurboDrivetrain(true));
        driverTrigger.whenPressed(new SetTurboDrivetrain(false));

        Button driverStopAll = new JoystickButton(driverJoystick, 11);
        driverStopAll.whenPressed(new StopAll());

        Button driverAlignTarget = new JoystickButton(driverJoystick, 12);
        driverAlignTarget.whenPressed(new AlignTargetCommand(Robot.drivetrain, Robot.drivetrain.getModularDrivetrain(), 0, 0));

        Button driveStickMoved = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Math.abs(getDriverSpeed()) > 0.3 || Math.abs(getDriverRotation()) > 0.3;
            }
        });
        driveStickMoved.whenPressed(new DrivetrainOperatorContol());

        //// -- Manipulator Xbox Controller setup

        Button manipulatorStopAll = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getBackButtonPressed() || xboxController.getStartButtonPressed();
            }
        });
        manipulatorStopAll.whenPressed(new StopAll());

        // Power Cell Manipulator

        Button manipulatorIntake = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getAButtonPressed();
            }
        });
        manipulatorIntake.whenPressed(new DeployIntakePowerCell());

        Button manipulatorShootHigh = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getXButtonPressed();
            }
        });
        manipulatorShootHigh.whenPressed(new ShootPowerCellHigh());

        Button manipulatorShootLow = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getBButtonPressed();
            }
        });
        manipulatorShootLow.whenPressed(new ShootPowerCellLow());

        Button manipulatorOuttake = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getYButtonPressed();
            }
        });
        manipulatorOuttake.whenPressed(new OuttakePowerCell());

        // Control Panel Manipulator

        Button manipulatorDeployPanelSpinner = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getBumperPressed(Hand.kRight);
            }
        });
        manipulatorDeployPanelSpinner.whenPressed(new DeployPanelSpinner());

        Button manipulatorRetractPanelSpinner = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getBumperPressed(Hand.kLeft);
            }
        });
        manipulatorRetractPanelSpinner.whenPressed(new RetractPanelSpinner());

        Button manipulatorRotateFast = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getTriggerAxis(Hand.kLeft) > 0.8 && Robot.controlPanelManipulator.isDeployed();
            }
        });
        manipulatorRotateFast.whenPressed(new RotatePanelSpinner());

        Button manipulatorRotateColor = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getTriggerAxis(Hand.kRight) > 0.8 && Robot.controlPanelManipulator.isDeployed();
            }
        });
        manipulatorRotateColor.whenPressed(new RotatePanelSpinnerToColor());

        // Climber

        DPadButton upButton = new DPadButton(xboxController, DPadButton.Direction.UP);
        upButton.whenPressed(new ExtendDelivery());

        DPadButton downButton = new DPadButton(xboxController, DPadButton.Direction.DOWN);
        downButton.whenPressed(new RetractDelivery());

        DPadButton leftButton = new DPadButton(xboxController, DPadButton.Direction.LEFT);
        leftButton.whenPressed(new RaiseWinch());

        DPadButton rightButton = new DPadButton(xboxController, DPadButton.Direction.RIGHT);
        rightButton.whenPressed(new StopClimberWinch());

    }

    public double getDriverSpeed() {
        return SINGLE_DRIVER_MODE ? xboxController.getY(Hand.kLeft) : driverJoystick.getY();
    }

    public double getDriverRotation() {
        return SINGLE_DRIVER_MODE ? xboxController.getX(Hand.kRight) : driverJoystick.getZ();
    }

    public void setLeftRumble(boolean rumble){
        xboxController.setRumble(RumbleType.kLeftRumble, rumble ? RUMBLE_INTENSITY : 0);
    }

    public void setRightRumble(boolean rumble) {
        xboxController.setRumble(RumbleType.kRightRumble, rumble ? RUMBLE_INTENSITY : 0);
    }

}
