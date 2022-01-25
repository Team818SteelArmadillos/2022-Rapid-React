package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.TankDriveCommand;
import frc.robot.subsystems.DriveSubsystem;

public class Robot extends TimedRobot {

  public static OI m_oi;
  public static DriveSubsystem m_driveSubsystem;

  private Command m_TankDrive;


  @Override
  public void robotInit() {
    
    m_oi = new OI();
    m_driveSubsystem = new DriveSubsystem();
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
}
