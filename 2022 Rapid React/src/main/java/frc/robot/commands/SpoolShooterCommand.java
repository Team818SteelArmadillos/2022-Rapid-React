// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

import static frc.robot.Constants.ShooterConstants.*;

public class SpoolShooterCommand extends CommandBase {
  double rpm;
  PIDController ShootPID;

  double ShooterMotorspeed;

  public SpoolShooterCommand() {
    rpm = 0;
    ShootPID = new PIDController( shooterFrontP, shooterFrontI, shooterFrontD);
    ShootPID.setTolerance(10);
    
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("Set Shooter speed", 0);
    Robot.m_ShooterSubsystem.setPowerFront(0);
    //Robot.m_ShooterSubsystem.setPowerBack(0);
  }

  @Override
  public void execute() {
    if( Robot.m_oi.getLeftBumper()  ||  Robot.m_oi.getRightBumper()){
      Robot.m_ShooterSubsystem.setPowerFront(1);
      //Robot.m_ShooterSubsystem.setPowerBack(1);
    }else{
      Robot.m_ShooterSubsystem.setPowerFront(0);
      //Robot.m_ShooterSubsystem.setPowerBack(0);
    }
    
    if(Robot.m_oi.getBackButton()){
      Robot.m_IndexSubsystem.setIndex(0.5);
    }else{
      Robot.m_IndexSubsystem.setIndex(0);
    }
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_ShooterSubsystem.setPowerFront(0);
    //Robot.m_ShooterSubsystem.setPowerBack(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
