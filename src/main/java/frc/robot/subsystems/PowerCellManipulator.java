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

    private static final double INTAKE_SPEED = 0.9;
    private static final double FEEDER_SPEED = 0.7;
    private static final double LOW_SHOOTER_SPEED = 0.35;
    private static final double HIGH_SHOOTER_SPEED = 0.95;
    // private static final double THRESHOLD_SHOOTER_RATE = 0;
    private static final double MAX_VOLTAGE = 12.5;

    private PID distancePID = new PID(0.1, 0.0, 0.0); // TODO: Tune PID values (low priority for first comp)
    private PID velocityPID = new PID(0.1, 0.0, 0.0);

    private PWMVictorSPX intakeController;
    private PWMVictorSPX feederController;
    private GSpeedController leftShooterController;
    private GSpeedController rightShooterController;
    private Encoder shooterEncoder;
    private Piston harvesterPiston;
    private Piston hoodPiston;

    public PowerCellManipulator() {

        intakeController = new PWMVictorSPX(RobotMap.INTAKE_PWM);
        intakeController.setInverted(false);

        feederController = new PWMVictorSPX(RobotMap.FEEDER_PWM);
        feederController.setInverted(false);

        shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_A, RobotMap.SHOOTER_ENCODER_B);
        shooterEncoder.setDistancePerPulse(1.0);

        leftShooterController = new GSpeedController(new PWMVictorSPX(RobotMap.SHOOTER_LEFT_PWM), shooterEncoder, distancePID, velocityPID);
        leftShooterController.setInverted(false);

        rightShooterController = new GSpeedController(new PWMVictorSPX(RobotMap.SHOOTER_RIGHT_PWM), shooterEncoder, distancePID, velocityPID);
        rightShooterController.setInverted(true);

        harvesterPiston = new GPiston(new Solenoid(RobotMap.HARVESTER_PISTON));
        harvesterPiston.setInverted(false);

        hoodPiston = new GPiston(new Solenoid(RobotMap.HOOD_PISTON));
        hoodPiston.setInverted(false);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ResetPowerCellManipulator());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putBoolean("Harvester Deployed?", harvesterPiston.isExtended());
        SmartDashboard.putBoolean("High Goal Mode?", hoodPiston.isRetracted());
        SmartDashboard.putBoolean("Low Goal Mode?", hoodPiston.isExtended());
        SmartDashboard.putNumber("Shooter Encoder", shooterEncoder.getRate());
    }

    public void stopIntake() {
        intakeController.stopMotor();
    }

    public void stopFeeder() {
        feederController.stopMotor();
        intakeController.stopMotor();
    }

    public void stopShooter() {
        leftShooterController.stopMotor();
        rightShooterController.stopMotor();
    }

    public void intake() {
        intakeController.set(INTAKE_SPEED);
    }

    public void feedBallsToShooter() {
        feederController.set(FEEDER_SPEED);
        intakeController.set(FEEDER_SPEED);
    }

    public void outtake() {
        intakeController.set(-INTAKE_SPEED);
    }

    public void shootHigh() {
        leftShooterController.setVoltage(HIGH_SHOOTER_SPEED * MAX_VOLTAGE);
        rightShooterController.setVoltage(HIGH_SHOOTER_SPEED * MAX_VOLTAGE);
    }

    public void shootLow() {
        leftShooterController.setVoltage(LOW_SHOOTER_SPEED * MAX_VOLTAGE);
        rightShooterController.setVoltage(LOW_SHOOTER_SPEED * MAX_VOLTAGE);
    }

    public void deploy() {
        harvesterPiston.extend();
    }

    public void retract() {
        harvesterPiston.retract();
    }

    public boolean isFlywheelReady() {
        return true;
        // return shooterEncoder.getRate() >= THRESHOLD_SHOOTER_RATE;
    }

    public void lowerHood() {
        hoodPiston.extend();
    }

    public void raiseHood() {
        hoodPiston.retract();
    }

}
