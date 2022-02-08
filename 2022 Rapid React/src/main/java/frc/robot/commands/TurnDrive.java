package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnDrive extends CommandBase {
  
  double turnAngle;
  public TurnDrive(double angle){
    turnAngle = angle;
  }


  @Override
  public void initialize() {
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_driveSubsystem.resetGyro();
  }

  @Override

  public void execute() {
    if(turnAngle > 0){
      Robot.m_driveSubsystem.shift(false);
      Robot.m_driveSubsystem.setRightMotors(0.25);
      Robot.m_driveSubsystem.setLeftMotors(-0.25);
    }
    else if(turnAngle < 0){
      Robot.m_driveSubsystem.setLeftMotors(0.25);
      Robot.m_driveSubsystem.setRightMotors(-0.25);
    }
  }

  @Override

  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_driveSubsystem.resetGyro();
  }

  @Override
  public boolean isFinished() {
    return Math.abs(turnAngle) - 15 < Math.abs(Robot.m_driveSubsystem.getAngle());
  }
}
