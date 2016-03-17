package Systems;

import Systems.JoyDrive;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

//Code for controlling the drivetrain

public class PWMDriveAssembly {
		
	private static boolean inited = false;
	
	// Speed controller IDs
	private final static int talon_left_1_id = 0;
	private final static int talon_left_2_id = 5;
	private final static int talon_right_1_id = 1;
	private final static int talon_right_2_id = 6;
	private final static int shooter_main_id = 4;
	private final static int shooter_launcher_id = 3;
	private final static int arm_2_id = 2;
	
	//VictorSP
	private static VictorSP talon_left_1;
	private static VictorSP talon_left_2;
	private static VictorSP talon_right_1;
	private static VictorSP talon_right_2;
	private static VictorSP shooter_main,shooter_launcher;
	private static VictorSP arm_2;
	
	//Robot Drive
	public static RobotDrive drive;
	
	//value
    static boolean startout_main,startout_launcher,startin;
	
	public static void init(){
		if(!inited){
			//talon
			talon_left_1 = new VictorSP(talon_left_1_id);
			talon_left_2 = new VictorSP(talon_left_2_id);
			talon_right_1 = new VictorSP(talon_right_1_id);
			talon_right_2 = new VictorSP(talon_right_2_id);
			shooter_main = new VictorSP(shooter_main_id);
			shooter_launcher = new VictorSP(shooter_launcher_id);
			arm_2 = new VictorSP(arm_2_id);
			JoyDrive.init();
			
			startout_main = true;  //if the shooter's part is enabled
			startout_launcher = true;
			startin = true;
		}	
	}
	
	public static void teleopPeriodic(){
		emergency();
		JoyDrive.Joystickvalue();
		DriveBase();
		Dashboard();
		shooter();
	}
	
	
	public static void Dashboard(){
    	SmartDashboard.putNumber("Left Motor Encoder Value", -talon_left_1.get());
    	SmartDashboard.putNumber("Right Motor Encoder Value", talon_right_1.get());
    	SmartDashboard.putNumber("spSpeedControaleed", (-talon_left_1.get()+ talon_right_1.get())/2);
    	SmartDashboard.putNumber("Speed Plot", (-talon_left_1.get()+ talon_right_1.get())/2);
	}
	
	public static void arm2(){
		arm_2.set(JoyDrive.RY/1.5);
	}
	
	public static void emergency(){
		if(JoyDrive.joy_y){
			talon_left_1.stopMotor();
			talon_left_2.stopMotor();
			talon_right_1.stopMotor();
			talon_right_2.stopMotor();
			shooter_launcher.stopMotor();
			shooter_main.stopMotor();
		}
	}
	
	public static void DriveBase(){
		
		
		
		
		talon_left_1.set(-JoyDrive.LY/2);
		talon_left_2.set(-JoyDrive.LY/2);
		talon_right_1.set(JoyDrive.RY/2);
		talon_right_2.set(JoyDrive.RY/2);
		
		
	}
	
	
	
	public static void shooter(){
		if(JoyDrive.joy_a){
			if(startout_main){
    			shooter_main.set(-0.7);
    			
    			startout_main = false;
    		}
		}
		else{
			if(!startout_main && startout_launcher){
				shooter_main.set(-0.7);
				shooter_launcher.set(-0.5);
				Timer.delay(1);
				startout_launcher = false;
			}
			if(!startout_main && !startout_launcher){
    			for(Double D = -0.5;D<=0;D=D+0.1){
    				shooter_main.set(D);
    				
    		    	Timer.delay(0.1);
    		    }	
            	startout_main = true ;
            	startout_launcher = true;
    		}	
		}
		
    	if(JoyDrive.joy_b){
    		if(startin){
    			shooter_main.set(0.6);
    			shooter_launcher.set(0.4);
    			startin = false;
    		}
    		
    	}
    	else{
    		if(!startin){
    			for(Double D=0.2; D>=0; D=D-0.05){
    				shooter_main.set(D);
    				Double Y = D-0.2;  //lower launcher speed
    				if(Y<0.0){
    					Y=0.0;
    				}
    				shooter_launcher.set(Y);
    		    	Timer.delay(0.1);
    		    }	
            	startin = true ;
    		}	
    	}
    	
    	
	}
}
