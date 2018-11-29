package org.usfirst.frc.team2890.robot.subsystems;
import org.usfirst.frc.team2890.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ManipulatorSubsystem extends Subsystem 
{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void controlManipulator()
    {
    	RobotMap.upperLimitSwitch = RobotMap.sensorSubsystem.isUpperLimitSwitchPressed();
		RobotMap.lowerLimitSwitch = RobotMap.sensorSubsystem.isLowerLimitSwitchPressed();
		
    	//controlTower method
    	//Tower Down
    	if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) > RobotMap.TRIGGER_SENSITIVIY && !RobotMap.upperLimitSwitch)
    	{
			if (!RobotMap.lowerLimitSwitch)
			{
				RobotMap.rightTowerTalon.set(RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) * RobotMap.TOWER_DOWN_DIRECTION * RobotMap.TOWER_DOWN_SLOW_SPEED);
    			RobotMap.leftTowerTalon.set(RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) * RobotMap.TOWER_DOWN_DIRECTION * RobotMap.TOWER_DOWN_SLOW_SPEED);
			}
			else
			{
    			RobotMap.rightTowerTalon.set(RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) * RobotMap.TOWER_DOWN_DIRECTION);
    			RobotMap.leftTowerTalon.set(RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) * RobotMap.TOWER_DOWN_DIRECTION);
			}
		}
		
    	//Tower up
    	else if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight) > RobotMap.TRIGGER_SENSITIVIY)
    	{
    		RobotMap.rightTowerTalon.set(RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight));
    		RobotMap.leftTowerTalon.set(RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight));
		}
		
    	//Stop tower
    	else
    	{
    		RobotMap.rightTowerTalon.stopMotor();
    		RobotMap.leftTowerTalon.stopMotor();
    	}
    	
    	//shiftGear method
    	if(RobotMap.assistantDriverController.getAButtonPressed())
    	{
    		if(RobotMap.shiftGearButtonFlag)
        	{
        		RobotMap.gearBoxSolenoid.set(DoubleSolenoid.Value.kForward);
        		RobotMap.highGear = false;
        		RobotMap.lowGear = true;
        		RobotMap.shiftGearButtonFlag = false;
        	}
        	else
        	{
        		RobotMap.gearBoxSolenoid.set(DoubleSolenoid.Value.kReverse);
				RobotMap.highGear = true;
				RobotMap.lowGear = false;
        		RobotMap.shiftGearButtonFlag = true;
        	}
    	}
    	
    	//controlCube method
    	if(RobotMap.assistantDriverController.getYButtonPressed())
    	{
    		//cube up
    		if(RobotMap.controlCubeFlag)
        	{
    			RobotMap.elbowSolenoid.set(DoubleSolenoid.Value.kReverse);
    			RobotMap.elbowIsDown = true;
        		RobotMap.controlCubeFlag = false;
        	}
    		//cube down
        	else
        	{
        		RobotMap.elbowSolenoid.set(DoubleSolenoid.Value.kForward);
        		RobotMap.elbowIsDown = false;
        		RobotMap.controlCubeFlag = true;
        	}
    	}
    	
    	//controlGripper method
    	if(RobotMap.assistantDriverController.getXButtonPressed())
    	{
    		//open gripper
    		if(RobotMap.controlGripperFlag)
        	{
    			RobotMap.grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
        		RobotMap.controlGripperFlag = false;
        	}
    		//close gripper
        	else
        	{
        		RobotMap.grabberSolenoid.set(DoubleSolenoid.Value.kForward);
        		RobotMap.controlGripperFlag = true;
        	}
    	}
		
		//Low and high gear
    	if(RobotMap.assistantDriverController.getStartButtonPressed())
    	{
    		if(RobotMap.controlRatchetFlag)
    		{
				RobotMap.ratchetSolenoid.set(DoubleSolenoid.Value.kForward);
				RobotMap.ratchetEngaged = false;
				RobotMap.controlRatchetFlag = false;
    		}
    		else
    		{
    			RobotMap.ratchetSolenoid.set(DoubleSolenoid.Value.kReverse);
    			RobotMap.ratchetEngaged = true;
    			RobotMap.controlRatchetFlag = true;
    		}
    	}
    }
    
    public void openGripper()
    {
    	RobotMap.grabberSolenoid.set(DoubleSolenoid.Value.kForward);
		RobotMap.openedGripperFlag = true;
		RobotMap.closedGripperFlag = false;
    }
    
    public void closeGripper()
    {
		RobotMap.grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
		RobotMap.openedGripperFlag = false;
    	RobotMap.closedGripperFlag = true;
    }
    
    public void dropClaw()
    {
    	RobotMap.elbowSolenoid.set(DoubleSolenoid.Value.kForward);
    	RobotMap.clawDownFlag = true;
    }
    
    public void liftUp()
    {
		if(RobotMap.upperLimitSwitch)
		{
    		RobotMap.rightTowerTalon.stopMotor();
    		RobotMap.leftTowerTalon.stopMotor();
		}
		else
		{
	    	RobotMap.rightTowerTalon.set(RobotMap.TOWER_UP_VARIABLE);
			RobotMap.leftTowerTalon.set(RobotMap.TOWER_UP_VARIABLE);
			RobotMap.liftUpFlag = true;
		}
    }
}
