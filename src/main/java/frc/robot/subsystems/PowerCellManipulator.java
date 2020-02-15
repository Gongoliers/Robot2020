package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.powercell.*;

import com.kylecorry.pid.PID;
import com.thegongoliers.output.actuators.GPiston;
import com.thegongoliers.output.actuators.GSpeedController;
import com.thegongoliers.output.interfaces.Piston;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The power cell manipulator is composed of (1) one intake motor
 * that is responsible for sucking power cells into the robot,
 * (2) one indexer motor that is responsible for moving the 
 * power cells through the inside of the robot, and (3)
 * two horiziontally mounted motors for shooting the 
 * power cells into the goal.
 * 
 * Our manipulator system can hold and handle up to
 * five power cells at a time.
 */
public class PowerCellManipulator extends Subsystem {

    private static final double INTAKE_SPEED = 0.5; // TODO: test and tune these values
    private static final double LOW_SHOOTER_SPEED = 0.1;
    private static final double HIGH_SHOOTER_SPEED = 0.6;
    private static final double THRESHOLD_SHOOTER_RATE = 0;

    private PID distancePID = new PID(0.1, 0.0, 0.0); // TODO: Tune PID values
    private PID velocityPID = new PID(0.1, 0.0, 0.0);

    private PWMVictorSPX intakeLeftController;
    private PWMVictorSPX intakeRightController;
    private GSpeedController leftShooterController;
    private GSpeedController rightShooterController;
    private Encoder shooterEncoder;
    private Piston harvesterPiston;

    public PowerCellManipulator() {

        intakeLeftController = new PWMVictorSPX(RobotMap.INTAKE_LEFT_PWM);
        intakeLeftController.setInverted(false);

        intakeRightController = new PWMVictorSPX(RobotMap.INTAKE_RIGHT_PWM);
        intakeRightController.setInverted(false);

        shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_A, RobotMap.SHOOTER_ENCODER_B);
        shooterEncoder.setDistancePerPulse(1.0);

        leftShooterController = new GSpeedController(new PWMVictorSPX(RobotMap.SHOOTER_LEFT_PWM), shooterEncoder, distancePID, velocityPID);
        leftShooterController.setInverted(true);

        rightShooterController = new GSpeedController(new PWMVictorSPX(RobotMap.SHOOTER_RIGHT_PWM), shooterEncoder, distancePID, velocityPID);
        rightShooterController.setInverted(false);

        harvesterPiston = new GPiston(new Solenoid(RobotMap.HARVESTER_PISTON));
        harvesterPiston.setInverted(false);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ResetPowerCellManipulator());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putBoolean("Harvester Deployed?", harvesterPiston.isExtended());
        SmartDashboard.putNumber("Intake Motor", (intakeLeftController.get() + intakeRightController.get()) / 2);
        SmartDashboard.putNumber("Shooter Motor", (leftShooterController.get() + rightShooterController.get()) / 2);
        SmartDashboard.putNumber("Shooter Encoder", shooterEncoder.getRate());
    }

    public void stopIntake() {
        intakeLeftController.stopMotor();
        intakeRightController.stopMotor();
    }

    public void stopShooter() {
        leftShooterController.stopMotor();
        rightShooterController.stopMotor();
    }

    public void intake() {
        intakeLeftController.set(INTAKE_SPEED);
        intakeRightController.set(INTAKE_SPEED);
    }

    public void outtake() {
        intakeLeftController.set(-INTAKE_SPEED);
        intakeLeftController.set(-INTAKE_SPEED);
    }

    public void shootHigh() {
        leftShooterController.set(HIGH_SHOOTER_SPEED);
        rightShooterController.set(HIGH_SHOOTER_SPEED);
    }

    public void shootLow() {
        leftShooterController.set(LOW_SHOOTER_SPEED);
        rightShooterController.set(LOW_SHOOTER_SPEED);
    }

    public void deploy() {
        harvesterPiston.extend();
    }

    public void retract() {
        harvesterPiston.retract();
    }

    public boolean isFlywheelReady() {
        return shooterEncoder.getRate() >= THRESHOLD_SHOOTER_RATE;
    }

}
