// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.TurretConstants.*;

public class TurretSubsystem extends SubsystemBase {
  public TalonFX talon1;
  public DigitalInput toplimitSwitch = new DigitalInput(0);
  public DigitalInput bottomlimitSwitch = new DigitalInput(1);

  /** Creates a new TurretSubsystem. */
  public TurretSubsystem() {
    talon1 = new TalonFX(TURRET_MOTOR);

    talon1.configFactoryDefault();
  }

  public double getCurrentTurretPosition(){
    return talon1.getSelectedSensorPosition();
  }

  
  public void setTurretSpeed(double speed) {
    talon1.set(ControlMode.PercentOutput, 0);
    if (Math.abs(speed) > 0) {
        if (toplimitSwitch.get() || bottomlimitSwitch.get()) {
          talon1.set(ControlMode.PercentOutput, 0);
        } else {
          talon1.set(ControlMode.PercentOutput, speed);
        }
    }
  }

  @Override
  public void periodic() {
    
  }
}
