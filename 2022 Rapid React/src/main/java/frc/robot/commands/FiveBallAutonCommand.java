package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class FiveBallAutonCommand extends SequentialCommandGroup {
  public FiveBallAutonCommand() {

    addCommands(
      new ResetGyroCommand(),
      new AutonAutoIntakeCommand(34, 1.5),
      new TurnDrive(110.25),
      new AutonAutoShootCommand(-0.2),
      new AutonAutoIntakeCommand(83, 2.5),
      new TurnDrive(112.25 + 90),
      // new DriveDistance(28),
      new AutonAutoShootCommand(0.2),
      // grab the two balls, no time to shoot
      new TurnDrive(112.25 + 90 -62.85),
      new AutonAutoIntakeCommand(157.5, 4),
      new IntakeForTimeCommand(1),
      new TurnDrive(145),
      new DriveDistance(100),
      new AutonAutoShootCommand(0)

    );
  }
}

