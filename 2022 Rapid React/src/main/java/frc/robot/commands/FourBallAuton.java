package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class FourBallAuton extends SequentialCommandGroup {
  public FourBallAuton() {

    addCommands(
      new ResetGyroCommand(),
      new AutonAutoIntakeCommand(44, 1.5),
      new DoubleTurnCommand(160, -0.05, 1400),
      new AutonAutoShootCommand(-0.2),
      new TurnDrive(16),
      new AutonAutoIntakeCommand(145, 4),
      // new IntakeForTimeCommand(1),
      new TurnDrive(200),
      new DriveDistance(125),
      new AutonAutoShootCommand(0.2)

    );
  }
}

