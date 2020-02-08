package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Reset extends Command {

    @Override
    protected void initialize() {
        System.out.println("Reseting Gyro");
        Robot.imu.reset();
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}