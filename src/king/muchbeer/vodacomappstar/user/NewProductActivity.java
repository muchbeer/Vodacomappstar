package king.muchbeer.vodacomappstar.user;

import king.muchbeer.vodacomappstar.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewProductActivity extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	EditText inputName;
	EditText inputPrice;
	EditText inputDesc;

	// url to create new product
	private static String url_create_product = "http://www.aac2014-tzmarketplace.appspot.com/create_product.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_product);
		
		//erroring
		//erroring message
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
			        new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			}

		// Edit Text
		inputName = (EditText) findViewById(R.id.inputName);
		inputPrice = (EditText) findViewById(R.id.inputPrice);
		inputDesc = (EditText) findViewById(R.id.inputDesc);

		// Create button
		Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);

		// button click event
		btnCreateProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				
				  if (  ( inputName.getText().toString().equals("")) || ( inputPrice.getText().toString().equals("")) || ( inputDesc.getText().toString().equals("") ) ) 
			        {
			             // TODO Auto-generated method stub
					  Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
						 
			            i.putExtra("Product", inputName.getText().toString());
			            i.putExtra("Price", inputPrice.getText().toString());
			            i.putExtra("Description", inputDesc.getText().toString());
			           // get the text here
			           final int result = 1;
			           startActivityForResult(i, result); 
			        } else {
				// creating new product in background thread
				new CreateNewProduct().execute();
			        }
			}
		});
	}

	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NewProductActivity.this);
			pDialog.setMessage("Adding New Item.. internet is required");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String name = inputName.getText().toString();
			String price = inputPrice.getText().toString();
			String description = inputDesc.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("price", price));
			params.add(new BasicNameValuePair("description", description));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
					startActivity(i);
					
					// closing this screen
					finish();
				} else {
					// failed to create product
				}
			} catch (JSONException e) {
				//e.printStackTrace();
				
		           e.printStackTrace();
		         
			}

			return null;
		}
		
		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

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
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
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
}
