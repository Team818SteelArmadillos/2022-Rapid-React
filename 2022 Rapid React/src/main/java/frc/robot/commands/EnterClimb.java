package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class EnterClimb extends SequentialCommandGroup {

  public EnterClimb() {

    addCommands(
      new ZeroTurretCommand(), 
      new ElevatorCommandTesting()

    );
  }
  
}
