package king.muchbeer.vodacomappstar.search;

import king.muchbeer.vodacomappstar.*;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;

import king.muchbeer.vodacomappstar.search.CustomHttpClient;
import king.muchbeer.vodacomappstar.user.EditProductActivity;
import king.muchbeer.vodacomappstar.user.MainScreenActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainSearchActivity extends Activity implements OnClickListener{
 EditText byear;   // To take birthyear as input from user
 Button submit, submit2;    
 TextView tv,tv2, tv3;      // TextView to show the result of MySQL query 
 
 String returnString,pid2, pid3, pid4, pid5;   // to store the result of MySQL query after decoding JSON
 
 private static final String TAG_TID2 = "naming";
 private static final String TAG_TID3 = "pricing";
 private static final String TAG_TID4 = "description";		 
 
 /** Called when the activity is first created. */
  
	@Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        byear = (EditText) findViewById(R.id.editText1);
        submit = (Button) findViewById(R.id.submitbutton);
        submit2 = (Button) findViewById(R.id.submitbutton2);
        tv = (TextView) findViewById(R.id.showresult);
        tv2 = (TextView) findViewById(R.id.price);
        tv3 = (TextView) findViewById(R.id.pid2);
        
        //list item name searched
        tv.setOnClickListener(this);  
        tv3.setOnClickListener(this);
        
        
        // define the action when user clicks on submit button
        submit.setOnClickListener(new View.OnClickListener(){        
         public void onClick(View v) {
        	//byear.setText("");
        	 // tv.setText("");
          // declare parameters that are passed to PHP script i.e. the name "birthyear" and its value submitted by user   
          ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
          
          // define the parameter
          postParameters.add(new BasicNameValuePair("name",
      byear.getText().toString()));
          String response = null;
          
          // call executeHttpPost method passing necessary parameters 
          try {
     response = CustomHttpClient.executeHttpPost(
    		 "http://aac2014-tzmarketplace.appspot.com/search.php", // your ip address if using localhost server
      // "http://omega.uta.edu/~kmr2464/jsonscript.php",  // in case of a remote server
       postParameters);
     //http://www.gcdcmwanzapp.appspot.com/search.php
     
     // store the result returned by PHP script that runs MySQL query
     String result = response.toString();  
              
      //parse json data
         try{
                 pid2 = "";
                 pid3 = "";
                 pid4 = "";
                 pid5 = "";
                 
           JSONArray jArray = new JSONArray(result);
                 for(int i=0;i<jArray.length();i++){
                         JSONObject json_data = jArray.getJSONObject(i);
                         Log.i("log_tag","pid: "+json_data.getInt("pid")+
                                 ", name: "+json_data.getString("name")+
                                 ", price: "+json_data.getInt("price")+
                                 ", description: "+json_data.getString("description")
                         );
                         
                         //Get an output to the screen
                         //returnString += "\n" + json_data.getString("name") + " -> "+ json_data.getInt("price") + " TSH";
                         
                         pid2 += json_data.getString("name"); 
                         pid3 += json_data.getInt("price") + " Tsh";
                         pid4 += json_data.getString("description");
                         pid5 = "No item found";
              }
         }
         catch(JSONException e){
        	 tv.setText(pid5);   
        	 Log.e("log_tag", "Error parsing data "+e.toString());
        	 Toast.makeText(getApplicationContext(), "No item found, Please try again",Toast.LENGTH_LONG).show();
     		
         }
     
         try{
        	
          tv.setText(pid2);
          tv2.setText(pid3);
  
}
         catch(Exception e){
          Log.e("log_tag","Error in Display!" + e.toString());
          tv.setText(pid5);
        //  e.printStackTrace();
          Toast.makeText(getApplicationContext(), "No item found, Please try again",Toast.LENGTH_LONG).show();
       	
         }   
    }
          catch (Exception e) {
     Log.e("log_tag","Error in http connection!!" + e.toString());  
     tv.setText(pid5);
    // e.printStackTrace();
     Toast.makeText(getApplicationContext(), "No item found, Please try again",Toast.LENGTH_LONG).show();
  	
    }
         }         
        });
        
        // Button for searching Electronics
        submit2.setOnClickListener(new View.OnClickListener(){        
            public void onClick(View v) {
           	//byear.setText("");
           	 // tv.setText("");
             // declare parameters that are passed to PHP script i.e. the name "birthyear" and its value submitted by user   
             ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
             
             // define the parameter
             postParameters.add(new BasicNameValuePair("name",
         byear.getText().toString()));
             String response = null;
             
             // call executeHttpPost method passing necessary parameters 
             try {
        response = CustomHttpClient.executeHttpPost(
       		 "http://aac2014-tzmarketplace.appspot.com/searchelectronic.php", // your ip address if using localhost server
         // "http://omega.uta.edu/~kmr2464/jsonscript.php",  // in case of a remote server
          postParameters);
        //http://www.gcdcmwanzapp.appspot.com/search.php
        
        // store the result returned by PHP script that runs MySQL query
        String result = response.toString();  
                 
         //parse json data
            try{
                    pid2 = "";
                    pid3 = "";
                    pid4 = "";
                    pid5 = "";
              JSONArray jArray = new JSONArray(result);
                    for(int i=0;i<jArray.length();i++){
                            JSONObject json_data = jArray.getJSONObject(i);
                            Log.i("log_tag","pid: "+json_data.getInt("pid")+
                                    ", name: "+json_data.getString("name")+
                                    ", price: "+json_data.getInt("price")+
                                    ", description: "+json_data.getString("description")
                            );
                            
                            //Get an output to the screen
                            //returnString += "\n" + json_data.getString("name") + " -> "+ json_data.getInt("price") + " TSH";
                            
                         //   pid2 += "\n" + json_data.getString("name")  + "  =>  "+ json_data.getInt("price") + " TSH"; 
                            
                            pid2 += json_data.getString("name");                            
                            pid3 += json_data.getInt("price") + " Tsh"; 
                            pid4 += json_data.getString("description");
                            pid5 = ("No item found");
                    }
            }
            catch(JSONException e){
           		 Log.e("log_tag", "Error parsing data "+e.toString());
           		 tv.setText(pid5); 
           		 Toast.makeText(getApplicationContext(), "No found item, Please try again",Toast.LENGTH_LONG).show();
              	
            }
        
            try{
           	
             tv.setText(pid2);
             tv2.setText(pid3);
     
   }
            catch(Exception e){
             Log.e("log_tag","Error in Display!" + e.toString());
             tv.setText(pid5);
           //  e.printStackTrace();
             Toast.makeText(getApplicationContext(), "No item found, Please try again",Toast.LENGTH_LONG).show();
          	
            }   
       }
             catch (Exception e) {
        Log.e("log_tag","Error in http connection!!" + e.toString());   
       // e.printStackTrace();
        tv.setText(pid5);
        Toast.makeText(getApplicationContext(), "No item found, Please try again",Toast.LENGTH_LONG).show();
     	
       }
            }         
           });
    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
 
        return super.onCreateOptionsMenu(menu);
    }
 public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.home:
        	// Returning Home
			Intent i = new Intent(getApplicationContext(), MainScreenActivity.class);
			startActivity(i);
			
            return true;
        case R.id.exit:
            // Return home
        	Intent intent = new Intent(Intent.ACTION_MAIN);
        	intent.addCategory(Intent.CATEGORY_HOME);
        	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	startActivity(intent);
            
            return true;
       	        default:
            return super.onOptionsItemSelected(item);
        }
 }
 
 @Override
public void onClick(View view) {
	// TODO Auto-generated method stub
	//String pid = ((TextView) view.findViewById(R.id.pid)).toString();
	
	//Starting new Intent
	Intent in = new Intent(getApplicationContext(),
			SearchResultActivity.class);
	
	//sending pid to next activity
	//in.putExtra(TAG_PID, pid4);
	
	in.putExtra(TAG_TID2, pid2);
	in.putExtra(TAG_TID3, pid3);
	in.putExtra(TAG_TID4, pid4);
	
	//starting new activity and expecting some response
	startActivityForResult(in, 100);
}
 
//Response from Edit Product Activity

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	// if result code 100
	if (resultCode == 100) {
		// if result code 100 is received 
		// means user edited/deleted product
		// reload this screen again
		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}

}

	
	
}