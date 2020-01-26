// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.subsystems;

import frc.robot.Robot;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Drivetrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private VictorSP left;
private VictorSP right;
public DifferentialDrive westCoastTankDrive;
public Joystick Joystick = new Joystick(0);
//public Joystick joystick2;

private static final double kAngleSetpoint = 0.0;
private static final double kP = 0.005; // propotional turning constant


//The gain for a simple P Loop

//The heading of the robot when starting the motion
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Drivetrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        left = new VictorSP(0);
        addChild("Left",left);
        left.setInverted(true);
        

        right = new VictorSP(1);
        addChild("Right",right);
        right.setInverted(false);
        
        westCoastTankDrive = new DifferentialDrive(left, right);
        addChild("West Coast Tank Drive",westCoastTankDrive);
        westCoastTankDrive.setSafetyEnabled(true);
        westCoastTankDrive.setExpiration(0.1);
        westCoastTankDrive.setMaxOutput(1.0);

        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    @Override
    public void periodic() {
        // Put code here to be run every loop

        double y = Joystick.getY();
        double x = Joystick.getX();
        double z = Joystick.getZ();
        y = correctErrors(y);
        x = correctErrors(x);
        z  = correctErrors(z);

        westCoastTankDrive.arcadeDrive(x, -y);
    }

    public double correctErrors(double value) {
        if (Math.abs(value) < 0.2) {
            value = 0;
        }
        return value;
    }

    public void moveForward() {
        westCoastTankDrive.arcadeDrive(0.5, 0);
    }

    public void moveBackward() {
        westCoastTankDrive.arcadeDrive(-0.5, 0);
    }

    /*public void teleopPeriodic() {
        double turningValue = (kAngleSetpoint - imu.getAngle()) * kP;
		// Invert the direction of the turn if we are going backwards
		turningValue = Math.copySign(turningValue, joystick2.getY());
		westCoastTankDrive.arcadeDrive(joystick2.getY(), turningValue);
    }*/

    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

