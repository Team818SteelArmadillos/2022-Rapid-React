package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class IntakeCommand extends CommandBase {
  boolean toggle;

  public IntakeCommand() {
    addRequirements(Robot.m_IntakeSubsystem);
    addRequirements(Robot.m_IndexSubsystem);


  }

  @Override
  public void initialize() {
    Robot.m_IndexSubsystem.SetIndex(0);
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IntakeSubsystem.setIntakePosition(0.5);
    Robot.m_IntakeSubsystem.setIntakeMotor(0);
    
  }

  @Override
  public void execute() {
    if (Robot.m_oi.getXButton() && !Robot.m_IndexSubsystem.SensorFront() && !Robot.m_IndexSubsystem.SensorBack()); {
    Robot.m_IntakeSubsystem.setIntakePosition(1);
    Robot.m_IntakeSubsystem.setIntakeMotor(0.5);
    Robot.m_IndexSubsystem.setConveyor(0);

    }

    if (Robot.m_oi.getXButton() && Robot.m_IndexSubsystem.SensorFront() && !Robot.m_IndexSubsystem.SensorBack()) {
    Robot.m_IntakeSubsystem.setIntakePosition(1);
    Robot.m_IntakeSubsystem.setIntakeMotor(0.5);
    Robot.m_IndexSubsystem.setConveyor(0.5);

    }

    if (Robot.m_IndexSubsystem.SensorFront() && Robot.m_IndexSubsystem.SensorBack()) {
    Robot.m_IntakeSubsystem.setIntakePosition(0.5);
    Robot.m_IntakeSubsystem.setIntakeMotor(0);
    Robot.m_IndexSubsystem.setConveyor(0);

    }

    if (!Robot.m_oi.getXButton()) {
    Robot.m_IntakeSubsystem.setIntakePosition(0.5);
    Robot.m_IntakeSubsystem.setIntakeMotor(0);
    Robot.m_IndexSubsystem.setConveyor(0);

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
