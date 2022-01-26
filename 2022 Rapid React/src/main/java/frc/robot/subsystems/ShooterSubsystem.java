package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ShooterConstants.*;

public class ShooterSubsystem extends SubsystemBase {

  public TalonFX talon1, talon2;
  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    talon1 = new TalonFX(SHOOTER_PORTS[0]);
    talon2 = new TalonFX(SHOOTER_PORTS[1]);
    
    talon1.configFactoryDefault();
    talon2.configFactoryDefault();

    talon2.follow(talon1);

    talon1.setInverted(SHOOTER_INVERTED);
    talon2.setInverted(SHOOTER_INVERTED);

  }
  public double getCurrentShooterSpeed(){

    return talon1.getSelectedSensorVelocity() * velocityCalculationsPerSecond *-1 * 60 / encoderPulsesPerRevolution;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setPower(double power) {
    talon1.set(ControlMode.PercentOutput, power);
  }
}
