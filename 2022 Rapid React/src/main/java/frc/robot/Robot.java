package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.ElevatorCommandTesting;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.HighShootManualCommand;
import frc.robot.commands.LowShootManualCommand;
import frc.robot.commands.SpoolShooterCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.TurretAutoTrackCommand;
import frc.robot.commands.TurretCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterVisionSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Robot extends TimedRobot {

  public static OI m_oi;
  public static DriveSubsystem m_driveSubsystem;
  public static ShooterVisionSubsystem m_shootervision;
  public static ShooterSubsystem m_ShooterSubsystem;
  public static HighShootManualCommand m_HighShootManualCommand;
  public static LowShootManualCommand m_LowShootManualCommand;
  public static SpoolShooterCommand m_SpoolShooterCommand;
  public static TurretAutoTrackCommand m_TurretAutoTrackCommand;
  public static IndexSubsystem m_IndexSubsystem;
  public static ElevatorSubsystem m_ElevatorSubsystem;
  public static TurretSubsystem m_TurretSubsystem;
  public static TurretCommand m_TurretCommand;
  public static ElevatorCommand m_ElevatorCommand;
  public static ElevatorCommandTesting m_ElevatorCommandTesting;
  public static IntakeSubsystem m_IntakeSubsystem;
  public static IntakeCommand m_IntakeCommand;
  public static Command m_TankDriveCommand;

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
    m_IntakeCommand = new IntakeCommand();
    m_ElevatorCommand = new ElevatorCommand();
    m_ElevatorCommandTesting = new ElevatorCommandTesting();
    m_TurretCommand = new TurretCommand();
    m_SpoolShooterCommand = new SpoolShooterCommand();
    m_TurretAutoTrackCommand = new TurretAutoTrackCommand();
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
    
    m_TankDriveCommand.schedule();

  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  public static void putMessage(String string) {
  }
}
