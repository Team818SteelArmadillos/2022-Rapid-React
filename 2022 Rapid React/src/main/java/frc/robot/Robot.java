package frc.robot;

//import edu.wpi.first.cscore.MjpegServer;
//import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutoShootCommand;
import frc.robot.commands.DynamicBraking;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.ElevatorCommandTesting;
import frc.robot.commands.EnterClimb;
import frc.robot.commands.FiveBallAutonCommand;
import frc.robot.commands.FourBallAuton;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.HighShootManualCommand;
import frc.robot.commands.LowShootManualCommand;
import frc.robot.commands.SpoolShooterCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.ThreeBallAutoDefensive;
import frc.robot.commands.ThreeBallAutonCommand;
import frc.robot.commands.TurretCommand;
import frc.robot.commands.TwoBallAutonCommandSequence;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
// import frc.robot.subsystems.LEDS;
import frc.robot.subsystems.ShooterVisionSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Robot extends TimedRobot {


  enum RobotState {
    DEFAULT, HIGHMANUALSHOOT, LOWMANUALSHOOT, AUTOSHOOT, CLIMB;
  }

  public static OI m_oi;
  public static IndexSubsystem m_IndexSubsystem;
  public static DriveSubsystem m_driveSubsystem;
  public static ShooterVisionSubsystem m_shootervision;
  public static ShooterSubsystem m_ShooterSubsystem;
  public static IntakeSubsystem m_IntakeSubsystem;
  public static ElevatorSubsystem m_ElevatorSubsystem;
  public static TurretSubsystem m_TurretSubsystem;
  // public static LEDS m_LEDS;

  public static HighShootManualCommand m_HighShootManualCommand;
  public static LowShootManualCommand m_LowShootManualCommand;
  public static SpoolShooterCommand m_SpoolShooterCommand;
  public static AutoShootCommand m_AutoShootCommand;
  public static TurretCommand m_TurretCommand;
  public static ElevatorCommand m_ElevatorCommand;
  public static ElevatorCommandTesting m_ElevatorCommandTesting;
  public static IntakeCommand m_IntakeCommand;
  public static Command m_TankDriveCommand;
  public static Command m_DynamicBraking;
  public static Command m_EnterClimb;

  public static Command m_TwoBallAuton;
  public static Command m_ThreeBallAuton;
  public static Command m_FiveBallAuton;
  public static Command m_ThreeBallDefensiveAuton;
  public static Command m_FourBallAuton;
  
  SendableChooser<Command> m_chooser;
  
  //UsbCamera usbCamera = new UsbCamera("USB Camera 0", 0);
  //MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1181);

  static RobotState Rstate = RobotState.DEFAULT;

  @Override
  public void robotInit() {

    //initalize oi and subsystems
    m_oi = new OI();
    m_shootervision = new ShooterVisionSubsystem();
    m_TurretSubsystem = new TurretSubsystem();
    m_IndexSubsystem = new IndexSubsystem();
    m_driveSubsystem = new DriveSubsystem();
    m_ShooterSubsystem = new ShooterSubsystem();
    m_ElevatorSubsystem = new ElevatorSubsystem();
    m_IntakeSubsystem = new IntakeSubsystem();
    // m_LEDS = new LEDS();

    //initialize commands
    m_HighShootManualCommand = new HighShootManualCommand();
    m_LowShootManualCommand = new LowShootManualCommand();
    m_TankDriveCommand = new TankDriveCommand();
    m_IntakeCommand = new IntakeCommand();
    m_ElevatorCommandTesting = new ElevatorCommandTesting();
    m_TurretCommand = new TurretCommand();
    m_SpoolShooterCommand = new SpoolShooterCommand();
    m_AutoShootCommand = new AutoShootCommand();
    m_DynamicBraking = new DynamicBraking();
    m_EnterClimb = new EnterClimb();

    //initialize auton commands
    m_TwoBallAuton = new TwoBallAutonCommandSequence();
    m_ThreeBallAuton = new ThreeBallAutonCommand();
    m_FiveBallAuton = new FiveBallAutonCommand();
    m_ThreeBallDefensiveAuton = new ThreeBallAutoDefensive();
    m_FourBallAuton = new FourBallAuton();

    //initalize auton chooser
    m_chooser = new SendableChooser<Command>();
    m_chooser.setDefaultOption("TwoBallAuton", m_TwoBallAuton);
    m_chooser.addOption("ThreeBallDefensive", m_ThreeBallDefensiveAuton);
    m_chooser.addOption("ThreeBallAuton", m_ThreeBallAuton);
    m_chooser.addOption("FiveBallAuton", m_FiveBallAuton);
    m_chooser.addOption("FOurBallAuton", m_FourBallAuton);
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

    // if (Robot.m_IndexSubsystem.SensorFront() && Robot.m_IndexSubsystem.SensorBack()){
    //   Robot.m_LEDS.candle.setLEDs(0, 0, 148, 0, 0, 108);
    // } else if(!Robot.m_IndexSubsystem.SensorFront() && Robot.m_IndexSubsystem.SensorBack()){
    //   Robot.m_LEDS.candle.setLEDs(0, 0, 148, 0, 0, 54);
    // } else {
    //   Robot.m_LEDS.candle.setLEDs(0, 0, 0, 0, 0, 108);
    // }
    
    
    //commands are run in groups, using a state machine format

    switch (Rstate) {

      //default state is when the general commands are being run during teleop
      case DEFAULT:

        //when a button is pressed switches the state to high manual shoot
        if(m_oi.getAButton()) {
          endDefault();
          startHighManualShoot();
          Rstate = RobotState.HIGHMANUALSHOOT;
        }

        //when b button is pressed switches the state to low manaul shoot
        if(m_oi.getBButton()) {
          endDefault();
          startLowManualShoot();
          Rstate = RobotState.LOWMANUALSHOOT;
        }

        //when left bumper is pressed swtiches the state to climb
        if(m_oi.getLeftBumper()){
          endDefault();
          startClimb();
          Rstate = RobotState.CLIMB;
        }

        //when either the left or right trigger are held switches state to auto shoot
        if(m_oi.getRightTrigger() || m_oi.getLeftTrigger()) {
          endDefault();
          startAutoShoot();
          Rstate = RobotState.AUTOSHOOT;
        }

        break;

      //high manual shoot state is run when we want to shoot at the high goal at a predetermined position
      case HIGHMANUALSHOOT:
        //when the a button is released switches state back to default
        if(!m_oi.getAButton()) {
          endHighManualShoot();
          startDefault();
          Rstate = RobotState.DEFAULT;
        }
        break;

      //low manual shoot is run when we want to shoot at the low goal at a predetermined position
      case LOWMANUALSHOOT:
        //when the b button is released switches state back to default
        if(!m_oi.getBButton()) {
          endLowManualShoot();
          startDefault();
          Rstate = RobotState.DEFAULT;
        }
        break;

      //auto shoot is run when we want to shoot the ball using the limelight from any distance 
      case AUTOSHOOT:
        //when either the left and right trigger are released sets state back to default
        if(!m_oi.getRightTrigger() && !m_oi.getLeftTrigger()) {
          endAutoShoot();
          startDefault();
          Rstate = RobotState.DEFAULT;
        }
        break;

      //climb state is ran when we want the robot to climb, and locks down robot functions not needed during climb state
      case CLIMB:
      //if the left bumper is pressed again switches state back to default
      if(m_oi.getLeftBumper()) {
        endClimb();
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

}

private void endDefault() {

  m_SpoolShooterCommand.cancel();
  m_TankDriveCommand.cancel();
  m_IntakeCommand.cancel();
  m_TurretCommand.cancel();

}

private void startHighManualShoot() {

  m_HighShootManualCommand.schedule();
  m_DynamicBraking.schedule();

}

private void endHighManualShoot() {

  m_HighShootManualCommand.cancel();
  m_DynamicBraking.cancel();

}

private void startLowManualShoot() {

  m_LowShootManualCommand.schedule();
  m_DynamicBraking.schedule();

}

private void endLowManualShoot() {

  m_LowShootManualCommand.cancel();
  m_DynamicBraking.cancel();

}

private void startAutoShoot() {

  m_AutoShootCommand.schedule();
  m_DynamicBraking.schedule();

}

private void endAutoShoot() {

  m_AutoShootCommand.cancel();
  m_DynamicBraking.cancel();

}

private void startClimb() {

  m_TankDriveCommand.schedule();
  m_EnterClimb.schedule();

}

private void endClimb() {

  m_TankDriveCommand.cancel();
  m_EnterClimb.cancel();

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
