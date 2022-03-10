package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoIntakeCommand extends CommandBase {
  boolean toggle;

  public AutoIntakeCommand() {
    addRequirements(Robot.m_IntakeSubsystem);
    addRequirements(Robot.m_IndexSubsystem);
    toggle = false;

  }

  @Override
  public void initialize() {
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IntakeSubsystem.setIntakePosition(1);
    Robot.m_IntakeSubsystem.setIntakeMotor(0);
    
  }

  @Override
  public void execute() {
    
    // if(toggle){
    //   toggle = !Robot.m_IndexSubsystem.SensorBack();

  if (!Robot.m_IndexSubsystem.SensorFront() && !Robot.m_IndexSubsystem.SensorBack()) {
    Robot.m_IntakeSubsystem.setIntakePosition(0.5);
    Robot.m_IntakeSubsystem.setIntakeMotor(1);
    Robot.m_IndexSubsystem.setConveyor(0.5);
    Robot.m_IndexSubsystem.setIndex(0.3);

    }else if (!Robot.m_IndexSubsystem.SensorFront()) {
      Robot.m_IntakeSubsystem.setIntakePosition(0.5);
      Robot.m_IntakeSubsystem.setIntakeMotor(1);
      Robot.m_IndexSubsystem.setConveyor(0.5);
      Robot.m_IndexSubsystem.setIndex(0);
  
      }
    
    else if (Robot.m_IndexSubsystem.SensorFront() && !Robot.m_IndexSubsystem.SensorBack()) {
    Robot.m_IntakeSubsystem.setIntakePosition(0.5);
    Robot.m_IntakeSubsystem.setIntakeMotor(1);
    Robot.m_IndexSubsystem.setIndex(0.3);
    Robot.m_IndexSubsystem.setConveyor(1);
    toggle = true;

    } 
  }

  @Override
  public void end(boolean interrupted) {

    Robot.m_IntakeSubsystem.setIntakeMotor(0);
    Robot.m_IndexSubsystem.setConveyor(0);
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
