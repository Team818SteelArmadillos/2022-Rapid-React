package frc.robot.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class FalconWrapper implements MotorController {
    public TalonFX motor;

    public FalconWrapper(TalonFX m){
        motor = m;
    }


    @Override
    public void set(double speed) {
        // TODO Auto-generated method stub
        motor.set(ControlMode.PercentOutput, speed);
    }

    @Override
    public double get() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setInverted(boolean isInverted) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean getInverted() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void stopMotor() {
        // TODO Auto-generated method stub
        
    }
    
}
