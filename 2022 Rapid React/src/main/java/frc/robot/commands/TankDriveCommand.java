package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TankDriveCommand extends CommandBase {

  boolean prevGearButton;
  boolean dynambreak;
  boolean prevGear;

 public TankDriveCommand() {
    addRequirements(Robot.m_driveSubsystem);
  } 

  @Override
  public void initialize() {
    prevGearButton = false;
    dynambreak = false;
    Robot.m_driveSubsystem.shift(false);
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  @Override
  public void execute() {
    Robot.m_driveSubsystem.setBothMotors(Robot.m_oi.getleftYAxis(), Robot.m_oi.getrightYAxis());
    
    if(Robot.m_oi.getRightJoystick2() && !prevGearButton){
      Robot.m_driveSubsystem.shift(!Robot.m_driveSubsystem.currentGear());
    }

    prevGearButton = Robot.m_oi.getRightJoystick2();
  

  //   if(Robot.m_oi.getRightJoystick2()){
  //     if(!prevGearButton){
  //       prevGearButton = Robot.m_oi.getRightJoystick2();
  //       Robot.m_driveSubsystem.shift(Robot.m_driveSubsystem.currentGear());
  //     }else{
  //     Robot.m_driveSubsystem.shift(!Robot.m_driveSubsystem.currentGear());
  //   }
  // }
    SmartDashboard.putBoolean("Shift", Robot.m_driveSubsystem.currentGear());
    SmartDashboard.putNumber("Angle", Robot.m_driveSubsystem.getAngle());

    if(Robot.m_oi.getRightJoystick5()){
      if(!dynambreak){
        dynambreak=true;
        Robot.m_driveSubsystem.resetEncoders();
        prevGear = Robot.m_driveSubsystem.currentGear();
        Robot.m_driveSubsystem.shift(true);
      }

      SmartDashboard.putNumber("shift low", 2);
      Robot.m_driveSubsystem.setBreak();
    }else{
      if (dynambreak) {
        dynambreak = false;
        Robot.m_driveSubsystem.shift(prevGear);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
