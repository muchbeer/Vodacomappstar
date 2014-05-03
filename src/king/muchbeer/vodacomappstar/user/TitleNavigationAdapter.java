package king.muchbeer.vodacomappstar.user;

import king.muchbeer.vodacomappstar.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TitleNavigationAdapter extends BaseAdapter{
	
	private ImageView imgIcon;
    private TextView txtTitle;
    private ArrayList<SpinnerDropActivity> spinnerNavItem;
    private Context context;
 
    public TitleNavigationAdapter(Context context,
            ArrayList<SpinnerDropActivity> spinnerNavItem) {
        this.spinnerNavItem = spinnerNavItem;
        this.context = context;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 if (convertView == null) {
	            LayoutInflater mInflater = (LayoutInflater)
	                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	            convertView = mInflater.inflate(R.layout.list_item_title_navigation, null);
	        }
	         
	        imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
	        txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
	         
	        imgIcon.setImageResource(spinnerNavItem.get(position).getIcon());
	        imgIcon.setVisibility(View.GONE);
	        txtTitle.setText(spinnerNavItem.get(position).getTitle());
	        return convertView;
	    }
	
	@Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item_title_navigation, null);
        }
         
        imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
         
        imgIcon.setImageResource(spinnerNavItem.get(position).getIcon());        
        txtTitle.setText(spinnerNavItem.get(position).getTitle());
        return convertView;
    }
 
}
