package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ShooterConstants.*;

public class ShooterSubsystem extends SubsystemBase {

  
  public TalonFX talon1, talon2;
  //public double shooterTolerance;
  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    talon1 = new TalonFX(SHOOTER_PORTS[0]);
    talon2 = new TalonFX(SHOOTER_PORTS[1]);
    
    talon1.configFactoryDefault();
    talon2.configFactoryDefault();

    talon1.setInverted(!SHOOTER_INVERTED);
    talon2.setInverted(!SHOOTER_INVERTED);

    talon2.config_kP(0, 0.18);
    talon2.config_kI(0, 0.0008);
    talon2.config_kF(0, 0.048);
    talon2.config_kD(0, 0);
    talon2.config_IntegralZone(0, 500);
    talon2.configPeakOutputForward(1);
    talon2.configPeakOutputReverse(0);
    //talon2.configVelocityMeasurementWindow(1);

  }
  public double getCurrentShooterSpeedTalonTwo(){

    return talon2.getSelectedSensorVelocity() * velocityCalculationsPerSecond * 60 / encoderPulsesPerRevolution;
  }

  public double getCurrentShooterSpeedTalonOne(){

    return ((talon1.getSelectedSensorVelocity() * velocityCalculationsPerSecond * 60 / encoderPulsesPerRevolution)/1.5);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setPowerFront(double powerFront) {
    talon2.set(ControlMode.PercentOutput, powerFront);
    
  }

  public void setVelocityFront(double rpm){
    talon2.set(ControlMode.Velocity, rpm * encoderPulsesPerRevolution / 10.0 / 60.0);
  }

  public void setPowerBack(double powerBack){
     talon1.set(ControlMode.PercentOutput, powerBack);
  }

  public boolean atSetpoint(double target, double tolerance){
    SmartDashboard.putNumber("closed loop error", talon2.getClosedLoopError());
    //return (Math.abs(talon2.getClosedLoopError()) * 10 * 60 / encoderPulsesPerRevolution) < rpm ;
    return Math.abs(getCurrentShooterSpeedTalonTwo() - target) < tolerance;
  }
}