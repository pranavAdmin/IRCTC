package info.android.IRCTC;

import info.android.IRCTC.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
/**/
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
/**/
import android.widget.Toast;

public class New_Booking extends Activity {
	private final String imageInSD = "/sdcard/er.PNG";

	ImageView myImageView;
	Spinner spinnerScale;
	SeekBar seekbarRotate;
	TextView scale1;
	TextView scale2;


	private static final String[] strScale	 = {"0.2x", "0.5x", "1.0x", "2.0x", "5.0x"};
	private static final Float[] floatScale	 = {0.2F, 0.5F, 1F, 2F, 5F};
	private final int defaultSpinnerScaleSelection = 2;

	private ArrayAdapter<String> adapterScale;

	private float curScale = 1F;
	private float curRotate = 0F;

	Bitmap bitmap;
	int bmpWidth, bmpHeight;

	  /** Called when the activity is first created. */
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_new__booking);
	    
	      myImageView = (ImageView)findViewById(R.id.imageview);
	      scale1=(TextView)findViewById(R.id.scale1);
	      scale2=(TextView)findViewById(R.id.scale2);
	      
	      myImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					for(int i=0;i<=180;i++){
					curRotate = (float)i;
					drawMatrix();
					}
				//Toast.makeText(getApplicationContext(), "Image clicked", Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
				
			}
		});
	      
	      spinnerScale = (Spinner)findViewById(R.id.scale);
	      seekbarRotate = (SeekBar)findViewById(R.id.rotate);
	    
	      adapterScale = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strScale);
	      adapterScale.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      spinnerScale.setAdapter(adapterScale);
	      spinnerScale.setSelection(defaultSpinnerScaleSelection);
	      curScale = floatScale[defaultSpinnerScaleSelection];
	    
	      //bitmap = BitmapFactory.decodeFile(imageInSD);
	      bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.images_64);
	      bmpWidth = bitmap.getWidth();
	      bmpHeight = bitmap.getHeight();
	    
	      drawMatrix();
	    
	      spinnerScale.setOnItemSelectedListener(spinnerScaleOnItemSelectedListener);
	      seekbarRotate.setOnSeekBarChangeListener(seekbarRotateSeekBarChangeListener);

	  }

	  private void drawMatrix(){
	  
	   Matrix matrix = new Matrix();
	      matrix.postScale(curScale, curScale);
	      matrix.postRotate(curRotate);
	      scale2.setText(Float.toString(curScale));
	      Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight, matrix, true);
	      myImageView.setImageBitmap(resizedBitmap);
	  
	  }

	  private SeekBar.OnSeekBarChangeListener seekbarRotateSeekBarChangeListener
	   = new SeekBar.OnSeekBarChangeListener(){

	  @Override
	  public void onProgressChanged(SeekBar seekBar, int progress,
	    boolean fromUser) {
	   // TODO Auto-generated method stub
	   curRotate = (float)progress;
	   scale1.setText("Scale 1"+Float.toString(progress));
	   drawMatrix();
	  }

	  @Override
	  public void onStartTrackingTouch(SeekBar seekBar) {
	   // TODO Auto-generated method stub
	  
	  }

	  @Override
	  public void onStopTrackingTouch(SeekBar seekBar) {
	   // TODO Auto-generated method stub
	  
	  }};

	  private Spinner.OnItemSelectedListener spinnerScaleOnItemSelectedListener
	   = new Spinner.OnItemSelectedListener(){

	  @Override
	  public void onItemSelected(AdapterView<?> arg0, View arg1,
	    int arg2, long arg3) {
	   // TODO Auto-generated method stub
	   curScale = floatScale[arg2];
	   drawMatrix();
	  }

	  @Override
	  public void onNothingSelected(AdapterView<?> arg0) {
	   // TODO Auto-generated method stub
	   spinnerScale.setSelection(defaultSpinnerScaleSelection);
	   curScale = floatScale[defaultSpinnerScaleSelection];
	  }};
}
