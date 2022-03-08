package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutoShootCommand;
import frc.robot.commands.AutonAutoShootCommand;
import frc.robot.commands.DynamicBraking;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.ElevatorCommandTesting;
import frc.robot.commands.FiveBallAutonCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.HighShootManualCommand;
import frc.robot.commands.LowShootManualCommand;
import frc.robot.commands.SpoolShooterCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.ThreeBallAutonCommand;
import frc.robot.commands.TurnDrive;
import frc.robot.commands.TurretCommand;
import frc.robot.commands.TwoBallAutonCommandSequence;
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
    DEFAULT, HIGHMANUALSHOOT, LOWMANUALSHOOT, AUTOSHOOT;
  }

  public static OI m_oi;
  public static IndexSubsystem m_IndexSubsystem;
  public static DriveSubsystem m_driveSubsystem;
  public static ShooterVisionSubsystem m_shootervision;
  public static ShooterSubsystem m_ShooterSubsystem;
  public static HighShootManualCommand m_HighShootManualCommand;
  public static LowShootManualCommand m_LowShootManualCommand;
  public static SpoolShooterCommand m_SpoolShooterCommand;
  public static AutoShootCommand m_AutoShootCommand;
  public static ElevatorSubsystem m_ElevatorSubsystem;
  public static TurretSubsystem m_TurretSubsystem;
  public static TurretCommand m_TurretCommand;
  public static ElevatorCommand m_ElevatorCommand;
  public static ElevatorCommandTesting m_ElevatorCommandTesting;
  public static IntakeSubsystem m_IntakeSubsystem;
  public static IntakeCommand m_IntakeCommand;
  public static Command m_TankDriveCommand;
  public static Command m_DynamicBraking;
  public static Command m_TwoBallAuton;
  public static Command m_ThreeBallAuton;
  public static Command m_FiveBallAuton;
  SendableChooser<Command> m_chooser;



  static RobotState Rstate = RobotState.DEFAULT;

  @Override
  public void robotInit() {

    m_TurretSubsystem = new TurretSubsystem();
    m_oi = new OI();
    m_IndexSubsystem = new IndexSubsystem();
    m_driveSubsystem = new DriveSubsystem();
    m_shootervision = new ShooterVisionSubsystem();
    m_ShooterSubsystem = new ShooterSubsystem();
     m_ElevatorSubsystem = new ElevatorSubsystem();
     m_IntakeSubsystem = new IntakeSubsystem();
    // m_HighShootManualCommand = new HighShootManualCommand();
    // m_LowShootManualCommand = new LowShootManualCommand();
    m_TankDriveCommand = new TankDriveCommand();
     m_IntakeCommand = new IntakeCommand();
   // m_ElevatorCommand = new ElevatorCommand();
    m_ElevatorCommandTesting = new ElevatorCommandTesting();
    m_TurretCommand = new TurretCommand();
    m_SpoolShooterCommand = new SpoolShooterCommand();
     m_AutoShootCommand = new AutoShootCommand();
    m_DynamicBraking = new DynamicBraking();
    m_TwoBallAuton = new TwoBallAutonCommandSequence();
    m_ThreeBallAuton = new ThreeBallAutonCommand();
    m_FiveBallAuton = new FiveBallAutonCommand();
    m_chooser = new SendableChooser<Command>();
    m_chooser.setDefaultOption("TwoBallAuton", m_TwoBallAuton);
    // m_chooser.addOption("ThreeBallAuton", m_ThreeBallAuton);
    // m_chooser.addOption("FiveBallAuton", m_FiveBallAuton);
    SmartDashboard.putData(m_chooser);
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
    m_chooser.getSelected().schedule();

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
    
    //m_IndexSubsystem.setConveyor(m_oi.getgamepadrightXAxis());
    //m_IndexSubsystem.setIndex(m_oi.getgamepadrightXAxis());
    //m_IntakeSubsystem.setIntakePosition(0.5);
    //if (m_oi.getXButton()) {
      // m_IntakeSubsystem.setIntake(-1);
      // m_IndexSubsystem.setConveyor(1);
      //m_IntakeSubsystem.setIntakePosition(1);
    //} else {
     //  m_IntakeSubsystem.setIntake(0);
     //  m_IndexSubsystem.setConveyor(0);
     // m_IntakeSubsystem.setIntakePosition(0.5);
    //}


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
        if(!m_oi.getRightTrigger() && !m_oi.getLeftTrigger()) {
          endAutoShoot();
          startDefault();
          Rstate = RobotState.DEFAULT;
        }
        break;

      }

  }

private void startDefault() {
  
  m_SpoolShooterCommand.schedule();
   m_TankDriveCommand.schedule();
  m_IntakeCommand.schedule();
   m_TurretCommand.schedule();
  // m_ElevatorCommand.schedule();
  m_ElevatorCommandTesting.schedule();
}

private void endDefault() {

  m_SpoolShooterCommand.cancel();
  m_TankDriveCommand.cancel();
  m_IntakeCommand.cancel();
   m_TurretCommand.cancel();
  m_ElevatorCommandTesting.cancel();
  //m_ElevatorCommand.cancel();

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

m_AutoShootCommand.schedule();
m_DynamicBraking.schedule();

}

private void endAutoShoot() {

  m_AutoShootCommand.cancel();
  m_DynamicBraking.cancel();

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
