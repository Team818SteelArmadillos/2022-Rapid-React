
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class TurretCommand extends CommandBase {
 
  public TurretCommand() {
    
  }


  @Override
  public void initialize() {
    Robot.m_TurretSubsystem.setTurretSpeed(0);
  }

 
  @Override
  public void execute() {
    if (Robot.m_shootervision.getTarget())  {
      Robot.m_TurretSubsystem.setTurretSpeed(-Robot.m_shootervision.getX() / 40);
    } else {
      Robot.m_TurretSubsystem.setTurretSpeed(-Robot.m_oi.getgamepadleftXAxis()* 0.25);
    }
  } 

  @Override
  public void end(boolean interrupted) {
    Robot.m_TurretSubsystem.setTurretSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
