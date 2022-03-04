package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class FiveBallAutonCommand extends SequentialCommandGroup {
  public FiveBallAutonCommand() {

    addCommands(
      new AutonAutoIntakeCommand(33),
      new TurnDrive(122.25),
      new AutonAutoShootCommand(0.3),
      new AutonAutoIntakeCommand(115),
      new TurnDrive(20),
      new AutonAutoShootCommand(0.3),
      new TurnDrive(-62.85),
      new AutonAutoIntakeCommand(157.5),
      new IntakeForTimeCommand(),
      new TurnDrive(145),
      new driveDistance(100),
      new AutonAutoShootCommand(0.3)

    );
  }
}

