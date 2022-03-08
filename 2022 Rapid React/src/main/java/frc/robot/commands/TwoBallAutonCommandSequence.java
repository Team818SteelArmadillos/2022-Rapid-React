package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TwoBallAutonCommandSequence extends SequentialCommandGroup {
  public TwoBallAutonCommandSequence() {

    addCommands(
      // new AutonAutoIntakeCommand(35),
      // new TurnDrive(180),
      // new AutonAutoShootCommand(0.5)
      // new driveDistance(-60)
      new TurnDrive(90)
    );
  }
}
