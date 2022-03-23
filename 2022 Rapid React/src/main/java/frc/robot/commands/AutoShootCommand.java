package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import static frc.robot.Constants.ShooterConstants.*;


public class AutoShootCommand extends CommandBase {
  boolean reachedTarget;

  double previousrpm;

  //int dumb;

  PIDController ShootFrontPID;
  PIDController ShootBackPID;

  private double dist[][];

  private double rpm, powerFront, powerBack;

 // Timer timer;

  public AutoShootCommand() {
    addRequirements(Robot.m_ShooterSubsystem, Robot.m_shootervision, Robot.m_TurretSubsystem, Robot.m_IntakeSubsystem, Robot.m_IndexSubsystem);

    //timer = new Timer();

    rpm = 3000;
    dist = new double[6][2];

  /* old distance to rmp pairs lookup table for just one wheel

    dist[0][0] = 52;
    dist[0][1] = 2650;

    dist[1][0] = 68;
    dist[1][1] = 2800;

    dist[2][0] = 83;
    dist[2][1] = 3250;

    dist[3][0] = 97;
    dist[3][1] = 3300;

    dist[4][0] = 105;
    dist[4][1] = 3450;

    dist[5][0] = 110;
    dist[5][1] = 3550;

    */

    //new distance to rpm array table with front and back wheels

    // 2500, 2700, 2900, 3100, 3300, 3500, 3700, 3900, 4100

    ShootFrontPID = new PIDController(shooterFrontP, shooterFrontI, shooterFrontD);
    ShootFrontPID.setTolerance(10);

    ShootBackPID = new PIDController(shooterBackP, shooterBackI, shooterBackD);
    ShootBackPID.setTolerance(100);

    SmartDashboard.putNumber("speed", 3000);
    SmartDashboard.putNumber("Rpm", 1000);

    // SmartDashboard.putNumber("P", 0);
    // SmartDashboard.putNumber("I", 0);
    // SmartDashboard.putNumber("D", 0);

  }

  @Override
  public void initialize() {

    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_ShooterSubsystem.setPowerBack(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);
    // timer.start();
    // previousrpm = 0;

  
  }

  @Override
  public void execute() {
  // double currentrpm = Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonOne();
  // if (currentrpm * previousrpm < 0){
  //   dumb ++;
  // } 
  //   previousrpm = currentrpm;

  // if (timer.hasElapsed(5)){
  //   SmartDashboard.putNumber("frequency", dumb/5.0);
  //   dumb =0;
  // }
    // SET PID values from Smart Dash
  // ShootFrontPID.setP(SmartDashboard.getNumber("P", 0));
  // ShootFrontPID.setI(SmartDashboard.getNumber("I", 0));
  // ShootFrontPID.setD(SmartDashboard.getNumber("D", 0));

  SmartDashboard.putNumber("Distance", 69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180));
  
  // SET TARGET RPM
 //rpm = shooterSpeed(69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180)) // Calc from vision
  rpm = SmartDashboard.getNumber("Rpm", 0); // from Smart Dash

  // DISPLAY Shooter Speed
  SmartDashboard.putNumber("actualrpm", Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonTwo());
  SmartDashboard.putNumber("actualrpmback", Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonOne());
 
    if(Robot.m_shootervision.getTarget()) {
      Robot.m_TurretSubsystem.setTurretSpeed(-Robot.m_shootervision.getX() / 40);
    if (Math.abs(Robot.m_shootervision.getX()) < 5) {
       reachedTarget = true;
    }
      powerFront = -ShootFrontPID.calculate(rpm - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonTwo());
      Robot.m_ShooterSubsystem.setPowerFront(powerFront);

      powerBack = -ShootBackPID.calculate((rpm * 1.15) - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonOne());
      Robot.m_ShooterSubsystem.setPowerBack(powerBack);
        
        SmartDashboard.putNumber("powerBack", powerFront);
        SmartDashboard.putNumber("powerBack", powerBack);
        SmartDashboard.putNumber("Shooter Speed", Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonOne());
        
    
        if(ShootFrontPID.atSetpoint() ){ //&& ShootBackPID.atSetpoint()){
          Robot.m_IndexSubsystem.setConveyor(0.5);
          Robot.m_IndexSubsystem.setIndex(0.3);
        }
    } else Robot.m_TurretSubsystem.setTurretSpeed(-Robot.m_oi.getgamepadleftXAxis()*0.25);

  }

  @Override
  public void end(boolean interrupted) {

    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_ShooterSubsystem.setPowerBack(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);
    Robot.m_driveSubsystem.shift(true);

  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private double shooterSpeed(double distance) {
    if (distance <= dist[0][0] || distance > dist[5][0]) {
      return 2650;
    }
    int i = 0;
    while (dist[i][0] < distance) i++;
     return dist[i-1][1] + (distance - dist[i-1][0]) * (dist[i][1] - dist[i-1][1])/(dist[i][0] - dist[i-1][0]);
  }

}