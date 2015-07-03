package info.android.IRCTC;

import info.android.IRCTC.app.AppConst;
import info.android.IRCTC.app.AppController;
import info.android.IRCTC.login.loginActivity;
import info.android.IRCTC.picasa.model.Category;
import info.android.IRCTC.util.AlertDialogManager;
import info.android.IRCTC.util.ConnectionDetector;
import info.android.IRCTC.R;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;



public class SplashActivity extends Activity {
	ConnectionDetector cd;
	AlertDialogManager alert = new AlertDialogManager();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.activity_splash);
		cd = new ConnectionDetector(getApplicationContext());
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(SplashActivity.this,"Internet Connection Error","Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}
		else{
			Intent intent=new Intent(getApplicationContext(),loginActivity.class);
			startActivity(intent);
			// closing spalsh activity
			finish();
		}

	}
}
