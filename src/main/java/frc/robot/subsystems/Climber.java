package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.climber.*;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/**
 * Our climber subsystem is composed of (1) a arm that delivers a hook to 
 * the shield generator switch and (2) a winch that lifts the robot off
 * the ground into a HANG.
 */
public class Climber extends Subsystem {

    private static final double BOTTOM_POTENTIOMETER_VALUE = 0; // TODO tune these values
    private static final double TOP_POTENTIOMETER_VALUE = 1;
    private static final double WINCH_SPEED = 0.5;
    private static final double DELIVERY_SPEED = 0.5;

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

    public void stopWinch() {
        climberWinchController.stopMotor();
    }

    public void stopDelivery() {
        climberDeliveryController.stopMotor();
    }

    public void extendDelivery() {
        climberDeliveryController.set(DELIVERY_SPEED);
    }

    public void retractDelivery() {
        climberDeliveryController.set(-DELIVERY_SPEED);
    }

    public boolean isDeliveryAtTop() {
        return climberPotentiometer.get() >= TOP_POTENTIOMETER_VALUE;
    }

    public boolean isDeliveryAtBottom() {
        return climberPotentiometer.get() <= BOTTOM_POTENTIOMETER_VALUE;
    }

	public void raiseWinch() {
        climberWinchController.set(WINCH_SPEED);
	}

}
