package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.climber.*;

import com.thegongoliers.input.switches.LimitSwitch;

import edu.wpi.first.wpilibj.PWMVictorSPX;

/**
 * Our climber subsystem is composed of (1) a arm that delivers a hook to 
 * the shield generator switch and (2) a winch that lifts the robot off
 * the ground into a HANG.
 */
public class Climber extends Subsystem {

    private static final double WINCH_SPEED = 0.6;
    private static final double DELIVERY_SPEED = 0.7;
    private static final double MAX_VOLTAGE = 12.5;

    private PWMVictorSPX climberDeliveryController;
    private PWMVictorSPX climberWinchController;
    private LimitSwitch highClimberSwitch;

    public Climber() {
        climberDeliveryController = new PWMVictorSPX(RobotMap.CLIMBER_DELIVER_PWM);
        climberDeliveryController.setInverted(false);

        climberWinchController = new PWMVictorSPX(RobotMap.CLIMBER_WINCH_PWM);
        climberWinchController.setInverted(false);

        highClimberSwitch = new LimitSwitch(RobotMap.HIGH_CLIMBER_SWITCH);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new StopClimber());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putBoolean("Delivery High?", highClimberSwitch.isTriggered());
    }

    public void stopWinch() {
        climberWinchController.stopMotor();
    }

    public void stopDelivery() {
        climberDeliveryController.stopMotor();
    }

    public void extendDelivery() {
        climberDeliveryController.setVoltage(DELIVERY_SPEED * MAX_VOLTAGE);
    }

    public void retractDelivery() {
        climberDeliveryController.setVoltage(-DELIVERY_SPEED * MAX_VOLTAGE);
    }

    public boolean isDeliveryAtTop() {
        return highClimberSwitch.isTriggered();
    }

	public void raiseWinch() {
        climberWinchController.set(WINCH_SPEED);
	}

}

