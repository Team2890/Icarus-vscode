/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2890.robot.subsystems;

import org.usfirst.frc.team2890.robot.RobotMap;
import org.usfirst.frc.team2890.robot.commands.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrainSubsystem extends Subsystem 
{
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	//requires.RobotMap.driveTrainSubsystem;

	public void initDefaultCommand() 
	{
		setDefaultCommand(new XboxDriveCommand());
	}
	

	public void xboxArcadeDrive()
	{
		//getY goes forwards
		// getX rotates left and right and needs to be inverted because of talon behavior
		RobotMap.driveTrain.arcadeDrive(RobotMap.driverController.getY(Hand.kLeft) * RobotMap.FORWARDS_BACKWARDS_SENSITIVITY, 
				RobotMap.driverController.getX(Hand.kRight) * RobotMap.X_INVERTED * RobotMap.ROTATION_SENSITIVTY);
	} 
	
	public void tankDrive()
	{
		RobotMap.driveTrain.tankDrive(RobotMap.driverController.getY(Hand.kLeft), RobotMap.driverController.getY(Hand.kRight));
	}
	
	public void driveForward()
	{
		RobotMap.driveTrain.arcadeDrive(RobotMap.AUTONOMOUS_FORWARD_SPEED, 0);
	}
	
	
	public void driveBackward()
	{
		RobotMap.driveTrain.arcadeDrive(RobotMap.AUTONOMOUS_BACKWARD_SPEED, 0);
	}
	
	public void turnLeft()
	{
		RobotMap.driveTrain.arcadeDrive(0, RobotMap.AUTONOMOUS_ROTATE_LEFT_SPEED);
	}
	
	public void turnRight()
	{
		RobotMap.driveTrain.arcadeDrive(0, RobotMap.AUTONOMOUS_ROTATE_RIGHT_SPEED);
	}
	    
	public void stopMoving()
	{
		RobotMap.driveTrain.stopMotor();
	}
	
	public void autonomousRotateWithCamera()
	{
		double turnValue = 0; //PLEASE REPLACE ZERO WITH CAMERA THREAD INPUT!!!
		double currentAngle = RobotMap.gyro.getAngle();
		double goalAngle = currentAngle + turnValue;
		
		if(turnValue > 0) 
			{
				turnLeft();
				goalAngle = currentAngle + turnValue;
				if(currentAngle <= goalAngle + 1 || currentAngle >= goalAngle - 1) stopMoving();
			}
		if(turnValue < 0) 
			{
				turnRight();
				goalAngle = currentAngle + turnValue;
				if(currentAngle <= goalAngle + 1 || currentAngle >= goalAngle - 1) stopMoving();
			}
	}
	
	public void turnDegrees(double goalAngle, double turnDegrees)
	{
		if(turnDegrees > 0)
		{
			if(RobotMap.gyro.getAngle() < goalAngle)
			{
				turnRight();
			}
			else
			{
				stopMoving();
				RobotMap.stopRotating = true;
			}
		}
		else
		{
			if(RobotMap.gyro.getAngle() > goalAngle)
			{
				turnLeft();
			}
			else
			{
				stopMoving();
				RobotMap.stopRotating = true;
			}
		}
	}
	
	public void rangedDriveForward()
	{
		if(RobotMap.rangeFinder.getRangeInches() >= RobotMap.RANGE_TARGET)
		{
			driveBackward();
		}
		else
		{
			stopMoving();
			RobotMap.rangeFinderExitFlag = true;
		}
	}
}
