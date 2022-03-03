package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ThreeBallAutonCommand extends SequentialCommandGroup {
  public ThreeBallAutonCommand() {

    addCommands(
      new AutonIntakeCommandgroup(33),
      new TurnDrive(90),
      new AutonAutoShootCommand(0.5)
    );
  }
}

