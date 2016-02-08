package Systems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;

//Code for controlling the drivetrain

public class PWMDriveAssembly {
	
	private static boolean inited = false;
	
	// Speed controller IDs
	private final static int talon_left_id = 0;
	private final static int talon_right_id = 1;
	
	//joystick
	private final static int joy_id = 0;
	private final static int joy3d_id = 1;
	private final static int joy_ly = 1;
	private final static int joy_ry = 5;
	private final static int joy3d_y = 1;
	
	//VictorSP
	private static VictorSP talon_left;
	private static VictorSP talon_right;
	
	//Drive
	private static RobotDrive drive;
	
	//joystick
	private static Joystick  joy,joy3d;
	
	private static double RY;
	private static double LY;
	private static double val;
	private static double arm_speedlimit;
	
	public static void init(){
		if(!inited){
			//talon
			talon_left = new VictorSP(talon_left_id);
			talon_right = new VictorSP(talon_right_id);
			
			//joystick
			joy = new Joystick(joy_id);
			joy3d = new Joystick(joy3d_id);
			
			drive = new RobotDrive(talon_left, talon_right);
		}
	}
	
	public static void teleopPeriodic(){
		Joystickvalue();
		drive.tankDrive(LY, RY);
	}
	
	public static void Joystickvalue(){
		
		//Arm
		if(-joy3d.getRawAxis(joy3d_y)>0.1||-joy3d.getRawAxis(joy3d_y)<0.1){
        	val = -joy3d.getRawAxis(joy3d_y);
        }	
		else if(-joy3d.getRawAxis(joy3d_y)>0.5||-joy3d.getRawAxis(joy3d_y)<-0.5){
			val = arm_speedlimit;
		}
		else{
			val = 0.0;
		}
		
		//Drivetrain
		if(joy.getRawAxis(joy_ly)>0.1 || joy.getRawAxis(joy_ly)<-0.1){		
        	LY = joy.getRawAxis(joy_ly);
        }	
        else{
        	LY = 0.0 ;	
        }	
        if(joy.getRawAxis(joy_ry)>0.1 || joy.getRawAxis(joy_ry)<-0.1){		
        	RY = joy.getRawAxis(joy_ry);
        }	
        else{
        	RY = 0.0 ;	
        }	
		
		
	}
}
