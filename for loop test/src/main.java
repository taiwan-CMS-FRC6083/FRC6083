import java.nio.charset.Charset;
import java.io.IOException;
import java.nio.file.*;
import static java.lang.System.*;

public class main {
	public static int i,j,k;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		j=0;
		for(i=100;i>=j;i--){
			System.out.println(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
