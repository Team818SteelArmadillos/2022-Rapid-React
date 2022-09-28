package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class AutonDriveTurn extends ParallelCommandGroup {

  public AutonDriveTurn(double dist, double speed) {

    addCommands(
      new DriveDistanceHigh(dist),
      new AutonTurnTurret(speed)
    );
  }
}