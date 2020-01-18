package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
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
        climberDeliveryController = new PWMVictorSPX(RobotMap.CLIMBER_DELIVER_PWM);
        climberDeliveryController.setInverted(false);

        climberWinchController = new PWMVictorSPX(RobotMap.CLIMBER_WINCH_PWM);
        climberWinchController.setInverted(false);

        climberPotentiometer = new AnalogPotentiometer(RobotMap.CLIMBER_POTENTIOMETER, 1.0, 0.0);

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
