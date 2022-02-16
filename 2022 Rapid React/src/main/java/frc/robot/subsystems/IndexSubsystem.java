package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.SensorPorts.*;
import static frc.robot.Constants.motorports.*;

public class IndexSubsystem extends SubsystemBase {

  private DigitalInput Sensor1, Sensor2;
  private TalonFX indexMotor;
  private TalonSRX conveyorMotor;
  ColorSensorV3 colorSensor1;
  I2C.Port i2cPort;

  public IndexSubsystem() {

    i2cPort  = I2C.Port.kOnboard;
    colorSensor1 = new ColorSensorV3(i2cPort);

    indexMotor = new TalonFX(indexMotorPortUpper);
    indexMotor.configFactoryDefault();
    conveyorMotor = new TalonSRX(indexMotorPortConveyor);
    conveyorMotor.configFactoryDefault();
    Sensor1 = new DigitalInput(indexSensorFront);
    Sensor2 = new DigitalInput(indexSensorBack);

  }

  public Color getBallColor() {
    return colorSensor1.getColor();
  }

  public void SetIndex(double Speed) {
    indexMotor.set(ControlMode.PercentOutput, -Speed);
  }

  public void setConveyor(double Speed) {
    conveyorMotor.set(ControlMode.PercentOutput, -Speed);
  }

  public boolean SensorFront() {
    return !Sensor1.get();
  }

  public boolean SensorBack() {
    return !Sensor2.get();
  }

  public void setIndex(double Speed) {
    indexMotor.set(ControlMode.PercentOutput, -Speed);
  }

  public void logData(){
    SmartDashboard.putBoolean("SensorFront", Sensor1.get());
    SmartDashboard.putBoolean("SensorBack", Sensor2.get());

  }

  @Override
  public void periodic() {
  }
}
