
package org.usfirst.frc.team1626.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import org.usfirst.frc.team1626.robot.commands.ExampleCommand;
import org.usfirst.frc.team1626.robot.subsystems.ExampleSubsystem;

public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;	

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	private Joystick leftTrigger;
	private Joystick rightTrigger;
	
	private RobotDrive mainDrive;

	//Driving Talons
	private Talon leftSideMotor;
	private Talon rightSideMotor;
	
	private DoubleSolenoid testSolenoid;  
	
	@Override
	public void robotInit() {
		oi = new OI();

		testSolenoid = new DoubleSolenoid(1,2);
		
		leftTrigger = new Joystick(1);
		rightTrigger = new Joystick(1);
		
		leftSideMotor = new Talon(1);
		rightSideMotor = new Talon(2);
		
		mainDrive = new RobotDrive(leftSideMotor, rightSideMotor); 
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
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// Autonomous stops running when teleop starts running
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		// Getting Joystick values
		double leftAxisValue = -(leftTrigger.getRawAxis(2));
		double rightAxisValue = -(rightTrigger.getRawAxis(5));
		
		mainDrive.tankDrive(leftAxisValue, rightAxisValue);
		// testSolenoid.set(DoubleSolenoid.Value.kOff);
		// testSolenoid.set(DoubleSolenoid.Value.kForward);
		// testSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
