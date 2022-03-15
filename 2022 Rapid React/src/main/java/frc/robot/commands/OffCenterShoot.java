package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import static frc.robot.Constants.ShooterConstants.*;


public class OffCenterShoot extends CommandBase {
  boolean reachedTarget;

  PIDController ShootPID;

  private double dist[][];

  private double rpm, power;

  public OffCenterShoot() {
    addRequirements(Robot.m_ShooterSubsystem, Robot.m_shootervision, Robot.m_TurretSubsystem, Robot.m_IntakeSubsystem, Robot.m_IndexSubsystem);

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


    // dist[6][0] = 169;
    // dist[6][1] = 3000;

    // dist[7][0] = 175;
    // dist[7][1] = 5000;

    ShootPID = new PIDController(p, i, d);

    ShootPID.setTolerance(10);

    SmartDashboard.putNumber("speed", 3000);
    // SmartDashboard.putNumber("Rpm", 3000);

    // SmartDashboard.putNumber("P", 0);
    // SmartDashboard.putNumber("I", 0);
    // SmartDashboard.putNumber("D", 0);

  }

  @Override
  public void initialize() {
    // if(Robot.m_oi.getLeftTrigger()) {
    //   Robot.m_ElevatorSubsystem.setDynamicPistons(1);
    // } else Robot.m_ElevatorSubsystem.setDynamicPistons(-1);
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPower(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);

  
  }
// tmrw finsih table array and uncomment out one line comment out other

  @Override
  public void execute() {

  // ShootPID.setP(SmartDashboard.getNumber("P", 0));
  // ShootPID.setI(SmartDashboard.getNumber("I", 0));
  // ShootPID.setD(SmartDashboard.getNumber("D", 0));

  // SmartDashboard.putNumber("Distance", 69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180));
  
 rpm = shooterSpeed(69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180));

  // rpm = SmartDashboard.getNumber("Rpm", 0);

    if(Robot.m_shootervision.getTarget()) {
      Robot.m_TurretSubsystem.setTurretSpeed(-(Robot.m_shootervision.getX()+6) / 40);
    if (Math.abs(Robot.m_shootervision.getX() + 6) < 3) {
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
    } else Robot.m_TurretSubsystem.setTurretSpeed(-Robot.m_oi.getgamepadleftXAxis());

  }

  @Override
  public void end(boolean interrupted) {

    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPower(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);
    Robot.m_driveSubsystem.shift(true);
    //Robot.m_ElevatorSubsystem.setDynamicPistons(-1);

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
