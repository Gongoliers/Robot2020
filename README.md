![Java Gradle Build](https://github.com/Gongoliers/Robot2020/workflows/Java%20Gradle%20Build/badge.svg)
# Robot2020
 Team 5112's official competition code for FRC Infinite Recharge 2020 season.
 Our robot code uses our homemade [Library of Gongolierium](https://github.com/Gongoliers/Library-of-Gongolierium), which you can easily implement in your robot code.
 
 #### What is FRC?
 The FIRST Robotics Competition is an international high school robotics competition that gives students just 7 weeks to design a 125 pound robot to complete challenges and play a sport!  [Learn more about this years game here.](https://www.youtube.com/watch?v=gmiYWTmFRVE)

## Controls
We use a joystick / flightstick for driving the chassis and we use an Xbox controller for manipulating the other subsystems.
![Xbox Controller Mappings](https://github.com/Gongoliers/Robot2020/raw/master/XboxController.png "Xbox Controller Mappings")
By default, the joystick plugged into port 0 will control drivetrain movement on the Y and Z axes, with button 09-10 being used for "stop all" and button 11-12 used for "align to target".  Xbox controller is plugged into port 1.  You may elect to use "single-player mode" by changing a flag in the OI class that will enable the thumbsticks on the Xbox controller to control drivetrain movement.

## Design Synopsis
Our robot consists of four major subsystems: the drivetrain, the climber, the control panel manipulator, and the power cell manipulator.

### Drivetrain
Team 5112's drivetrain consists of a drop-center West Coast chassis with six wheels.  Three Victor SPXs are used to control the three miniCIMs for each side, but each side's motor controllers are spliced together (so the code only considers one controller per side).  We use a NavX for rotational sensing and an encoder for each side of the chassis.

### Climber
Team 5112's climber subsystem consists of a hook-delivery arm and a winch to do a pull-up on the shield generator switch.  The hook-delivery arm is a telescopic design that lifts and attaches a hook to the bar.  The arm then retracts, leaving the hook there, and a winch pulls to robot up to the hook.  There is one Victor SPX used for the hook-delivery system, and one Victor SPX used for the winch.  A limit switch can determine when the delivery is at the top, and another limit switch can determine when it is at the bottom.

### Control Panel Manipulator
Team 5112's control panel manipulator is composed of a Victor SPX for spinning the control panel and a pneumatic piston for deploying the spinner.  An encoder is attached to the spinner motor.  A color sensor / proximity sensor is used for detecting the color of the control panel.

### Power Cell Manipulator
Team 5112's power cell manipulator is composed of two Victor SPXs used for intaking power cells and two Victor SPXs for the flywheel/scoring the power cells.  A pneumatic piston will be used for deploying the intake harvester.  Another pneumatic piston will flip the hood between high/low goal positions.  An encoder will be on the flywheel.

### Vision
We use a Limelight for target processing, especially for aligning with the power port for shooting.  As documented above, the control panel subsystem uses a color sensor / proximity sensor.
