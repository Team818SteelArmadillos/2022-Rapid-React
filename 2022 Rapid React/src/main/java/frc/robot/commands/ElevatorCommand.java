package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ElevatorCommand extends CommandBase {

  Timer pistonTimer;
  DriverStation driverStation;

  public ElevatorCommand(int pistonVal) {
    addRequirements(Robot.m_ElevatorSubsystem);
    pistonTimer = new Timer();
  }

  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
  }
}
