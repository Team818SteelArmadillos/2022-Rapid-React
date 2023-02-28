package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DriveConstants.*;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;
import frc.robot.Robot;
import frc.robot.Constants.Autonpath;
import frc.robot.Constants.DriveConstants;
import frc.robot.util.FalconWrapper;

import static frc.robot.Constants.Autonpath.*;

public class AutoDriveSubsystem extends SubsystemBase {
 
    // private TalonFX talonLeft2, talonRight2;
    // private TalonFX talonLeft1, talonRight1;
    // private int leftOffset = 0;
    // private int rightOffset = 0;
    // // The motors on the left side of the drive.

    // private final DifferentialDrive m_drive;

    // // The gyro sensor
    // private final PigeonIMU m_pigeon = new PigeonIMU(Robot.m_IndexSubsystem.conveyorMotor);
    // //Grabs the index conveyormotor due to it being the closet motor port 

    // // Odometry class for tracking robot pose
    // private final DifferentialDriveOdometry m_odometry;

    // /** Creates a new DriveSubsystem. */
    // public AutoDriveSubsystem() {
    //   talonLeft1 = new TalonFX(MOTOR_PORTS_LEFT[0]);
    //   talonRight1 = new TalonFX(MOTOR_PORTS_RIGHT[0]);
    //   talonLeft2 = new TalonFX(MOTOR_PORTS_LEFT[1]);
    //   talonRight2 = new TalonFX(MOTOR_PORTS_RIGHT[1]);

      
    //   talonLeft1.configFactoryDefault();
    //   talonLeft1.setInverted(LEFT_INVERTED);
    //   talonLeft1.configOpenloopRamp(RAMP_RATE);
    //   talonLeft1.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 10, 15, 0.5));

    //   talonRight1.configFactoryDefault();
    //   talonRight1.setInverted(!LEFT_INVERTED);
    //   talonRight1.configOpenloopRamp(RAMP_RATE);
    //   talonRight1.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 80, 1));

    //   talonLeft2.configFactoryDefault();
    //   talonLeft2.setInverted(LEFT_INVERTED);
    //   talonLeft2.follow(talonLeft1);
    //   talonLeft2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 80, 1));
        
    //   talonRight2.configFactoryDefault();
    //   talonRight2.setInverted(!LEFT_INVERTED);
    //   talonRight2.follow(talonRight1);
    //   talonRight2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 80, 1));

    //   m_drive = new DifferentialDrive(new FalconWrapper(talonLeft1), new FalconWrapper(talonRight1));

    //   // Sets the distance per pulse for the encoders

    //   resetEncoders();
    //   m_odometry = new DifferentialDriveOdometry(new Rotation2d(m_pigeon.getYaw()));
      
    // }

    // @Override
    // public void periodic() {
    //   // Update the odometry in the periodic block
    //   double leftpositionmeters = -(talonLeft1.getSelectedSensorPosition() - leftOffset) * distancePerPulse / high;
    //   double rightpositionmeters = -(talonRight1.getSelectedSensorPosition() - rightOffset) * distancePerPulse / high;
    //   m_odometry.update(
    //     new Rotation2d(m_pigeon.getYaw()), leftpositionmeters, rightpositionmeters);
    // }

    // /**
    //  * Returns the currently-estimated pose of the robot.
    //  *
    //  * @return The pose.
    //  */
    // public Pose2d getPose() {
    //   return m_odometry.getPoseMeters();
    // }

    // /**
    //  * Returns the current wheel speeds of the robot.
    //  *
    //  * @return The current wheel speeds.
    //  */
    // public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    //   return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
    // }

    // /**
    //  * Resets the odometry to the specified pose.
    //  *
    //  * @param pose The pose to which to set the odometry.
    //  */
    // public void resetOdometry(Pose2d pose) {
    //   resetEncoders();
    //   m_odometry.resetPosition(pose, new Rotation2d(m_pigeon.getYaw()));
    // }

    // /**
    //  * Drives the robot using arcade controls.
    //  *
    //  * @param fwd the commanded forward movement
    //  * @param rot the commanded rotation
    //  */
    // public void arcadeDrive(double fwd, double rot) {
    //   m_drive.arcadeDrive(fwd, rot);
    // }

    // /**
    //  * Controls the left and right sides of the drive directly with voltages.
    //  *
    //  * @param leftVolts the commanded left output
    //  * @param rightVolts the commanded right output
    //  */
    // public void tankDriveVolts(double leftVolts, double rightVolts) {
    //   m_leftMotors.setVoltage(leftVolts);
    //   m_rightMotors.setVoltage(rightVolts);
    //   m_drive.feed();
    // }

    // /** Resets the drive encoders to currently read a position of 0. */
    // public void resetEncoders() {
    //   talonLeft1.setSelectedSensorPosition(0);
    //   talonLeft2.setSelectedSensorPosition(0);
    //   talonRight1.setSelectedSensorPosition(0);
    //   talonRight2.setSelectedSensorPosition(0);
    // }

    // /**
    //  * Gets the average distance of the two encoders.
    //  *
    //  * @return the average of the two encoder readings
    //  */
    // public double getAverageEncoderDistance() {
    //   return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
    // }

    // /**
    //  * Gets the left drive encoder.
    //  *
    //  * @return the left drive encoder
    //  */
    // public Encoder getLeftEncoder() {
    //   return m_leftEncoder;
    // }

    // /**
    //  * Gets the right drive encoder.
    //  *
    //  * @return the right drive encoder
    //  */
    // public Encoder getRightEncoder() {
    //   return m_rightEncoder;
    // }

    // /**
    //  * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
    //  *
    //  * @param maxOutput the maximum output to which the drive will be constrained
    //  */
    // public void setMaxOutput(double maxOutput) {
    //   m_drive.setMaxOutput(maxOutput);
    // }

    // /** Zeroes the heading of the robot. */
    // public void zeroHeading() {
    //   m_pigeon.setYaw(0);
    // }

    // /**
    //  * Returns the heading of the robot.
    //  *
    //  * @return the robot's heading in degrees, from -180 to 180
    //  */
    // public double getHeading() {
    //   return m_pigeon.getYaw();
    // }

    // /**
    //  * Returns the turn rate of the robot.
    //  *
    //  * @return The turn rate of the robot, in degrees per second
    //  */
    // public double getTurnRate() {
    //   return -m_gyro.getRate();
    // }
    
}
