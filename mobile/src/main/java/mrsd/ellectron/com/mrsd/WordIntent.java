package mrsd.ellectron.com.mrsd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class WordIntent extends Activity {

    int fileNb;
    TextView tv3;
    private Typeface caviarDreamsItalic;
    View linearL;
    LinearLayout ll;
    File acttxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_intent);

        fileNb = getIntent().getIntExtra("nb", 0);

        caviarDreamsItalic = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams_Italic.ttf");

        tv3 = (TextView) findViewById(R.id.mrsD2);
        tv3.setTypeface(caviarDreamsItalic);
        ((Button) findViewById(R.id.button)).setTypeface(caviarDreamsItalic);

        linearL = findViewById(R.id.llay);
        ll = (LinearLayout) findViewById(R.id.llay);

        dispWords();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.word_intent, menu);
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

    public void add(View v) {
        LayoutInflater linf = LayoutInflater.from(this);
        final View inflator = linf.inflate(R.layout.addword_dialog, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Tilte");
        alert.setMessage("Message");
        alert.setView(inflator);

        final EditText et1 = (EditText) inflator.findViewById(R.id.eng);
        final EditText et2 = (EditText) inflator.findViewById(R.id.fr);

        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String s1 = et1.getText().toString();
                String s2 = et2.getText().toString();

                FileOutputStream fos = null;

                int count = 0;

                try {
                    fos = new FileOutputStream(acttxt, true);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

                    InputStream is = new BufferedInputStream(new FileInputStream(acttxt));
                    try {
                        byte[] c = new byte[1024];
                        int readChars = 0;
                        boolean empty = true;
                        while ((readChars = is.read(c)) != -1) {
                            empty = false;
                            for (int i = 0; i < readChars; ++i) {
                                if (c[i] == '\n') {
                                    ++count;
                                }
                            }
                        }
                    } finally {
                        is.close();
                    }

                    if (count % 2 == 0) {
                        bw.write(s1);
                        bw.newLine();
                        bw.write(s2);
                    } else {
                        bw.newLine();
                        bw.write(s1);
                        bw.newLine();
                        bw.write(s2);
                        bw.newLine();
                    }


                    bw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ll.removeAllViews();
                dispWords();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        alert.show();
    }

    public void dispWords() {
        // Dossier Mrs D
        File file = new File(Environment.getExternalStorageDirectory(), "Documents/Mrs D");

        // On parse les fichiers du dossier et on crée un ArrayList à categories[index] de nom names[index]
        File[] files = file.listFiles();

        acttxt = files[fileNb];
        FileInputStream fis = null;
        try {

            fis = new FileInputStream(acttxt);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line = null;
            String text = "";
            int a = 0;

            while ((line = br.readLine()) != null) {
                if (a % 2 == 0) {
                    text += line;
                } else {
                    text += "\n" + line;
                }
                if (a % 2 != 0) {
                    //TODO: swap TextViews with cards
                    Button b = new Button(this);
                    b.setHeight(200);
                    if (a / 2 % 2 == 0) {
                        b.setBackgroundResource(R.color.grey);
                    } else {
                        b.setBackgroundResource(R.color.white);
                    }
                    b.setText(text);
                    ((LinearLayout) linearL).addView(b);
                    text = "";
                }
                a++;
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

