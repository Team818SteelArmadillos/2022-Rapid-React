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

public class LowShootManualCommand extends CommandBase {
  double rpm;
  PIDController ShootFrontPID, ShootBackPID;
  double powerFront, powerBack;
  boolean dataLogged;
  Timer timer;

  public LowShootManualCommand() {
    ShootFrontPID = new PIDController(shooterFrontP, shooterFrontI, shooterFrontD);
    ShootFrontPID.setTolerance(10);

    ShootBackPID = new PIDController(shooterBackP, shooterBackI, shooterBackD);
    ShootBackPID.setTolerance(100);

    SmartDashboard.putNumber("low Shooter speed", 1000);

    timer = new Timer();

    dataLogged = false;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //change value once redetemined
    rpm = 900; 

      // powerFront = -ShootFrontPID.calculate(rpm - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonTwo());
      // Robot.m_ShooterSubsystem.setPowerFront(powerFront);
      Robot.m_ShooterSubsystem.setVelocityFront(rpm);

      // powerBack = -ShootBackPID.calculate((rpm * 1.15) - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonOne());
      // Robot.m_ShooterSubsystem.setPowerBack(powerBack);
      Robot.m_ShooterSubsystem.setVelocityBack(rpm);

      if(timer.hasElapsed(0.3)){ //&& ShootBackPID.atSetpoint()){
        if (!dataLogged) {
          dataLogged = true;
          System.out.println(String.format("Distance, %.2f, rpm, %d", 69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180), (int)rpm));
        }
        Robot.m_IndexSubsystem.setIndex(0.30);
        Robot.m_IndexSubsystem.setConveyor(-1);
        if (timer.hasElapsed(1)){
          Robot.m_IndexSubsystem.setConveyor(0);
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
