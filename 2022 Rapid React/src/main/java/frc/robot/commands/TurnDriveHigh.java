package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnDriveHigh extends CommandBase {
  
  double power;
  PIDController anglePID;
  double turnAngle;
  Timer timer;
  public TurnDriveHigh(double angle){
    turnAngle = angle;
    timer = new Timer();
    // SmartDashboard.putNumber("Target Angle", 90);
    // SmartDashboard.putNumber("p", 0.01);
    // SmartDashboard.putNumber("i", 0.0009);
    // SmartDashboard.putNumber("d", 0.0046);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    Robot.m_driveSubsystem.setBothMotors(0);
    anglePID = new PIDController(0.01, 0, 0.0001);
    anglePID.setTolerance(10);

    // turnAngle = SmartDashboard.getNumber("Target Angle", 0);
    // Robot.m_driveSubsystem.resetGyro();
  }

  @Override

  public void execute() {
    SmartDashboard.putNumber( "Angle" , Robot.m_driveSubsystem.getAngle());
    power = MathUtil.clamp(anglePID.calculate(turnAngle - Robot.m_driveSubsystem.getAngle()), -0.6, 0.6);
    if (Math.abs(power) < 0.1) {
      if (power > 0) power += 0.07;
      else power -= 0.07;
    }
    // anglePID.setPID(SmartDashboard.getNumber("p", 0), SmartDashboard.getNumber("i", 0), SmartDashboard.getNumber("d", 0));
    
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
    return ((anglePID.atSetpoint() && Math.abs(Robot.m_driveSubsystem.getLeftVelocity()) < 0.05)|| timer.hasElapsed(0.55));
    
  }
}
