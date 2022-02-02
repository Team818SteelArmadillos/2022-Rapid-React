package frc.robot;
public class Constants {

public static class oi {
    //Joysticks/Gamepad
    public static final int leftJoystickPort = 0;
    public static final int rightJoystickPort = 1;
    public static final int gamePadPort = 2;
    public static final int gamePadDriverPort = 3;
    }

public static class DriveConstants {
    //Drive Motors
    public static final int[] MOTOR_PORTS_LEFT = {1, 2};
    public static final int[] MOTOR_PORTS_RIGHT = {3, 4};
    public static final boolean LEFT_INVERTED = true;
    public static final double RAMP_RATE = 0.5;
    public static final double wheelCircumference = 6*Math.PI;
    public static final int[] shiftPistonPorts = {0, 1};

    //Drive Constants
    public static final double WHEEL_DIAMETER = 6;
    public static final double ENCODER_GEAR_RATIO = 1;
    public static final int ENCODER_PULSES_PER_REVOLUTION = 2048;
    public static final int VELOCITY_CALCULATION_PER_SECOND = 10;
    public static final double distancePerPulse = wheelCircumference/(double) ENCODER_PULSES_PER_REVOLUTION;

    public static final double low = 21.67;
    public static final double high = 8.41;

    }   

    public static class ShooterConstants {

    public static final int[] SHOOTER_PORTS = {5, 6};
    public static final boolean SHOOTER_INVERTED = true; 
    public static final double velocityCalculationsPerSecond = 10;
    public static final double encoderPulsesPerRevolution = 4096; 
    public static final int TURRET_MOTOR = 7;

    public static final double p = 0.0009;
    public static final double i = 0.00537;
    public static final double d = 0.00003657;

    }

    public static class motorports {

    public static final int intakeMotorPort = 5;
    public static final int indexMotorPortConveyor = 6;
    public static final int indexMotorPortUpper = 7;

    }

    public static class ElevatorConstants {

    public static final int elevatorMotorPort = 5;    
    public static final int[] ratchetPistonPort = {6, 7};
    public static final int[] AnglePistonPort1 = {8, 9};
    public static final int[] AnglePistonPort2 = {10, 11};
    public static final int[] flipUpHookPort1 = {12, 13};
    public static final int[] flipUpHookPort2 = {14, 15};
    public static final double P = 0.01;
    public static final double I = 0;
    public static final double D = 0;
    public static final double[] ElevatorHeights = {0, 100, 200, 300};
    }

    public static class LEDNumbers {

    public static final int LEDs_Full = 200;
    public static final int LED_CANdle = 7;
    public static final int half_LEDS = 100;

    }

    public static final int JOYSTICK_PORT_LEFT = 0;
    public static final int JOYSTICK_PORT_RIGHT = 1;
    public static final double JOYSTICK_LEFT_DEADZONE_Y = 0.02;
    public static final double JOYSTICK_RIGHT_DEADZONE_Y = 0.02;

    public static class Pistons {

    public static final int[] shiftPistonPorts = {0, 1};
    public static final int[] intakePistonPortOne = {2, 3};

    }

    public static class SensorPorts {

    public static final int indexSensorFront = 0;
    public static final int indexSensorBack = 1;  

    }

    public static class LED {

    public static final int LEDLight = 6;

    }

    public static int intakeMotorPort;

    

}