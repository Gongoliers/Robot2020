package frc.robot;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.BooleanSupplier;

import com.thegongoliers.hardware.Hardware;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.DPadButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.commands.autonomous.*;
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
    private static final double RUMBLE_INTENSITY = 0.9;

    private static final int DRIVER_JOYSTICK_PORT = 0;
    private static final int MANIPULATOR_XBOX_PORT = 1;

    public Joystick driverJoystick;
    public XboxController xboxController;

    /**
     * The driver joystick controls the drivetrain with the Y and Z axis.
     * Button 1 on the joystick will enable turbo mode while held.
     * Button 09-10 on the joystick will stop all active commands when pressed.
     * Button 11-12 on the joystick will align the drivetrain to the camera target.
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

        Button driverTurbo = new JoystickButton(driverJoystick, 1); // trigger
        driverTurbo.whenReleased(new SetTurboDrivetrain(false));
        driverTurbo.whenPressed(new SetTurboDrivetrain(true));

        Button driverStopAll1 = new JoystickButton(driverJoystick, 11); // bottom row of buttons
        Button driverStopAll2 = new JoystickButton(driverJoystick, 12);
        driverStopAll1.whenPressed(new StopAll());
        driverStopAll2.whenPressed(new StopAll());

        Button driverAlignTarget1 = new JoystickButton(driverJoystick, 9); // second to bottom row of buttons
        Button driverAlignTarget2 = new JoystickButton(driverJoystick, 10);
        driverAlignTarget1.whileHeld(new EnableTargetingAlignToTarget());
        driverAlignTarget2.whileHeld(new EnableTargetingAlignToTarget());
        driverAlignTarget1.whenReleased(new DisableTargetingMode());
        driverAlignTarget2.whenReleased(new DisableTargetingMode());

        Button driveStickMoved = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Math.abs(getDriverSpeed()) > 0.3 || Math.abs(getDriverRotation()) > 0.3;
            }
        });
        driveStickMoved.whenPressed(new DrivetrainOperatorContol());

        Button driverRaiseWinch1 = new JoystickButton(driverJoystick, 7);
        Button driverRaiseWinch2 = new JoystickButton(driverJoystick, 8);
        driverRaiseWinch1.whileHeld(new RaiseWinch());
        driverRaiseWinch2.whileHeld(new RaiseWinch());

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
        manipulatorIntake.toggleWhenPressed(new IntakePowerCell());

        Button manipulatorShootHigh = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getXButton();
            }
        });
        manipulatorShootHigh.whileHeld(new ShootPowerCellHigh());

        Button manipulatorShootLow = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getBButton();
            }
        });
        manipulatorShootLow.toggleWhenPressed(new ShootPowerCellLow());

        Button manipulatorOuttake = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getYButton();
            }
        });
        manipulatorOuttake.whileHeld(new OuttakePowerCell());

        // Control Panel Manipulator

        /*
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
        manipulatorRotateFast.whileHeld(new RotatePanelSpinner());

        Button manipulatorRotateColor = Hardware.makeButton(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return xboxController.getTriggerAxis(Hand.kRight) > 0.8 && Robot.controlPanelManipulator.isDeployed();
            }
        });
        manipulatorRotateColor.whenPressed(new RotatePanelSpinnerToColor());
        */

        // Climber

        DPadButton upButton = new DPadButton(xboxController, DPadButton.Direction.UP);
        upButton.whileHeld(new ExtendDelivery());

        DPadButton downButton = new DPadButton(xboxController, DPadButton.Direction.DOWN);
        downButton.whileHeld(new RetractDelivery());

        DPadButton leftButton = new DPadButton(xboxController, DPadButton.Direction.LEFT);
        leftButton.whenPressed(new RaiseWinch());

        DPadButton rightButton = new DPadButton(xboxController, DPadButton.Direction.RIGHT);
        rightButton.whenPressed(new RaiseWinch());

        // SmartDashboard buttons
        
        SmartDashboard.putData(new EnableCompressor());
        SmartDashboard.putData(new DisableCompressor());
        SmartDashboard.putData(new FullSystemCheck());

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

    public void quickRumble(boolean left) {
        xboxController.setRumble(left ? RumbleType.kLeftRumble : RumbleType.kRightRumble, 1.0);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                xboxController.setRumble(left ? RumbleType.kLeftRumble : RumbleType.kRightRumble, 0);
            }
        }, 500);
    }

}
