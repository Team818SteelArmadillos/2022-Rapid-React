package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
<<<<<<< Updated upstream
=======
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
>>>>>>> Stashed changes
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import static frc.robot.Constants.ElevatorConstants.*;

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
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
    Robot.m_ElevatorSubsystem.resetEncoders();


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
<<<<<<< Updated upstream
  
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
=======
    
    SmartDashboard.putNumber("Elevator Position", Robot.m_ElevatorSubsystem.getEncoderPosition());

    if ((Robot.m_oi.getElevatorUp() && Robot.m_ElevatorSubsystem.getEncoderPosition() < (1373473 - 10000))||PreviousUp){
        Robot.m_ElevatorSubsystem.setStaticPistons(1);
        if ((Robot.m_oi.getElevatorUp()) && !PreviousUp){
          Robot.m_ElevatorSubsystem.setRatchetPiston(1) ;
          PreviousUp = true;
          timer.start();
        } else if (PreviousUp) {
            if (timer.hasElapsed(0.1) && (Robot.m_oi.getElevatorUp())) {
              Robot.m_ElevatorSubsystem.setElevatorMotor(1);
            } else if (!(Robot.m_oi.getElevatorUp())) {
              PreviousUp = false;
              timer.stop();
              timer.reset();
            }
        }
    } else if (Robot.m_oi.getElevatorUp()) {
      Robot.m_ElevatorSubsystem.setElevatorMotor(0);
      Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
    }

    else if ((Robot.m_oi.getElevatorLeft() && Robot.m_ElevatorSubsystem.getEncoderPosition() > (307913))||Robot.m_oi.elevatorZero()){
      Robot.m_ElevatorSubsystem.setElevatorMotor(-1);
    }
    else if ((Robot.m_oi.getElevatorDown() && Robot.m_ElevatorSubsystem.getEncoderPosition() < (1373473 - 10000))||PreviousDown||Robot.m_oi.elevatorUp()){
      if ((Robot.m_oi.getElevatorDown()||Robot.m_oi.elevatorUp()) && !PreviousDown){
        Robot.m_ElevatorSubsystem.setRatchetPiston(1);
        PreviousDown = true;
        timer.start();
      } else if (PreviousDown) {
          if (timer.hasElapsed(0.1) && (Robot.m_oi.getElevatorDown()||Robot.m_oi.elevatorUp())) {
            Robot.m_ElevatorSubsystem.setElevatorMotor(1);
          } else if (!(Robot.m_oi.getElevatorDown()||Robot.m_oi.elevatorUp())) {
            PreviousDown = false;
            timer.stop();
            timer.reset();
          }
      }
    } else if (Robot.m_oi.getElevatorDown()) {
      Robot.m_ElevatorSubsystem.setElevatorMotor(0);
      Robot.m_ElevatorSubsystem.setDynamicPistons(-1);
      Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
    }

    else if (Robot.m_oi.getElevatorRight() && Robot.m_ElevatorSubsystem.getEncoderPosition() > (1073473))
      Robot.m_ElevatorSubsystem.setElevatorMotor(-1);
    else if (Robot.m_oi.getElevatorRight() && Robot.m_ElevatorSubsystem.getEncoderPosition() > (673473)) {
      Robot.m_ElevatorSubsystem.setDynamicPistons(1);
      Robot.m_ElevatorSubsystem.setElevatorMotor(-1);
    } else {
      Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
      Robot.m_ElevatorSubsystem.setElevatorMotor(0);
    }


>>>>>>> Stashed changes

    if (Robot.m_oi.getElevatorDown() && ElevatorHeightIndex > 0 && !PreviousDown){
      ElevatorHeightIndex -= 1;
    }

    // // if (Robot.m_oi.elevatorZeroPressed() && Robot.m_ElevatorSubsystem.getEncoderPosition()>0+3000){
    //   if (Robot.m_oi.elevatorZero()){
    //     Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
    //   Robot.m_ElevatorSubsystem.setElevatorMotor(-0.5);
    // // } else if (Robot.m_oi.elevatorZeroReleased() || (Robot.m_ElevatorSubsystem.getEncoderPosition()<0+3000) &&  Robot.m_oi.elevatorZero()) {
    // } else if (!Robot.m_oi.elevatorZero() ) {
    //  Robot.m_ElevatorSubsystem.setElevatorMotor(0);
    // }

    Robot.m_ElevatorSubsystem.setElevatorMotorPostion(ElevatorHeightIndex);

    
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
