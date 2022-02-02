package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
 
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import static frc.robot.Constants.DriveConstants.*;

public class DriveSubsystem extends SubsystemBase {

  private static final int[] MOTOR_PORTS_RIGHT = null;
private TalonFX talonLeft2, talonRight2;
  private TalonFX talonLeft1, talonRight1;
  private DoubleSolenoid shiftPistonLeft;

  private int leftOffset = 0;
  private int rightOffset = 0;

  boolean brake = false;
  boolean highGear = false;
  boolean isHighGear = false;
  private AnalogGyro gyro;

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

    shiftPistonLeft = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, shiftPistonPorts[0], shiftPistonPorts[1]);
  }
  public double getAngle(){
    return gyro.getAngle();
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

public double getLeftPosition() {
  if (isHighGear){
    return (talonLeft1.getSelectedSensorPosition() - leftOffset) * distancePerPulse / high;
  } else {
    return (talonLeft1.getSelectedSensorPosition() - leftOffset) * distancePerPulse / low;
  }

}

public double getRightPosition() {
  if (isHighGear){
    return (talonRight1.getSelectedSensorPosition() - rightOffset) * distancePerPulse / high;
  } else {
    return (talonRight1.getSelectedSensorPosition() - rightOffset) * distancePerPulse / low;
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