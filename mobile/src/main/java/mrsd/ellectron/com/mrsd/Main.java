package mrsd.ellectron.com.mrsd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Main extends Activity {

    String LOG_TAG = "MRS D";
    int[] smileyId = {R.drawable.smiley_01,
            R.drawable.smiley_02,
            R.drawable.smiley_03,
            R.drawable.smiley_04,
            R.drawable.smiley_05,
            R.drawable.smiley_06,
            R.drawable.smiley_07,
            R.drawable.smiley_08,
            R.drawable.smiley_09,
            R.drawable.smiley_10,
            R.drawable.smiley_11,
            R.drawable.smiley_12,
            R.drawable.smiley_13,
            R.drawable.smiley_14,
            R.drawable.smiley_15,
            R.drawable.smiley_16,
            R.drawable.smiley_17,
            R.drawable.smiley_18,
            R.drawable.smiley_19,
            R.drawable.smiley_20,
            R.drawable.smiley_21,
            R.drawable.smiley_22,
            R.drawable.smiley_23,
            R.drawable.smiley_24,
            R.drawable.smiley_25,
            R.drawable.smiley_26,
            R.drawable.smiley_27,
            R.drawable.smiley_28,
            R.drawable.smiley_29,
            R.drawable.smiley_30,
            R.drawable.smiley_31,
            R.drawable.smiley_32,
            R.drawable.smiley_33,
            R.drawable.smiley_34,
            R.drawable.smiley_35,
            R.drawable.smiley_36,
            R.drawable.smiley_37,
            R.drawable.smiley_38,
            R.drawable.smiley_39,
            R.drawable.smiley_40,
            R.drawable.smiley_41,
            R.drawable.smiley_42,
            R.drawable.smiley_43,
            R.drawable.smiley_44,
            R.drawable.smiley_45,
            R.drawable.smiley_46,
            R.drawable.smiley_47,
            R.drawable.smiley_48,
            R.drawable.smiley_49,
            R.drawable.smiley_50,
            R.drawable.smiley_51,
            R.drawable.smiley_52,
            R.drawable.smiley_53,
            R.drawable.smiley_54,
            R.drawable.smiley_55,
            R.drawable.smiley_56,
            R.drawable.smiley_57,
            R.drawable.smiley_58,
            R.drawable.smiley_59,
            R.drawable.smiley_60,
            R.drawable.smiley_61,
            R.drawable.smiley_62,
            R.drawable.smiley_63,
            R.drawable.smiley_64,
            R.drawable.smiley_65,
            R.drawable.smiley_66,
            R.drawable.smiley_67,
            R.drawable.smiley_68,
            R.drawable.smiley_69,
            R.drawable.smiley_70};

    String[] smileyNoms = {
            "Aggressive",
            "Agonized = Angoissé",
            "Anxious",
            "Apologetic",
            "Arrogant",
            "Bashful = Timide",
            "Blissful = Divin",
            "Bored",
            "Cautious = Prudent",
            "Cold",
            "Concentrating",
            "Confident = Sûr",
            "Curious",
            "Demure = Sage",
            "Determined",
            "Disappointed",
            "Dissaproving",
            "Disbelieving = Incrédule",
            "Disgusted",
            "Distasteful = Désagréable",
            "Eavesdropping = Espion",
            "Ecstatic",
            "Enraged",
            "Envious",
            "Exasperated",
            "Exhausted",
            "Frightened",
            "Frustated",
            "Grieving",
            "Guilty",
            "Happy",
            "Horrified",
            "Hot",
            "Hungover",
            "Hurt",
            "Hysterical",
            "Indifferent",
            "Idiotic",
            "Innocent",
            "Interested",
            "Jealous",
            "Joyful"
    };

    TextView tvd1;
    TextView tvd2;
    TextView tv3;
    ImageView smileyView;
    Button b1;
    int angMot = 0;
    boolean smiley = false;
    private Typeface caviarDreams;
    private Typeface caviarDreamsBold;
    private Typeface caviarDreamsItalic;
    boolean check = false;

    WordFinder wordFinder = new WordFinder();

    public static final String PREFS_NAME = "MD";
    int catNb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Custom font

        caviarDreams = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        caviarDreamsBold = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams_Bold.ttf");
        caviarDreamsItalic = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams_Italic.ttf");
        b1 = (Button) findViewById(R.id.buttonNext);
        b1.setTypeface(caviarDreams);
        b1.setText("NEXT");

        tvd2 = (TextView) findViewById(R.id.textAnswer);
        tvd2.setTypeface(caviarDreams);
        tvd1 = (TextView) findViewById(R.id.textToFind);
        tvd1.setTypeface(caviarDreamsBold);
        tv3 = (TextView) findViewById(R.id.mrsD);
        tv3.setTypeface(caviarDreamsItalic);

        // File stuff

        // Dossier Mrs D
        File file = new File(Environment.getExternalStorageDirectory(), "Documents/Mrs D");
        file.mkdirs();

        // On parse les fichiers du dossier et on crée un ArrayList à categories[index] de nom names[index]
        File[] files = file.listFiles();
        catNb = files.length;

        // 30 Mots Custom
        if (files.length == 0) {
            try {
                File cust30 = new File(file.getPath() + "/30 mots");
                PrintWriter out = new PrintWriter(cust30.getPath());
                out.println("On account of");
                out.println("Étant donné");
                out.println("Furthermore");
                out.println("En outre");
                out.println("On top of that");
                out.println("De plus ");
                out.println("Into the bargain");
                out.println("Par dessus le marché");
                out.println("As against");
                out.println("En opposition à");
                out.println("Conversely");
                out.println("Inversement");
                out.println("Otherwise");
                out.println("Autrement");
                out.println("Although / Though");
                out.println("Bien que");
                out.println("No matter how");
                out.println("Peu importe comment");
                out.println("No matter what");
                out.println("Peu importe ce que");
                out.println("Yet / Still");
                out.println("Pourtant");
                out.println("Nonetheless");
                out.println("Néanmoins");
                out.println("Despite");
                out.println("Malgré");
                out.println("Whenever");
                out.println("A chaque fois que");
                out.println("Meanwhile");
                out.println("Pendant ce temps là");
                out.println("In the meantime");
                out.println("Entre temps");
                out.println("At times");
                out.println("Parfois");
                out.println("Provided");
                out.println("Pourvu que / A condition que");
                out.println("Till / Until");
                out.println("Jusqu'à (ce que)");
                out.println("At first sight");
                out.println("A première vue");
                out.println("At all events");
                out.println("En tout cas");
                out.println("To some extent ");
                out.println("Dans une certaine mesure");
                out.println("On second thoughts");
                out.println("A la réflexion");
                out.println("Anyway");
                out.println("De toutes façons");
                out.println("In this respect");
                out.println("A cet égard");
                out.println("Namely");
                out.println("C'est à dire");
                out.println("Above all");
                out.println("Surtout");
                out.println("Owing to");
                out.println("En raison de");
                out.println("Due to");
                out.println("Du fait de");
                out.println("Hence");
                out.println("D'où");
                out.close();
                catNb++;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        for (int a = 0; a < files.length; a++) {
            File acttxt = files[a];
            FileInputStream fis = null;
            try {

                fis = new FileInputStream(acttxt);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));

                wordFinder.addCategory();
                wordFinder.addName(acttxt.getName());

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("n" + Integer.toString(a), acttxt.getName());
                editor.commit();

                String line = null;

                while ((line = br.readLine()) != null) {
                    wordFinder.addWord(a, line);
                }
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void nextD(View view) {

        if (check) {

            tvd2 = (TextView) findViewById(R.id.textAnswer);
            if (!smiley) {
                tvd2.setText(wordFinder.answer());
            } else {
                tvd2.setText(smileyNoms[angMot]);
            }

        } else {

            int old = angMot;
            tvd2 = (TextView) findViewById(R.id.textAnswer);
            tvd2.setText("");
            if (!smiley) {
                tvd1 = (TextView) findViewById(R.id.textToFind);
                tvd1.setText(wordFinder.word());
            } else {
                // Changer le smiley
                while (angMot == old)
                    angMot = (int) Math.floor(Math.random() * smileyNoms.length);
                smileyView = (ImageView) findViewById(R.id.imageView);
                smileyView.setImageResource(smileyId[angMot]);

            }
        }
        clickBig();
    }

    public void switchs(View view) {

        check = false;

        //On vide les textview
        tvd2 = (TextView) findViewById(R.id.textAnswer);
        tvd2.setText("");
        tvd1 = (TextView) findViewById(R.id.textToFind);
        tvd1.setText("");
        //On vide le smiley
        smileyView = (ImageView) findViewById(R.id.imageView);
        smileyView.setImageResource(0);

        if (smiley) { // On passe en vue mots
            smiley = false;
            //on affiche un mot
            nextD(view);
        } else { // On passe en vue smiley
            smiley = true;
            //on affiche un smiley
            nextD(view);
        }

        b1 = (Button) findViewById(R.id.buttonNext);
        b1.setText("CHECK");
        check = true;

    }

    public void clickBig() {
        b1 = (Button) findViewById(R.id.buttonNext);
        if (check) {
            check = !check;
            b1.setText("NEXT");
        } else {
            check = !check;
            b1.setText("CHECK");

        }
    }

    public void callList(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("nb", catNb);
        startActivity(intent);
    }
}
