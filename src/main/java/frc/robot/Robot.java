package frc.robot;

import com.thegongoliers.input.power.Battery;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
    public static OI oi;
    public static Battery battery;

    public static Compressor compressor;
    public static Drivetrain drivetrain;
    public static PowerCellManipulator powerCellManipulator;
    public static ControlPanelManipulator controlPanelManipulator;
    public static Climber climber;
    public static Vision vision;

    private Command autonomousCommand;
    private SendableChooser<Command> autoChooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up.
     */
    @Override
    public void robotInit() {

        vision = new Vision();
        drivetrain = new Drivetrain(vision);
        powerCellManipulator = new PowerCellManipulator();
        // controlPanelManipulator = new ControlPanelManipulator();
        climber = new Climber();
        compressor = new Compressor();

        battery = new Battery(11.5, 13.1, 18);

        oi = new OI();

        // autoChooser.addOption("Low Goal", new AutoLowGoal());
        autoChooser.setDefaultOption("Shoot 3 No Delay", new AutoShoot3());
        autoChooser.addOption("Shoot 3 Delay 2s", new AutoShoot3(2));
        autoChooser.addOption("Shoot 3 Delay 3s", new AutoShoot3(3));
        autoChooser.addOption("Shoot 3 Delay 4s", new AutoShoot3(4));
        autoChooser.addOption("Shoot 3 Delay 5s", new AutoShoot3(5));
        autoChooser.addOption("Shoot 6 Trench", new AutoShoot6());
        autoChooser.addOption("No Auto", null);
        SmartDashboard.putData("Auto mode", autoChooser);
        
    }

    /**
     * This function is called every robot packet, no matter the mode.
     */
    @Override
    public void robotPeriodic() {
        SmartDashboard.putNumber("Match Time", Timer.getMatchTime());
        SmartDashboard.putNumber("Battery %", battery.getBatteryPercentage());
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters Autonomous mode.
     * The auto command that is run is chosen by the SmartDashboard sendableChooser.
     */
    @Override
    public void autonomousInit() {
        vision.setDriverMode(true);
        compressor.stop();
        drivetrain.resetStabilityModule();

        autonomousCommand = autoChooser.getSelected();

        // schedule the autonomous command
        if (autonomousCommand != null) {
            autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters Teleop mode.
     */
    @Override
    public void teleopInit() {
        vision.setDriverMode(true);
        compressor.stop();
        drivetrain.resetStabilityModule();
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    @Override
    public void testInit() {
        compressor.start();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
