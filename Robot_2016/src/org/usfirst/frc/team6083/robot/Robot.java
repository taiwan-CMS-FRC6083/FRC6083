
package org.usfirst.frc.team6083.robot;

import Systems.CANDriveAssembly;
import Systems.PWMDriveAssembly;
import Systems.Ultrasonic;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
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
    final String defaultTele = "Normal";
    final String pidTele = "pid_test";
    final String currentTele = "talon_current_test";
    String autoSelected;
    String teleSelected;
    SendableChooser chooserAuto;
    SendableChooser chooserTele;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooserAuto = new SendableChooser();
        chooserTele = new SendableChooser();
        chooserAuto.addDefault("Default Auto", defaultAuto);
        chooserAuto.addObject("My Auto", customAuto);
        chooserTele.addDefault("Normal", defaultTele);
        chooserTele.addObject("pid_test", pidTele);
        chooserTele.addObject("talon_current_test", currentTele);
        SmartDashboard.putData("Auto choices", chooserAuto);
        SmartDashboard.putData("Tele choices", chooserTele);
        
        //init
        
        CANDriveAssembly.init();
    	PWMDriveAssembly.init();
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
    	autoSelected = (String) chooserAuto.getSelected();
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
    public void teleopInit(){
    	teleSelected = (String) chooserTele.getSelected();
    	System.out.println("Tele selected: " + teleSelected);
    	
    	
    }
    
    
    
    public void teleopPeriodic() {
    	switch(teleSelected) {
    	
    	
    	case defaultTele:
    	default:
        	CANDriveAssembly.teleopPreiodic();
        	PWMDriveAssembly.teleopPeriodic();
    		break;
    	}
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    }
    
    
}
