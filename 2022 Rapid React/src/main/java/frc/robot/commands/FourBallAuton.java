package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class FourBallAuton extends SequentialCommandGroup {
  public FourBallAuton() {

    addCommands(
      new ResetGyroCommand(),
      new AutonAutoIntakeCommand(44, 1.5),
      new TurnDrive(160),
      new AutonAutoShootCommand(-0.2),
      new TurnDrive(20),
      new AutonAutoIntakeCommand(150, 3.5),
      new IntakeForTimeCommand(1),
      new TurnDrive(180),
      new DriveDistance(70),
      new AutonAutoShootCommand(0)

    );
  }
}

