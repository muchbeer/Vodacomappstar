package king.muchbeer.vodacomappstar.user;

import java.util.ArrayList;
import java.util.List;

import king.muchbeer.vodacomappstar.*;
import king.muchbeer.vodacomappstar.login.MainLoginActivity;
import king.muchbeer.vodacomappstar.search.MainSearchActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


public class MainScreenActivity extends Activity implements OnItemSelectedListener{
	
	Button btnViewProducts;
	Button btnNewProduct;
	Button searchProduct;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		//spiner selected
		Spinner spinner =  (Spinner) findViewById(R.id.spinner);
		
		//spinner selected listener
		spinner.setOnItemSelectedListener(this);
		// Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select Category");
        categories.add("Electronic Store");
        categories.add("Personal Store");
        
        // Creating adapter for spinner
   		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
   		
   		// Drop down layout style - list view with radio button
   		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
   		
   		// attaching data adapter to spinner
   		spinner.setAdapter(dataAdapter);
		
		// Buttons
		//btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
		btnNewProduct = (Button) findViewById(R.id.btnCreateProduct);
		searchProduct = (Button) findViewById(R.id.btnSearchProduct);
		// view products click event
	
		
		// view products click event
		btnNewProduct.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching create new product activity
				//Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
			//	startActivity(i);
//				Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
				Intent i = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(i);
				
			}
		});
		
		// search products click event
		searchProduct.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching create new product activity
				//Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
			//	startActivity(i);
//				Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
				Intent i = new Intent(getApplicationContext(), MainSearchActivity.class);
				startActivity(i);
				
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
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
		
		// On selecting a spinner item
		String item = parent.getItemAtPosition(position).toString();
		
		// Showing selected spinner item
		Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

		if(item.equalsIgnoreCase("Electronic Store")) {
			 Intent i = new Intent(getApplicationContext(), AllElectronicsActivity.class);
   				startActivity(i); 
		}
		
		else if(item.equalsIgnoreCase("Personal Store")) {
			 Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
   				startActivity(i); 
		}
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
