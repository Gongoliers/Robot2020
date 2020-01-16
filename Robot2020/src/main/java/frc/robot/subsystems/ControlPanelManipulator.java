package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.StopPanelSpinner;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 *
 */
public class ControlPanelManipulator extends Subsystem {

    private PWMVictorSPX panelSpinningController;
    private Encoder panelSpinningEncoder;

    public ControlPanelManipulator() {
        panelSpinningController = new PWMVictorSPX(4);
        addChild("PanelSpinningController", panelSpinningController);
        panelSpinningController.setInverted(false);

        panelSpinningEncoder = new Encoder(6, 7, false, EncodingType.k4X);
        addChild("PanelSpinningEncoder", panelSpinningEncoder);
        panelSpinningEncoder.setDistancePerPulse(1.0);

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
