// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
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
        
        double deltaLX = leftDistance * Math.cos(angle);
        double deltaLY = leftDistance * Math.sin(angle);

        double deltaRX = rightDistance * Math.cos(angle);
        double deltaRY = rightDistance* Math..sin(angle);
        robotLX += deltaLX;
        robotLY += deltaLY;
        robotRX += deltaRX;
        robotRY += deltaRY;



        SmartDashboard.putNumber("LeftWheelCoordinateX", robotLX);
        SmartDashboard.putNumber("LeftWheelCoordinateY", robotLY);
        SmartDashboard.putNumber("RightWheelCoordinateX", robotRX);
        SmartDashboard.putNumber("RightWheelCoordinateY", robotRY);
    }
    public void setPosition(double robotX, double robotY){
        //can be used to set position for the start of the match, for recalibration purposes if distance between wheels is greater than 1.25, or if requested by the limelight.
        double angle = Robot.m_driveSubsystem.getAngle();
        
        double roboAngle = angle - 90;
        double peakAngle = 180 - (angle + 90);
        
        double setX = Math.sin(peakAngle)/Math.sin(90)/(Constants.DriveConstants.ROBOTWIDTH/2);
        double setY = Math.sin(roboAngle)/Math.sin(90)/(Constants.DriveConstants.ROBOTWIDTH/2);

        robotRX = robotX + setX;
        robotRY = robotY + setY;
        robotLX = robotX + setX;
        robotLY = robotY + setY;
    }
    public void limelightCalibration(){
        double limelightAngle = Robot.m_TurretSubsystem.getCurrentTurretPosition() + Robot.m_driveSubsystem.getAngle();
        double limelightDistance = 69.3142/Math.tan((Robot.m_shootervision.getY()+39.78)*Math.PI/180);

        double RobotX = (robotRX + robotLX)/2;
        double RobotY = (robotRY + robotLY)/2;
        
        double positiveX = (-Math.pow(Math.tan(limelightAngle), 2)*RobotX + Math.tan(limelightAngle) * RobotY + Math.sqrt(16 * Math.pow(Math.tan(limelightAngle),2)
        + 8 * Math.pow(Math.tan(limelightAngle), 2) * limelightDistance + Math.pow(Math.tan(limelightAngle), 2) * Math.pow(limelightDistance, 2)
        - Math.pow(Math.tan(limelightAngle), 2) * Math.pow(RobotX, 2) - Math.pow(RobotY, 2) + 2 * Math.tan(limelightAngle) * RobotX * RobotY + 16 + 8
        * limelightDistance + Math.pow(limelightDistance, 2)))/(Math.pow(Math.tan(limelightAngle), 2) + 1);

        double negativeX = (-Math.pow(Math.tan(limelightAngle), 2)*RobotX + Math.tan(limelightAngle) * RobotY - Math.sqrt(16 * Math.pow(Math.tan(limelightAngle),2)
        + 8 * Math.pow(Math.tan(limelightAngle), 2) * limelightDistance + Math.pow(Math.tan(limelightAngle), 2) * Math.pow(limelightDistance, 2)
        - Math.pow(Math.tan(limelightAngle), 2) * Math.pow(RobotX, 2) - Math.pow(RobotY, 2) + 2 * Math.tan(limelightAngle) * RobotX * RobotY + 16 + 8
        * limelightDistance + Math.pow(limelightDistance, 2)))/(Math.pow(Math.tan(limelightAngle), 2) + 1);

        double positiveY = Math.sqrt(Math.pow(-positiveX, 2) + Math.pow(4 + limelightDistance, 2));
        double negativeY = Math.sqrt(Math.pow(-negativeX, 2) + Math.pow(4 + limelightDistance, 2));

        double deltaPositive = ((RobotX - positiveX) + (RobotY - positiveY));
        double deltaNegative = ((RobotX - negativeX) + (RobotY - negativeY));

        if(deltaPositive > 5 || deltaNegative > 5){
            if(deltaPositive < deltaNegative){
                setPosition(positiveX, positiveY);
            }else{
                setPosition(negativeX, negativeY);
            }
        }

    }

}
