package Systems;

import Systems.JoyDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.Timer;

public class currentcontrol {
	
	private static boolean inited = false;
	
	//talon info
	private static final int talon_arm_id = 2;
	
	//talon drive
	private static CANTalon talon_arm;
	
	//value
	
	private static int PulseWidthpos,PulseWidthus,periodus,PulseWidthVel ;
	
	private static double current,w,voltage;
	
	private final static double current_limit = 20;
	
	public static void init(){
		if(true){
			talon_arm = new CANTalon(talon_arm_id);
			
			talon_arm.enableControl();
			talon_arm.setSafetyEnabled(true);
			talon_arm.changeControlMode(CANTalon.TalonControlMode.Current);
			talon_arm.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
			talon_arm.setEncPosition(0);
			
			JoyDrive.init();
			
			SmartDashboard.putNumber("W", 0);
			
			inited = true;
		}
		
		
	}
	
	public static void teleopPeriodic(){
		getencoder();
		Dashboard();
		current = w / talon_arm.getBusVoltage();
		
		
		limit();
		talon_arm.set(current);
		
		
	}
	
	public static void limit(){
		if(current > current_limit){
			current = current_limit;
		}
	}
	
	public static void Dashboard(){
		voltage = talon_arm.getBusVoltage();
		SmartDashboard.putNumber("arm_value", talon_arm.get());
		SmartDashboard.putInt("PulseWidthpos", PulseWidthpos);
		SmartDashboard.putInt("PulseWidthus", PulseWidthus);
		SmartDashboard.putInt("periodus", periodus);
		SmartDashboard.putInt("PulseWidthVel", PulseWidthVel);
		SmartDashboard.putNumber("arm_value",talon_arm.get());
		w = SmartDashboard.getNumber("W");
		SmartDashboard.putNumber("voltage", voltage);
		SmartDashboard.putNumber("current", current);
	}
	
	public static void getencoder(){
		PulseWidthpos = talon_arm.getPulseWidthPosition();
		PulseWidthus = talon_arm.getPulseWidthRiseToFallUs();
		periodus = talon_arm.getPulseWidthRiseToRiseUs();
		PulseWidthVel = talon_arm.getPulseWidthVelocity();
		FeedbackDeviceStatus sensorstaus = talon_arm.isSensorPresent(FeedbackDevice.CtreMagEncoder_Absolute);
	}
	
	
	
}
