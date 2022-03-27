package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ThreeBallAutonCommand extends SequentialCommandGroup {
  public ThreeBallAutonCommand() {

    addCommands(
      new ResetGyroCommand(),
      new AutonAutoIntakeCommand(33, 1.5),
      new TurnDrive(112.25),
      new AutonAutoShootCommand(-0.2),
      new AutonAutoIntakeCommand(70, 2.5),
      new TurnDrive(112.25 + 90),
      new DriveDistance(25),
      new AutonAutoShootCommand(-0.2)

    );
  }
}

