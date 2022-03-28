package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ElevatorCommandTesting extends CommandBase {

  boolean PreviousUp;
  boolean PreviousDown;
  DriverStation driverStation;
  Timer timer = new Timer();

  public ElevatorCommandTesting() {
    addRequirements(Robot.m_ElevatorSubsystem);
    //says which one of these heights we want to go too
    
    PreviousDown = false;
    PreviousUp = false;
  }

  @Override
  public void initialize() {
    // Robot.m_ElevatorSubsystem.setDynamicPistons(-1);
    Robot.m_ElevatorSubsystem.setRatchetPiston(1);
    // Robot.m_ElevatorSubsystem.setStaticPistons(-1);
    Robot.m_ElevatorSubsystem.setElevatorMotor(0);
    timer.reset();
    Robot.m_ElevatorSubsystem.limitSwitch = new DigitalInput(7);

    // Robot.m_ElevatorSubsystem.elevatorMotorOne.setSelectedSensorPosition(0);


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
    // if (Robot.m_oi.getDPadRight()){
    //   Robot.m_ElevatorSubsystem.setDynamicPistons(1);
    // } else if (Robot.m_oi.getDpadLeft()){
    //   Robot.m_ElevatorSubsystem.setDynamicPistons(-1);
    // }
    
    // if (Robot.m_oi.getYButton()){
    //   Robot.m_ElevatorSubsystem.setStaticPistons(1);
    // } 

    if (Robot.m_oi.getDPadUp() && !PreviousUp){
      // Robot.m_ElevatorSubsystem.setRatchetPiston(1);
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
    else if (Robot.m_oi.getDPadDown()){
      // Robot.m_ElevatorSubsystem.setRatchetPiston(1);
      Robot.m_ElevatorSubsystem.setElevatorMotor(-1);
    } 
    else if(Robot.m_ElevatorSubsystem.getlimitSwitch()){
      Robot.m_ElevatorSubsystem.resetEncoders();
      Robot.m_ElevatorSubsystem.setElevatorMotor(0);
    }
    else {
      Robot.m_ElevatorSubsystem.setElevatorMotor(0);
      // Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
    }

    SmartDashboard.putNumber("Elevator Position", Robot.m_ElevatorSubsystem.getEncoderPosition());
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_ElevatorSubsystem.setElevatorMotor(0);
    Robot.m_ElevatorSubsystem.setRatchetPiston(-1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
