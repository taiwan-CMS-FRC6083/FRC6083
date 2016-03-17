package Systems;

import Systems.JoyDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.Timer;

public class CANDriveAssembly {
	
    private static final int talon_arm_id = 3;
    
    private static CANTalon talon_arm;
    
    private static double kp;
    private static double pos, error, posnow;
    private static double angle,x,y;
	
	public static void init(){
        talon_arm = new CANTalon(talon_arm_id);
        talon_arm.changeControlMode(CANTalon.TalonControlMode.Voltage);
        talon_arm.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        SmartDashboard.putNumber("angle", 0);
        SmartDashboard.putNumber("kp", 0);
        talon_arm.setEncPosition(0);
        
        kp = 0;
        y = 0;
        JoyDrive.init();
	}
	
	//Called in teleopMode
	public static void teleopPreiodic(){
		if(JoyDrive.joy_Start){
			talon_arm.setEncPosition(0);
		}
		JoyDrive.Joystickvalue();
    	double tempangle,lastangle;
    	lastangle = angle;
    	angle = SmartDashboard.getNumber("angle");   	
    	SmartDashboard.putNumber("arm_value", talon_arm.get());
    	kp = SmartDashboard.getNumber("kp");
    	
    	posnow = talon_arm.getEncPosition();
    	
    	
    	error = angle - posnow * 0.088;
		
    	
        SmartDashboard.putNumber("posnow", posnow );
        SmartDashboard.putNumber("error", error);
        
        
        
        if(error > 0){
        	if(error > 2){
        		x = -error * kp ;
        	}
        	else{
        		x = 0;
        	}
        }
        else{
        	if(error < -2){
        		x = -error * kp;
        	}
        	else{
        		x = 0;
        	}
        }
        
        
        
        
        talon_arm.set(x);
        SmartDashboard.putNumber("x", x);
        
        y = x;
	}
	
	public static void joystick(){
		
	}
}
