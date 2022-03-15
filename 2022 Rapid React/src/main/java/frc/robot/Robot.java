package frc.robot;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutoShootCommand;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.ElevatorCommandTesting;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.HighShootManualCommand;
import frc.robot.commands.LowShootManualCommand;
import frc.robot.commands.OffCenterShoot;
import frc.robot.commands.SpoolShooterCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.TurnDrive;
import frc.robot.commands.TurretCommand;
import frc.robot.commands.driveDistance;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterVisionSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Robot extends TimedRobot {


  enum RobotState {
<<<<<<< Updated upstream
    DEFAULT, HIGHMANUALSHOOT, LOWMANUALSHOOT, AUTOSHOOT;
=======
    DEFAULT, HIGHMANUALSHOOT, LOWMANUALSHOOT, AUTOSHOOT, CLIMB, OFFSHOOT;
>>>>>>> Stashed changes
  }

  public static OI m_oi;
  public static DriveSubsystem m_driveSubsystem;
  public static ShooterVisionSubsystem m_shootervision;
  public static ShooterSubsystem m_ShooterSubsystem;
  public static HighShootManualCommand m_HighShootManualCommand;
  public static LowShootManualCommand m_LowShootManualCommand;
  public static SpoolShooterCommand m_SpoolShooterCommand;
  public static AutoShootCommand m_AutoShootCommand;
  public static IndexSubsystem m_IndexSubsystem;
  public static ElevatorSubsystem m_ElevatorSubsystem;
  public static TurretSubsystem m_TurretSubsystem;
  public static TurretCommand m_TurretCommand;
  public static ElevatorCommand m_ElevatorCommand;
  public static ElevatorCommandTesting m_ElevatorCommandTesting;
  public static IntakeSubsystem m_IntakeSubsystem;
  public static IntakeCommand m_IntakeCommand;
  public static Command m_TankDriveCommand;
<<<<<<< Updated upstream
  public static Command m_TurnDrive;
  public static Command m_driveDistance;

=======
  public static Command m_DynamicBraking;
  public static Command m_TwoBallAuton;
  public static Command m_ThreeBallAuton;
  public static Command m_FiveBallAuton;
  public static Command m_EnterClimb;
  public static Command m_offshoot;
  SendableChooser<Command> m_chooser;
  
  UsbCamera usbCamera = new UsbCamera("USB Camera 0", 0);
  MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1181);
>>>>>>> Stashed changes

  static RobotState Rstate = RobotState.DEFAULT;

  @Override
  public void robotInit() {


    m_TurretSubsystem = new TurretSubsystem();
    m_oi = new OI();
    m_driveSubsystem = new DriveSubsystem();
    m_shootervision = new ShooterVisionSubsystem();
    m_ShooterSubsystem = new ShooterSubsystem();
    m_ElevatorSubsystem = new ElevatorSubsystem();
    m_IntakeSubsystem = new IntakeSubsystem();
    m_HighShootManualCommand = new HighShootManualCommand();
    m_LowShootManualCommand = new LowShootManualCommand();
    m_TankDriveCommand = new TankDriveCommand();
<<<<<<< Updated upstream
    m_IntakeCommand = new IntakeCommand();
    m_ElevatorCommand = new ElevatorCommand();
    m_ElevatorCommandTesting = new ElevatorCommandTesting();
    m_TurretCommand = new TurretCommand();
    m_SpoolShooterCommand = new SpoolShooterCommand();
    m_AutoShootCommand = new AutoShootCommand();
=======
     m_IntakeCommand = new IntakeCommand();
   m_ElevatorCommand = new ElevatorCommand();
    m_ElevatorCommandTesting = new ElevatorCommandTesting();
    m_TurretCommand = new TurretCommand();
    m_SpoolShooterCommand = new SpoolShooterCommand();
     m_AutoShootCommand = new AutoShootCommand();
    m_DynamicBraking = new DynamicBraking();
    m_TwoBallAuton = new TwoBallAutonCommandSequence();
    m_ThreeBallAuton = new ThreeBallAutonCommand();
    m_FiveBallAuton = new FiveBallAutonCommand();
    m_EnterClimb = new EnterClimb();
    m_offshoot = new OffCenterShoot();
    m_chooser = new SendableChooser<Command>();
    m_chooser.setDefaultOption("TwoBallAuton", m_TwoBallAuton);
    m_chooser.addOption("ThreeBallAuton", m_ThreeBallAuton);
    m_chooser.addOption("FiveBallAuton", m_FiveBallAuton);
    SmartDashboard.putData(m_chooser);
>>>>>>> Stashed changes
  }

  @Override
  public void robotPeriodic() {

    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    
    Rstate = RobotState.DEFAULT;
    startDefault();

  }

  @Override
  public void teleopPeriodic() {

    switch (Rstate) {
      case DEFAULT:
        if(m_oi.getAButton()) {
          endDefault();
          startHighManualShoot();
          Rstate = RobotState.HIGHMANUALSHOOT;
        }

        if(m_oi.getBButton()) {
          endDefault();
          startLowManualShoot();
          Rstate = RobotState.LOWMANUALSHOOT;
        }

        if(m_oi.getRightTrigger() || m_oi.getLeftTrigger()) {
          endDefault();
          startAutoShoot();
          Rstate = RobotState.AUTOSHOOT;
        }
        if(m_oi.get11()){
          endDefault();
          startOffshoot();
          Rstate = RobotState.OFFSHOOT;
        }

        break;

      case HIGHMANUALSHOOT:
        if(!m_oi.getAButton()) {
          endHighManualShoot();
          startDefault();
          Rstate = RobotState.DEFAULT;
       }
        break;

      case LOWMANUALSHOOT:
        if(!m_oi.getBButton()) {
          endLowManualShoot();
          startDefault();
          Rstate = RobotState.DEFAULT;
        }
        break;

      case AUTOSHOOT:
        if(!m_oi.getRightTrigger() || !m_oi.getLeftTrigger()) {
          endAutoShoot();
          startDefault();
          Rstate = RobotState.DEFAULT;
        }
        break;

<<<<<<< Updated upstream
=======
      case CLIMB:
      if(m_oi.getLeftBumper()) {
        endClimb();
        startDefault();
        Rstate = RobotState.DEFAULT;
      }
      break;

      case OFFSHOOT:
      if(!m_oi.get11()) {
        endOffshoot();
        startDefault();
        Rstate = RobotState.DEFAULT;
      }
      break;
>>>>>>> Stashed changes
      }

  }

private void startDefault() {
  
  m_SpoolShooterCommand.schedule();
  m_TankDriveCommand.schedule();
  m_IntakeCommand.schedule();
  m_TurretCommand.schedule();
<<<<<<< Updated upstream
  m_ElevatorCommand.schedule();

=======
  // m_ElevatorCommand.schedule();
  // m_ElevatorCommandTesting.schedule();
>>>>>>> Stashed changes
}

private void endDefault() {

  m_SpoolShooterCommand.cancel();
  m_TankDriveCommand.cancel();
  m_IntakeCommand.cancel();
  m_TurretCommand.cancel();
<<<<<<< Updated upstream
  m_ElevatorCommand.cancel();
=======
  // m_ElevatorCommandTesting.cancel();
  //m_ElevatorCommand.cancel();
>>>>>>> Stashed changes

}

private void startHighManualShoot() {

  m_HighShootManualCommand.schedule();

}

private void endHighManualShoot() {

  m_HighShootManualCommand.cancel();

}

private void startLowManualShoot() {

  m_LowShootManualCommand.schedule();

}

private void endLowManualShoot() {

  m_LowShootManualCommand.cancel();

}

private void startAutoShoot() {

//autoshootcommand

}
private void endAutoShoot() {

  m_AutoShootCommand.cancel();
  m_DynamicBraking.cancel();

}
private void startOffshoot() {

  m_offshoot.schedule();
  m_DynamicBraking.schedule();
  
  }
  private void endOffshoot() {
  
    m_offshoot.cancel();
    m_DynamicBraking.cancel();
  
  }

<<<<<<< Updated upstream
private void endAutoShoot() {



=======
private void startClimb() {
// m_ElevatorCommand.schedule();
m_TankDriveCommand.schedule();
m_EnterClimb.schedule();
}

private void endClimb() {
//  m_ElevatorCommand.cancel();
m_TankDriveCommand.cancel();
m_EnterClimb.cancel();
>>>>>>> Stashed changes
}



  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  public static void putMessage(String string) {
  }
}
