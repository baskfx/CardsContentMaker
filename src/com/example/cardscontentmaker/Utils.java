package com.example.cardscontentmaker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class Utils {
	
	final static String DIR_SD = "jMorpho";
	  
	static void writeFileSD(String filename, String text) {
	    if (!Environment.getExternalStorageState().equals(
	        Environment.MEDIA_MOUNTED)) {
	    	return;
	    }
	    File sdPath = Environment.getExternalStorageDirectory();
	    sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
	    sdPath.mkdirs();
	    File sdFile = new File(sdPath, filename);
	    try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile, true));
			bw.write(text + "\n");
			bw.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
    
    
}