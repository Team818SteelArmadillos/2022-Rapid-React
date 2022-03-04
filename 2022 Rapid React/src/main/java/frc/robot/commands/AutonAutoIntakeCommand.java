package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class AutonAutoIntakeCommand extends ParallelCommandGroup {

  public AutonAutoIntakeCommand(double dist) {

    addCommands(
      new AutoIntakeCommand(),
      new driveDistance(dist)
    );
  }
}