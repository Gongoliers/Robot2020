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
    // --> ANALOG (potentiometers)
    public static final int CLIMBER_POTENTIOMETER = 0;

    // Control Panel Manipulator
    // --> PWM (motors)
    public static final int PANEL_SPINNER_PWM = 4;
    // --> DIO (encoders & limit switches)
    public static final int PANEL_SPINNER_ENCODER_A = 12;
    public static final int PANEL_SPINNER_ENCODER_B = 13;

    // Power Cell Manipulator
    // --> PWM (motors)
    public static final int INTAKE_PWM = 5;
    public static final int INDEXER_PWM = 6;
    public static final int LEFT_SHOOTER_PWM = 7;
    public static final int RIGHT_SHOOTER_PWM = 8;
    // --> DIO (encoders & limit switches)
    public static final int INDEXER_ENCODER_A = 4;
    public static final int INDEXER_ENCODER_B = 5;
    public static final int LEFT_SHOOTER_ENCODER_A = 6;
    public static final int LEFT_SHOOTER_ENCODER_B = 7;
    public static final int RIGHT_SHOOTER_ENCODER_A = 8;
    public static final int RIGHT_SHOOTER_ENCODER_B = 9;
    public static final int CELL_ENTER_SWITCH = 10;
    public static final int CELL_EXIT_SWITCH = 11;
}
