package org.usfirst.frc.team2890.robot.commands;

import org.usfirst.frc.team2890.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class OpenGripperCommand extends TimedCommand {

    public OpenGripperCommand(double timeout) {
    	super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.manipulatorSubsystem.openGripper();
    	System.out.println("In Gripper Command");
    }

    // Make this return true when this Command no longer needs to run execute()
    
//    protected boolean isFinished() {
//        return RobotMap.openedGripperFlag;
//    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
