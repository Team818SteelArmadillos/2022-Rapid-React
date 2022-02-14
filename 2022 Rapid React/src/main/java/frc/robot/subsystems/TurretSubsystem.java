package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ShooterConstants.*;

public class TurretSubsystem extends SubsystemBase {
  public TalonFX talon1;
  public DigitalInput toplimitSwitch = new DigitalInput(0);
  public DigitalInput bottomlimitSwitch = new DigitalInput(1);

  private double turretoffset = 0;

  /** Creates a new TurretSubsystem. */
  public TurretSubsystem() {
    talon1 = new TalonFX(TURRET_MOTOR);

    talon1.configFactoryDefault();
  }

  public void setTurretSpeed(double speed) {
    talon1.set(ControlMode.PercentOutput, speed);
    
    if ((speed > 0) && (getCurrentTurretPosition()) > encoderPulsesPerRevolution * 0.25 * turretGearRatio){
      talon1.set(ControlMode.PercentOutput, 0);
    }
    if ((-1 * (speed) > 0) && (getCurrentTurretPosition()) > encoderPulsesPerRevolution * 0.25 * turretGearRatio){
      talon1.set(ControlMode.PercentOutput, 0);
      }
  }

  public void resetEncoderPosition(){
    turretoffset = talon1.getSelectedSensorPosition();
  }
  public double getCurrentTurretPosition(){
    return (talon1.getSelectedSensorPosition() - turretoffset) * encoderPulsesPerRevolution;
  }


  @Override
  public void periodic() {
    
  }
}
