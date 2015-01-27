package com.example.cardscontentmaker;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity1 extends Activity {

	RelativeLayout rl;

	public class Card{
		public int W;
		public int H;
		public double a;
	}

	TextView tv;
    TextView upper = null;
    TextView lefter = null;
    TextView prev_tv = null;

	private void Calc(Card[] cards, int from, int to)
	{
	    Random rnd = new Random(); 

        float dpToPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        
		int Wfixed = getResources().getDisplayMetrics().widthPixels;
        double sumA = 0;
        for (int i = from; i < to; i++)
        {
            sumA += cards[i].a;
        }

        int H = (int)(Wfixed / sumA);
        for (int i = from; i < to; i++)
        {
            Log.i("{0}x{1} => {2}x{3}", cards[i].W + "x"+ cards[i].H  + " => "+  (int)(H * cards[i].a) + "x"+  H);
        }
        
        int col = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, getResources().getDisplayMetrics());

		new LoadBackground("http://icode.renren.com/getcode.do?t=web_reg&rnd=1357253201496", "androidfigure", tv).execute();
		TextView newUpper = null;
	    for(int i=from;i<to;i++)
	    {
	    	int newW = (int)(H*cards[i].a);
	    	int newH = H;
	    	
	    	tv = new TextView(this);
		    col = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		    tv.setBackgroundColor(col);
		    tv.setWidth(newW);
		    tv.setHeight(newH);
		    tv.setText(i+") " + cards[i].W + "x" + cards[i].H + "\n" + newW + "x" + newH);
		  
		    if((new Random()).nextDouble()<.5)
		    	tv.setBackgroundResource(R.drawable.ic_launcher);
		    else
		    	tv.setBackgroundResource(R.drawable.stub);

		    tv.setId(i+1);

		    RelativeLayout.LayoutParams rlp1 = new RelativeLayout.LayoutParams(
	    		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

		    if(i==from)
		    {
	    		rlp1.addRule(RelativeLayout.ALIGN_LEFT);
	    		newUpper = tv;
		    }

		    if(lefter!=null)
	    	{	    		
	    		rlp1.addRule(RelativeLayout.RIGHT_OF, 	lefter.getId());
	    		Log.i("addRule", "RIGHT_OF "+lefter.getId());
	    	}

		    if(upper!=null)
		    {
		    	rlp1.addRule(RelativeLayout.BELOW, 		upper.getId());
	    		Log.i("addRule", "BELOW "+upper.getId());
		    }
		    
		    rl.addView(tv, rlp1);

		    lefter = tv;
		    prev_tv = tv;
	    }
	    upper = newUpper;
	    lefter = null;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); 
			StrictMode.setThreadPolicy(policy);
		}
		
		ScrollView sv = new ScrollView(this);
	    rl = new RelativeLayout(this);
	    rl.setBackgroundColor(Color.parseColor("#111111"));
	    rl.setId(9999);
	    sv.addView(rl);

	    Random rnd = new Random(); 
	    int n = 77;
		
		Card[] cards = new Card[n];
		for (int i = 0; i < n; i++)
        {
            int w = rnd.nextInt(100) + 50;
            int h = rnd.nextInt(150) + 50;
            Card c = new Card();
            c.H = h;
            c.W = w;
            c.a = (double)(w) / h;
            Log.i("CARD", w + "x" + h + " => " + c.a);
            cards[i] = c;
        }

		int k = 0;
		while(k<n)
		{
			int l=rnd.nextInt(7)+2;
			int to=k+l;
			if(to>n) to=n;
		    Calc(cards, k,	 to	);
			k = to;
		}

		setContentView(sv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class LoadBackground extends AsyncTask<String, Void, Drawable> {

	    private String imageUrl , imageName;
	    private TextView _tv;

	    public LoadBackground(String url, String file_name, TextView tv) {
	        this.imageUrl = url;
	        this.imageName = file_name;
	    }

	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	    }

	    @Override
	    protected Drawable doInBackground(String... urls) {

	        try {
	            InputStream is = (InputStream) this.fetch(this.imageUrl);
	            Drawable d = Drawable.createFromStream(is, this.imageName);
	            return d;
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	            return null;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    private Object fetch(String address) throws MalformedURLException,IOException {
	        URL url = new URL(address);
	        Object content = url.getContent();
	        return content;
	    }


	    @Override
	    protected void onPostExecute(Drawable result) {
	        super.onPostExecute(result);
	        rl.setBackgroundDrawable(result);
	        //tv.setBackgroundDrawable(result);
	    }
	}
	
}