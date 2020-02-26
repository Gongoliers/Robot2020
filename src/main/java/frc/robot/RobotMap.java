package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // Drivetrain
    // --> PWM (motors)
    public static final int LEFT_DRIVE_PWM = 0;
    public static final int RIGHT_DRIVE_PWM = 1;
    // --> DIO (encoders & limit switches)
    public static final int LEFT_DRIVE_ENCODER_A = 0;
    public static final int LEFT_DRIVE_ENCODER_B = 1;
    public static final int RIGHT_DRIVE_ENCODER_A = 2;
    public static final int RIGHT_DRIVE_ENCODER_B = 3;

    // Climber
    // --> PWM (motors)
    public static final int CLIMBER_DELIVER_PWM = 2;
    public static final int CLIMBER_WINCH_PWM = 3;
    // --> DIGITAL (encoders & limit switches)
    public static final int HIGH_CLIMBER_SWITCH = 9;

    // Control Panel Manipulator
    // --> PWM (motors)
    public static final int PANEL_SPINNER_PWM = 4;
    // --> Pneumatic (pistons)
    public static final int PANEL_DEPLOY_PISTON = 0;
    // --> DIO (encoders & limit switches)
    public static final int PANEL_SPINNER_ENCODER_A = 4;
    public static final int PANEL_SPINNER_ENCODER_B = 5;

    // Power Cell Manipulator
    // --> PWM (motors)
    public static final int INTAKE_PWM = 5;
    public static final int FEEDER_PWM = 6;
    public static final int SHOOTER_LEFT_PWM = 7;
    public static final int SHOOTER_RIGHT_PWM = 8;
    // --> Pneumatic (pistons)
    public static final int HARVESTER_PISTON = 1;
    public static final int HOOD_PISTON = 2;
    // --> DIO (encoders & limit switches)
    public static final int SHOOTER_ENCODER_A = 6;
    public static final int SHOOTER_ENCODER_B = 7;
}
