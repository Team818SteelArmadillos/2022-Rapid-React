package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import static frc.robot.Constants.oi.*;

public class ShooterVisionSubsystem extends SubsystemBase {
  static Solenoid LEDLight;
  NetworkTable table;
  
  public ShooterVisionSubsystem() {

  LEDLight = new Solenoid(PneumaticsModuleType.CTREPCM, LEDLight);
  table = NetworkTableInstance.getDefault().getTable("Vision");

  }
   
  public void LightOn() {
    LEDLight.set(true);
  }

  public void LightOff() {
    LEDLight.set(false);
  }

  public double getX() {
    return table.getEntry("Horizontal Angle").getDouble(0.0);
  }

  public double getY() {
    return table.getEntry("Vertical Angle").getDouble(0.0);
  }

  public Boolean getTarget() {
    return table.getEntry("Has Target").getDouble(0.0) > 0.0;

  }

  public void logData() {
    //Logging Data
    SmartDashboard.putNumber("VisionX", getX());
    SmartDashboard.putNumber("VisionY", getY());
    SmartDashboard.putBoolean("Target", getTarget());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
