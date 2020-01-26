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

    private static final double STOWED_POTENTIOMETER_VALUE = 0; // TODO tune these values
    private static final double DEPLOYED_POTENTIOMETER_VALUE = 1;

    private GSpeedController panelSpinningController;
    private Encoder panelSpinningEncoder;
    private Piston panelDeployPiston;

    public ControlPanelManipulator() {
        
        panelSpinningEncoder = new Encoder(RobotMap.PANEL_SPINNER_ENCODER_A, RobotMap.PANEL_SPINNER_ENCODER_B);
        panelSpinningEncoder.setDistancePerPulse(1.0);

        panelSpinningController = new GSpeedController(new PWMVictorSPX(RobotMap.PANEL_SPINNER_PWM), panelSpinningEncoder, 0.1, 0.1); // TODO: tune
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

}
