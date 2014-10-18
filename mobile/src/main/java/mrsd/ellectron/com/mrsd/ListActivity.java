package mrsd.ellectron.com.mrsd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ListActivity extends Activity {

    TextView tv3;
    private Typeface caviarDreamsItalic;
    public static final String PREFS_NAME = "MD";
    int cardsNb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        cardsNb = getIntent().getIntExtra("nb", 0);

        caviarDreamsItalic = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams_Italic.ttf");

        tv3 = (TextView) findViewById(R.id.mrsD2);
        tv3.setTypeface(caviarDreamsItalic);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        View linearL = findViewById(R.id.llay);

        for (int a = 0; a < cardsNb; a++) {

            //TODO: swap TextViews with cards
            // Oui mais au moins là ça marche
            Button b = new Button(this);
            b.setHeight(200);
            b.setTag(a);
            if (a % 2 == 0) {
                b.setBackgroundResource(R.color.grey);
            } else {
                b.setBackgroundResource(R.color.white);
            }
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), WordIntent.class);
                    intent.putExtra("nb", (Integer) v.getTag());
                    startActivity(intent);
                }
            });
            b.setText(settings.getString("n" + Integer.toString(a), "BUG ! please report"));
            ((LinearLayout) linearL).addView(b);

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
