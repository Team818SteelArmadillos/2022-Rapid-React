// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

  public TalonFX talon1, talon2;
  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    talon1 = new TalonFX(Constants.ShooterConstants.SHOOTER_PORTS[0]);
    talon2 = new TalonFX(Constants.ShooterConstants.SHOOTER_PORTS[1]);
    
    talon1.configFactoryDefault();
    talon2.configFactoryDefault();

    talon2.follow(talon1);

    talon1.setInverted(Constants.ShooterConstants.SHOOTER_INVERTED);
    talon2.setInverted(Constants.ShooterConstants.SHOOTER_INVERTED);

  }
  public double getCurrentShooterSpeed(){

    return talon1.getSelectedSensorVelocity() * Constants.ShooterConstants.velocityCalculationsPerSecond *-1 * 60 / Constants.ShooterConstants.encoderPulsesPerRevolution;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setPower(double power) {
    talon1.set(ControlMode.PercentOutput, power);
  }
}
