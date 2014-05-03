package king.muchbeer.vodacomappstar;


import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import king.muchbeer.vodacomappstar.library.DatabaseHandler;
import king.muchbeer.vodacomappstar.library.UserFunctions;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


//@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class RegisterActivity extends Activity {
	Button btnRegister;
	Button btnLinkToLogin;
	EditText inputFullName;
	EditText inputEmail;
	EditText inputPassword;
	TextView registerErrorMsg;
	
	//json
	JSONObject json;
	UserFunctions userFunction;
	
	
	
	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
	
		if (android.os.Build.VERSION.SDK_INT > 9) {
		StrictMode.ThreadPolicy policy = 
		   new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			}
		
		// Importing all assets like buttons, text fields
		inputFullName = (EditText) findViewById(R.id.registerName);
		inputEmail = (EditText) findViewById(R.id.registerEmail);
		inputPassword = (EditText) findViewById(R.id.registerPassword);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
		registerErrorMsg = (TextView) findViewById(R.id.register_error);
		
		
		// Register Button Click event
		btnRegister.setOnClickListener(new View.OnClickListener() {			
			private String name, email,password;

			public void onClick(View view) {
				 name = inputFullName.getText().toString();
				 email = inputEmail.getText().toString();
				 password = inputPassword.getText().toString();
				 userFunction = new UserFunctions();
				int position = email.indexOf("@");
				
						
						json = userFunction.registerUser(name, email, password);
				/*		
					 if(position == -1) {
						Toast.makeText(getApplicationContext(), "Invalid email address!! Try Again", Toast.LENGTH_LONG).show();
					inputEmail.requestFocus();
					return;
					}
					
					*/
						
					//( inputFullName.getText().toString().equals(""))
					//( inputPassword.getText().toString().equals("") )
					// check for login response
					 if (   ( inputEmail.getText().toString().equals("")) ) 
				        {
						// Toast.makeText(getApplicationContext(), "Please fill the username",Toast.LENGTH_LONG).show();
							
						 	inputEmail.requestFocus();
						  // TODO Auto-generated method stub
						  Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
							 
				          startActivity(i); 
				        }
									
				// check for login response
					 
				 new ShowDialogAsyncTask().execute();
			}

		
		});
		
		// Link to Login Screen
		btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				
						
				Intent i = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(i);
				// Close Registration View
				finish();
				
			}

		
		});
	}
	
	//Class for Async Check for login response
			private class ShowDialogAsyncTask extends AsyncTask<Void, Void, Void> {
			ProgressDialog dialog;
			String erroring;
			private String comp;
			private JSONObject json_user;
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = new ProgressDialog(RegisterActivity.this);
				dialog.setMessage("Loading...");
				dialog.setCancelable(true);
				dialog.setIndeterminate(false);
				dialog.show();
				
				 
			}
			@Override
			protected Void doInBackground(Void... params) {
				try {
					
					// erroring =  json.getJSONArray(KEY_ERROR_MSG);
					// erroring = json_user.getString(KEY_ERROR_MSG).toString();
																							
						if (json.getString(KEY_SUCCESS) != null) {
							//registerErrorMsg.setText("");
							String res = json.getString(KEY_SUCCESS); 
							if(Integer.parseInt(res) == 1){
								// user successfully registred
								// Store user details in SQLite Database
								DatabaseHandler db = new DatabaseHandler(getApplicationContext());
								 json_user = json.getJSONObject("user");
								
								// Clear all previous data in database
								userFunction.logoutUser(getApplicationContext());
								db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
								// Launch Dashboard Screen
								Intent dashboard = new Intent(getApplicationContext(), LoginActivity.class);
								// Close all views before launching Dashboard
								dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								startActivity(dashboard);
								// Close Registration Screen
								finish();
							}
						}
						
					        else{
								// Error in registration
								registerErrorMsg.setText("Error occured in registration");
							}
						}
					 
		catch (JSONException e) {
						e.printStackTrace();
					
					}
				return null;
				}
				
				  protected void onPostExecute(Void result) {
	   super.onPostExecute(result);
	   comp = "\'\'{\"tag\":\"register\",\"success\":0,\"error\":2,\"error_msg\":\"User already existed\"\'\'}";
			   
			   // Toast.makeText(getApplicationContext(),json.toString(), 4).show();
	   if (json.toString().contains("User already existed")) {
		   Toast.makeText(getApplicationContext(), "User already existed, Enter again",Toast.LENGTH_LONG).show();
			  
	   }else {
			 Toast.makeText(getApplicationContext(), "Registered successful",Toast.LENGTH_LONG).show();
				
	  }
	  // Toast.makeText(getApplicationContext(), erroring,Toast.LENGTH_LONG).show();
		   
		//   json.toString().contains(comp);
	   
	   
	   
		
		// Toast.makeText(getApplicationContext(), json.toString(),Toast.LENGTH_LONG).show();
	
	//  String erroring =  json.getJSONArray(KEY_ERROR_MSG).toString();
	//   json.getJSONObject(KEY_ERROR_MSG);
	              
	   }
				
			}	
}
