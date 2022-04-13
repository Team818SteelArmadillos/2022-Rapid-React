package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class AutonZeroDrive extends ParallelCommandGroup {

  public AutonZeroDrive(double dist, double time) {

    addCommands(
      new IntakeForTimeCommand(time),
      new DriveDistanceHigh(dist),
      new ZeroTurretCommand()
    );
  }
}