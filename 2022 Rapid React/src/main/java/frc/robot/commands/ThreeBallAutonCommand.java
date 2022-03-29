package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ThreeBallAutonCommand extends SequentialCommandGroup {
  public ThreeBallAutonCommand() {

    addCommands(
      new ResetGyroCommand(),
      new AutonAutoIntakeCommand(34, 1.5),
      new TurnDrive(110.25),
      new AutonAutoShootCommand(-0.2),
      new AutonAutoIntakeCommand(83, 2.5),
      new TurnDrive(112.25 + 90),
      // new DriveDistance(28),
      new AutonAutoShootCommand(0.2)

    );
  }
}

