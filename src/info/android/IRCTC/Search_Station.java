package info.android.IRCTC;

import info.android.IRCTC.util.Railway;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class Search_Station extends Activity  implements TextWatcher{

	private AutoCompleteTextView autoComplete;
    private ArrayAdapter<String> adapter;
    public CharSequence charSequence;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_station);
		
		autoComplete=(AutoCompleteTextView)findViewById(R.id.autoStationName);
		
		autoComplete.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				// TODO Auto-generated method stub
				String text=s.toString();
		        if (text.length() > 3) {
		            //MyAsyncTask myTask = new MyAsyncTask();
		        	Railway myTask=new Railway();
		        	JSONObject jobj=myTask.getStationSuggest(text);
		        	Log.d("Json Object", jobj.toString());
		        	
		        }
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
	}


	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}
	
	
		 
}		 
