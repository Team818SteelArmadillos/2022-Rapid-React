package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ThreeBallAutonCommand extends SequentialCommandGroup {
  public ThreeBallAutonCommand() {

    addCommands(
      new AutonAutoIntakeCommand(33),
      new TurnDrive(122.25),
      new AutonAutoShootCommand(0.3),
      new AutonAutoIntakeCommand(115),
      new TurnDrive(45),
      new AutonAutoShootCommand(0.3)


    );
  }
}

