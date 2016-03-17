
package org.usfirst.frc.team6083.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

//import defined part
import Systems.CANDriveAssembly;
import Systems.PWMDriveAssembly;
import edu.wpi.first.wpilibj.CameraServer;
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
	
    int session;
    Image frame;
	
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";

    String autoSelected;
    SendableChooser chooserAuto;
	
    final String defaultTele = "Normal";
    final String pidTele = "pid_test";
    final String currentTele = "talon_current_test";
    
    String teleSelected;
    SendableChooser chooserTele;
    // add Auto and Teleop chooser
    
    
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
        
        
        CANDriveAssembly.init();//init CANDevice
    	PWMDriveAssembly.init();//init PWMDevice
    	
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
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
    	NIVision.IMAQdxStartAcquisition(session);

        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);

        while (isOperatorControl() && isEnabled()) {

            NIVision.IMAQdxGrab(session, frame, 1);
            NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                    DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
            
            CameraServer.getInstance().setImage(frame);

            /** robot code here! **/
            
        	switch(teleSelected) {
        	
        	
        	case defaultTele:
        	default:
            	CANDriveAssembly.teleopPreiodic();//CANDrive teleop mode
            	PWMDriveAssembly.teleopPeriodic();//PWMDrive teleop mode
        		break;
        	}
            
        }
        NIVision.IMAQdxStopAcquisition(session);
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    }
    
    
}
