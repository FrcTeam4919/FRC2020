package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class limelightSeek extends Command {
    double kp = -0.1f;
    double min_command = 0.05f;
    double tx = Limelight.tx.getDouble(0.0);
    double y = OI.joystick.getY();
    double x = OI.joystick.getX();

    protected void execute(){
        double heading_error = -tx;
        double steering_adjust = 0.0f;
        if(tx > 1.0){
            steering_adjust = kp*heading_error - min_command;
        } else if(tx < 1.0){
            steering_adjust = kp*heading_error + min_command;
        }
        Drivetrain.westCoastTankDrive.arcadeDrive(x+=steering_adjust, y-=steering_adjust);
    }

    protected boolean isFinished(){
        return false;
    }
}