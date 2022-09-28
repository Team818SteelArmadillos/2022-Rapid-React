// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class AutonDriveSpool extends ParallelCommandGroup {
  /** Creates a new DoubleTurnCommand. */
  public AutonDriveSpool(double dist, double rpm) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new driveDistance(dist),
      new AutonSpollCommmand(rpm)
      );
  }
}
