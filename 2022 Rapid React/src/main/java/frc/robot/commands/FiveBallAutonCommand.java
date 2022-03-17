package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class FiveBallAutonCommand extends SequentialCommandGroup {
  public FiveBallAutonCommand() {

    addCommands(
      new AutonAutoIntakeCommand(33, 3),
      new TurnDrive(122.25),
      new AutonAutoShootCommand(0),
      new AutonAutoIntakeCommand(115, 4),
      new TurnDrive(20),
      new AutonAutoShootCommand(0),
      new TurnDrive(-62.85),
      new AutonAutoIntakeCommand(157.5, 4),
      new IntakeForTimeCommand(2),
      new TurnDrive(145),
      new DriveDistance(100),
      new AutonAutoShootCommand(0)

    );
  }
}

