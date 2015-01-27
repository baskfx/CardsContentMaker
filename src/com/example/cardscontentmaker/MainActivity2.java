//группы с видео: 
//http://vk.com/videos-34479639
//http://vk.com/videos-11292596
//http://vk.com/videos-36581751
//http://vk.com/videos-11781082
//http://vk.com/videos-135285

//In-App Billing
//http://habrahabr.ru/post/117944/

package com.example.cardscontentmaker;

//import java.io.Console;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity2 extends Activity {

    ListView list;
    LazyAdapter adapter;
    List<String> images = new ArrayList<String>();
    List<String> titles = new ArrayList<String>();
    List<String> ids = new ArrayList<String>();
    List<String> vid = new ArrayList<String>();
    List<String> delay = new ArrayList<String>();
    private static ProgressDialog progressDialog;
    Handler h;
    
    private Button searchBtn;
    private EditText searchText;
    
    public OnClickListener listener=new OnClickListener(){
        public void onClick(View arg0) {
        	Log.d("onClick listener", "START");
            adapter.imageLoader.clearCache();
            adapter.notifyDataSetChanged();
        }
    };
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); 
			StrictMode.setThreadPolicy(policy);
		}

		//getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        h = new Handler();
        
        setContentView(R.layout.activity_main2);

        SearchItems("");
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onDestroy()
    {
    	if(list != null)
    		list.setAdapter(null);
        super.onDestroy();
    }
   
    public void SearchItems(String query){
    	try{
	        images = new ArrayList<String>();
	        titles = new ArrayList<String>();
  			titles.add("image #1");
	        images.add("http://icode.renren.com/getcode.do?t=web_reg&rnd=1357253201496");
	        
	         list=(ListView)findViewById(R.id.list);
	         
	         list.setClickable(true);
	         list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        	 public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	        	    Object o = list.getItemAtPosition(position);
	        	    Log.i("Item click", o.toString() + ") " + images.get(position));
	
	        	 }
	        	});
	         
        	adapter=new LazyAdapter(this, titles, images);
	        list.setAdapter(adapter);
    	}
    	catch(Exception e){
            Toast msg = Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG);
            msg.show();
    	}
    }
}
