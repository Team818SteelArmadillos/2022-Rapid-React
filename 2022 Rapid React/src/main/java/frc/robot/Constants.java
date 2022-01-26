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
    public static final int INTAKE_PORTS = 6;

    //Drive Constants
    public static final double WHEEL_DIAMETER = 6;
    public static final double ENCODER_GEAR_RATIO = 1;
    public static final int ENCODER_PULSES_PER_REVOLUTION = 2048;
    public static final int VELOCITY_CALCULATION_PER_SECOND = 10;
    public static final double distancePerPulse = wheelCircumference/(double) ENCODER_PULSES_PER_REVOLUTION;

    public static final double low = 21.67;
    public static final double high = 8.41;
}

    public static class motorports {

    public static final int intakeMotorPort = 5;
    public static final int indexMotorPort = 6;

    }

    public static final int JOYSTICK_PORT_LEFT = 0;
    public static final int JOYSTICK_PORT_RIGHT = 1;
    public static final double JOYSTICK_LEFT_DEADZONE_Y = 0.02;
    public static final double JOYSTICK_RIGHT_DEADZONE_Y = 0.02;

    public static class Pistons{
    public static final int[] shiftPistonPorts = {0, 1};

    }

    public static class LED {

    public static final int LEDLight = 6;

    }

}