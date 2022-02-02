package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import static frc.robot.Constants.ElevatorConstants.*;

public class ElevatorCommandTesting extends CommandBase {

  boolean PreviousUp;
  boolean PreviousDown;
  DriverStation driverStation;

  public ElevatorCommandTesting() {
    addRequirements(Robot.m_ElevatorSubsystem);
    //says which one of these heights we want to go too
    
    PreviousDown = false;
    PreviousUp = false;
  }

  @Override
  public void initialize() {
    Robot.m_ElevatorSubsystem.setDynamicPistons(-1);
    Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
    Robot.m_ElevatorSubsystem.setStaticPistons(-1);
    Robot.m_ElevatorSubsystem.setElevatorMotor(0);
    Robot.m_ElevatorSubsystem.resetEncoders();


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
    if (Robot.m_oi.getElevatorOut()){
      Robot.m_ElevatorSubsystem.setDynamicPistons(1);
    } else if (Robot.m_oi.getElevatorIn()){
      Robot.m_ElevatorSubsystem.setDynamicPistons(-1);
    }
    
    if (Robot.m_oi.getYButton()){
      Robot.m_ElevatorSubsystem.setStaticPistons(1);
    } 

    if (Robot.m_oi.getElevatorUp()){
      Robot.m_ElevatorSubsystem.setElevatorMotor(0.5);
    } 

    if (Robot.m_oi.getElevatorDown()){
      Robot.m_ElevatorSubsystem.setElevatorMotor(-0.5);
    }

    SmartDashboard.putNumber("Elevator Position", Robot.m_ElevatorSubsystem.getEncoderPosition());
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_ElevatorSubsystem.setElevatorMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
