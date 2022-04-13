// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

import static frc.robot.Constants.ShooterConstants.*;

public class HighShootManualCommand extends CommandBase {
  double rpm;
  PIDController ShootFrontPID, ShootBackPID;

  Timer timer;
  
  double powerFront, powerBack;

  public HighShootManualCommand() {
    ShootFrontPID = new PIDController( shooterFrontP, shooterFrontI, shooterFrontD);
    ShootFrontPID.setTolerance(10);

    ShootBackPID = new PIDController(shooterBackP, shooterBackI, shooterBackD);
    ShootBackPID.setTolerance(100);

    SmartDashboard.putNumber("Rpm", 1000);

    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("Set Shooter speed", 0);

    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //change value once redetemined

  // rpm = SmartDashboard.getNumber("Rpm", 0);
     rpm = 1650;
    
      powerFront = -ShootFrontPID.calculate(rpm - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonTwo());
      Robot.m_ShooterSubsystem.setPowerFront(powerFront);

      powerBack = -ShootBackPID.calculate((rpm * 1.15) - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonOne());
      Robot.m_ShooterSubsystem.setPowerBack(powerBack);

    if(ShootFrontPID.atSetpoint()){
      Robot.m_IndexSubsystem.setConveyor(-1);
      Robot.m_IndexSubsystem.setIndex(0.32);
      timer.start();
      if (timer.hasElapsed(0.2)){

        Robot.m_IndexSubsystem.setConveyor(1);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_ShooterSubsystem.setPowerBack(0);
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
