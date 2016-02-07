
package org.usfirst.frc.team6083.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.vision.USBCamera;



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
    
    
    //motor
    VictorSP motor_left = new VictorSP(1);
    VictorSP motor_right = new VictorSP(0);
    VictorSP hand1 = new VictorSP(2);
    
    //Joystick
    Joystick joy = new Joystick(0);
    JoystickButton left = new JoystickButton(joy,5);
    JoystickButton right = new JoystickButton(joy,6);
    JoystickButton A = new JoystickButton(joy,1);
    JoystickButton B = new JoystickButton(joy,2);
    
    //Device
    PowerDistributionPanel pdp = new PowerDistributionPanel(1);
    Compressor comp = new Compressor(0);
    
    //SmartDashboard
    Preferences pref;
    
    //camera
    int session;
    Image frame;
    
    //Double
    Double LY;
    Double RY;
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        
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
        	Double SpeedControal = 2.0;
        	
        	if(joy.getRawAxis(1)>0.1 || joy.getRawAxis(1)<-0.1){		
                LY = joy.getRawAxis(1);
            }	
            else{
                LY = 0.0 ;	
            }	
        	if(joy.getRawAxis(5)>0.1 || joy.getRawAxis(5)<-0.1){		
                RY = joy.getRawAxis(5);
            }	
            else{
                RY = 0.0 ;	
            }	
        	if(left.get()){
        		motor_left.set(LY/SpeedControal);                     	
        	}	
        	else {
        		motor_left.set(LY/(SpeedControal*2));
        	}
        	
        	if(right.get()){
        		motor_right.set(-RY/SpeedControal);                     	
        	}	
        	else {
        		motor_right.set(-RY/(SpeedControal*2));
        	}
        	
        	

        	SmartDashboard.putNumber("Left Motor Encoder Value", -motor_left.get());
        	SmartDashboard.putNumber("Right Motor Encoder Value", motor_right.get());
        	SmartDashboard.putNumber("spSpeedControaleed", (-motor_left.get()+ motor_right.get())/2);
        	SmartDashboard.putNumber("Speed Plot", (-motor_left.get()+ motor_right.get())/2);
        	SmartDashboard.putNumber("LY value", joy.getRawAxis(1));
        	SmartDashboard.putNumber("RY value", joy.getRawAxis(5));
        	SmartDashboard.putNumber("PDP Voltage", pdp.getVoltage());
        }
        NIVision.IMAQdxStopAcquisition(session);
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
