package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.oi.*;

public class OI {

    Joystick leftJoyStick, rightJoyStick;
    XboxController gamePad, gamePadDriver;

    JoystickButton intakeButton;
    JoystickButton elevatorButton;

    public OI() {

    leftJoyStick = new Joystick(leftJoystickPort);
    rightJoyStick = new Joystick(rightJoystickPort);
    gamePad = new XboxController(gamePadPort);
    gamePadDriver = new XboxController(gamePadDriverPort);
    elevatorButton = new JoystickButton(gamePad, 7);

}

      public boolean getElevatorUp() {
        return (gamePad.getPOV() == 0);
      }

      public boolean getElevatorDown() {
        return (gamePad.getPOV() == 180);
      }

      public boolean getElevatorOut(){
        return (gamePad.getPOV() == 270);
      }

      public boolean getElevatorIn(){
        return (gamePad.getPOV() == 90);
      }

      public boolean getXButton() {
        return gamePad.getXButton();
      }    

      public boolean getYButton() {
        return gamePad.getYButton();
      }

      public boolean getAButton() {
        return gamePad.getAButton();
      }

      public boolean getBButton() {
        return gamePad.getBButton();
      }

      public boolean getLeftBumper(){
        return gamePad.getRawButtonPressed(5);
      }

      public boolean getRightBumper(){
        return gamePad.getRawButtonPressed(6);
      }

      public double getleftYAxis() {
        // return Math.pow(-leftJoyStick.getY(), 3.0);
        return leftJoyStick.getY();
        // // return Math.pow(-gamePadDriver.getLeftY(), 3.0);
        //return gamePadDriver.getLeftY();
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