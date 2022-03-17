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

  PIDController ShootPID;

  private double dist[][];

  private double rpm, power;

  public AutonAutoShootCommand(double turret) {
    addRequirements(Robot.m_ShooterSubsystem, Robot.m_shootervision, Robot.m_TurretSubsystem, Robot.m_IntakeSubsystem, Robot.m_IndexSubsystem);
    this.turret = turret;
    rpm = 3000;
    dist = new double[6][2];

//distance to rmp pairs lookup table

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


    ShootPID = new PIDController(p, i, d);

    ShootPID.setTolerance(10);


  }

  @Override
  public void initialize() {
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPower(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);
    timer = new Timer();
    timer.start();
  
  }

  @Override
  public void execute() {
  
    rpm = shooterSpeed(69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180));

    if(Robot.m_shootervision.getTarget()) {
      Robot.m_TurretSubsystem.setTurretSpeed(-(Robot.m_shootervision.getX() + 5) / 40);
    // The 5 is an offset so the shooter shoots slightly offcenter 
    if (Math.abs(Robot.m_shootervision.getX() + 5) < 3) {
       reachedTarget = true;
    }
      power = -ShootPID.calculate(rpm - Robot.m_ShooterSubsystem.getCurrentShooterSpeed());
        Robot.m_ShooterSubsystem.setPower(power);
        
        SmartDashboard.putNumber("power", power);
        SmartDashboard.putNumber("Shooter Speed", Robot.m_ShooterSubsystem.getCurrentShooterSpeed());
    
        if(ShootPID.atSetpoint()){
          Robot.m_IndexSubsystem.setConveyor(0.5);
          Robot.m_IndexSubsystem.setIndex(0.3);
        }
    } else Robot.m_TurretSubsystem.setTurretSpeed(turret);

  }

  @Override
  public void end(boolean interrupted) {
    timer.stop();
    timer.reset();
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPower(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);
    Robot.m_driveSubsystem.shift(true);

  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(3);
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