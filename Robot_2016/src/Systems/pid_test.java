package Systems;

import Systems.JoyDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;

public class pid_test {
	
	private static boolean inited = false;
	
	//talon info
	private static final int talon_arm_id = 2;
	
	//talon drive
	private static CANTalon talon_arm;
	
	//value
	private static double BusVoltage[];
	private static double OutputVoltage[];
	
	private static final int device_num = 1;
	
	private static int PulseWidthpos,PulseWidthus,periodus,PulseWidthVel ;
	
	
	//PID zone
	
	/*this zone is for the PID control systems
	 * 
	 * 
	 */
	
	private static final int pos_angle_a = 0;
	
	
	
	
	
	//end for the PID zone
	
	
	public static void init(){
		if(!inited){
			talon_arm = new CANTalon(talon_arm_id);
			talon_arm.enableControl();
			talon_arm.setSafetyEnabled(true);
			talon_arm.changeControlMode(CANTalon.TalonControlMode.Position);
			talon_arm.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
			JoyDrive.init();
			BusVoltage = new double[device_num];
			OutputVoltage = new double[device_num];
			inited = true;
			
			
			
		}
			
	}
	
	public static void teleopPreiodic(){
		
		
		getfeedback();
		
	}
	
	
	public static void getencoder(){
		PulseWidthpos = talon_arm.getPulseWidthPosition();
		PulseWidthus = talon_arm.getPulseWidthRiseToFallUs();
		periodus = talon_arm.getPulseWidthRiseToRiseUs();
		PulseWidthVel = talon_arm.getPulseWidthVelocity();
		FeedbackDeviceStatus sensorstaus = talon_arm.isSensorPresent(FeedbackDevice.CtreMagEncoder_Absolute);
	}
	
	public static void getfeedback(){
		getencoder();
		BusVoltage[0] = talon_arm.getBusVoltage();
		OutputVoltage[0] = talon_arm.getOutputVoltage();
		
	}
	
	public static void pidcontrol(double angle){
		
		//pos init
		double pos, posnow, posdiff;
		pos = postoang(angle,1);
		posnow = PulseWidthpos;
		posdiff = pos - posnow;
		
		//PID control
		
		
		
	}
	
	public static double postoang(double input, int mode){
		double temp;
		if(mode == 2){
			temp = input*(360/4096);
		}
		else{
			temp = input/(360/4096);
		}
		return temp;
		
	}
	
	
}
