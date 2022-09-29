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
    public FINAL ROBOTWIDTH = 1.25;
    

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
    public void setPositon(robotX, robotY){
        double angle = Robot.m_driveSubsystem.getAngle();
        
        double roboAngle = angle - 90;
        double peakAngle = 180 - (robotAngle + 90);
        
        double setX = Math.sin(peakAngle)/Math.sin(90)/0.625;
        double setY = Math.sin(roboAngle)/Math.sin(90)/0.625;

        robotRX = robotX + setX;
        robotRY = robotY + setY;
        robotLX = robotX + setX;
        robotLY = robotY + setY;
    }

}
