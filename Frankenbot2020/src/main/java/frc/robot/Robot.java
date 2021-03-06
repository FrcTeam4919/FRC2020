// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.first.wpilibj.util.Color;

//import com.revrobotics.ColorSensorV3;
//import com.revrobotics.ColorMatchResult;
//import com.analog.adis16448.frc.ADIS16448_IMU;
//import com.revrobotics.ColorMatch;
//import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.cscore.UsbCamera;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.commands.*;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

    /*private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();
    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.540, 0.250);
    private final Color kRedTarget = ColorMatch.makeColor(0.450, 0.380, 0.170);
    private final Color kYellowTarget = ColorMatch.makeColor(0.320, 0.525, 0.150);*/
    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();
    //public static final ADIS16448_IMU imu = new ADIS16448_IMU();
     //private static final double kAngleSetpoint = 0.0;
     // propotional turning constant
    
    //GYROTHINGS
    /*double leftSlow = 0.24;
    double rightSlow = -0.24;
    double rotateSpeed = 0.35;
    double rotateSpeedSlow = 0.25;*/



    public static OI oi;
    
    //CameraServer server;
    //CameraServer server2;
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drivetrain drivetrain;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        /*m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);*/
        //server = CameraServer.getInstance();
        //server2 = CameraServer.getInstance();
        //UsbCamera camera = server.startAutomaticCapture();
        //UsbCamera camera2 = server2.startAutomaticCapture();

        //camera.setResolution(640, 480);
        //camera2.setResolution(640, 480);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrain = new Drivetrain();
        //imu.calibrate();
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        // (which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_RobotBuilder);

        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Auto mode", chooser);
        Drivetrain.encoder.setDistancePerPulse(1.0/280.0);
        
    }

    @Override
    public void robotPeriodic() {
        /*m_colorMatcher.setConfidenceThreshold(0.95);
        Color detectedColor = m_colorSensor.getColor();
        String colorString;
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
        if (match.color == kBlueTarget) {
            colorString = "Blue";
        } else if (match.color == kRedTarget) {
            colorString = "Red";
        } else if (match.color == kYellowTarget) {
            colorString = "Yellow";
        } else if (match.color == kGreenTarget) {
            colorString = "Green";
        } else {
            colorString = "Unknown";
        }
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);
        double IR = m_colorSensor.getIR();
        SmartDashboard.putNumber("IR", IR);*/
        //int proximity = m_colorSensor.getProximity();
        //SmartDashboard.putNumber("Proximity", proximity);
        //SmartDashboard.putNumber("Gyro", imu.getAngle());
        SmartDashboard.putNumber("Encoder Distance", Drivetrain.encoder.getDistance());
        SmartDashboard.putNumber("Encoder Rate", Drivetrain.encoder.getRate());
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        Drivetrain.encoder.reset();
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        if(Drivetrain.encoder.getDistance() < 5){
            Drivetrain.encoderM.set(0.1);
        } else {
            Drivetrain.encoderM.set(0.0);
        }
        /*if (Math.abs(imu.getAngle()) <= 3) {
            Drivetrain.left.set(leftSlow - (imu.getAngle()) / 15);
            Drivetrain.right.set(rightSlow - (imu.getAngle()) / 15);
           } else if (Math.abs(imu.getAngle()) < 10) {
            if (imu.getAngle() > 0) {
            Drivetrain.left.set(leftSlow);
            Drivetrain.right.set(1.1 * rightSlow);
            } else if (imu.getAngle() < 0) {
            Drivetrain.left.set(1.1 * leftSlow);
            Drivetrain.right.set(rightSlow);
            }
           } else
            if (imu.getAngle() > 0) {
             while (imu.getAngle() > 10 && isAutonomous()) {
            Drivetrain.left.set(-rotateSpeed);
            Drivetrain.right.set(-rotateSpeed);
             }
            while (imu.getAngle() > 0 && isAutonomous()) {
            Drivetrain.left.set(-rotateSpeedSlow);
            Drivetrain.right.set(-rotateSpeedSlow);
            }
            while (imu.getAngle() < 0 && isAutonomous()) {
            Drivetrain.left.set(rotateSpeedSlow);
            Drivetrain.right.set(rotateSpeedSlow);
            }
           } else {
            while (imu.getAngle() < -10 && isAutonomous()) {
            Drivetrain.left.set(rotateSpeed);
            Drivetrain.right.set(rotateSpeed);
            }
            while (imu.getAngle() < 0 && isAutonomous()) {
            Drivetrain.left.set(rotateSpeedSlow);
            Drivetrain.right.set(rotateSpeedSlow);
            }
            while (imu.getAngle() > 0 && isAutonomous()) {
            Drivetrain.left.set(-rotateSpeedSlow);
            Drivetrain.right.set(-rotateSpeedSlow);
            }
           }*/

        /*double error = -imu.getRate();

        drivetrain.westCoastTankDrive.arcadeDrive(.5 + kP * error, .5 - kP * error);*/
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        /*double turningValue = (kAngleSetpoint - imu.getAngle()) * kP;
		// Invert the direction of the turn if we are going backwards
		turningValue = Math.copySign(turningValue, drivetrain.joystick2.getY());
        drivetrain.westCoastTankDrive.arcadeDrive(drivetrain.joystick2.getY(), turningValue);*/
      
      
        /*  
        if(imu.getAngle()<0){
        drivetrain.left.set((drivetrain.left.getSpeed() + drivetrain.left.getSpeed()/10));
            } else {
        drivetrain.right.set((drivetrain.right.getSpeed() + drivetrain.right.getSpeed()/10));
            }

            */
        
        //drivetrain.westCoastTankDrive.arcadeDrive(.5 + kP * error, .5 - kP * error);
    }
}
