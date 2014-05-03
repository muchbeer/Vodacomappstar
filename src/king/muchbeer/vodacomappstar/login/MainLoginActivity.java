package king.muchbeer.vodacomappstar.login;

import king.muchbeer.vodacomappstar.*;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import king.muchbeer.vodacomappstar.login.CustomHttpClient;
import king.muchbeer.vodacomappstar.user.NewProductActivity;
//import com.example.androiddbconnection.CustomHttpClient;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainLoginActivity extends Activity {
 EditText byear, pass;   // To take birthyear as input from user
 Button submit;    
 TextView tv;      // TextView to show the result of MySQL query 
 
 String returnString, userlogo, passlogo, editUser, editPass;   // to store the result of MySQL query after decoding JSON
 
    /** Called when the activity is first created. */
  
	@Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        byear = (EditText) findViewById(R.id.editText1);
        pass = (EditText) findViewById(R.id.editText2);
        submit = (Button) findViewById(R.id.submitbutton);
        tv = (TextView) findViewById(R.id.showresult);
        
        
        editUser = byear.getText().toString();   
        editPass = pass.getText().toString();
        // define the action when user clicks on submit button
        submit.setOnClickListener(new View.OnClickListener(){        
         public void onClick(View v) {
          // declare parameters that are passed to PHP script i.e. the name "birthyear" and its value submitted by user   
          ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
          
          // define the parameter
          postParameters.add(new BasicNameValuePair("username", byear.getText().toString()));
          postParameters.add(new BasicNameValuePair("password", pass.getText().toString()));
        	          String response = null;
          
          
          // call executeHttpPost method passing necessary parameters 
          try {
     response = CustomHttpClient.executeHttpPost(
       "http://192.168.43.8:100/loginproduct.php", // your ip address if using localhost server
      // "http://omega.uta.edu/~kmr2464/jsonscript.php",  // in case of a remote server
       postParameters);
     
     // store the result returned by PHP script that runs MySQL query
     String result = response.toString();  
              
      //parse json data
         try{
                 returnString = "";
                 userlogo="";
                 passlogo="";
                 editUser = byear.getText().toString();
                 editPass =  pass.getText().toString();
                 
           JSONArray jArray = new JSONArray(result);
                 for(int i=0;i<jArray.length();i++){
                         JSONObject json_data = jArray.getJSONObject(i);
                         Log.i("log_tag","id: "+json_data.getInt("id")+
                                 ", username: "+json_data.getString("username")+
                                 ", password: "+json_data.getString("password")+
                                 ", fullname: "+json_data.getString("fullname")+
                                 ", gender: "+json_data.getString("gender")
                         );
                         //Get an output to the screen
                         returnString += "\n" + json_data.getString("username") + " -> "+ json_data.getString("password");
                         userlogo += json_data.getString("username");
                         passlogo += json_data.getString("password");
                 }
         }
         catch(JSONException e){
                 Log.e("log_tag", "Error parsing data "+e.toString());
                 e.printStackTrace();
         }
     
         try{
        	 if(editPass.equalsIgnoreCase(passlogo) && editUser.equalsIgnoreCase(userlogo)) {
        		 Intent i = new Intent(getApplicationContext(), MainCategoryActivity.class);
   				startActivity(i); 
        //	tv.setText(returnString);
        	 }else {
        		// tv.setTextSize(30);
        		// tv.setText("Invalid user");
        		 Intent i = new Intent(getApplicationContext(), MainCategoryActivity.class);
    				startActivity(i);
        	 }
       
         }
         catch(Exception e){
          Log.e("log_tag","Error in Display!" + e.toString());; 
          e.printStackTrace();
         // tv.setText("Invalid user");
         }   
    }
          catch (Exception e) {
     Log.e("log_tag","Error in http connection!!" + e.toString());     
    }
         }         
        });
    }
}