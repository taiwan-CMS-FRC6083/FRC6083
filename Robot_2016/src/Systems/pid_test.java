package Systems;

import Systems.JoyDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.Timer;

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
	 */
	private static double kp,ki,kd;
	
	private static double targetPositionRotations;
	
	
	
	//end for the PID zone
	
	
	public static void init(){
		if(true){
			talon_arm = new CANTalon(talon_arm_id);
			
			
			talon_arm.enableControl();
			talon_arm.setSafetyEnabled(true);
			talon_arm.changeControlMode(CANTalon.TalonControlMode.Follower);
			talon_arm.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
			talon_arm.reverseSensor(false);
			
//			talon_arm.configNo minalOutputVoltage(+0f, -0f);
//			
//			talon_arm.configPeakOutputVoltage(+12f, -12f);
//			talon_arm.setAllowableClosedLoopErr(0);
//	        talon_arm.setProfile(0);
//	        talon_arm.setF(0.0);
//	        talon_arm.setP(0.1);
//	        talon_arm.setI(0.0); 
//	        talon_arm.setD(0.0); 
//	        talon_arm.setEncPosition(0);
			
			
			JoyDrive.init();
			BusVoltage = new double[device_num];
			OutputVoltage = new double[device_num];
			
			
			
			kp = 0;
			ki = 0;
			kd = 0;
			talon_arm.setPID(1.0, 0.0, 0.0);			
			inited = true;
		}
			
	}
	
	public static void teleopPeriodic(){
		
		getfeedback();
		//pidcontrol();
		talon_arm.set(JoyDrive.LY);
		
	}
	
	public static void getfeedback(){
		getencoder();
//		BusVoltage[0] = talon_arm.getBusVoltage();
//		OutputVoltage[0] = talon_arm.getOutputVoltage();
		Dashboard();
	}
	
	public static void pidcontrol(){
		double leftYstick = JoyDrive.LY;
    	targetPositionRotations = leftYstick * 50.0; /* 50 Rotations in either direction */
//    	talon_arm.changeControlMode(CANTalon.TalonControlMode.Position);
    	talon_arm.set(targetPositionRotations); /* 50 rotations in either direction */
	}
	
	
	
	public static void Dashboard(){
		SmartDashboard.putNumber("arm_value", talon_arm.get());
		SmartDashboard.putInt("PulseWidthpos", PulseWidthpos);
		SmartDashboard.putInt("PulseWidthus", PulseWidthus);
		SmartDashboard.putInt("periodus", periodus);
		SmartDashboard.putInt("PulseWidthVel", PulseWidthVel);
		SmartDashboard.putNumber("arm_value",talon_arm.get());
	}
	
	@SuppressWarnings("unused")
	public static void getencoder(){
		PulseWidthpos = talon_arm.getPulseWidthPosition();
		PulseWidthus = talon_arm.getPulseWidthRiseToFallUs();
		periodus = talon_arm.getPulseWidthRiseToRiseUs();
		PulseWidthVel = talon_arm.getPulseWidthVelocity();
		FeedbackDeviceStatus sensorstaus = talon_arm.isSensorPresent(FeedbackDevice.CtreMagEncoder_Absolute);
	}
}
