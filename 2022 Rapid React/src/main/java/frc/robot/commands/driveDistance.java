
package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDistance extends CommandBase {

  double distance, power;


  public DriveDistance(double dist){
    addRequirements(Robot.m_driveSubsystem);
    distance = dist;
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_driveSubsystem.shift(false);
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_driveSubsystem.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   power = Robot.m_driveSubsystem.setDriveMotorPostion(distance);
   SmartDashboard.putNumber("Right Distance Travled (in)", Robot.m_driveSubsystem.getRightPosition());
   SmartDashboard.putNumber("Left Distance Travled (in)", Robot.m_driveSubsystem.getLeftPosition());
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Robot.m_driveSubsystem.DrivePIDLeft.atSetpoint() && Math.abs(Robot.m_driveSubsystem.getLeftVelocity()) < 0.1);
    
  }
}
