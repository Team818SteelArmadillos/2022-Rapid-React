package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class IntakeCommand extends CommandBase {

  public IntakeCommand() {
    addRequirements(Robot.m_IntakeSubsystem);
    addRequirements(Robot.m_IndexSubsystem);

  }

  @Override
  public void initialize() {
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IntakeSubsystem.setIntakePosition(1);
    Robot.m_IntakeSubsystem.setIntakeMotor(0);
    
  }

  @Override
  public void execute() {
    
    //if start button is held runs everything backwards to expel the balls
    if(Robot.m_oi.getStartButton()){
      Robot.m_IntakeSubsystem.setIntakePosition(0.5);
      Robot.m_IntakeSubsystem.setIntakeMotor(-1);
      Robot.m_IndexSubsystem.setConveyor(-1);
      Robot.m_IndexSubsystem.setIndex(-0.3);
    }

    //if both sensors are sensed or if neither op button is held, set everything to closed/not running
    else if ((Robot.m_IndexSubsystem.SensorFront() && Robot.m_IndexSubsystem.SensorBack()) || (!Robot.m_oi.getXButton() && !Robot.m_oi.getStartButton())) {
      Robot.m_IntakeSubsystem.setIntakePosition(1);
      Robot.m_IntakeSubsystem.setIntakeMotor(0);
      Robot.m_IndexSubsystem.setConveyor(0);
      Robot.m_IndexSubsystem.setIndex(0);
    
    }

  //if x button is held and no balls run everything
    else if (Robot.m_oi.getXButton() && !Robot.m_IndexSubsystem.SensorFront() && !Robot.m_IndexSubsystem.SensorBack()) {
      Robot.m_IntakeSubsystem.setIntakePosition(0.5);
      Robot.m_IntakeSubsystem.setIntakeMotor(1);
      Robot.m_IndexSubsystem.setConveyor(1);
      Robot.m_IndexSubsystem.setIndex(0.3);

    }
    
    //if x button and ball in back run everything but index
    else if (Robot.m_oi.getXButton() && !Robot.m_IndexSubsystem.SensorFront()) {
      Robot.m_IntakeSubsystem.setIntakePosition(0.5);
      Robot.m_IntakeSubsystem.setIntakeMotor(1);
      Robot.m_IndexSubsystem.setConveyor(1);
      Robot.m_IndexSubsystem.setIndex(0);
  
    }
    
    //if x button and ball in front but not back everything
    else if (Robot.m_oi.getXButton() && Robot.m_IndexSubsystem.SensorFront() && !Robot.m_IndexSubsystem.SensorBack()) {
      Robot.m_IntakeSubsystem.setIntakePosition(0.5);
      Robot.m_IntakeSubsystem.setIntakeMotor(1);
      Robot.m_IndexSubsystem.setIndex(0.3);
      Robot.m_IndexSubsystem.setConveyor(1);

    } 

    //manual spool of index and conveyor
    if (Robot.m_oi.getBackButton()) {
      Robot.m_IndexSubsystem.setConveyor(1);
      Robot.m_IndexSubsystem.setIndex(0.5);
    }

    SmartDashboard.putBoolean("Position 1", Robot.m_IndexSubsystem.SensorFront());
    SmartDashboard.putBoolean("Position 2", Robot.m_IndexSubsystem.SensorBack());
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_IntakeSubsystem.setIntakePosition(1);
    Robot.m_IntakeSubsystem.setIntakeMotor(0);
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
