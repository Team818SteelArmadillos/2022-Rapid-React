package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnDrive extends CommandBase {
  
  double p = 0.01;
  double i = 0;
  double d = 0;
  double power;
  PIDController anglePID;
  double turnAngle;
  public TurnDrive(double angle){
    turnAngle = angle;
  }


  @Override
  public void initialize() {
   p = SmartDashboard.getNumber("p", 0);
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_driveSubsystem.resetGyro();
    anglePID = new PIDController(p, i, d);
    anglePID.setTolerance(1.5);
  }

  @Override

  public void execute() {
    SmartDashboard.putNumber( "Angle" , Robot.m_driveSubsystem.getAngle());
    power = MathUtil.clamp(anglePID.calculate(turnAngle - Robot.m_driveSubsystem.getAngle()), -0.2, 0.2);
    Robot.m_driveSubsystem.setLeftMotors(power);
    Robot.m_driveSubsystem.setRightMotors(-power);
    SmartDashboard.putNumber( "Power" , power);
  }

  @Override

  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_driveSubsystem.resetGyro();
  }

  @Override
  public boolean isFinished() {
    //return anglePID.atSetpoint();
    return false;
  }
}
