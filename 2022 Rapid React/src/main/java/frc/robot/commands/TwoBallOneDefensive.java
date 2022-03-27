package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TwoBallOneDefensive extends SequentialCommandGroup {
  public TwoBallOneDefensive() {

    addCommands(
      new ResetGyroCommand(),
      new AutonAutoIntakeCommand(33, 2),
      new TurnDrive(180),
      new DriveDistance(24),
      new AutonAutoShootCommand(0),
      new TurnDrive(180 - 90),
      new AutonAutoIntakeCommand(80, 2),
      new TurnDrive(180 - 90 - 90)
    );
  }
}

