package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.motorports.*;
import static frc.robot.Constants.sensorPorts.*;

public class IndexSubsystem extends SubsystemBase {

  private DigitalInput SensorFront, SensorBack;
  private TalonFX indexMotor;
  private VictorSPX conveyorMotor;
  ColorSensorV3 colorSensor1;
  ColorSensorV3 colorSensor2;
  I2C.Port i2cPort;

  public IndexSubsystem() {

    i2cPort  = I2C.Port.kOnboard;
    colorSensor1 = new ColorSensorV3(i2cPort);


    indexMotor = new TalonFX(indexMotorPortUpper);
    indexMotor.configFactoryDefault();
    conveyorMotor = new VictorSPX(indexMotorPortConveyor);
    conveyorMotor.configFactoryDefault();
    SensorFront = new DigitalInput(indexSensorFront);
    SensorBack = new DigitalInput(indexSensorBack);

  }

  public Color getBallColor() {
    return colorSensor1.getColor();
  }

  public void doIndex(double Speed) {
    indexMotor.set(ControlMode.PercentOutput, -Speed);
    conveyorMotor.set(ControlMode.PercentOutput, -Speed);
  }

  public void logData(){
    SmartDashboard.putBoolean("SensorFront", SensorFront.get());
    SmartDashboard.putBoolean("SensorBack", SensorBack.get());

  }

  @Override
  public void periodic() {
  }
}
