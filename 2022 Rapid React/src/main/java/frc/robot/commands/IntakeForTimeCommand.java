package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

public class IntakeForTimeCommand extends ParallelRaceGroup {
  public IntakeForTimeCommand(double time) {

    addCommands(
      new AutoIntakeCommand(),
      new WaitCommand(time)
    );
  }
}
