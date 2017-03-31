package sample.example.com.camerabasics;

/**
 * Created by sudhanshu on 30/3/17.
 */

public class Word {
    private String wordName;

    public Word(int frequency, String wordName) {
        this.frequency = frequency;
        this.wordName = wordName;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    private int frequency;
}
