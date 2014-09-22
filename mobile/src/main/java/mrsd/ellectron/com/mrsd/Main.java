package mrsd.ellectron.com.mrsd;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Locale;


public class Main extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

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
            R.drawable.smiley_21};

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
            "Eavesdropping = Espion"
    };

    TextView tvd1;
    TextView tvd2;
    TextView tvb1;
    TextView tvb2;
    ImageView smileyView;
    int allMot = 0;
    int angMot = 0;
    boolean smiley = false;

    String[] allMots = {
            "der Beruf",
            "le métier",
            "der Rettungsheffer (-)",
            "le secouriste",
            "der Sanitäter (-)",
            "le secouriste",
            "die Uniform",
            "l'uniforme",
            "tragen (ä, u, a)",
            "porter",
            "in Kreis stehen",
            "se tenir en cercle",
            "sich unterhalten",
            "discuter",
            "malen",
            "peindre",
            "zeichnen",
            "dessiner",
            "die Pinsel (n)",
            "le pinceau",
            "jemanden betreuen / sich um +ACC kümmern",
            "s'occuper de qn",
            "das Au-Pair-Mädchen",
            "la fille au pair",

            //PART 2
            "das Studium",
            "les études",
            "der Student (en)",
            "l'étudiant",
            "die Universität (en)",
            "l'université",
            "die Vorlesung (en)",
            "le cours magistral",
            "der Hörsaal (e)",
            "l'amphithéâtre",
            "das Laboratorium",
            "le laboratoire",
            "das Mikroskop",
            "le microscope",
            "halten (ä, ie, a)",
            "tenir",
            "studieren",
            "étudier",
            "sich interessieren für +ACC",
            "s'intéresser à qch",

            //PART 3
            "die Reise",
            "le voyage",
            "der Wanderer (-)",
            "le randonneur",
            "der Rucksack (¨e)",
            "le sac à dos",
            "ein schmaler Weg",
            "un chemin étroit",
            "hoch / laufen",
            "monter, escalader",
            "das Ehe parr (e)",
            "le couple",
            "der Blumenstrauß",
            "le bouquet de fleurs"
    };

    public void next(View view) {
        int old = allMot;
        while (allMot == old)
            allMot = (int) Math.floor(Math.random() * allMots.length);

        tvb2 = (TextView) findViewById(R.id.textAnswerB);
        tvb2.setText("");
        tvb1 = (TextView) findViewById(R.id.textToFindB);
        tvb1.setText((CharSequence) allMots[allMot]);
    }

    public void check(View view) {

        tvb2 = (TextView) findViewById(R.id.textAnswerB);
        if (allMot % 2 == 0) {
            tvb2.setText(allMots[allMot + 1]);
        } else {
            tvb2.setText(allMots[allMot - 1]);
        }
    }

    public void nextD(View view) {

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
                angMot = (int) Math.floor(Math.random() * smileyId.length);
            smileyView = (ImageView) findViewById(R.id.imageView);
            smileyView.setImageResource(smileyId[angMot]);
        }
    }

    public void checkD(View view) {

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
    }

    public void switchs(View view) {

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
            next(view);
        } else { // On passe en vue smiley
            smiley = true;
            //on affiche un smiley
            nextD(view);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "Mrs D");

        file.mkdirs();

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

            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line = null;
            while ((line = br.readLine()) != null) {
                custom30w.add(line);
                Log.i(LOG_TAG, line);
            }

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
// Récupérer les smileys et les mettre dans la liste smileyId

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            switch (this.getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_d, container, false);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_b, container, false);
                    break;
            }
            return rootView;
        }
    }

}
