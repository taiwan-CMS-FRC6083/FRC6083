package Systems;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.vision.USBCamera;
import com.ni.vision.NIVision.Image;

public class Camera {
	
	public USBCamera camera;
	public String name;
	public ColorImage lastImage;
	
	
	
	public Camera(String name){
		this.name = name;
		this.camera = new USBCamera(name);
	}
	public ColorImage get_Image() {
		ColorImage image = null;
		
		return image;
	}
	
}
