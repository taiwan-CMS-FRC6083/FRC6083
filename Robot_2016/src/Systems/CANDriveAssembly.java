package Systems;

import Systems.JoyDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.Joystick;

public class CANDriveAssembly {
	
	private static boolean inited = false;
	
	//talon info
	private static final int talon_arm_id = 2;
	
	//talon drive
	private static CANTalon talon_arm;
	
	//double
	private static int PulseWidthpos,PulseWidthus,periodus,PulseWidthVel ;
	
	public static void init(){
		
		if(inited){
			talon_arm = new CANTalon(talon_arm_id);
			talon_arm.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
			JoyDrive.init();
		}
	}
	
	public static void teleopPreiodic(){
		getencoder();
		//talon_arm.set(JoyDrive.val);
		Dashboard();
	}
	
	
	public static void Dashboard(){
		SmartDashboard.putNumber("arm_value", talon_arm.get());
		SmartDashboard.putInt("PulseWidthpos", PulseWidthpos);
		SmartDashboard.putInt("PulseWidthus", PulseWidthus);
		SmartDashboard.putInt("periodus", periodus);
		SmartDashboard.putInt("PulseWidthVel", PulseWidthVel);
		SmartDashboard.putNumber("val", JoyDrive.val);
	}
	
	public static void getencoder(){
		
		PulseWidthpos = talon_arm.getPulseWidthPosition();
		PulseWidthus = talon_arm.getPulseWidthRiseToFallUs();
		periodus = talon_arm.getPulseWidthRiseToRiseUs();
		PulseWidthVel = talon_arm.getPulseWidthVelocity();
		FeedbackDeviceStatus sensorstaus = talon_arm.isSensorPresent(FeedbackDevice.CtreMagEncoder_Absolute);
	}
}
