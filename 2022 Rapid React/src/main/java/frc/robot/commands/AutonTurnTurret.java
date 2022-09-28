// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class AutonTurnTurret extends CommandBase {

  double turret;
  /** Creates a new AutonTurnTurret. */
  public AutonTurnTurret(double turret) {
    addRequirements(Robot.m_ShooterSubsystem, Robot.m_shootervision, Robot.m_TurretSubsystem, Robot.m_IntakeSubsystem, Robot.m_IndexSubsystem);
    this.turret = turret;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_TurretSubsystem.setTurretSpeed(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.m_TurretSubsystem.setTurretSpeed(turret);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
