
package org.usfirst.frc.team1626.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.Talon;
// import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import org.usfirst.frc.team1626.robot.commands.ExampleCommand;
import org.usfirst.frc.team1626.robot.subsystems.ExampleSubsystem;
/**
 * Kitbot - This is a simple iterative robot with a TankDrive and
 * a simple extending piston controlled by an XboxController
 * 
 * @author Rohan Mishra & Team 1626
 * @version 1.0
 */
public class Robot extends IterativeRobot {
	
	// Decided to do build this robot without separate commands or
	// subsystems in order to speed up development
	public static OI oi;	

	// Driver Controller
	private Joystick leftTrigger;
	private Joystick rightTrigger;
	private XboxController xbox;
	
	private RobotDrive mainDrive;

	// Driving Talons
	private Talon frontLeftSide;
	private Talon rearLeftSide;
	private Talon frontRightSide;
	private Talon rearRightSide;
	
	// private DoubleSolenoid testSolenoid;
	
	@Override
	public void robotInit() {
		// load subsystems before, commands after - don't move it or you'll
		// get a lot of errors. This code does not have either so
		// I kept it before everything just as a precaution
		oi               = new OI();

		// testSolenoid = new DoubleSolenoid(1,2);
		
		leftTrigger      = new Joystick(1);
		rightTrigger     = new Joystick(1);
		xbox             = new XboxController(1);
				
		frontLeftSide    = new Talon(0);
		rearLeftSide     = new Talon(1);
		frontRightSide   = new Talon(2);
		rearRightSide    = new Talon(3);
		
		mainDrive        = new RobotDrive(frontLeftSide, rearLeftSide, frontRightSide, rearRightSide); 
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
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		// Getting Joystick values
		double leftAxisValue = leftTrigger.getRawAxis(2);
		double rightAxisValue = rightTrigger.getRawAxis(5);
		
		mainDrive.tankDrive(leftAxisValue, rightAxisValue);
		
		if (xbox.getRawButton(1) == true) {
			
		}
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
