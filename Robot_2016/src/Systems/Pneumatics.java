package Systems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import Systems.JoyDrive;



public class Pneumatics {
	
	private static boolean inited = false;
	
	//device id
	private static final int comp_id = 0;
	private static final int twoway_forward_id[] = {0,2};
	private static final int twoway_reverse_id[] = {1,3};
	private static final int oneway_id[] = {};
	
	//device
	public static Compressor comp;
	public static DoubleSolenoid twoway[];
	public static Solenoid oneway[];
	
	//device number
	private static final int twoway_num = 1;
	private static final int oneway_num = 0;
	
	
	
	public static void init(){
		if(!inited){
			int a,b;
			twoway = new DoubleSolenoid[twoway_num];
			oneway = new Solenoid[oneway_num];
			comp = new Compressor(comp_id);
			for(a=0;a < twoway_num;a++){
				twoway[a] = new DoubleSolenoid(twoway_forward_id[a],twoway_reverse_id[a]);
			}
			for(b=0;b < oneway_num;b++){
				oneway[b] = new Solenoid(oneway_id[b]);
			}
			JoyDrive.init();
		}
		
	}
	
	public static void teleopPreiodic(){
		if(JoyDrive.joy_a){
			if(twoway[0].get() == DoubleSolenoid.Value.kForward){
				twoway_control(0, 2);
			}
			else if(twoway[0].get() == DoubleSolenoid.Value.kReverse){
				twoway_control(0, 1);
			}
			else{
				twoway_control(0, 2);
			}
		}
		
		if(JoyDrive.joy_x){
			if(twoway[1].get() == DoubleSolenoid.Value.kForward){
				twoway_control(1, 2);
			}
			else if(twoway[0].get() == DoubleSolenoid.Value.kReverse){
				twoway_control(1, 1);
			}
			else{
				twoway_control(1, 2);
			}
		}
		
		if(JoyDrive.joy_b){
			twoway_control(0, 3);
		}
		
		if(JoyDrive.joy_y){
			twoway_control(0, 3);
		}
	}
	
	public static void twoway_control(int num,int mode){
		/*mode list
		 * 1 forward
		 * 2 reverse
		 * 3 off
		 */
		switch(mode){
		case 1:
			twoway[num].set(DoubleSolenoid.Value.kForward);
			
			break;
		case 2:
			twoway[num].set(DoubleSolenoid.Value.kReverse);
			
			
			break;
		case 3:
			twoway[num].set(DoubleSolenoid.Value.kOff);
			
			
		break;
		//default kForward
		default:
			twoway[num].set(DoubleSolenoid.Value.kForward);
			break;
		}
	}
	
	
	
	
	
}
