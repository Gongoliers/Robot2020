package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.StopPowerCellManipulator;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/**
 *
 */
public class PowerCellManipulator extends Subsystem {

    private PWMVictorSPX intakeController;
    private PWMVictorSPX indexerController;
    private Encoder indexerEncoder;
    private PWMVictorSPX leftShooterController;
    private PWMVictorSPX rightShooterController;
    private Encoder rightShooterEncoder;
    private Encoder leftShooterEncoder;
    private DigitalInput cellEnterLimitSwitch;
    private DigitalInput cellExitLimitSwitch;

    public PowerCellManipulator() {
        intakeController = new PWMVictorSPX(5);
        addChild("IntakeController", intakeController);
        intakeController.setInverted(false);

        indexerController = new PWMVictorSPX(6);
        addChild("IndexerController", indexerController);
        indexerController.setInverted(false);

        indexerEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        addChild("IndexerEncoder", indexerEncoder);
        indexerEncoder.setDistancePerPulse(1.0);

        leftShooterController = new PWMVictorSPX(7);
        addChild("LeftShooterController", leftShooterController);
        leftShooterController.setInverted(false);

        rightShooterController = new PWMVictorSPX(8);
        addChild("RightShooterController", rightShooterController);
        rightShooterController.setInverted(false);

        rightShooterEncoder = new Encoder(4, 5, false, EncodingType.k4X);
        addChild("RightShooterEncoder", rightShooterEncoder);
        rightShooterEncoder.setDistancePerPulse(1.0);

        leftShooterEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        addChild("LeftShooterEncoder", leftShooterEncoder);
        leftShooterEncoder.setDistancePerPulse(1.0);

        cellEnterLimitSwitch = new DigitalInput(8);
        addChild("CellEnterLimitSwitch", cellEnterLimitSwitch);

        cellExitLimitSwitch = new DigitalInput(9);
        addChild("CellExitLimitSwitch", cellExitLimitSwitch);

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
