package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ThreeBallAutonCommand extends SequentialCommandGroup {
  public ThreeBallAutonCommand() {

    addCommands(
      new AutonAutoIntakeCommand(29, 1.5),
      new TurnDrive(112.25),
      new AutonAutoShootCommand(-0.2),
      new AutonAutoIntakeCommand(115, 4),
      new TurnDrive(45),
      new AutonAutoShootCommand(0)


    );
  }
}

