package org.usfirst.frc.team1626.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;

// import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import org.usfirst.frc.team1626.robot.commands.ExampleCommand;
// import org.usfirst.frc.team1626.robot.subsystems.ExampleSubsystem;
/**
 * Kitbot - This WAS supposed to be a simple iterative robot with a TankDrive and
 * a simple extending piston controlled by an XboxController but then testing stuff
 * 
 * @author Rohan Mishra & Team 1626
 * @version 1.0
 */
public class Robot extends IterativeRobot {
	
	// Decided to do build this robot without separate commands or
	// subsystems in order to speed up development
	public static OI oi;	

	// Driver Controller
	// private Joystick leftTrigger;
	// private Joystick rightTrigger;
	private XboxController xbox;
	
	private RobotDrive mainDrive;

	// Driving Talons
	// private Talon frontLeftSide;
	// private Talon rearLeftSide;
	// private Talon frontRightSide;
	// private Talon rearRightSide;
	
	private Talon winchMotor;
	private Talon pickUpMotor;
	private Talon shooterMotor;
	private Talon shooterMotorTwo;
	
	private DoubleSolenoid shooterSolenoidOne;
	private DoubleSolenoid shooterSolenoidTwo;
	
	// Echo code
	int autoLoopCounter;
	XboxActionRecorder actions;
	
	// private DoubleSolenoid testSolenoid;
	
	@Override
	public void robotInit() {
		// load subsystems before, commands after - don't move it or you'll
		// get a lot of errors. This code does not have either so
		// I kept it before everything just as a precaution
		oi               = new OI();
		
		// leftTrigger      = new Joystick(1);
		// rightTrigger     = new Joystick(1);
		xbox             = new XboxController(1);
		
		// Drive Controllers
		// TODO Put in the right PWD for. every. single. talon.
		// frontLeftSide    = new Talon(0);
		// rearLeftSide     = new Talon(1);
		// frontRightSide   = new Talon(2);
		// rearRightSide    = new Talon(3);
		// mainDrive        = new RobotDrive(frontLeftSide, rearLeftSide, frontRightSide, rearRightSide);
		
		// Talons
		pickUpMotor      = new Talon(4);
		winchMotor 	     = new Talon(6);
		shooterMotor     = new Talon(5);
		shooterMotorTwo  = new Talon(3);
		
		shooterSolenoidOne = new DoubleSolenoid(0,1);
		// shooterSolenoidTwo = new DoubleSolenoid();
			
		// Autonomous Recorder
		actions.setMethod(this, "robotOperation", DriverInput.class).
			setUpButton(xbox, 1).
			setDownButton(xbox, 2).
			setRecordButton(xbox, 3);
		DriverInput.nameInput("Left-Trigger-Y-Axis");
		DriverInput.nameInput("Right-Trigger-Y-Axis");
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autoLoopCounter = 0;
		actions.autonomousInit();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		try
		{
			if (actions != null)
			{
                // actions.playback();
				actions.longPlayback(this, -1);
			} else
			{
				Timer.delay(0.010);
			}
		} catch (Exception e)
		{
			System.out.println("AP: " + e.toString());
		}
	}

	@Override
	public void teleopInit() {
//		DriverInput.setRecordTime();
//		actions.teleopInit();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		// Getting Joystick values
		//double leftAxisValue = leftTrigger.getRawAxis(2);
		//double rightAxisValue = rightTrigger.getRawAxis(5);
		
		// mainDrive.tankDrive(leftAxisValue, rightAxisValue);
		
		if (xbox.getAButton() == true) {
			// testSolenoid.set(DoubleSolenoid.Value.kReverse);
			pickUpMotor.set(-.99);
		} else if (xbox.getBButton() == true) {
			pickUpMotor.set(.99);
		} else {
			pickUpMotor.set(0);
		}
					
		if (xbox.getXButton() == true) {
			shooterMotor.set(.99);
			shooterMotorTwo.set(.99);
		} else if (xbox.getYButton() == true) {
			shooterMotor.set(-.99);
			shooterMotorTwo.set(-.99);
		} else {
			shooterMotor.set(0);
			shooterMotorTwo.set(0);
		}
		
		if (xbox.getStickButton() == true && xbox.getAButton() == true) {
			// testSolenoid.set(DoubleSolenoid.Value.kReverse);
			winchMotor.set(-.99);
		} else if (xbox.getStickButton() == true && xbox.getBButton() == true) {
			winchMotor.set(.99);
		} else {
			winchMotor.set(0);
		}
					
//		if (xbox.getBumper() == true) {
//			shooterSolenoidOne.set(DoubleSolenoid.Value.kForward);
//			shooterSolenoidTwo.set(DoubleSolenoid.Value.kForward);
//		} else {
//			shooterSolenoidOne.set(DoubleSolenoid.Value.kReverse);
//			shooterSolenoidTwo.set(DoubleSolenoid.Value.kReverse);
//		}
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
