
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import static frc.robot.Constants.motorports.*;
import static frc.robot.Constants.Pistons.*;

public class IntakeSubsystem extends SubsystemBase {
  private TalonFX MotorIntake;
  static DoubleSolenoid intakePiston;
  double intakePistonVal;

  
  public IntakeSubsystem() {
    MotorIntake = new TalonFX(intakeMotorPort);
    intakePiston = new DoubleSolenoid(intakePistonPortOne[2], PneumaticsModuleType.CTREPCM, intakePistonPortOne[0], intakePistonPortOne[1]);
    MotorIntake.setInverted(true);
  }

  public void setIntakeMotor(double Speed) {
    MotorIntake.set(ControlMode.PercentOutput, -Speed);
  }

  public void setIntakePosition(double intakePistonVal) {
    if(intakePistonVal == 0.5) {
      intakePiston.set(DoubleSolenoid.Value.kReverse);
    } else if (intakePistonVal == 1) {
      intakePiston.set(DoubleSolenoid.Value.kForward);
    }

    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setIntake(double power) {
    MotorIntake.set(ControlMode.PercentOutput, power);
  }
}