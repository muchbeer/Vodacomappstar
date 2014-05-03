
package king.muchbeer.vodacomappstar.user;

import king.muchbeer.vodacomappstar.*;
import king.muchbeer.vodacomappstar.user.NewElectronicsActivity;
import king.muchbeer.vodacomappstar.user.NewProductActivity;

import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewCategoryActivity extends Activity implements OnItemSelectedListener{
	//Intent openStartingPoint = new Intent(MainActivity.this, New.class);
	//startActivity(openStartingPoint);
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		//spiner selected
		Spinner spinner =  (Spinner) findViewById(R.id.spinner);
		
		//spinner selected listener
		spinner.setOnItemSelectedListener(this);
		// Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select Product");
        categories.add("Electronics");
        categories.add("Personal");

       // categories.add("Travel");
        
        // Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
		
		// Drop down layout style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// On selecting a spinner item
				String item = parent.getItemAtPosition(position).toString();
				
				// Showing selected spinner item
				Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

				if(item.equalsIgnoreCase("Personal")) {
					 Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
		   				startActivity(i); 
				}
				
				else if(item.equalsIgnoreCase("Electronics")) {
					 Intent i = new Intent(getApplicationContext(), NewElectronicsActivity.class);
		   				startActivity(i); 
				}
			
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}