package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

public class ExpelIntakeCommand extends ParallelRaceGroup {
  public ExpelIntakeCommand(double time) {

    addCommands(
      new AutoBackIntakeCommand(),
      new WaitCommand(time)
    );
  }
}
