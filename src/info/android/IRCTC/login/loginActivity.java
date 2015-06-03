package info.android.IRCTC.login;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import info.android.IRCTC.app.AppController;
import info.android.IRCTC.util.Railway;
import info.android.IRCTC.util.customRequest;
import info.androidhive.IRCTC.R;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class loginActivity extends Activity {
	
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	
	private static String LOGIN_URL="http://10.0.2.2/all_structure/admin/checkLogin.php";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		inputEmail = (EditText) findViewById(R.id.loginEmail);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);
		

		inputEmail.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				loginErrorMsg.setText("");
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				
				if(email==""){
					KEY_ERROR="User Name should not blank\n";
					inputEmail.setFocusable(true);
				}

				if(password==""){
					KEY_ERROR="Password should not blank\n";
					inputPassword.setFocusable(true);
				}
				
				/*Railway class called*/
				
				Railway rs=new Railway();
				Date dt=Calendar.getInstance().getTime();
				//rs.getPNR("8457952224");
				//rs.getStationCode("ahmedabad");
				rs.getSeatavailable("16337", "adi", "thvm", dt, "GN");
				//rs.getFare("12555", "gkp", "ndls", 25, dt);
				
				/**/
				/********* Custom code by pranav*/
				//String url = some valid url;
				Map<String, String> params = new HashMap<String, String>();
				params.put("username", email);
				params.put("password", password);

				customRequest jsObjRequest = new customRequest(Method.POST, LOGIN_URL, params, new Response.Listener<JSONObject>() {

		            @Override
		            public void onResponse(JSONObject response) {
		            	parseJsonFeed(response);
		            }
		        }, new Response.ErrorListener() {
		            @Override
		            public void onErrorResponse(VolleyError response) {
		            }
		        });
		        AppController.getInstance().addToRequestQueue(jsObjRequest);
				/**********************************/
			}
		});
		// Link to Register Screen
		btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				/*Intent i = new Intent(getApplicationContext(),registerActivity.class);
				startActivity(i);
				finish();*/
			}
		});
		
	}
	private void parseJsonFeed(JSONObject response) {
		try {
			JSONArray feedArray = response.getJSONArray("record");
			if(response.getString("success")=="1"){
				/*Intent i=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(i);
				finish();*/
			}
			else {
				KEY_ERROR="You are not authorised\n";
				loginErrorMsg.setText(KEY_ERROR);
				}
			/*for (int i = 0; i < feedArray.length(); i++) {
				
				JSONObject feedObj = (JSONObject) feedArray.get(i);
				FeedItem item = new FeedItem();
				item.setId(feedObj.getInt("id"));
				item.setName(feedObj.getString("name"));

				// Image might be null sometimes
				String image = feedObj.isNull("image") ? null : feedObj.getString("image");
				item.setImge(image);
				item.setStatus(feedObj.getString("status"));
				item.setProfilePic(feedObj.getString("profilePic"));
				item.setTimeStamp(feedObj.getString("timeStamp"));

				// url might be null sometimes
				String feedUrl = feedObj.isNull("url") ? null : feedObj.getString("url");
				item.setUrl(feedUrl);

				//feedItems.add(item);
			}*/

			// notify data changes to list adapater
			//listAdapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
