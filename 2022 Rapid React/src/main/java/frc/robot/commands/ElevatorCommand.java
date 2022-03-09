package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import static frc.robot.Constants.ElevatorConstants.*;

public class ElevatorCommand extends CommandBase {

  int ElevatorHeightIndex;
  boolean PreviousUp;
  boolean PreviousDown;
  DriverStation driverStation;

  public ElevatorCommand() {
    addRequirements(Robot.m_ElevatorSubsystem);
    //says which one of these heights we want to go too
    ElevatorHeightIndex = 0;
    
    PreviousDown = false;
    PreviousUp = false;
  }

  @Override
  public void initialize() {
    Robot.m_ElevatorSubsystem.setDynamicPistons(-1);
    Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
    Robot.m_ElevatorSubsystem.setStaticPistons(-1);
    Robot.m_ElevatorSubsystem.setElevatorMotor(0);
    // Robot.m_ElevatorSubsystem.resetEncoders();


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

    if (Robot.m_oi.getElevatorUp() && ElevatorHeightIndex < ElevatorHeights.length-1 && !PreviousUp){
      ElevatorHeightIndex += 1;
    } 

    if (Robot.m_oi.getElevatorDown() && ElevatorHeightIndex > 0 && !PreviousDown){
      ElevatorHeightIndex -= 1;
    }

    PreviousUp = Robot.m_oi.getElevatorUp();
    PreviousDown = Robot.m_oi.getElevatorDown();

    Robot.m_ElevatorSubsystem.setElevatorMotorPostion(ElevatorHeightIndex);

    // from my understanidn in constants set values of encoders from testing 1. for height of bar 2 to climb 2. for height to have lifted and have static bars be above bar 3. to go all the wya up/secure static bars 4. to climb up all the way
    
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
