package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

public class DriveForTime extends ParallelRaceGroup {
  public DriveForTime(double time) {

    addCommands(
      new DriveTime(),
      new AutonSpollCommmand(2400),
      new WaitCommand(time)
    );
  }
}
