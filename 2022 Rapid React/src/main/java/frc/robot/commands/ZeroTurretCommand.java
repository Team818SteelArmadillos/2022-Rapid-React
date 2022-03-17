package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class ZeroTurretCommand extends CommandBase {
  
  public ZeroTurretCommand() {
   
  }

  @Override
  public void initialize() {
    Robot.m_TurretSubsystem.setTurretSpeed(0);
  }

  @Override
  public void execute() {
    if (Robot.m_TurretSubsystem.getCurrentTurretPosition() > 1)
      Robot.m_TurretSubsystem.setTurretSpeed(-0.3);
    else if (Robot.m_TurretSubsystem.getCurrentTurretPosition() < -1)
      Robot.m_TurretSubsystem.setTurretSpeed(0.3);
    else 
      Robot.m_TurretSubsystem.setTurretSpeed(0);
  } 

  @Override
  public void end(boolean interrupted) {
    Robot.m_TurretSubsystem.setTurretSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(Robot.m_TurretSubsystem.getCurrentTurretPosition()) < 500;
  }
}
