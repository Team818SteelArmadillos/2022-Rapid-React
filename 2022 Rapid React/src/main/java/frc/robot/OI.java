package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import static frc.robot.Constants.oi.*;

public class OI {

    Joystick leftJoyStick, rightJoyStick;
    XboxController gamePad, gamePadDriver;

    public OI() {

    leftJoyStick = new Joystick(leftJoystickPort);
    rightJoyStick = new Joystick(rightJoystickPort);
    gamePad = new XboxController(gamePadPort);
    gamePadDriver = new XboxController(gamePadDriverPort);
    
}

      public double getleftYAxis() {
        // return Math.pow(-leftJoyStick.getY(), 3.0);
        return leftJoyStick.getY();
        // // return Math.pow(-gamePadDriver.getLeftY(), 3.0);
        // return gamePadDriver.getLeftY();
      }
    
      public double getrightYAxis() {
        // return Math.pow(rightJoyStick.getY(), 3.0);
        return rightJoyStick.getY();
        // // return Math.pow(gamePadDriver.getRightY(), 3.0);
        // return gamePadDriver.getRightY();
      }
    
      public double getleftXAxis() {
        // return Math.pow(-rightJoyStick.getX(), 3.0);
        return leftJoyStick.getX();
        // // return Math.pow(-gamePadDriver.getLeftX(), 3.0);
        // return gamePadDriver.getLeftX();
      }
      
      public double getrightXAxis() {
        // return Math.pow(-rightJoyStick.getX(), 3.0);
        return rightJoyStick.getX();
        // // return Math.pow(-gamePadDriver.getRightX(), 3.0);
        // return gamePadDriver.getRightX();
      }

      public boolean shiftGears(){
      return (rightJoyStick.getRawButton(2));
      }
    }