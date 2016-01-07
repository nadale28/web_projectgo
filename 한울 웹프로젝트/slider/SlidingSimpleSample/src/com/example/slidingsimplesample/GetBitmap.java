package com.example.slidingsimplesample;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class GetBitmap {
	
	public Bitmap getBitmap(String fname){
		
		String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
	    String basePath = sdPath+File.separator+"/MyAPP"+File.separator+fname+".jpg";
	    BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inSampleSize = 1;
	    Bitmap mPhotoBitmap = BitmapFactory.decodeFile(basePath, options);
	    Bitmap resized = Bitmap.createScaledBitmap(mPhotoBitmap, 300, 300, true);
	    
	    return resized;
	}
	
}
