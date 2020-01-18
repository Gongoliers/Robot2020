package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.powerCell.*;

import com.thegongoliers.output.actuators.GSpeedController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/**
 *
 */
public class PowerCellManipulator extends Subsystem {

    private PWMVictorSPX intakeController;
    private GSpeedController indexerController;
    private Encoder indexerEncoder;
    private GSpeedController leftShooterController;
    private GSpeedController rightShooterController;
    private Encoder rightShooterEncoder;
    private Encoder leftShooterEncoder;
    private DigitalInput cellEnterLimitSwitch;
    private DigitalInput cellExitLimitSwitch;

    public PowerCellManipulator() {

        intakeController = new PWMVictorSPX(RobotMap.INTAKE_PWM);
        intakeController.setInverted(false);

        indexerEncoder = new Encoder(RobotMap.INDEXER_ENCODER_A, RobotMap.INDEXER_ENCODER_B);
        indexerEncoder.setDistancePerPulse(1.0);

        indexerController = new GSpeedController(new PWMVictorSPX(RobotMap.INDEXER_PWM), indexerEncoder, 0.1, 0.1); // TODO: tune
        indexerController.setInverted(false);

        rightShooterEncoder = new Encoder(RobotMap.RIGHT_SHOOTER_ENCODER_A, RobotMap.RIGHT_SHOOTER_ENCODER_B);
        rightShooterEncoder.setDistancePerPulse(1.0);

        leftShooterEncoder = new Encoder(RobotMap.LEFT_SHOOTER_ENCODER_A, RobotMap.LEFT_SHOOTER_ENCODER_B);
        leftShooterEncoder.setDistancePerPulse(1.0);

        leftShooterController = new GSpeedController(new PWMVictorSPX(RobotMap.LEFT_SHOOTER_PWM), leftShooterEncoder, 0.1, 0.1);  // TODO: tune
        leftShooterController.setInverted(false);

        rightShooterController = new GSpeedController(new PWMVictorSPX(RobotMap.RIGHT_SHOOTER_PWM), rightShooterEncoder, 0.1, 0.1);  // TODO: tune
        rightShooterController.setInverted(false);

        cellEnterLimitSwitch = new DigitalInput(RobotMap.CELL_ENTER_SWITCH);

        cellExitLimitSwitch = new DigitalInput(RobotMap.CELL_EXIT_SWITCH);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new StopPowerCellManipulator());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

}
