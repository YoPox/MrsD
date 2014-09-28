package mrsd.ellectron.com.mrsd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoPox on 28/09/2014.
 * This class is used to provide a word to find and the corresponding answer.
 */
public class WordFinder {
    boolean changed = true;
    private int totalInd;
    private String word;
    private String answer;
    private int actindex = 0;
    private List<Integer> activated = new ArrayList<Integer>();
    private List<List<String>> categories = new ArrayList<List<String>>();
    private List<String> names = new ArrayList<String>();

    public String word() {
        //Find new index
        if (changed) calcIndex();
        int oldI = actindex;
        do {
            actindex = (int) Math.floor(Math.random() * totalInd);
        } while (oldI == actindex);
        //Set word and answer
        setWords();
        return word;
    }

    public String answer() {
        return answer;
    }

    public void addName(String name) {
        this.names.add(name);
    }

    public void addCategory() {
        this.categories.add(new ArrayList<String>());
    }

    public void addWord(int index, String word) {
        changed = true;
        this.categories.get(index).add(word);
    }

    private void calcIndex() {
        for (int a = 0; a < categories.size(); a++) {
            this.totalInd += categories.get(a).size();
        }
        changed = false;
    }

    private void setWords() {
        boolean found = false;
        int category = 0; //
        int total = 0;
        int totaltosub = 0;
        for (int a = 0; a < categories.size(); a++) {
            total += categories.get(a).size();
            if (total > actindex && !found) {
                found = true;
                totaltosub = total - categories.get(a).size();
                category = a;
            }
        }
        this.word = this.categories.get(category).get(actindex - totaltosub);
        if ((actindex - totaltosub) % 2 == 0) {
            this.answer = this.categories.get(category).get(actindex - totaltosub + 1);
        } else {
            this.answer = this.categories.get(category).get(actindex - totaltosub - 1);
        }
    }

}
