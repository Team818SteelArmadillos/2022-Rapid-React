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

  /** Creates a new SpoolShooterCommand. */
  public SpoolShooterCommand() {
    rpm = 0;
    ShootPID = new PIDController( p, i, d);
    ShootPID.setTolerance(10);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("Set Shooter speed", 0);
    Robot.m_ShooterSubsystem.setPower(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.m_oi.getLeftBumper() || Robot.m_oi.getRightBumper()){
      Robot.m_ShooterSubsystem.setPower(10000);
    }else{
      Robot.m_ShooterSubsystem.setPower(0);
    }
    
    SmartDashboard.putNumber("Set Shooter speed", Robot.m_ShooterSubsystem.getCurrentShooterSpeed());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_ShooterSubsystem.setPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
