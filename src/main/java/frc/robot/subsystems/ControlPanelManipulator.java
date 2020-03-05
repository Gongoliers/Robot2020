package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;
import frc.robot.commands.controlpanel.*;

import com.kylecorry.pid.PID;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.thegongoliers.input.gameMessages.GameSpecificMessage2020.ColorAssignment;
import com.thegongoliers.output.actuators.GPiston;
import com.thegongoliers.output.actuators.GSpeedController;
import com.thegongoliers.output.interfaces.Piston;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class ControlPanelManipulator extends Subsystem {

    public static final int DISTANCE_FROM_SENSOR_TO_PANEL = 150;
    private static final double PANEL_SPINNER_SPEED = 0.6; // TODO: tune panel spinner speed
    private static final double SLOW_PANEL_SPINNER_SPEED = 0.1;

    private GSpeedController panelSpinningController;
    private Encoder panelSpinningEncoder;
    private Piston panelDeployPiston;

    private PID distancePID = new PID(0.1, 0.0, 0.0); // TODO: Tune PID values (low priority for first comp)
    private PID velocityPID = new PID(0.1, 0.0, 0.0);

    private ColorSensorV3 colorSensor;
    private ColorMatch colorMatcher;
    private final Color blueTarget;
    private final Color greenTarget;
    private final Color redTarget;
    private final Color yellowTarget;

    private ColorAssignment colorSensorReading = ColorAssignment.Unknown;

    public ControlPanelManipulator() {

        panelSpinningEncoder = new Encoder(RobotMap.PANEL_SPINNER_ENCODER_A, RobotMap.PANEL_SPINNER_ENCODER_B);
        panelSpinningEncoder.setDistancePerPulse(1.0);

        panelSpinningController = new GSpeedController(new PWMVictorSPX(RobotMap.PANEL_SPINNER_PWM),
                panelSpinningEncoder, distancePID, velocityPID);
        panelSpinningController.setInverted(false);

        panelDeployPiston = new GPiston(new Solenoid(RobotMap.PANEL_DEPLOY_PISTON));
        panelDeployPiston.setInverted(false);

        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        colorMatcher = new ColorMatch();

        blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
        greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
        redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
        yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

        colorMatcher.addColorMatch(blueTarget);
        colorMatcher.addColorMatch(greenTarget);
        colorMatcher.addColorMatch(redTarget);
        colorMatcher.addColorMatch(yellowTarget);

        SmartDashboard.putString("Detected Color", "N/A");
        SmartDashboard.putNumber("Color Confidence", 0);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new StopPanelSpinner());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putBoolean("Spinner Deployed?", isDeployed());
        SmartDashboard.putNumber("Spinner Encoder", getSpinnerDistance());
        SmartDashboard.putNumber("Proximity", getProximity());
        SmartDashboard.putBoolean("Above Panel", getProximity() > DISTANCE_FROM_SENSOR_TO_PANEL);
        readColorSensor();
    }

    public ColorAssignment getColor() {
        return colorSensorReading;
    }

    private void readColorSensor() {
        ColorMatchResult matchResult;
        ColorAssignment colorResult;

        try {
            matchResult = colorMatcher.matchClosestColor(colorSensor.getColor());
        } catch (Exception e) {
            e.printStackTrace();
            matchResult = new ColorMatchResult(Color.kBlack, 0);
        }
        
        if (matchResult.color == blueTarget) {
            colorResult = ColorAssignment.Blue;
        } else if (matchResult.color == redTarget) {
            colorResult = ColorAssignment.Red;
        } else if (matchResult.color == greenTarget) {
            colorResult = ColorAssignment.Green;
        } else if (matchResult.color == yellowTarget) {
            colorResult = ColorAssignment.Yellow;
        } else {
            colorResult = ColorAssignment.Unknown;
        }

        SmartDashboard.putString("Detected Color", colorResult.name());
        SmartDashboard.putNumber("Color Confidence", matchResult.confidence);
        colorSensorReading = colorResult;
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

    public void slowRotate(boolean positive) {
        if (positive) {
            panelSpinningController.set(SLOW_PANEL_SPINNER_SPEED);
        } else {
            panelSpinningController.set(-SLOW_PANEL_SPINNER_SPEED);
        }
    }

    public void stopSpinner() {
        panelSpinningController.stopMotor();
    }

    public double getSpinnerDistance() {
        return panelSpinningController.getDistance();
    }

    public void setSpinnerDistance(double distance) {
        panelSpinningController.setDistance(distance);
    }

    public void resetSpinnerDistance() {
        panelSpinningEncoder.reset();
    }

    public boolean isSpinnerAtDistance() {
        return distancePID.atSetpoint();
    }

    public boolean isDeployed() {
        return panelDeployPiston.isExtended();
    }

    // These two methods are mainly for approaching the control panel
    public int getProximity(){
        try {
            return colorSensor.getProximity();
        } catch (Exception ex) {
            return -1;
        }
    }

}
