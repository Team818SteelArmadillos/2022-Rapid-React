package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ElevatorConstants.*;

public class ElevatorSubsystem extends SubsystemBase {
  static TalonFX elevatorMotorOne;
  // static TalonFX elevatorMotorTwo;
  static DoubleSolenoid ratchetPiston, anglePiston1, anglePiston2, hookPiston1, hookPiston2;

  public ElevatorSubsystem() {
    elevatorMotorOne = new TalonFX(elevatorMotorPort);
    // elevatorMotorTwo = new TalonFX(elevatorMotorPortTwo);
    ratchetPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ratchetPistonPort[0], ratchetPistonPort[1]);
    anglePiston1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, AnglePistonPort1[0], AnglePistonPort1[1]);
    anglePiston2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, AnglePistonPort2[0], AnglePistonPort2[1]);
    hookPiston1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, flipUpHookPort1[0], flipUpHookPort1[1]);
    hookPiston2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, flipUpHookPort2[0], flipUpHookPort2[1]);


    elevatorMotorOne.configFactoryDefault();
    elevatorMotorOne.setNeutralMode(NeutralMode.Brake);
    elevatorMotorOne.configOpenloopRamp(0.2, 30);

  }

  public void resetEncoders() {
    elevatorMotorOne.setSelectedSensorPosition(0);

  }

  public void setElevatorMotor(double Speed) {
    elevatorMotorOne.set(ControlMode.PercentOutput, Speed);
    // elevatorMotorTwo.set(ControlMode.PercentOutput, -Speed);
  }

  public void setRatchetPiston(int pistonVal) {
    if (pistonVal == 0) {
      ratchetPiston.set(DoubleSolenoid.Value.kOff);
    } else if (pistonVal == -1) {
      ratchetPiston.set(DoubleSolenoid.Value.kReverse);
    } else if (pistonVal == 1) {
      ratchetPiston.set(DoubleSolenoid.Value.kForward);
    }
  }

    public void setAnglePistons(int pistonVal) {
      if (pistonVal == 0) {
        anglePiston1.set(DoubleSolenoid.Value.kOff);
        anglePiston2.set(DoubleSolenoid.Value.kOff);
      } else if (pistonVal == -1) {
        anglePiston1.set(DoubleSolenoid.Value.kReverse);
        anglePiston2.set(DoubleSolenoid.Value.kReverse);
      } else if (pistonVal == 1) {
        anglePiston1.set(DoubleSolenoid.Value.kForward);
        anglePiston2.set(DoubleSolenoid.Value.kForward);
      }
    }

      public void setHookPistons(int pistonVal) {
        if (pistonVal == 0) {
          hookPiston1.set(DoubleSolenoid.Value.kOff);
          hookPiston2.set(DoubleSolenoid.Value.kOff);
        } else if (pistonVal == -1) {
          hookPiston1.set(DoubleSolenoid.Value.kReverse);
          hookPiston2.set(DoubleSolenoid.Value.kReverse);
        } else if (pistonVal == 1) {
          hookPiston1.set(DoubleSolenoid.Value.kForward);
          hookPiston2.set(DoubleSolenoid.Value.kForward);
        }
      }

  @Override
  public void periodic() {

  }
}
