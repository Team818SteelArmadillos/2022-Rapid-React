package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
 
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import static frc.robot.Constants.DriveConstants.*;
import static frc.robot.Constants.motorports.*;

public class DriveSubsystem extends SubsystemBase {

  private TalonFX talonLeft2, talonRight2;
  private TalonFX talonLeft1, talonRight1;
  private DoubleSolenoid shiftPistonLeft;

  private int leftOffset = 0;
  private int rightOffset = 0;

  boolean brake = false;
  boolean highGear = false;
  boolean isHighGear = false;
  private PigeonIMU pigeon;
  private AnalogGyro gyro;
  PIDController DrivePIDLeft, DrivePIDRight;

  public DriveSubsystem() {
    talonLeft1 = new TalonFX(MOTOR_PORTS_LEFT[0]);
    talonRight1 = new TalonFX(MOTOR_PORTS_RIGHT[0]);
    talonLeft2 = new TalonFX(MOTOR_PORTS_LEFT[1]);
    talonRight2 = new TalonFX(MOTOR_PORTS_RIGHT[1]);
    
    talonLeft1.configFactoryDefault();
    talonLeft1.setInverted(LEFT_INVERTED);
    talonLeft1.configOpenloopRamp(RAMP_RATE);

    talonRight1.configFactoryDefault();
    talonRight1.setInverted(!LEFT_INVERTED);
    talonRight1.configOpenloopRamp(RAMP_RATE);

    talonLeft2.configFactoryDefault();
    talonLeft2.setInverted(LEFT_INVERTED);
    talonLeft2.follow(talonLeft1);
    talonLeft2.setInverted(LEFT_INVERTED);
      
    talonRight2.configFactoryDefault();
    talonRight2.setInverted(!LEFT_INVERTED);
    talonRight2.follow(talonRight1);
    talonRight2.setInverted(!LEFT_INVERTED);

    DrivePIDLeft = new PIDController(P, I, D);
    DrivePIDRight = new PIDController(P, I, D);

    shiftPistonLeft = new DoubleSolenoid(shiftPistonPorts[2], PneumaticsModuleType.CTREPCM, shiftPistonPorts[0], shiftPistonPorts[1]);

    pigeon = new PigeonIMU(indexMotorPortConveyor);

    gyro = new AnalogGyro(new AnalogInput(GYRO_PORTS));
  }
  public double getAngle(){
    // double[] ypr = new double[3];
    // pigeon.getYawPitchRoll(ypr);
    // SmartDashboard.putNumber("yaw", ypr[0]);
    // SmartDashboard.putNumber("pitch", ypr[1]);
    // SmartDashboard.putNumber("roll", ypr[2]);
    SmartDashboard.putNumber("Angle", gyro.getAngle());
    // return ypr[0];
    return gyro.getAngle();
  }

  public void resetGyro() {
    // pigeon.setYaw(0);
    gyro.reset();
  }

  @Override
  public void periodic() {

  }

  public void shift(boolean highGear){
    isHighGear = highGear;
    if(highGear){
      shiftPistonLeft.set(DoubleSolenoid.Value.kReverse);
    }else{
      shiftPistonLeft.set(DoubleSolenoid.Value.kForward);
    }
  }

  public boolean currentGear(){
    return isHighGear;
  }
  public void setBothMotors(double speedLeft, double speedRight) {
    setLeftMotors(speedLeft);
    setRightMotors(speedRight);
}

public void resetEncoders() {
  talonLeft1.setSelectedSensorPosition(0);
  talonLeft2.setSelectedSensorPosition(0);
  talonRight1.setSelectedSensorPosition(0);
  talonRight2.setSelectedSensorPosition(0);
}

public void setLeftMotors(double speed) {
  talonLeft1.set(ControlMode.PercentOutput, speed);
}

public void setRightMotors(double speed) {
  talonRight1.set(ControlMode.PercentOutput, speed);
}

public void setBothMotors(double speed) {
  setLeftMotors(speed);
  setRightMotors(speed);
}

public void setDriveMotorPostion(double distance){

  setLeftMotors(MathUtil.clamp(DrivePIDLeft.calculate(distance - getLeftPosition()), -0.5, 0.5));
  setRightMotors(MathUtil.clamp(DrivePIDRight.calculate(distance - getRightPosition()), -0.5, 0.5));
 
}

public double getLeftPosition() {
  if (isHighGear){
    return -(talonLeft1.getSelectedSensorPosition() - leftOffset) * distancePerPulse / high;
  } else {
    return -(talonLeft1.getSelectedSensorPosition() - leftOffset) * distancePerPulse / low;
  }

}

public double getRightPosition() {
  if (isHighGear){
    return -(talonRight1.getSelectedSensorPosition() - rightOffset) * distancePerPulse / high;
  } else {
    return -(talonRight1.getSelectedSensorPosition() - rightOffset) * distancePerPulse / low;
  }

}

public double getPosition() {
  return (getRightPosition() + getLeftPosition()) * 0.5;
}

public double getLeftVelocity() {
  if(isHighGear){
  return talonLeft1.getSelectedSensorVelocity() * distancePerPulse * VELOCITY_CALCULATION_PER_SECOND * Math.PI / (12 * high);
  } else {
    return talonLeft1.getSelectedSensorVelocity() * distancePerPulse * VELOCITY_CALCULATION_PER_SECOND * Math.PI / (12 * low);
  }
}

public double getRightVelocity() {
  if(isHighGear){
  return talonRight1.getSelectedSensorVelocity() * distancePerPulse * VELOCITY_CALCULATION_PER_SECOND * Math.PI / (12 * high);
  } else {
  return talonRight1.getSelectedSensorVelocity() * distancePerPulse * VELOCITY_CALCULATION_PER_SECOND * Math.PI / (12 * low);
}
}

public double getVelocity() {
  return (getLeftVelocity() + getRightVelocity()) * 0.5;
}

}