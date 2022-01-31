// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid; 

import static frc.robot.Constants.Pistons.*;


public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */ 
  private TalonFX MotorIntake;
  static DoubleSolenoid intakePiston;
  double intakePistonVal;
  public boolean isPistonOut = false;
  
  public IntakeSubsystem() {
    MotorIntake = new TalonFX(Constants.intakeMotorPort);
    intakePiston = new DoubleSolenoid(13, null, intakePistonPortOne[2], intakePistonPortOne[3]);

  }

  public void setIntakeMotor(double Speed) {
    MotorIntake.set(ControlMode.PercentOutput, -Speed);
  }

  public void setIntakePosition(double intakePistonVal) {
    if(intakePistonVal == 0.5) {
      intakePiston.set(DoubleSolenoid.Value.kReverse);
      isPistonOut = false;
    } else if (intakePistonVal == 1) {
      intakePiston.set(DoubleSolenoid.Value.kForward);
      isPistonOut = true;
    }

    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setIntake(double power) {
    MotorIntake.set(ControlMode.PercentOutput, power);
  }
}