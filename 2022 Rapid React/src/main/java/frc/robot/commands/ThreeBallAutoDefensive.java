package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


public class ThreeBallAutoDefensive extends SequentialCommandGroup {
  public ThreeBallAutoDefensive() {
    addCommands(
      new ResetGyroCommand(),
      new AutonAutoIntakeCommand(33, 2),
      new TurnDrive(180),
      new DriveDistance(24),
      new AutonAutoShootCommand(0),
      new TurnDrive(180 - 45),
      new AutonAutoIntakeCommand(33, 2),
      new TurnDrive(0),
      new ExpelIntakeCommand(3)

    );
  }
}
