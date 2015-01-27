package com.example.cardscontentmaker;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private List<String> words;
    private List<String> images;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 

    public LazyAdapter(Activity a, List<String> w, List<String> i) {
    	
    	activity = a;
    	words = w;
    	images = i;
        
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return images.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.item, null);

        TextView text=(TextView)vi.findViewById(R.id.text);
        text.setText(words.get(position));
        
        ImageView image=(ImageView)vi.findViewById(R.id.image);
        imageLoader.DisplayImage(images.get(position), image);
        return vi;
    }
}
