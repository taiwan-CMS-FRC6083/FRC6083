package Systems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoyDrive {
	
	//joystick
	public final static int joy_id = 0;
	public final static int joy3d_id = 1;
	public final static int joy_ly = 1;
	public final static int joy_ry = 5;
	public final static int joy_lt = 2;
	public final static int joy_rt = 3; 
	public final static int joy3d_y = 1;
	
	public final static int joy_a_id = 1;
	public final static int joy_b_id = 2;
	public final static int joy_x_id = 3;
	public final static int joy_y_id = 4;
	public final static int joy_LB_id = 5;
	public final static int joy_RB_id = 6;
	public final static int joy_Start_id = 8;
	public final static int joy_Back_id = 7;
	
	
	//joystick dirve
	public static Joystick  joy,joy3d;
	
	//value
	public static double RY;
	public static double LY;
	public static double val;
	
    public static boolean joy_a, joy_b, joy_x, joy_y, joy_LB, joy_RB, joy_Start, joy_Back;
	
	
	
	
	public static void init(){
		joy = new Joystick(joy_id);
		joy3d = new Joystick(joy3d_id);
	}
	
	public static void Joystickvalue(){
		
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
		
		joy_a = joy.getRawButton(joy_a_id); 
        joy_b = joy.getRawButton(joy_b_id);
		joy_x = joy.getRawButton(joy_x_id);
        joy_y = joy.getRawButton(joy_y_id);
        joy_LB = joy.getRawButton(joy_LB_id);
        joy_RB = joy.getRawButton(joy_RB_id);
        joy_Start = joy.getRawButton(joy_Start_id);
        joy_Back = joy.getRawButton(joy_Back_id);
        Dashboard();
	}
	
	public static void Dashboard(){
    	SmartDashboard.putNumber("LY", JoyDrive.LY);
    	SmartDashboard.putNumber("RY", JoyDrive.RY);
	}
}


