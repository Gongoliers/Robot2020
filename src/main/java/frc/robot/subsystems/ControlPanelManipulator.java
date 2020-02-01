package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.controlPanel.*;

import com.thegongoliers.output.actuators.GPiston;
import com.thegongoliers.output.actuators.GSpeedController;
import com.thegongoliers.output.interfaces.Piston;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class ControlPanelManipulator extends Subsystem {
    
    private static final double PANEL_SPINNER_SPEED = 0.6; // TODO: tune panel spinner speed

    private GSpeedController panelSpinningController;
    private Encoder panelSpinningEncoder;
    private Piston panelDeployPiston;

    public ControlPanelManipulator() {
        
        panelSpinningEncoder = new Encoder(RobotMap.PANEL_SPINNER_ENCODER_A, RobotMap.PANEL_SPINNER_ENCODER_B);
        panelSpinningEncoder.setDistancePerPulse(1.0);

        panelSpinningController = new GSpeedController(new PWMVictorSPX(RobotMap.PANEL_SPINNER_PWM), panelSpinningEncoder, null, null); // TODO: Implement PID for panel spinner ***
        panelSpinningController.setInverted(false);

        panelDeployPiston = new GPiston(new Solenoid(RobotMap.PANEL_DEPLOY_PISTON));
        panelDeployPiston.setInverted(false);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new StopPanelSpinner());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    }

    public void deploy() {
        panelDeployPiston.extend();
    }

    public void retract() {
        panelDeployPiston.retract();
    }

	public void rotate() {
        panelSpinningController.set(PANEL_SPINNER_SPEED);
	}

	public void stopSpinner() {
        panelSpinningController.stopMotor();
    }
    
    public double getSpinnerDistance() {
        return panelSpinningEncoder.getDistance();
    }

    public boolean isDeployed() {
        return panelDeployPiston.isExtended();
    }

}
