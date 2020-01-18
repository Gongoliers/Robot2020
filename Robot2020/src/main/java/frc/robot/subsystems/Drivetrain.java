package frc.robot.subsystems;

import frc.robot.NavxGyro;
import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.List;

import com.kauailabs.navx.frc.AHRS;
import com.thegongoliers.output.drivetrain.ModularDrivetrain;
import com.thegongoliers.output.drivetrain.PathFollowerModule;
import com.thegongoliers.output.drivetrain.PowerEfficiencyModule;
import com.thegongoliers.output.drivetrain.StabilityModule;
import com.thegongoliers.output.drivetrain.VoltageControlModule;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

    private PWMVictorSPX leftDriveController;
    private PWMVictorSPX rightDriveController;
    private Encoder leftDriveEncoder;
    private Encoder rightDriveEncoder;

    public AHRS navx = new AHRS(SerialPort.Port.kMXP);

    private ModularDrivetrain modularDrivetrain;

    public Drivetrain() {
        leftDriveController = new PWMVictorSPX(RobotMap.LEFT_DRIVE_PWM);
        leftDriveController.setInverted(false);

        rightDriveController = new PWMVictorSPX(RobotMap.RIGHT_DRIVE_PWM);
        rightDriveController.setInverted(false);

        leftDriveEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER_A, RobotMap.LEFT_DRIVE_ENCODER_B);
        leftDriveEncoder.setDistancePerPulse(1.0);

        rightDriveEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_A, RobotMap.RIGHT_DRIVE_ENCODER_B);
        rightDriveEncoder.setDistancePerPulse(1.0);

        DifferentialDrive differentialDrive = new DifferentialDrive(leftDriveController, rightDriveController);
        differentialDrive.setSafetyEnabled(true);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);
        differentialDrive.setDeadband(0.02); // TODO: tune

        modularDrivetrain = ModularDrivetrain.from(differentialDrive);
        Gyro gyro = new NavxGyro(navx);

        StabilityModule stabilityModule = new StabilityModule(gyro, 0.02, 0.5); // TODO: tune
        stabilityModule.setTurnThreshold(0.05); // TODO: tune

        PathFollowerModule pathFollowerModule = new PathFollowerModule(gyro,
                List.of(leftDriveEncoder, rightDriveEncoder), 0.5, 0.02);
        pathFollowerModule.setForwardTolerance(0.6); // 1/2 feet
        pathFollowerModule.setTurnTolerance(1); // 1 degree

        VoltageControlModule voltageControlModule = new VoltageControlModule(12);

        PowerEfficiencyModule powerEfficiencyModule = new PowerEfficiencyModule(0.25, 0.2);

        modularDrivetrain.setModules(stabilityModule, pathFollowerModule, voltageControlModule, powerEfficiencyModule);

    }

    @Override
    public void initDefaultCommand() {

        setDefaultCommand(new DrivetrainOperatorContol());

    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putNumber("Heading", navx.getAngle());
        SmartDashboard.putNumber("Distance", (leftDriveEncoder.getDistance() + rightDriveEncoder.getDistance()) / 2);
        SmartDashboard.putNumber("Speed", (leftDriveEncoder.getRate() + rightDriveEncoder.getRate()) / 2);
    }

    public void arcadeDrive(double forwardSpeed, double turnSpeed) {
        modularDrivetrain.arcade(forwardSpeed, turnSpeed);
    }

    public void stop() {
        modularDrivetrain.stop();
    }

    public ModularDrivetrain getModularDrivetrain() {
        return modularDrivetrain;
    }

}
