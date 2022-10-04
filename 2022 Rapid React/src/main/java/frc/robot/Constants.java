package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

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
    public static final int[] MOTOR_PORTS_LEFT = {14, 2};
    public static final int[] MOTOR_PORTS_RIGHT = {3, 4};
    public static final boolean LEFT_INVERTED = true;
    public static final double RAMP_RATE = 0.5;
    public static final double wheelCircumference = 4 * Math.PI;
    public static final int[] shiftPistonPorts = {2, 3, 1};
    public static final double driveP = 0.05;
    public static final double driveI = 0.0004;
    public static final double driveD = 0.006;
    public static final double brakeP = 0.2;
    public static final double brakeI = 0;
    public static final double brakeD = 0;



    //Drive Constants
    public static final int ENCODER_PULSES_PER_REVOLUTION = 2048;
    public static final int VELOCITY_CALCULATION_PER_SECOND = 10;
    public static final double distancePerPulse = wheelCircumference/(double) ENCODER_PULSES_PER_REVOLUTION;

    public static final double low = 14.44;
    public static final double high = 7.74;

    public static final int GYRO_PORTS = 0;

    }   

    public static class ShooterConstants {

    public static final int[] SHOOTER_PORTS = {5, 6};
    public static final boolean SHOOTER_INVERTED = true; 
    public static final double velocityCalculationsPerSecond = 10;
    public static final double encoderPulsesPerRevolution = 2048; 
    public static final int TURRET_MOTOR = 7;

    public static final int[] hoodPistonPort = {4, 5, 0};

    public static final double shooterFrontP = 0.00075;
    public static final double shooterFrontI = 0.003;
    public static final double shooterFrontD = 0.00001;

    public static final double shooterBackP = 0.00039882;
    public static final double shooterBackI = 0.00377562;
    public static final double shooterBackD = 0.000005;

    public static final double turretGearRatio = 55;

    }

    public static class motorports {

    public static final int intakeMotorPort = 8;
    public static final int indexMotorPortConveyor = 9;
    public static final int indexMotorPortUpper = 10;
    public static final boolean INDEX_INVERTED = true;
    }

    public static class ElevatorConstants {

    public static final int elevatorMotorPort = 11;    
    public static final int[] ratchetPistonPort = {2, 3, 0};
    public static final int[] AnglePistonPort1 = {0, 1, 0};
    public static final int[] flipUpHookPort1 = {4, 5, 1};
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

    public static final int[] intakePistonPortOne = {0, 1, 1};


    }

    public static class SensorPorts {

    public static final int indexSensorFront = 9;
    public static final int indexSensorBack = 8;  

    }

    public static class LED {

    public static final int LEDLight = 6;

    }  

    public static class Autonpath {
    //get from system identification 
    public static final double ksVolts = 0.22;
    public static final double kvVoltSecondsPerMeter = 1.98;
    public static final double kaVoltSecondsSquaredPerMeter = 0.2;

    public static final double kPDriveVel = 8.5;

    public static final double kTrackwidthMeters = 0.69;
    public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;

    //noise ratios for the model and for the robot
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;

    }

}