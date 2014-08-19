package com.prgguru.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;



import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditImage extends Activity {

	Button btnUndo;
	Button btnSave;
	TextView textSource, textInfo;
	ImageView imageResult;
	
	final int RQS_IMAGE1 = 1;

	Uri source;
	Bitmap bitmapMaster;
	Canvas canvasMaster;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editimage);
		

		btnUndo = (Button) findViewById(R.id.resetImage);
		btnSave = (Button) findViewById(R.id.saveImage);
		imageResult = (ImageView) findViewById(R.id.result);		
		
		 String path = Environment.getExternalStorageDirectory()+ "/Dev/example.jpg"; 
         File imgFile = new File(path);
         if(imgFile.exists())
         {
             bitmapMaster = BitmapFactory.decodeFile(imgFile.getAbsolutePath());                  
            
             imageResult.setImageBitmap(bitmapMaster);
         }	
         
         OnClickListener undoListener = new OnClickListener() {
             public void onClick(View v) {
               // change text of the TextView (tvOut)
            	 String path = Environment.getExternalStorageDirectory()+ "/Dev/example.jpg"; 
                 File imgFile = new File(path);
                 if(imgFile.exists())
                 {
                     bitmapMaster = BitmapFactory.decodeFile(imgFile.getAbsolutePath());          
                     imageResult.setImageBitmap(bitmapMaster);
                 }	
           }
         };
         
         btnUndo.setOnClickListener(undoListener);
         
         OnClickListener saveListener = new OnClickListener() {
             public void onClick(View v) {
               // change text of the TextView (tvOut)
            	 String path = Environment.getExternalStorageDirectory()+ "/Dev/example1.jpg"; 
                 File imgFile = new File(path);
                 try {
					imgFile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                 OutputStream fOut = null;
				try {
					fOut = new FileOutputStream(imgFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                 if(imgFile.exists())
                 {
                     bitmapMaster.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                     try {
						fOut.flush();
						fOut.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

            //         MediaStore.Images.Media.insertImage(context.getContentResolver(), imgFile.getAbsolutePath(), imgFile.getName(), imgFile.getName());
                 }	
           }
         };
         canvasMaster = new Canvas(bitmapMaster);
         btnSave.setOnClickListener(saveListener);
         
// imageResult.setOnTouchListener(new View.OnTouchListener() {

//	public boolean onTouch(View v, MotionEvent event) {
//		
//		int action = event.getAction();
//		int x = (int) event.getX();
//		int y = (int) event.getY();
//		Toast.makeText(EditImage.this,"Hello x== "+x+"y == "+y, Toast.LENGTH_SHORT).show();
//		drawOnProjectedBitMap((ImageView)v, bitmapMaster, x, y);
//		return true;


//		if(x<0 || y<0 || x > v.getWidth() || y > v.getHeight()){
//			//outside ImageView
//			return true;
//		} else {
//			int projectedX = (int)((double)x * ((double)bitmapMaster.getWidth()/(double)v.getWidth()));
//			int projectedY = (int)((double)y * ((double)bitmapMaster.getHeight()/(double)v.getHeight()));
//
//			Paint   paint = new Paint();
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setColor(Color.RED);
//            paint.setStrokeWidth(10);
//          //canvasMaster.drawCircle(projectedX, projectedY, 200, paint);
//            //////////////////
//            Bitmap output = Bitmap.createBitmap(bitmapMaster.getWidth(),
//                    bitmapMaster.getHeight(), Config.ARGB_8888);
//            Canvas canvas = new Canvas(output);
//            canvas.drawCircle(projectedX, projectedY, 200, paint);
//			////////////////////
//			imageResult.invalidate();
//			imageResult.setImageBitmap(output);
//		return true;
//		}
		
	//}
	//});        
           
	// Balaji }
	
	////////////////////////////////////////
		

		//imageResult.setOnTouchListener(new OnTouchListener(){

//			public boolean onTouch(View v, MotionEvent event) {
//				
//				int action = event.getAction();
//				int x = (int) event.getX();
//				int y = (int) event.getY();
//				Toast.makeText(EditImage.this,"Hello", Toast.LENGTH_LONG).show();
//				drawOnProjectedBitMap((ImageView)v, bitmapMaster, x, y);
//
//				return true;
//			}});
		
	}
//	public boolean onTouch(View v, MotionEvent event) {
//	
//	int action = event.getAction();
//	int x = (int) event.getX();
//	int y = (int) event.getY();
//	Toast.makeText(EditImage.this,"Hello", Toast.LENGTH_LONG).show();
//	drawOnProjectedBitMap((ImageView)v, bitmapMaster, x, y);
//
//	return true;
//}
//	
//	/*
//	 * Project position on ImageView to position on Bitmap
//	 * draw on it
//	 */
	private void drawOnProjectedBitMap(ImageView iv, Bitmap bm, int x, int y){
		if(x<0 || y<0 || x > iv.getWidth() || y > iv.getHeight()){
			//outside ImageView
			return;
		}else{
			int projectedX = (int)((double)x * ((double)bm.getWidth()/(double)iv.getWidth()));
			int projectedY = (int)((double)y * ((double)bm.getHeight()/(double)iv.getHeight()));

			Paint   paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(10);
			canvasMaster.drawCircle(projectedX, projectedY, 200, paint);
			imageResult.invalidate();
			
		//	textSource.setText(x + ":" + y + "/" + iv.getWidth() + " : " + iv.getHeight() + "\n" +
		//			projectedX + " : " + projectedY + "/" + bm.getWidth() + " : " + bm.getHeight()
		//			);
		}
	}

//	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		Bitmap tempBitmap;
		
		if(resultCode == RESULT_OK){
			switch (requestCode){
			case RQS_IMAGE1:
				source = data.getData();
				textSource.setText(source.toString());
				
				try {
					//tempBitmap is Immutable bitmap,
					//cannot be passed to Canvas constructor
					tempBitmap = BitmapFactory.decodeStream(
							getContentResolver().openInputStream(source));
					
					Config config;
					if(tempBitmap.getConfig() != null){
						config = tempBitmap.getConfig();
					}else{
						config = Config.ARGB_8888;
					}
					
					//bitmapMaster is Mutable bitmap
					bitmapMaster = Bitmap.createBitmap(
							tempBitmap.getWidth(),
							tempBitmap.getHeight(),
							config);
					
					canvasMaster = new Canvas(bitmapMaster);
					canvasMaster.drawBitmap(tempBitmap, 0, 0, null);
					
					imageResult.setImageBitmap(bitmapMaster);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
		}
	}

	
	//////////////////////////////////////

}
