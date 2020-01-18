package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.StopPanelSpinner;

import com.thegongoliers.output.actuators.GSpeedController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/**
 *
 */
public class ControlPanelManipulator extends Subsystem {

    private GSpeedController panelSpinningController;
    private Encoder panelSpinningEncoder;

    public ControlPanelManipulator() {
        
        panelSpinningEncoder = new Encoder(RobotMap.PANEL_SPINNER_ENCODER_A, RobotMap.PANEL_SPINNER_ENCODER_B);
        panelSpinningEncoder.setDistancePerPulse(1.0);

        panelSpinningController = new GSpeedController(new PWMVictorSPX(RobotMap.PANEL_SPINNER_PWM), panelSpinningEncoder, 0.1, 0.1); // TODO: tune
        panelSpinningController.setInverted(false);

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
