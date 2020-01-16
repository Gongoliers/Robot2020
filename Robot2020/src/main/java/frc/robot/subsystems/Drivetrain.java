package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drivetrain extends Subsystem {

    private PWMVictorSPX leftDriveController;
    private PWMVictorSPX rightDriveController;
    private DifferentialDrive differentialDrive;

    public Drivetrain() {
        leftDriveController = new PWMVictorSPX(0);
        addChild("LeftDriveController", leftDriveController);
        leftDriveController.setInverted(false);

        rightDriveController = new PWMVictorSPX(1);
        addChild("RightDriveController", rightDriveController);
        rightDriveController.setInverted(false);

        differentialDrive = new DifferentialDrive(leftDriveController, rightDriveController);
        addChild("Differential Drive", differentialDrive);
        differentialDrive.setSafetyEnabled(true);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);

    }

    @Override
    public void initDefaultCommand() {

        setDefaultCommand(new DrivetrainOperatorContol());

    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

}
