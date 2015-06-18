package info.android.IRCTC;

import info.android.IRCTC.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
	ImageView myImageView;
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_new__booking);
	      
	  }
	  public void clockwise(View view){
		  myImageView = (ImageView)findViewById(R.id.imageview);
	      Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
	      myImageView.startAnimation(animation);
	   }
}
