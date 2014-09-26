package mrsd.ellectron.com.mrsd;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.List;


public class Main extends Activity {

    String LOG_TAG = "MRS D";
    List<String> custom30w = new ArrayList<String>();
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
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "Mrs D");
        file.mkdirs();

        // TODO : Parser les fichiers du dossier et créer un ArrayList à MainArrayList[index fichier]

        // 30 Mots Custom
        File cust30 = new File(file.getPath() + "/30.txt");
        if (!cust30.exists()) {
            try {
                PrintWriter out = new PrintWriter(cust30.getPath());
                out.println("Mot anglais");
                out.println("Mot français");
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        try {
            FileInputStream fis = new FileInputStream(cust30);

            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line = null;
            while ((line = br.readLine()) != null) {
                custom30w.add(line);
            }

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void nextD(View view) {

        if (check) {

            tvd2 = (TextView) findViewById(R.id.textAnswer);
            if (!smiley) {
                if (angMot % 2 == 0) {
                    tvd2.setText(custom30w.get(angMot + 1));
                } else {
                    tvd2.setText(custom30w.get(angMot - 1));
                }
            } else {
                tvd2.setText(smileyNoms[angMot]);
            }

        } else {

            int old = angMot;
            tvd2 = (TextView) findViewById(R.id.textAnswer);
            tvd2.setText("");
            if (!smiley) {
                while (angMot == old)
                    angMot = (int) Math.floor(Math.random() * custom30w.size());

                tvd1 = (TextView) findViewById(R.id.textToFind);
                tvd1.setText(custom30w.get(angMot));
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

    public void clickBig(){
        b1 = (Button) findViewById(R.id.buttonNext);
        if(check){
            check = !check;
            b1.setText("NEXT");
        } else {
            check = !check;
            b1.setText("CHECK");

        }
    }

}
