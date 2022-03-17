package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnDrive extends CommandBase {
  
  double power;
  PIDController anglePID;
  double turnAngle;
  public TurnDrive(double angle){
    turnAngle = angle;
  }

  @Override
  public void initialize() {
    Robot.m_driveSubsystem.setBothMotors(0);
    anglePID = new PIDController(0.0185, 0.0009, 0.0046);
    anglePID.setTolerance(2);
  }

  @Override

  public void execute() {
    SmartDashboard.putNumber( "Angle" , Robot.m_driveSubsystem.getAngle());
    power = MathUtil.clamp(anglePID.calculate(turnAngle - Robot.m_driveSubsystem.getAngle()), -0.6, 0.6);
    
    Robot.m_driveSubsystem.setLeftMotors(power);
    Robot.m_driveSubsystem.setRightMotors(-power);
    SmartDashboard.putNumber( "Power" , power);
  }

  @Override

  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  @Override
  public boolean isFinished() {
    return (anglePID.atSetpoint() && Math.abs(Robot.m_driveSubsystem.getLeftVelocity()) < 0.05);
  
  }
}
