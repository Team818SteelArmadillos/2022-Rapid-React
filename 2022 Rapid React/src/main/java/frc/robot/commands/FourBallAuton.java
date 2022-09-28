package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class FourBallAuton extends SequentialCommandGroup {
  public FourBallAuton() {

    addCommands(
      new ResetGyroCommand(),
      new AutonAutoIntakeCommand(44, 1.5),
      new DoubleTurnCommand(160, -0.05, 1400),
      new AutonAutoShootCommand(-0.2),
      new TurnDrive(10),
      new AutonAutoIntakeCommand(151, 3.5),
      // new IntakeForTimeCommand(1),
      new AutonAutoIntakeCommand(-150, 3.5),
      new DoubleTurnCommand(200, -0.05, 1700),
      new AutonAutoShootCommand(0.2)

    );
  }
}

