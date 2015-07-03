package info.android.IRCTC;

import info.android.IRCTC.helper.SuggestionAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import info.android.IRCTC.R;

public class Search_Station extends Activity {

	private AutoCompleteTextView autoComplete;
    public CharSequence charSequence;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_station);
		autoComplete=(AutoCompleteTextView)findViewById(R.id.autoStationName);
		autoComplete.setAdapter(new SuggestionAdapter(this,autoComplete.getText().toString()));

	}

}		 
