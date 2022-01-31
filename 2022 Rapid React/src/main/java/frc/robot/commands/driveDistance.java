
package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class driveDistance extends CommandBase {

  PIDController PIDLeft;
  PIDController PIDRight;
  double PIDLeftOutput;
  double distance;

  public driveDistance(double dist){
    PIDLeft = new PIDController(0.05, 0, 0);
    PIDRight = new PIDController(0.05, 0, 0);
    PIDLeft.setTolerance(2);
    addRequirements(Robot.m_driveSubsystem);
    distance = dist;
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_driveSubsystem.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   Robot.m_driveSubsystem.shift(false);
   PIDLeftOutput = MathUtil.clamp(PIDLeft.calculate(distance + Robot.m_driveSubsystem.getLeftPosition()), -0.5, 0.5);
   Robot.m_driveSubsystem.setLeftMotors(PIDLeftOutput);
   Robot.m_driveSubsystem.setRightMotors(MathUtil.clamp(PIDRight.calculate(distance + Robot.m_driveSubsystem.getLeftPosition()), -0.5, 0.5));
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
    return PIDLeft.atSetpoint() && PIDRight.atSetpoint();
  }
}
