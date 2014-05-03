package king.muchbeer.vodacomappstar.search;


import king.muchbeer.vodacomappstar.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SearchResultActivity extends Activity implements OnClickListener{

	
	private static final String TAG_TID2 = "naming";
	 private static final String TAG_TID3 = "pricing";
	 private static final String TAG_TID4 = "description";
	 String gname, gprice, gdesc;
	 EditText txtName, txtPrice, txtDesc;
	 Button btnSendResult;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_product);
		
		// getting product details from intent
		Intent i = getIntent();
		
		gname = i.getStringExtra(TAG_TID2);
		gprice = i.getStringExtra(TAG_TID3);
		gdesc = i.getStringExtra(TAG_TID4);
		
		// Edit Text
		txtName = (EditText) findViewById(R.id.inputName);
		txtPrice = (EditText) findViewById(R.id.inputPrice);
		txtDesc = (EditText) findViewById(R.id.inputDesc);

		// display product data in EditText
		txtName.setText(gname);
		txtPrice.setText(gprice);
		txtDesc.setText(gdesc);
		
		btnSendResult = (Button) findViewById(R.id.btnSave);
		btnSendResult.setOnClickListener(this);
		 


	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// Item succeded viewed
		// notify previous activity by sending code 100
		Intent i = new Intent();
		// send result code 100 to notify about product deletion
		setResult(100, i);
		finish();
	}

}
