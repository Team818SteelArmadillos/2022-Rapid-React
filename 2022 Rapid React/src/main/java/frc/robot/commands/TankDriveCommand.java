package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TankDriveCommand extends CommandBase {

  boolean prevGearButton;

 public TankDriveCommand() {
    addRequirements(Robot.m_driveSubsystem);
  } 

  @Override
  public void initialize() {
    prevGearButton = false;
    Robot.m_driveSubsystem.shift(false);
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.m_driveSubsystem.setBothMotors(Robot.m_oi.getleftYAxis(), Robot.m_oi.getrightYAxis());
    if(Robot.m_oi.shiftGears() && !prevGearButton){
      Robot.m_driveSubsystem.shift(!Robot.m_driveSubsystem.currentGear());
    }
    prevGearButton = Robot.m_oi.shiftGears();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_driveSubsystem.setBothMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
