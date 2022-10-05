// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

/** Add your docs here. */
public class OdometrySubsystem extends SubsystemBase{
    public double robotRX;
    public double robotRY;
    public double robotLX;
    public double robotLY;
    
    //Seperation between wheels physcially throughout the program will allow for better correction programming later. Ie: if distance between left wheel and right wheel > 1.25, average points and recalibrate with gyro to re-establish points.
    //Allows for more accuracy and redundancy, necessary becuase accuracy of left and right velocity could vary independently.
    public void mapChange(){
        double angle = Robot.m_driveSubsystem.getAngle();
        double leftVlo = Robot.m_driveSubsystem.getLeftVelocity();
        double rightVlo = Robot.m_driveSubsystem.getRightVelocity();

        double leftDistance = leftVlo * .02;
        double rightDistance = rightVlo * .02;
        
        double deltaLX = Math.sin(90-angle)/Math.sin(90)/leftDistance;
        double deltaLY = Math.sin(angle)/Math.sin(90)/leftDistance;

        double deltaRX = Math.sin(90-angle)/Math.sin(90)/rightDistance;
        double deltaRY = Math.sin(angle)/Math.sin(90)/rightDistance;

        robotLX += deltaLX;
        robotLY += deltaLY;
        robotRX += deltaRX;
        robotRY += deltaRY;



        SmartDashboard.putNumber("LeftWheelCoordinateX", robotLX);
        SmartDashboard.putNumber("LeftWheelCoordinateY", robotLY);
        SmartDashboard.putNumber("RightWheelCoordinateX", robotRX);
        SmartDashboard.putNumber("RightWheelCoordinateY", robotRY);
    }
    public void setPositon(double robotX, double robotY){
        //can be used to set position for the start of the match, for recalibration purposes if distance between wheels is greater than 1.25, or if requested by the limelight.
        double angle = Robot.m_driveSubsystem.getAngle();
        
        double roboAngle = angle - 90;
        double peakAngle = 180 - (angle + 90);
        
        double setX = Math.sin(peakAngle)/Math.sin(90)/0.625;
        double setY = Math.sin(roboAngle)/Math.sin(90)/0.625;

        robotRX = robotX + setX;
        robotRY = robotY + setY;
        robotLX = robotX + setX;
        robotLY = robotY + setY;
    }
    public void limelightCalibration(){
        double limelightAngle = Robot.m_TurretSubsystem.getCurrentTurretPosition() + Robot.m_driveSubsystem.getAngle();
        double limelightDistance = 69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180);
        
    }

}
