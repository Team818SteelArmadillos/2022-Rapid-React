// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.motorports.*;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */ 
  private TalonFX MotorIntake;
  public IntakeSubsystem() {
    MotorIntake = new TalonFX(intakeMotorPort);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setIntake(double power) {
    MotorIntake.set(ControlMode.PercentOutput, power);
  }
}