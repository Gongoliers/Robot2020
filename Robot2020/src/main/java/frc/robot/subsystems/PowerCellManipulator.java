package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.powerCell.*;

import com.thegongoliers.output.actuators.GSpeedController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;

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
    private static final double INDEXER_SPEED = 0.4;
    private static final double LOW_SHOOTER_SPEED = 0.1;
    private static final double HIGH_SHOOTER_SPEED = 0.6;

    private PWMVictorSPX intakeController;
    private GSpeedController indexerController;
    private Encoder indexerEncoder;
    private GSpeedController shooterController;
    private Encoder shooterEncoder;

    public PowerCellManipulator() {

        intakeController = new PWMVictorSPX(RobotMap.INTAKE_PWM);
        intakeController.setInverted(false);

        indexerEncoder = new Encoder(RobotMap.INDEXER_ENCODER_A, RobotMap.INDEXER_ENCODER_B);
        indexerEncoder.setDistancePerPulse(1.0);

        indexerController = new GSpeedController(new PWMVictorSPX(RobotMap.INDEXER_PWM), indexerEncoder, 0.1, 0.1); // TODO: Implement PID
        indexerController.setInverted(false);

        shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_A, RobotMap.SHOOTER_ENCODER_B);
        shooterEncoder.setDistancePerPulse(1.0);

        shooterController = new GSpeedController(new PWMVictorSPX(RobotMap.SHOOTER_PWM), shooterEncoder, 0.1, 0.1);  // TODO: Implement PID
        shooterController.setInverted(false);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new StopPowerCellManipulator());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    public void stopIndexer() {
        indexerController.stopMotor();
    }

    public void stopIntake() {
        intakeController.stopMotor();
    }

    public void stopShooter() {
        shooterController.stopMotor();
    }

    public void intake() {
        intakeController.set(INTAKE_SPEED);
    }

    public void outtake() {
        intakeController.set(-INTAKE_SPEED);
    }

    public void shootHigh() {
        shooterController.set(HIGH_SHOOTER_SPEED);
    }

    public void shootLow() {
        shooterController.set(LOW_SHOOTER_SPEED);
    }

}
