/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

 // NOTE: This code was taken from https://gist.github.com/pordonj/970b2c189cc6ee06388b3e2f12abcb72

package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;


public class DPadButton extends Button {
    XboxController contorller;
    Direction direction;
    public DPadButton(XboxController controller, Direction direction) {

        this.contorller = controller;
        this.direction = direction;

    }

    public static enum Direction {
        UP(0), RIGHT(90), DOWN(180), LEFT(270);
        int direction;
        private Direction(int direction) {
            this.direction = direction;

        }

    }

    public boolean get() {

        int dPadValue = contorller.getPOV();
        return (dPadValue == direction.direction) || (dPadValue == (direction.direction + 45) % 360) || (dPadValue == (direction.direction + 315) % 360);

    }

}
