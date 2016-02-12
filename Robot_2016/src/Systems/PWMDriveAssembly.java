package Systems;

import Systems.JoyDrive;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

//Code for controlling the drivetrain

public class PWMDriveAssembly {
		
	private static boolean inited = false;
	
	// Speed controller IDs
	private final static int talon_left_id = 0;
	private final static int talon_right_id = 1;
	private final static int talon_ball_id = 2;
	
	//VictorSP
	private static VictorSP talon_left;
	private static VictorSP talon_right;
	private static VictorSP talon_ball;
	
	//Drive
	private static RobotDrive drive;
	
	
	
	
	public static void init(){
		if(!inited){
			//talon
			talon_left = new VictorSP(talon_left_id);
			talon_right = new VictorSP(talon_right_id);
			talon_ball = new VictorSP(talon_ball_id);
			JoyDrive.init();
		}	
	}
	
	public static void teleopPeriodic(){
		//drive();
		
		Dashboard();
	}
	
	public static void drive(){
		talon_left.set(JoyDrive.LY);
		talon_right.set(JoyDrive.RY);
	}
	
	public static void Dashboard(){
    	SmartDashboard.putNumber("Left Motor Encoder Value", -talon_left.get());
    	SmartDashboard.putNumber("Right Motor Encoder Value", talon_right.get());
    	SmartDashboard.putNumber("spSpeedControaleed", (-talon_left.get()+ talon_right.get())/2);
    	SmartDashboard.putNumber("Speed Plot", (-talon_left.get()+ talon_right.get())/2);
    	SmartDashboard.putNumber("LY", JoyDrive.LY);
    	SmartDashboard.putNumber("RY", JoyDrive.RY);
	}
	

}
