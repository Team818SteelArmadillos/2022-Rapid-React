package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import static frc.robot.Constants.DriveConstants.*;

public class DriveSubsystem extends SubsystemBase {

  private TalonFX talonLeft2, talonRight2;
  private TalonFX talonLeft1, talonRight1;
  private DoubleSolenoid shiftPistonLeft;
  private PigeonIMU pigeon;

  private int leftOffset = 0;
  private int rightOffset = 0;

  boolean brake = false;
  //boolean highGear = false;
  boolean isLowGear = false;
  
  public PIDController BrakePIDLeft, BrakePIDRight, DistancePID;

  public DriveSubsystem() {

    talonLeft1 = new TalonFX(MOTOR_PORTS_LEFT[0]);
    talonRight1 = new TalonFX(MOTOR_PORTS_RIGHT[0]);
    talonLeft2 = new TalonFX(MOTOR_PORTS_LEFT[1]);
    talonRight2 = new TalonFX(MOTOR_PORTS_RIGHT[1]);
    
    talonLeft1.configFactoryDefault();
    talonLeft1.setInverted(LEFT_INVERTED);
    talonLeft1.configOpenloopRamp(RAMP_RATE);

    talonRight1.configFactoryDefault();
    talonRight1.setInverted(!LEFT_INVERTED);
    talonRight1.configOpenloopRamp(RAMP_RATE);

    talonLeft2.configFactoryDefault();
    talonLeft2.setInverted(LEFT_INVERTED);
    talonLeft2.follow(talonLeft1);
      
    talonRight2.configFactoryDefault();
    talonRight2.setInverted(!LEFT_INVERTED);
    talonRight2.follow(talonRight1);

    DistancePID = new PIDController(driveP, driveI, driveD);
    DistancePID.setTolerance(2);

    BrakePIDLeft = new PIDController(brakeP, brakeI, brakeD);
    BrakePIDRight = new PIDController(brakeP, brakeI, brakeD);



    shiftPistonLeft = new DoubleSolenoid(shiftPistonPorts[2], PneumaticsModuleType.CTREPCM, shiftPistonPorts[0], shiftPistonPorts[1]);

    pigeon = new PigeonIMU(Robot.m_IndexSubsystem.conveyorMotor);

  }
  public double getAngle(){

    return pigeon.getYaw();

  }

  public void resetGyro() {

    pigeon.setYaw(0);
  
  }

  @Override
  public void periodic() {

  }

  public void shift(boolean lowGear){
    isLowGear = lowGear;
    if(lowGear){
      shiftPistonLeft.set(DoubleSolenoid.Value.kForward);
    }else{
      shiftPistonLeft.set(DoubleSolenoid.Value.kReverse);
    }
  }

  public boolean currentGear(){
    return isLowGear;
  }

  public void setBothMotors(double speedLeft, double speedRight) {
    setLeftMotors(speedLeft);
    setRightMotors(speedRight);
  }

  public void setBothMotors(double speed) {
    setLeftMotors(speed);
    setRightMotors(speed);
  }


  public void setLeftMotors(double speed) {
    talonLeft1.set(ControlMode.PercentOutput, speed);
  }

  public void setRightMotors(double speed) {
    talonRight1.set(ControlMode.PercentOutput, speed);
  }

  public double setDriveMotorPostion(double distance){
    double power = MathUtil.clamp(DistancePID.calculate(distance - getLeftPosition()), -0.5, 0.5);  
    setBothMotors(power);
    return power;
  }

  public void setBreak(){

    setLeftMotors(MathUtil.clamp(BrakePIDLeft.calculate(0 - getLeftPosition()), -1, 1));
    setRightMotors(MathUtil.clamp(BrakePIDRight.calculate(0 - getRightPosition()), -1, 1));
  
  }

  public void resetEncoders() {
    talonLeft1.setSelectedSensorPosition(0);
    talonLeft2.setSelectedSensorPosition(0);
    talonRight1.setSelectedSensorPosition(0);
    talonRight2.setSelectedSensorPosition(0);
  }

  public double getLeftPosition() {
    if (!isLowGear){
      return -(talonLeft1.getSelectedSensorPosition() - leftOffset) * distancePerPulse / high;
    } else {
      return -(talonLeft1.getSelectedSensorPosition() - leftOffset) * distancePerPulse / low;
    }

  }

  public double getRightPosition() {
    if (!isLowGear){
      return -(talonRight1.getSelectedSensorPosition() - rightOffset) * distancePerPulse / high;
    } else {
      return -(talonRight1.getSelectedSensorPosition() - rightOffset) * distancePerPulse / low;
    }

  }

  public double getLeftVelocity() {
    if(isLowGear){
    return talonLeft1.getSelectedSensorVelocity() * distancePerPulse * VELOCITY_CALCULATION_PER_SECOND * Math.PI / (12 * high);
    } else {
      return talonLeft1.getSelectedSensorVelocity() * distancePerPulse * VELOCITY_CALCULATION_PER_SECOND * Math.PI / (12 * low);
    }
  }

  public double getRightVelocity() {
    if(isLowGear){
    return talonRight1.getSelectedSensorVelocity() * distancePerPulse * VELOCITY_CALCULATION_PER_SECOND * Math.PI / (12 * high);
    } else {
    return talonRight1.getSelectedSensorVelocity() * distancePerPulse * VELOCITY_CALCULATION_PER_SECOND * Math.PI / (12 * low);
  }
  }

}