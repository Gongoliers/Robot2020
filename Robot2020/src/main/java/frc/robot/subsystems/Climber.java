package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.StopClimber;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/**
 *
 */
public class Climber extends Subsystem {

    private PWMVictorSPX climberDeliveryController;
    private PWMVictorSPX climberWinchController;
    private AnalogPotentiometer climberPotentiometer;

    public Climber() {
        climberDeliveryController = new PWMVictorSPX(2);
        addChild("ClimberDeliveryController", climberDeliveryController);
        climberDeliveryController.setInverted(false);

        climberWinchController = new PWMVictorSPX(3);
        addChild("ClimberWinchController", climberWinchController);
        climberWinchController.setInverted(false);

        climberPotentiometer = new AnalogPotentiometer(0, 1.0, 0.0);
        addChild("ClimberPotentiometer", climberPotentiometer);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new StopClimber());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

}
