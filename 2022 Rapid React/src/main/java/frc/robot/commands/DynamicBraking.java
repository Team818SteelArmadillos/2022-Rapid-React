
package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DynamicBraking extends CommandBase {

  PIDController PIDLeft;
  PIDController PIDRight;
  double PIDLeftOutput;

  public DynamicBraking(){
    PIDLeft = new PIDController(0.05, 0, 0);
    PIDRight = new PIDController(0.05, 0, 0);
    PIDLeft.setTolerance(2);
    addRequirements(Robot.m_driveSubsystem);
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
   Robot.m_driveSubsystem.setDriveMotorPostion(0);
   SmartDashboard.putNumber("Right Distance Travled (in)", Robot.m_driveSubsystem.getRightPosition());
   SmartDashboard.putNumber("Left Distance Travled (in)", Robot.m_driveSubsystem.getLeftPosition());
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.shift(true);
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}