package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.HighShootManualCommand;
import frc.robot.commands.LowShootManualCommand;
import frc.robot.commands.TankDriveCommand;
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
  public static ShooterSubsystem m_ShooterSubsytem;
  public static HighShootManualCommand m_HighShootManualCommand;
  public static LowShootManualCommand m_LowShootManualCommand;
  public static IndexSubsystem m_IndexSubsystem;
  public static ElevatorSubsystem m_ElevatorSubsystem;
  public static TurretSubsystem m_TurretSubsystem;
  public static TurretCommand m_TurretCommand;
  public static IntakeSubsystem m_IntakeSubsystem;

  private Command m_TankDrive;

  @Override
  public void robotInit() {


    m_TurretCommand = new TurretCommand();
    m_TurretSubsystem = new TurretSubsystem();
    m_oi = new OI();
    m_driveSubsystem = new DriveSubsystem();
    m_shootervision = new ShooterVisionSubsystem();
    m_ShooterSubsytem = new ShooterSubsystem();
    m_ElevatorSubsystem = new ElevatorSubsystem();
    m_IntakeSubsystem = new IntakeSubsystem();
    m_HighShootManualCommand = new HighShootManualCommand();
    m_LowShootManualCommand = new LowShootManualCommand();

    m_TankDrive = new TankDriveCommand();
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
