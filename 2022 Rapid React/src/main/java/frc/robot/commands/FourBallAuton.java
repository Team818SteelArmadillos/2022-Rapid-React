package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class FourBallAuton extends SequentialCommandGroup {
  public FourBallAuton() {

    addCommands(
      new ResetGyroCommand(),
      new AutonAutoIntakeCommand(44, 1.5),
      new DoubleTurnCommand(160, -0.05, 1400),
      new AutonAutoShootCommand(-0.2),
      new TurnDrive(13),
      new AutonAutoIntakeCommand(147, 3.5),
      // new IntakeForTimeCommand(1),
      new TurnDriveHigh(200),
      new DriveForTime(1),
      new AutonAutoShootCommand(0.2)

    );
  }
}

