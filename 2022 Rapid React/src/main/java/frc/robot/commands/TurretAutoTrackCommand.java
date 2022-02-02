// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class TurretAutoTrackCommand extends CommandBase {
  public boolean hasTarget;
  public boolean reachedTarget;
  /** Creates a new TurretAutoTrackCommand. */
  public TurretAutoTrackCommand() {


    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.m_shootervision.getTarget()){
      hasTarget = true;
      if(Robot.m_shootervision.getX() - Robot.m_driveSubsystem.getAngle() > 0){
        Robot.m_TurretSubsystem.setTurretSpeed(-0.4);
      } else if(Robot.m_shootervision.getX() - Robot.m_driveSubsystem.getAngle() < 0){
        Robot.m_TurretSubsystem.setTurretSpeed(0.4);
      } else
        reachedTarget = true; 
    }
    else hasTarget = false;
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
