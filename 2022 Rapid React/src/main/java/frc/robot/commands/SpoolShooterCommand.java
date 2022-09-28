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
  double rpm, powerFront, powerBack;
  PIDController ShootFrontPID, ShootBackPID;

  private double dist[][];

  double ShooterMotorspeed;

  public SpoolShooterCommand() {
    addRequirements(Robot.m_ShooterSubsystem, Robot.m_shootervision, Robot.m_TurretSubsystem, Robot.m_IntakeSubsystem, Robot.m_IndexSubsystem);

    ShootFrontPID = new PIDController(shooterFrontP, shooterFrontI, shooterFrontD);
    ShootFrontPID.setTolerance(15);

    ShootBackPID = new PIDController(shooterBackP, shooterBackI, shooterBackD);
    ShootBackPID.setTolerance(100);

    dist = new double[14][2];


    dist[0][0] = 42;
    dist[0][1] = 1500 /*1430*/;

    dist[1][0] = 57;
    dist[1][1] = 1600 /*1550*/;

    dist[2][0] = 70;
    dist[2][1] = 1625;

    dist[3][0] = 81;
    dist[3][1] = 1750;

    dist[4][0] = 84;
    dist[4][1] = 1800 /*1780*/;

    dist[5][0] = 94;
    dist[5][1] = 1850 /*1820*/;

    dist[6][0] = 98;
    dist[6][1] = 1850 /*1830*/;

    dist[7][0] = 102;
    dist[7][1] = 1950;

    dist[8][0] = 105.5;
    dist[8][1] = 2050;

    dist[9][0] = 110;
    dist[9][1] = 2250;

    dist[10][0] = 121;
    dist[10][1] = 2500;

    dist[11][0] = 128.5;
    dist[11][1] = 2850;
    
    dist[12][0] = 140;
    dist[12][1] = 3225;

    dist[13][0] = 160;
    dist[13][1] = 3450;
    
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("Set Shooter speed", 0);
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_ShooterSubsystem.setPowerBack(0);
  }

  @Override
  public void execute() {

    rpm = shooterSpeed(69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180));

    if(Robot.m_oi.getRightBumper()){

      if(Robot.m_shootervision.getTarget()){
      powerFront = -ShootFrontPID.calculate(rpm - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonTwo());
      Robot.m_ShooterSubsystem.setPowerFront(powerFront);

      powerBack = -ShootBackPID.calculate((rpm * 1.15) - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonOne());
      Robot.m_ShooterSubsystem.setPowerBack(powerBack);

     }else{

      rpm = 1650;

      powerFront = -ShootFrontPID.calculate(rpm - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonTwo());
      Robot.m_ShooterSubsystem.setPowerFront(1);

      powerBack = -ShootBackPID.calculate((rpm * 1.15) - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonOne());
      Robot.m_ShooterSubsystem.setPowerBack(1);

    }
  }
    
    if(Robot.m_oi.getBackButton()){
      Robot.m_IndexSubsystem.setIndex(0.5);
    }else{
      Robot.m_IndexSubsystem.setIndex(0);
    }
  }
  

  // private double shooterSpeed(double d) {
  //   return 0;
  // }

  @Override
  public void end(boolean interrupted) {
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_ShooterSubsystem.setPowerBack(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private double shooterSpeed(double distance) {
    if (distance <= dist[0][0] || distance > dist[13][0]) {
      return 1650;
    }
    int i = 0;
    while (dist[i][0] < distance) i++;
     return dist[i-1][1] + (distance - dist[i-1][0]) * (dist[i][1] - dist[i-1][1])/(dist[i][0] - dist[i-1][0]);
  }
}

