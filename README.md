# Robot2020
 Team 5112's official competition code for FRC Infinite Recharge 2020 season.
 Our robot code uses our homemade [Library of Gongolierium](https://github.com/Gongoliers/Library-of-Gongolierium), which you can easily implement in your robot code.
 **This project is in progress and is not yet complete.**
 
 #### What is FRC?
 The FIRST Robotics Competition is an international high school robotics competition that gives students just 7 weeks to design a 125 pound robot to complete challenges and play a sport!  [Learn more about this years game here.](https://www.youtube.com/watch?v=gmiYWTmFRVE)

## Design Synopsis
Our robot consists of four major subsystems: the drivetrain, the climber, the control panel manipulator, and the power cell manipulator.

### Drivetrain
Team 5112's drivetrain consists of a drop-center West Coast chassis with six wheels.  Three Victor SPXs are used to control the three miniCIMs for each side, but each side's motor controllers are spliced together (so the code only considers one controller per side).  We use a NavX for rotational sensing and an encoder for each side of the chassis.

### Climber
Team 5112's climber subsystem consists of a hook-delivery arm and a winch to do a pull-up on the shield generator switch.  The hook-delivery arm is a telescopic design that lifts and attaches a hook to the bar.  The arm then retracts, leaving the hook there, and a winch pulls to robot up to the hook.  There is one Victor SPX used for the hook-delivery system, and one Victor SPX used for he winch.  A potentiometer is attached to the hook-delivery motor.

### Control Panel Manipulator
Team 5112's control panel manipulator is composed of a Victor SPX for spinning the control panel and a pneumatic piston for deploying the spinner.  An encoder is attached to the spinner motor.

### Power Cell Manipulator
Team 5112's power cell manipulator is composed of a Victor SPX used for intaking power cells, a Victor SPX for indexing, and one Victor SPX for the flywheel/scoring the power cells.  A pneumatic piston will be used for deploying the intake harvester.  An encoder will be on the flywheel.

### Vision
We use a Limelight for target processing, especially for aligning with the power port for shooting.
