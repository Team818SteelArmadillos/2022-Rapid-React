package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.oi.*;

public class OI {

    Joystick leftJoyStick, rightJoyStick;
    XboxController  gamePad, gamePadDriver;

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

      public boolean getElevatorRight(){
        return (gamePad.getPOV() == 270);
      }

      public boolean getElevatorLeft(){
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

      public boolean getLeftBumper() {
        return gamePad.getLeftBumperPressed();
      }

      public boolean getRightBumper() {
        return gamePad.getRightBumper();
      }

      public boolean getRightTrigger() {
        return gamePad.getRightTriggerAxis() > 0.5;
      }

      public boolean getLeftTrigger() {
        return gamePad.getLeftTriggerAxis() > 0.5;
      }

      public boolean getBackButton() {
        return gamePad.getBackButton();
      }
      
      public boolean getStartButton(){
        return gamePad.getStartButton();
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

      public double getgamepadleftXAxis() {
        if (Math.abs(gamePad.getLeftX()) > 0.05){
          return 0.25 * gamePad.getLeftX();
        } else {
          return 0;
        }
      }
      public double getgamepadrightXAxis() {
        if (Math.abs(gamePad.getRightX()) > 0.05){
          return  gamePad.getRightX();
        } else {
          return 0;
        }
      }

      public boolean shiftGears(){
      return (rightJoyStick.getRawButton(2));
      }
      public boolean elevatorUp(){
        return (rightJoyStick.getRawButton(8));
      }
      public boolean elevatorZero(){
        return (rightJoyStick.getRawButton(7));
      }

      public boolean dynamicBreaking(){
        return (rightJoyStick.getRawButton(5));
      }

      public boolean get11(){
        return (rightJoyStick.getRawButton(11));
      }

    }