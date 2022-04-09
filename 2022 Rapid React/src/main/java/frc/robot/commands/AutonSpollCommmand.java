// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

import static frc.robot.Constants.ShooterConstants.*;

public class AutonSpollCommmand extends CommandBase {
  double rpm, powerFront, powerBack;
  PIDController ShootFrontPID, ShootBackPID;

  double ShooterMotorspeed;

  public AutonSpollCommmand(double rpm) {
    this.rpm = rpm;

    ShootFrontPID = new PIDController( shooterFrontP, shooterFrontI, shooterFrontD);
    ShootFrontPID.setTolerance(10);

    ShootBackPID = new PIDController(shooterBackP, shooterBackI, shooterBackD);
    ShootBackPID.setTolerance(100);
    
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("Set Shooter speed", 0);
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_ShooterSubsystem.setPowerBack(0);
  }

  @Override
  public void execute() {
    
      powerFront = -ShootFrontPID.calculate(rpm - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonTwo());
      Robot.m_ShooterSubsystem.setPowerFront(powerFront);

      powerBack = -ShootBackPID.calculate((rpm * 1.15) - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonOne());
      Robot.m_ShooterSubsystem.setPowerBack(powerBack);
    
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_ShooterSubsystem.setPowerBack(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
