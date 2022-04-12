
package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DynamicBraking extends CommandBase {

  public DynamicBraking(){
    addRequirements(Robot.m_driveSubsystem);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_driveSubsystem.shift(true);
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_driveSubsystem.resetEncoders();
    // Robot.m_driveSubsystem.DrivePIDLeft.setD(0.05);
    // Robot.m_driveSubsystem.DrivePIDLeft.setTolerance(0.5);
    // Robot.m_driveSubsystem.DrivePIDRight.setD(0.05);
    // Robot.m_driveSubsystem.DrivePIDRight.setTolerance(0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

   Robot.m_driveSubsystem.setBreak();
   SmartDashboard.putNumber("Right Distance", Robot.m_driveSubsystem.getRightPosition());
   SmartDashboard.putNumber("Left Distance", Robot.m_driveSubsystem.getLeftPosition());
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.shift(false);
    Robot.m_driveSubsystem.setBothMotors(0);
    // Robot.m_driveSubsystem.DrivePIDLeft.setD(0);
    // Robot.m_driveSubsystem.DrivePIDRight.setD(0);
    // Robot.m_driveSubsystem.DrivePIDLeft.setTolerance(2);
    // Robot.m_driveSubsystem.DrivePIDRight.setTolerance(2);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}