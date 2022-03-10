// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

import static frc.robot.Constants.ShooterConstants.*;

public class LowShootManualCommand extends CommandBase {
  double rpm;
  PIDController ShootPID;
  double ShooterMotorspeed;

  public LowShootManualCommand() {
    ShootPID = new PIDController(p, i, d);
    ShootPID.setTolerance(10);
    SmartDashboard.putNumber("low Shooter speed", 1000);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_ShooterSubsystem.setPower(0);
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rpm = 1300;
      double shooterPower = ShootPID.calculate(rpm - Robot.m_ShooterSubsystem.getCurrentShooterSpeed());
      SmartDashboard.putNumber("Shooter Power", shooterPower);
      Robot.m_ShooterSubsystem.setPower(-shooterPower);

      // if (Robot.m_oi.getBButton() && Robot.m_TurretSubsystem.getCurrentTurretPosition() > 0) {
      //   Robot.m_TurretSubsystem.setTurretSpeed(-0.3);
      // } else if (Robot.m_oi.getBButton() && Robot.m_TurretSubsystem.getCurrentTurretPosition() < 0) {
      //   Robot.m_TurretSubsystem.setTurretSpeed(0.3);
      // } else {
      //   Robot.m_TurretSubsystem.setTurretSpeed(0);
      // }

      if(ShootPID.atSetpoint()){

        Robot.m_IndexSubsystem.setConveyor(0.5);
        Robot.m_IndexSubsystem.setIndex(0.3);
  

      }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_ShooterSubsystem.setPower(0);
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
