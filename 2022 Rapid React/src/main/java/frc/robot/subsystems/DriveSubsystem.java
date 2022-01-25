package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.Constants;
import static frc.robot.Constants.DriveConstants.*;

public class DriveSubsystem extends SubsystemBase {

  private TalonFX talonLeft2, talonRight2;
  private TalonFX talonLeft1, talonRight1;

  private DoubleSolenoid shiftPistonLeft;

  private int leftOffset = 0;
  private int rightOffset = 0;

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

    shiftPistonLeft = new DoubleSolenoid(13, shiftPistonPorts[0], shiftPistonPorts[1]);
  }

  @Override
  public void periodic() {
  }

  public void setBothMotors(double speedLeft, double speedRight) {
    setLeftMotors(speedLeft);
    setRightMotors(speedRight);
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