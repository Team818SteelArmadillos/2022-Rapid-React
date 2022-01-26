package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.TankDriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterVisionSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Robot extends TimedRobot {

  public static OI m_oi;
  public static DriveSubsystem m_driveSubsystem;
  public static ShooterVisionSubsystem m_shootervision;

  private Command m_TankDrive;

  private RobotContainer m_robotContainer;
  public static ShooterSubsystem m_ShooterSubsytem = new ShooterSubsystem();

  @Override
  public void robotInit() {
    
    m_oi = new OI();
    m_driveSubsystem = new DriveSubsystem();
    m_TankDrive = new TankDriveCommand();
    m_shootervision = new ShooterVisionSubsystem();

    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_ShooterSubsytem = new ShooterSubsystem();
    
    m_robotContainer = new RobotContainer();
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
}
