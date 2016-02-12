package Systems;

import edu.wpi.first.wpilibj.Joystick;

public class JoyDrive {
	
	//joystick
	public final static int joy_id = 0;
	public final static int joy3d_id = 1;
	public final static int joy_ly = 1;
	public final static int joy_ry = 5;
	public final static int joy3d_y = 1;
	
	//joystick dirve
	public static Joystick  joy,joy3d;
	
	//value
	public static double RY;
	public static double LY;
	public static double val;
	public static double arm_speedlimit = 0.7;
	
	public static void init(){
		joy = new Joystick(joy_id);
		joy3d = new Joystick(joy3d_id);
	}
	
	public static void Joystickvalue(){
		
		//Arm
		if(-joy3d.getRawAxis(joy3d_y)>0.1||-joy3d.getRawAxis(joy3d_y)<0.1){
        	val = -joy3d.getRawAxis(joy3d_y);
        }	
		else if(-joy3d.getRawAxis(joy3d_y)>arm_speedlimit||-joy3d.getRawAxis(joy3d_y)<-arm_speedlimit){
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
