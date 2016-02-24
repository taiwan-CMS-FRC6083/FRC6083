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
	private final static int talon_left_id = 0;
	private final static int talon_right_id = 1;
	private final static int shooter_main_id = 4;
	private final static int shooter_launcher_id = 3;
	
	//VictorSP
	private static VictorSP talon_left;
	private static VictorSP talon_right;
	private static VictorSP shooter_main,shooter_launcher;
	
	
	//value
    static boolean startout_main,startout_launcher,startin;
	
	public static void init(){
		if(!inited){
			//talon
			talon_left = new VictorSP(talon_left_id);
			talon_right = new VictorSP(talon_right_id);
			shooter_main = new VictorSP(shooter_main_id);
			shooter_launcher = new VictorSP(shooter_launcher_id);
			JoyDrive.init();
			
			startout_main = true;
			startout_launcher = true;
			startin = true;
		}	
	}
	
	public static void teleopPeriodic(){
		JoyDrive.Joystickvalue();
		DriveBase();
		Dashboard();
		shooter();
	}
	
	
	public static void Dashboard(){
    	SmartDashboard.putNumber("Left Motor Encoder Value", -talon_left.get());
    	SmartDashboard.putNumber("Right Motor Encoder Value", talon_right.get());
    	SmartDashboard.putNumber("spSpeedControaleed", (-talon_left.get()+ talon_right.get())/2);
    	SmartDashboard.putNumber("Speed Plot", (-talon_left.get()+ talon_right.get())/2);
	}
	
	public static void DriveBase(){
		talon_left.set(-JoyDrive.LY/2);
		talon_right.set(JoyDrive.RY/2);
	}
	
	
	
	public static void shooter(){
		if(JoyDrive.joy_a){
			if(startout_main){
    			shooter_main.set(-0.7);
    			
    			startout_main = false;
    		}
		}
		else{
			if(!startout_main){
    			for(Double D = -0.5;D<=0;D=D+0.1){
    				shooter_main.set(D);
    				
    		    	Timer.delay(0.1);
    		    }	
            	startout_main = true ;
    		}	
		}
		
    	if(JoyDrive.joy_x){
    		if(startout_launcher){
    			
    			shooter_launcher.set(-0.5);
    			
    			startout_launcher = false;
    		}
    		
    	}
    	else{
    		if(!startout_launcher){
    			for(Double D = -0.3;D<=0;D=D+0.05){
    			
                	shooter_launcher.set(D);
    		    	Timer.delay(0.1);
    		    }
    			startout_launcher = true ;
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
    				Double Y = D-0.2;
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
