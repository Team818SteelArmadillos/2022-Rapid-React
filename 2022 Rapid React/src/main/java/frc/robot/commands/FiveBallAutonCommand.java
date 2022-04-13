package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class FiveBallAutonCommand extends SequentialCommandGroup {
  public FiveBallAutonCommand() {

    addCommands(
      new ResetGyroCommand(),
      new AutonAutoIntakeCommand(33, 1.5),
      new DoubleTurnCommand(110.25, -0.15, 1400),
      new AutonAutoShootCommand(0),
      new AutonZeroDrive(113, 2.5),
      new DoubleTurnCommand(157.25, -0.2, 1300),
      new AutonAutoShootCommand(-0.2),
      // grab the two balls, no time to shoot
      new TurnDrive(112.25 + 90 -120),
      new AutonZeroDrive(120, 4),
      // new IntakeForTimeCommand(1),
      new TurnDrive(112.25 + 90 -127 + 180),
      new AutonDriveSpool(100, 1800),
      new AutonAutoShootCommand(-0.2)

    );
  }
}

