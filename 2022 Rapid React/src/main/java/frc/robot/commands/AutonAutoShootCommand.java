package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class AutonAutoShootCommand extends CommandBase {
  boolean reachedTarget, dataLogged;

  Timer timer;

  double turret;

  private double dist[][];

  private double rpm, powerFront, powerBack;

  public AutonAutoShootCommand(double turret) {
    addRequirements(Robot.m_ShooterSubsystem, Robot.m_shootervision, Robot.m_TurretSubsystem, Robot.m_IntakeSubsystem, Robot.m_IndexSubsystem);
    this.turret = turret;
    timer = new Timer();
    // rpm = 3000;

    dist = new double[17][2];

    dist[0][0] = 42; /*42*/
    dist[0][1] = 1570;

    dist[1][0] = 47; /*42*/
    dist[1][1] = 1610; /* FIELD 1500 MACOMB 1430*/

    dist[2][0] = 57;
    dist[2][1] = 1670; /* 1600 MACOMB 1550*/

    dist[3][0] = 64;
    dist[3][1] = 1750;

    dist[4][0] = 70;
    dist[4][1] = 1820; /*FIELD 1625 */

    dist[5][0] = 73;
    dist[5][1] = 1845;

    dist[6][0] = 79;
    dist[6][1] = 1910;

    dist[7][0] = 84.5;  /*84 */
    dist[7][1] = 2010; /*MACOMB 1780 WE ARE HERE*/

    dist[8][0] = 92;
    dist[8][1] = 2110; /* FIELD MACOMB 1820*/

    dist[9][0] = 98;
    dist[9][1] = 2150; /*MACOMB 1830*/

    dist[10][0] = 102;
    dist[10][1] = 2170; /*FIELD 1950 */

    dist[11][0] = 106;
    dist[11][1] = 2220; /*FIELD 2050*/

    dist[12][0] = 110;
    dist[12][1] = 2350; /*FIELD  */

    dist[13][0] = 121;
    dist[13][1] = 2410; /*FIELD 2500 */

    dist[14][0] = 128.5;
    dist[14][1] = 2520; /*FIELD 2850 */
    
    dist[15][0] = 140;
    dist[15][1] = 2750;  /*FIELD 3225 */

    dist[16][0] = 160;
    dist[16][1] = 3050; /*FIELD 3450 */



    // ShootFrontPID = new PIDController(shooterFrontP, shooterFrontI, shooterFrontD);
    // ShootFrontPID.setTolerance(10);

    // ShootBackPID = new PIDController(shooterBackP, shooterBackI, shooterBackD);
    // ShootBackPID.setTolerance(100);


  }

  @Override
  public void initialize() {
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_ShooterSubsystem.setPowerBack(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);
    Robot.m_driveSubsystem.setBothMotors(0);
    timer.reset();
    dataLogged = false;
  
  }

  @Override
  public void execute() {
  
    rpm = shooterSpeed(69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180));


    if(Robot.m_shootervision.getTarget()) {
      Robot.m_TurretSubsystem.setTurretSpeed(-Robot.m_shootervision.getX() / 40);
      timer.start();
    if (Math.abs(Robot.m_shootervision.getX()) < 5) {
       reachedTarget = true;
    } 
      // powerFront = -ShootFrontPID.calculate(rpm - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonTwo());
      // Robot.m_ShooterSubsystem.setPowerFront(powerFront);
      Robot.m_ShooterSubsystem.setVelocityFront(rpm);

      // powerBack = -ShootBackPID.calculate((rpm * 1.15) - Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonOne());
      // Robot.m_ShooterSubsystem.setPowerBack(powerBack);
      Robot.m_ShooterSubsystem.setVelocityBack(rpm * 1.5);

        
        SmartDashboard.putNumber("powerBack", powerFront);
        SmartDashboard.putNumber("powerBack", powerBack);
        SmartDashboard.putNumber("Shooter Speed", Robot.m_ShooterSubsystem.getCurrentShooterSpeedTalonOne());
        
    
        if(timer.hasElapsed(0.3)){ //&& ShootBackPID.atSetpoint()){
          if (!dataLogged) {
            dataLogged = true;
            System.out.println(String.format("Distance, %.2f, rpm, %d", 69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180), (int)rpm));
          }
          Robot.m_IndexSubsystem.setIndex(0.30);
          Robot.m_IndexSubsystem.setConveyor(-1);
          if (timer.hasElapsed(0.8)){
            Robot.m_IndexSubsystem.setConveyor(0);
          }
        }
    } else Robot.m_TurretSubsystem.setTurretSpeed(turret);

  }
  @Override
  public void end(boolean interrupted) {
    timer.reset();
    Robot.m_IndexSubsystem.setConveyor(0);
    Robot.m_IndexSubsystem.setIndex(0);
    Robot.m_ShooterSubsystem.setPowerFront(0);
    Robot.m_ShooterSubsystem.setPowerBack(0);
    Robot.m_TurretSubsystem.setTurretSpeed(0);
    Robot.m_driveSubsystem.setBothMotors(0);

  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(1.5);
    //return timer.hasElapsed(2.5);
  }

  private double shooterSpeed(double distance) {
    if (distance <= dist[0][0]){
      return 900;
    }else if (distance > dist[16][0]){
      return 1650;
    }
    int i = 0;
    while (dist[i][0] < distance) i++;
     return dist[i-1][1] + (distance - dist[i-1][0]) * (dist[i][1] - dist[i-1][1])/(dist[i][0] - dist[i-1][0]);
  }

}