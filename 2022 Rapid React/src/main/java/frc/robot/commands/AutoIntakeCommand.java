// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoIntakeCommand extends CommandBase {
  boolean toggle;
  /** Creates a new AutoIntakeCommand. */
  public AutoIntakeCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0); //i added this
    Robot.m_IntakeSubsystem.setIntakePosition(0.5);
    Robot.m_IntakeSubsystem.setIntakeMotor(0);
    toggle = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if(Robot.m_IndexSubsystem.SensorBack() && Robot.m_IndexSubsystem.SensorFront()){
      Robot.m_IndexSubsystem.setConveyor(0);
      Robot.m_IndexSubsystem.setIndex(0); //i added this
      Robot.m_IntakeSubsystem.setIntakePosition(0.5);
      Robot.m_IntakeSubsystem.setIntakeMotor(0);
    }
    else if(toggle == true && !Robot.m_IndexSubsystem.SensorFront() && !Robot.m_IndexSubsystem.SensorBack()){
      Robot.m_IntakeSubsystem.setIntakePosition(1);
      Robot.m_IntakeSubsystem.setIntakeMotor(0.5);
      Robot.m_IndexSubsystem.setConveyor(1); //added
      Robot.m_IndexSubsystem.setIndex(0.3); //added
    }
    else if (toggle == true && !Robot.m_IndexSubsystem.SensorFront()){
      Robot.m_IntakeSubsystem.setIntakePosition(1);
      Robot.m_IntakeSubsystem.setIntakeMotor(0.5);
      Robot.m_IndexSubsystem.setConveyor(1); //added
      Robot.m_IndexSubsystem.setIndex(0); //added
   
    } else if(toggle == true && Robot.m_IndexSubsystem.SensorFront() && !Robot.m_IndexSubsystem.SensorBack()){
      Robot.m_IndexSubsystem.setConveyor(1);
      Robot.m_IndexSubsystem.setIndex(0.3); //i added this
      Robot.m_IntakeSubsystem.setIntakePosition(1);
      Robot.m_IntakeSubsystem.setIntakeMotor(0.5);

    } 

    // if(Robot.m_IndexSubsystem.SensorFront()){
    //   Robot.m_IndexSubsystem.setConveyor(0.5);
    //   toggle = false;
    // }
    
    // if(Robot.m_IndexSubsystem.SensorBack()){
    //   Robot.m_IndexSubsystem.setConveyor(0);
    //   Robot.m_IntakeSubsystem.setIntakePosition(0.5);
    // }

  }

  /*
  if (Robot.m_IndexSubsystem.SensorFront() && Robot.m_IndexSubsystem.SensorBack()) {
    Robot.m_IntakeSubsystem.setIntakePosition(1);
    Robot.m_IntakeSubsystem.setIntakeMotor(0);
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
  
  }
  else if (Robot.m_oi.getXButton() && !Robot.m_IndexSubsystem.SensorFront() && !Robot.m_IndexSubsystem.SensorBack()) {
    Robot.m_IntakeSubsystem.setIntakePosition(0.5);
    Robot.m_IntakeSubsystem.setIntakeMotor(1);
    Robot.m_IndexSubsystem.setConveyor(1);
    Robot.m_IndexSubsystem.setIndex(0.3);

    }else if (Robot.m_oi.getXButton() && !Robot.m_IndexSubsystem.SensorFront()) {
      Robot.m_IntakeSubsystem.setIntakePosition(0.5);
      Robot.m_IntakeSubsystem.setIntakeMotor(1);
      Robot.m_IndexSubsystem.setConveyor(1);
      Robot.m_IndexSubsystem.setIndex(0);
  
      }
    
    else if (Robot.m_oi.getXButton() && Robot.m_IndexSubsystem.SensorFront() && !Robot.m_IndexSubsystem.SensorBack()) {
    Robot.m_IntakeSubsystem.setIntakePosition(0.5);
    Robot.m_IntakeSubsystem.setIntakeMotor(1);
    Robot.m_IndexSubsystem.setIndex(0.3);
    Robot.m_IndexSubsystem.setConveyor(1);
    toggle = true;

    } 
*/

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
