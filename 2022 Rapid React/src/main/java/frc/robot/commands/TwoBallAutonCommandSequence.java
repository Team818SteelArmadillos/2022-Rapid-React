package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


public class TwoBallAutonCommandSequence extends SequentialCommandGroup {
  public TwoBallAutonCommandSequence() {
    addCommands(
      new driveDistance(240)
        //new ResetGyroCommand(),
        //new AutonAutoIntakeCommand(41, 2), //33
        //new TurnDrive(180),
        //new driveDistance(24), //24
        //new AutonAutoShootCommand(0)
    );
  }
}
