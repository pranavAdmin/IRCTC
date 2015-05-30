package info.androidhive.IRCTC.util;
import info.androidhive.IRCTC.app.AppController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.sql.Timestamp;

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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;

import android.content.Context;
import android.util.Log;

public class Railway {
	
	private String url="http://api.railwayapi.com/";
	private String INRapi="20238";
	private String apiKeyStr="/apikey/"+INRapi;

	private JSONObject jObj;
	private String forFare="fare/train/";
	private String trainArrival="arrivals/station/";
	private String stationSuggest="suggest_station/name/";
	private String trainSuggest="suggest_train/trains/";
	private String liveTrain="live/train/";	
	private String pnrStatus="pnr_status/pnr/";
	private String seatAvailibity="check_seat/train/";	
	private String trainRoute="route/train/";
	private String trainBtwnStation="between/source/";
	private String trainName="name_number/train/";
	private String stationName="name_to_code/station/";
	private String stationCode="code_to_name/code/";
	
	private String[] Quota={"GN","LD","HO","DF","PH","FT","DP","CK","PT","SS","HP","RE","GNRS","OS","PQ","RC(RAC)","RS","YU","LB"};
	
	public JSONObject getPNR(String PNR){
		url+=pnrStatus+PNR;
		return customRequestCall(url);
	}
	
	public JSONObject getSeatavailable(String train,String Source,String Destination,Date dt,String Class){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
		url+=seatAvailibity+train+"/source/"+Source+"/dest/"+Destination+"/date/"+sdf.format(dt)+"/class/CC/quota/"+Class;
		return customRequestCall(url);
	}

	public JSONObject getFare(String train,String source,String Destination,int age,Date dt){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
		url+=forFare+train+"/source/"+source+"/dest/"+Destination+"/age/"+Integer.toString(age)+"/quota/PT/doj/"+sdf.format(dt);
		return customRequestCall(url);
	}
/* STATION RELATED */
	public JSONObject getStationName(String getStationCode){

		url+=stationCode+getStationCode;
		return customRequestCall(url);
	}

	public JSONObject getStationCode(String station){

		url+=stationName+station;
		return customRequestCall(url);
	}
	public JSONObject getStationSuggest(String train){

		url+=stationSuggest+train;
		return customRequestCall(url);
	}
/* STATION RELATED OVER */
	
/* TRAIN RELATED INFORMATION STARTS*/
	public JSONObject getTrainName(String train){

		url+=trainName+train+"/apikey/"+INRapi;
		return customRequestCall(url);
	}
	public JSONObject getTrainRoute(String train){

		url+=trainRoute+train;
		return customRequestCall(url);
	}
	public JSONObject getTrainBtwnStation(String Source,String Destination){
		url+=trainBtwnStation+Source+"dest"+Destination;
		return customRequestCall(url);
	}
	public JSONObject getTrainSuggest(String train){
		url+=trainSuggest+train;
		return customRequestCall(url);
	}
	public JSONObject getLiveTrain(String train,Date dt){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyymmdd");
		url+=liveTrain+train+"/doj/"+sdf.format(dt);
		return customRequestCall(url);
	}
	public JSONObject getTrainArrival(String Station,Timestamp tm){
		SimpleDateFormat sdf=new SimpleDateFormat("hh");
		url+=trainArrival+Station+"/hours/"+sdf.format(tm);
		return customRequestCall(url);
	}
/*TRAIN RELATED INFORMATION OVER*/
	
/* json volley custom request class call method*/
	private JSONObject customRequestCall(String Url){
		
		customRequest jsObjRequest = new customRequest(Method.GET, url+apiKeyStr, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            	Log.d("response", response.toString());
            	jObj=response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError response) {
            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);
		return jObj;
	}
}
