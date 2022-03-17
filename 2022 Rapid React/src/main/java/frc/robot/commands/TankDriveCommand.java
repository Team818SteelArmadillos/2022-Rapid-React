package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TankDriveCommand extends CommandBase {

  boolean prevGearButton;
  boolean dynambreak;

 public TankDriveCommand() {
    addRequirements(Robot.m_driveSubsystem);
  } 

  @Override
  public void initialize() {
    prevGearButton = false;
    dynambreak=false;
    Robot.m_driveSubsystem.shift(false);
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.m_driveSubsystem.setBothMotors(Robot.m_oi.getleftYAxis(), Robot.m_oi.getrightYAxis());
    if(Robot.m_oi.getRightJoystick2() && !prevGearButton){
      Robot.m_driveSubsystem.shift(!Robot.m_driveSubsystem.currentGear());
    }
    prevGearButton = Robot.m_oi.getRightJoystick2();
    
    SmartDashboard.putNumber("Angle", Robot.m_driveSubsystem.getAngle());

    if(Robot.m_oi.getRightJoystick5()){
      if(!dynambreak){
        dynambreak=true;
        Robot.m_driveSubsystem.resetEncoders();
        Robot.m_driveSubsystem.shift(true);
        Robot.m_driveSubsystem.DrivePIDLeft.setD(0.05);
        Robot.m_driveSubsystem.DrivePIDRight.setD(0.05);
      }

      SmartDashboard.putNumber("shift low", 2);
      Robot.m_driveSubsystem.setBreak();
    }else{
      dynambreak=false;
      Robot.m_driveSubsystem.DrivePIDLeft.setD(0);
      Robot.m_driveSubsystem.DrivePIDRight.setD(0);
      Robot.m_driveSubsystem.shift(false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.setBothMotors(0);
    Robot.m_driveSubsystem.DrivePIDLeft.setD(0);
    Robot.m_driveSubsystem.DrivePIDRight.setD(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
