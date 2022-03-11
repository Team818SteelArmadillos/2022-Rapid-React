package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class ZeroTurretCommand extends CommandBase {
  /** Creates a new TurretCommand. */
  public ZeroTurretCommand() {
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
    if (Robot.m_TurretSubsystem.getCurrentTurretPosition() > 1)
      Robot.m_TurretSubsystem.setTurretSpeed(0.3);
    else if (Robot.m_TurretSubsystem.getCurrentTurretPosition() < -1)
      Robot.m_TurretSubsystem.setTurretSpeed(-0.3);
    else 
      Robot.m_TurretSubsystem.setTurretSpeed(0);
  } 

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_TurretSubsystem.setTurretSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(Robot.m_TurretSubsystem.getCurrentTurretPosition()) < 1;
  }
}
