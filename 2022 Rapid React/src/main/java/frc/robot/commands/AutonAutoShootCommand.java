package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import static frc.robot.Constants.ShooterConstants.*;


public class AutonAutoShootCommand extends CommandBase {
  boolean reachedTarget;

  Timer timer;

  double turret;

  PIDController ShootFrontPID;
  PIDController ShootBackPID;

  private double dist[][];

  private double rpm, powerFront, powerBack;

  public AutonAutoShootCommand(double turret) {
    addRequirements(Robot.m_ShooterSubsystem, Robot.m_shootervision, Robot.m_TurretSubsystem, Robot.m_IntakeSubsystem, Robot.m_IndexSubsystem);
    this.turret = turret;
    rpm = 3000;
    dist = new double[12][2];


    dist[0][0] = 42;
    dist[0][1] = 1500;

    dist[1][0] = 57;
    dist[1][1] = 1600;

    dist[2][0] = 81;
    dist[2][1] = 1750;

    dist[3][0] = 94;
    dist[3][1] = 1825;

    dist[4][0] = 98;
    dist[4][1] = 1850;

    dist[5][0] = 102;
    dist[5][1] = 1950;

    dist[6][0] = 105.5;
    dist[6][1] = 2050;

    dist[7][0] = 110;
    dist[7][1] = 2250;

    dist[8][0] = 121;
    dist[8][1] = 2500;

    dist[9][0] = 128.5;
    dist[9][1] = 2900;
    
    dist[10][0] = 140;
    dist[10][1] = 3225;

    dist[11][0] = 160;
    dist[11][1] = 3450;


    ShootFrontPID = new PIDController(shooterFrontP, shooterFrontI, shooterFrontD);
    ShootFrontPID.setTolerance(10);

    ShootBackPID = new PIDController(shooterBackP, shooterBackI, shooterBackD);
    ShootBackPID.setTolerance(100);


  }

  @Override
  public void initialize() {
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_ShooterSubsystem.setPowerBack(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);
    timer = new Timer();
  
  }

  @Override
  public void execute() {
  
    rpm = shooterSpeed(69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180));

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
          Robot.m_IndexSubsystem.setIndex(0.3);
          Robot.m_IndexSubsystem.setConveyor(-1);
           timer.start();
          if (timer.hasElapsed(0.2)){
            Robot.m_IndexSubsystem.setConveyor(1);
          }
        }
    } else Robot.m_TurretSubsystem.setTurretSpeed(turret);

  }
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    timer.reset();
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_ShooterSubsystem.setPowerBack(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);
    Robot.m_driveSubsystem.shift(true);

  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(3);
  }

  private double shooterSpeed(double distance) {
    if (distance <= dist[0][0] || distance > dist[11][0]) {
      return 1650;
    }
    int i = 0;
    while (dist[i][0] < distance) i++;
     return dist[i-1][1] + (distance - dist[i-1][0]) * (dist[i][1] - dist[i-1][1])/(dist[i][0] - dist[i-1][0]);
  }

}