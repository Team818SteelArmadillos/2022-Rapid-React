package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ElevatorCommand extends CommandBase {

  int ElevatorHeightIndex;
  boolean PreviousUp;
  boolean PreviousDown;
  DriverStation driverStation;
  Timer timer = new Timer();

  public ElevatorCommand() {
    addRequirements(Robot.m_ElevatorSubsystem);
    //says which one of these heights we want to go too
    ElevatorHeightIndex = 0;
    
    PreviousDown = false;
    PreviousUp = false;
    timer = new Timer();
  }

  @Override
  public void initialize() {
    Robot.m_ElevatorSubsystem.setDynamicPistons(1);
    Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
    Robot.m_ElevatorSubsystem.setStaticPistons(-1);
    Robot.m_ElevatorSubsystem.setElevatorMotor(0);
    timer.reset();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (Robot.m_oi.getDPadUp() && Robot.m_ElevatorSubsystem.getEncoderPosition() < (582063 - 10000)){
        Robot.m_ElevatorSubsystem.setStaticPistons(1);
        if (Robot.m_oi.getDPadUp() && !PreviousUp){
          Robot.m_ElevatorSubsystem.setRatchetPiston(1);
          PreviousUp = true;
          timer.start();
        } else if (PreviousUp) {
            if (timer.hasElapsed(0.1) && Robot.m_oi.getDPadUp()) {
              Robot.m_ElevatorSubsystem.setElevatorMotor(1);
            } else if (!Robot.m_oi.getDPadUp()) {
              PreviousUp = false;
              timer.stop();
              timer.reset();
            }
        }
    } else if (Robot.m_oi.getDPadUp()) {
      Robot.m_ElevatorSubsystem.setElevatorMotor(0);
      Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
    }

    else if (Robot.m_oi.getDPadRight() && Robot.m_ElevatorSubsystem.getEncoderPosition() > (94713)){
      Robot.m_ElevatorSubsystem.setElevatorMotor(-1);

    }    
    else if (Robot.m_oi.getDPadDown() && Robot.m_ElevatorSubsystem.getEncoderPosition() < (582063 - 10000)){
      Robot.m_ElevatorSubsystem.setStaticPistons(1);
      if (Robot.m_oi.getDPadUp() && !PreviousUp){
        Robot.m_ElevatorSubsystem.setRatchetPiston(1);
        PreviousUp = true;
        timer.start();
      } else if (PreviousUp) {
          if (timer.hasElapsed(0.1) && Robot.m_oi.getDPadUp()) {
            Robot.m_ElevatorSubsystem.setElevatorMotor(1);
          } else if (!Robot.m_oi.getDPadUp()) {
            PreviousUp = false;
            timer.stop();
            timer.reset();
          }
      }
  } else if (Robot.m_oi.getDPadDown()) {
    Robot.m_ElevatorSubsystem.setElevatorMotor(0);
    Robot.m_ElevatorSubsystem.setDynamicPistons(-1);
    Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
  }

else if (Robot.m_oi.getDpadLeft() && Robot.m_ElevatorSubsystem.getEncoderPosition() > (479736))
  Robot.m_ElevatorSubsystem.setElevatorMotor(-1);
  else if (Robot.m_oi.getDpadLeft() && Robot.m_ElevatorSubsystem.getEncoderPosition() > (310268)) {
    Robot.m_ElevatorSubsystem.setDynamicPistons(1);
    Robot.m_ElevatorSubsystem.setElevatorMotor(-1);
  } else {
    Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
    Robot.m_ElevatorSubsystem.setElevatorMotor(0);
  }


    PreviousUp = Robot.m_oi.getDPadUp();
    PreviousDown = Robot.m_oi.getDPadDown();


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
