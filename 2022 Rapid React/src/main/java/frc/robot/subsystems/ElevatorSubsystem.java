package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ElevatorConstants.*;


public class ElevatorSubsystem extends SubsystemBase {
  public TalonFX elevatorMotorOne;
  static DoubleSolenoid ratchetPiston, anglePiston1, hookPiston1;
  public DigitalInput limitSwitch;
  
  public ElevatorSubsystem() {
    elevatorMotorOne = new TalonFX(elevatorMotorPort);
    ratchetPiston = new DoubleSolenoid(ratchetPistonPort[2], PneumaticsModuleType.CTREPCM, ratchetPistonPort[0], ratchetPistonPort[1]);
    anglePiston1 = new DoubleSolenoid(AnglePistonPort1[2], PneumaticsModuleType.CTREPCM, AnglePistonPort1[0], AnglePistonPort1[1]);
    hookPiston1 = new DoubleSolenoid(flipUpHookPort1[2] ,PneumaticsModuleType.CTREPCM, flipUpHookPort1[0], flipUpHookPort1[1]);

    elevatorMotorOne.configFactoryDefault();
    elevatorMotorOne.setInverted(true);
    elevatorMotorOne.setNeutralMode(NeutralMode.Brake);
    elevatorMotorOne.configOpenloopRamp(0.2, 30);

  }


  public double getEncoderPosition(){
    return elevatorMotorOne.getSelectedSensorPosition();
  }


  public void setElevatorMotor(double Speed) {
    elevatorMotorOne.set(ControlMode.PercentOutput, Speed);
    
 
  }

  public void setRatchetPiston(int pistonVal) {
    if (pistonVal == 0) {
      ratchetPiston.set(DoubleSolenoid.Value.kOff);
    } else if (pistonVal == 1) {
      ratchetPiston.set(DoubleSolenoid.Value.kReverse);
    } else if (pistonVal == -1) {
      ratchetPiston.set(DoubleSolenoid.Value.kForward);
    }
  }

  public void setDynamicPistons(int pistonVal) {
    if (pistonVal == 0) {
      anglePiston1.set(DoubleSolenoid.Value.kOff);
    } else if (pistonVal == -1) {
      anglePiston1.set(DoubleSolenoid.Value.kReverse);
    } else if (pistonVal == 1) {
      anglePiston1.set(DoubleSolenoid.Value.kForward);
    }
  }

  public void setStaticPistons(int pistonVal) {
    if (pistonVal == 0) {
      hookPiston1.set(DoubleSolenoid.Value.kOff);
    } else if (pistonVal == 1) {
      hookPiston1.set(DoubleSolenoid.Value.kReverse);
    } else if (pistonVal == -1) {
      hookPiston1.set(DoubleSolenoid.Value.kForward);
    }
  }

  public boolean getlimitSwitch(){
    return limitSwitch.get();
  }

  public void resetEncoders(){
    elevatorMotorOne.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
  
  }
}
