package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import static frc.robot.Constants.ShooterConstants.*;


public class AutoShootCommand extends CommandBase {
  boolean reachedTarget;

  PIDController ShootPID;

  private double dist[][];

  private double rpm, power;

  public AutoShootCommand() {
    addRequirements(Robot.m_ShooterSubsystem, Robot.m_shootervision, Robot.m_TurretSubsystem, Robot.m_IntakeSubsystem, Robot.m_IndexSubsystem);

    rpm = 3000;
    dist = new double[8][2];
//distance to rmp pairs lookup table
    dist[0][0] = 110;
    dist[0][1] = 2000;

    dist[1][0] = 125;
    dist[1][1] = 2000;

    dist[2][0] = 140;
    dist[2][1] = 2200;

    dist[3][0] = 150;
    dist[3][1] = 2400;

    dist[4][0] = 155;
    dist[4][1] = 2550;

    dist[5][0] = 163;
    dist[5][1] = 2800;

    dist[6][0] = 169;
    dist[6][1] = 3000;

    dist[7][0] = 175;
    dist[7][1] = 5000;

    ShootPID = new PIDController(p, i, d);

    ShootPID.setTolerance(10);

    SmartDashboard.putNumber("speed", 3000);

  }

  @Override
  public void initialize() {
    // if(Robot.m_oi.getLeftTrigger()) {
    //   Robot.m_ElevatorSubsystem.setDynamicPistons(1);
    // } else Robot.m_ElevatorSubsystem.setDynamicPistons(-1);
    // SmartDashboard.putNumber("Rpm", 0);
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPower(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);
  }

  @Override
  public void execute() {

  SmartDashboard.putNumber("Distance", 47.25/Math.tan(Robot.m_shootervision.getY()+(2*Math.PI/18)));

  //  rpm = shooterSpeed(47.25/Math.tan(Robot.m_shootervision.getY()+(2*Math.PI/18)));

  rpm = SmartDashboard.getNumber("Rpm", 0);

    if(Robot.m_shootervision.getTarget()) {
      Robot.m_TurretSubsystem.setTurretSpeed(Robot.m_shootervision.getX() / 40);
    if (Math.abs(Robot.m_shootervision.getX()) < 5) {
       reachedTarget = true;
    }
      power = ShootPID.calculate(rpm - Robot.m_ShooterSubsystem.getCurrentShooterSpeed());
        Robot.m_ShooterSubsystem.setPower(power);
        
        SmartDashboard.putNumber("power", power);
        SmartDashboard.putNumber("Shooter Speed", Robot.m_ShooterSubsystem.getCurrentShooterSpeed());
    
        if(ShootPID.atSetpoint()){
          Robot.m_IndexSubsystem.setConveyor(0.5);
          Robot.m_IndexSubsystem.setIndex(0.5);
        }
    } else Robot.m_TurretSubsystem.setTurretSpeed(-Robot.m_oi.getgamepadleftXAxis());

    Robot.m_ShooterSubsystem.setPower(0);
  }

  @Override
  public void end(boolean interrupted) {

    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPower(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);
    //Robot.m_ElevatorSubsystem.setDynamicPistons(-1);

  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private double shooterSpeed(double distance) {
    if (distance <= dist[0][0] || distance > dist[7][0]) {
      return 5000;
    }
    int i = 0;
    while (dist[i][0] < distance) i++;
     return dist[i-1][1] + (distance - dist[i-1][0]) * (dist[i][1] - dist[i-1][1])/(dist[i][0] - dist[i-1][0]);
  }

}
