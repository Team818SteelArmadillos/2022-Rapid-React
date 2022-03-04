package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class WaitCommand extends CommandBase {
  Timer timer = new Timer();
  double time;
  public WaitCommand(double time) {
    this.time = time;
  }

  @Override
  public void initialize() {

    timer.reset();
    timer.start();

  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(time);
  }
}
