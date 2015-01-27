package com.example.cardscontentmaker;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.*;

public class MainActivity1 extends Activity {

	public class Card{
		public int W;
		public int H;
		public double a;
	}

	RelativeLayout rl;
	/*
	ImageView tv;
	ImageView upper = null;
    ImageView lefter = null;
    ImageView prev_tv = null;
*/
	TextView tv;
    TextView upper = null;
    TextView lefter = null;
    TextView prev_tv = null;

	private void Calc(Card[] cards, int from, int to)
	{
	    Random rnd = new Random(); 

		int Wfixed = 240;
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

		TextView newUpper = null;
	    for(int i=from;i<to;i++)
	    {
	    	int newW = (int)(H*cards[i].a);
	    	int newH = H;
	    	
	    	tv = new TextView(this);
		    //tv.setBackgroundColor(Color.parseColor("#ff00ff"));
		    col = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		    tv.setBackgroundColor(col);
			/*
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(newW, newH);
			tv.setLayoutParams(layoutParams);
			*/
		//	tv.getLayoutParams().width = newW;
		//	tv.getLayoutParams().height = newH;
		    tv.setWidth(newW);
		    tv.setHeight(newH);
		    tv.setText(i+") " + cards[i].W + "x" + cards[i].H + "\n" + newW + "x" + newH);
		  
		    tv.setId(i+1);//>0

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

}