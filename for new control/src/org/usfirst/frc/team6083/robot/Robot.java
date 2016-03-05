
package org.usfirst.frc.team6083.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
	Joystick joy;
    double lx,ly;
	public final static int joy_ly = 1;
	public final static int joy_ry = 5;
	public final static int joy_lx = 2;
	public final static int joy_rx = 3;
    
	
	private static VictorSP talon_left;
	private static VictorSP talon_right;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        joy = new Joystick(0);
        talon_left = new VictorSP(0);
        talon_right = new VictorSP(1);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	double left,right;
    	
        lx = joy.getRawAxis(2);
		if(joy.getRawAxis(joy_ly)>0.1 || joy.getRawAxis(joy_ly)<-0.1){	
			if(joy.getRawAxis(joy_ly) >= 0.5){
				ly = 0.5;
			}
			else{
	        	ly = joy.getRawAxis(joy_ly);
			}
        }	
        else{
        	ly = 0.0 ;	
        }
		
		if(lx<0){
			left = -lx/2;
		}
		else{
			left = 0.0;
		}
		
		if(lx>0){
			right = lx/2;
		}
		else{
			right = 0;
		}
		talon_left.set(ly+left);
		talon_right.set(ly+right);
		
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
