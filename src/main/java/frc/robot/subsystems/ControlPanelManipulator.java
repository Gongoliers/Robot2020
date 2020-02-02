package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;
import frc.robot.commands.controlPanel.*;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
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

    private ColorSensorV3 colorSensor;
    private ColorMatch colorMatcher;
    private final Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    public ControlPanelManipulator() {

        panelSpinningEncoder = new Encoder(RobotMap.PANEL_SPINNER_ENCODER_A, RobotMap.PANEL_SPINNER_ENCODER_B);
        panelSpinningEncoder.setDistancePerPulse(1.0);

        panelSpinningController = new GSpeedController(new PWMVictorSPX(RobotMap.PANEL_SPINNER_PWM),
                panelSpinningEncoder, null, null); // TODO: Implement PID for panel spinner ***
        panelSpinningController.setInverted(false);

        panelDeployPiston = new GPiston(new Solenoid(RobotMap.PANEL_DEPLOY_PISTON));
        panelDeployPiston.setInverted(false);

        colorMatcher = new ColorMatch();

        colorMatcher.addColorMatch(blueTarget);
        colorMatcher.addColorMatch(greenTarget);
        colorMatcher.addColorMatch(redTarget);
        colorMatcher.addColorMatch(yellowTarget);

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

    // These two methods are mainly for approaching the control panel
    public int getProximity(){
        return colorSensor.getProximity();
    }

    public boolean isConfident(){
        return colorMatcher.matchClosestColor(colorSensor.getColor()).confidence >= .75;
    }
}
