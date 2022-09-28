package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

public class AutonAutoIntakeCommand extends ParallelRaceGroup {

  public AutonAutoIntakeCommand(double dist, double time) {

    addCommands(
      new IntakeForTimeCommand(time),
      new driveDistance(dist)
    );
  }
}