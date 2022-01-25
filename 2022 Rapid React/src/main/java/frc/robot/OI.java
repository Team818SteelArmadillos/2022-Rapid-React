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
      }
    
      public double getrightYAxis() {
        // return Math.pow(rightJoyStick.getY(), 3.0);
        return rightJoyStick.getY();
      }
    
      public double getleftXAxis() {
        // return Math.pow(-rightJoyStick.getX(), 3.0);
        return leftJoyStick.getX();
      }
      
      public double getrightXAxis() {
        // return Math.pow(-rightJoyStick.getX(), 3.0);
        return rightJoyStick.getX();
      }


      public double getleftYAxisController() {
        // return Math.pow(-gamePadDriver.getLeftY(), 3.0);
        return gamePadDriver.getLeftY();
      }
    
      public double getrightYAxisController() {
        // return Math.pow(gamePadDriver.getRightY(), 3.0);
        return gamePadDriver.getRightY();
      }
    
      public double getleftXAxisController() {
        // return Math.pow(-gamePadDriver.getLeftX(), 3.0);
        return gamePadDriver.getLeftX();
      }
      
      public double getrightXAxisController() {
        // return Math.pow(-gamePadDriver.getRightX(), 3.0);
        return gamePadDriver.getRightX();
      }

    }