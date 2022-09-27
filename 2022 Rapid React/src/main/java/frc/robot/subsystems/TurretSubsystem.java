package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

<<<<<<< Updated upstream
import edu.wpi.first.math.MathUtil;
=======

import edu.wpi.first.wpilibj.DigitalInput;
>>>>>>> Stashed changes
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ShooterConstants.*;

public class TurretSubsystem extends SubsystemBase {
  public TalonFX turretTalon;
  
  public TurretSubsystem() {
    turretTalon = new TalonFX(TURRET_MOTOR);

    turretTalon.configFactoryDefault();
    turretTalon.setNeutralMode(NeutralMode.Brake);
    resetEncoderPosition();
  }

  public void setTurretSpeed(double speed) {
    turretTalon.set(ControlMode.PercentOutput, MathUtil.clamp(speed, -0.22, 0.22));
    if ((speed > 0) && (getCurrentTurretPosition()) > encoderPulsesPerRevolution * 0.22 * turretGearRatio){
      turretTalon.set(ControlMode.PercentOutput, 0);
    }
    if ((speed < 0) && (getCurrentTurretPosition()) < encoderPulsesPerRevolution * -0.22 * turretGearRatio){
      turretTalon.set(ControlMode.PercentOutput, 0);
      }
  }

  private void resetEncoderPosition(){
    turretTalon.setSelectedSensorPosition(0);
  }
  public double getCurrentTurretPosition(){
    return turretTalon.getSelectedSensorPosition();
  }


  @Override
  public void periodic() {
    
  }
}
