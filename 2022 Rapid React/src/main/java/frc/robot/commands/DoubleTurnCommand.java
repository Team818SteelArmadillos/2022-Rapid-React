// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class DoubleTurnCommand extends ParallelCommandGroup {
  /** Creates a new DoubleTurnCommand. */
  public DoubleTurnCommand( double angle, double speed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new TurnDrive(angle),
      new AutonTurnTurret(speed)
      );
  }
}
