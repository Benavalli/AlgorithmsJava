package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TopPhrases {

    protected String folderPath;
    protected String fileName;
    protected Path path;
    private static final String PHRASE_SEPARATOR = "\\|";
    private static final int PHRASE_MAX_COUNT = 100000;

    public TopPhrases(String folderPath, String fileName) {
        this.folderPath = folderPath;
        this.fileName = fileName;
        loadPath();
    }

    protected void loadPath() {
        if (folderPath != null && fileName != null) {
            path = Paths.get(folderPath, fileName);
        }
    }

    /**
     * This method is used to print top Phrases.
     * The complexity of this method is O(n)
     *
     * @param phraseObjects
     */
    public void printTopPhrases(PhraseObject[] phraseObjects) {
        for (PhraseObject phrase : phraseObjects) {
            System.out.println(phrase.toString());
        }
    }

    /**
     * This method is used to get HashMap values and create objects with then.
     * This objects are comparable, and the sort is made.
     * The complexity of this method is O(n log n).
     *
     * @return Problems.PhraseObject[] return sorted list Objects
     */
    public PhraseObject[] listTopPhrases() throws IOException {

        HashMap<String, Integer> mapPhrases = readFile();
        Set<Map.Entry<String, Integer>> set = mapPhrases.entrySet();
        java.util.Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
        PhraseObject[] phraseObjects = new PhraseObject[mapPhrases.size()];

        //iteration with class that implements Comparable
        //O(N)
        int loop = 0;
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String str = (String) entry.getKey();
            int integer = (int) entry.getValue();
            phraseObjects[loop] = new PhraseObject(str, integer);
            loop++;
        }

        //Sort the objects by frequency
        //O(n log n) for sort
        Arrays.sort(phraseObjects);

        if (phraseObjects.length > PHRASE_MAX_COUNT) {
            return Arrays.copyOf(phraseObjects, PHRASE_MAX_COUNT);
        }

        return phraseObjects;

    }

    /**
     * This method is used to return a HashMap with the phrases and their ocurrency
     * in a large file separate by pipe.
     * HashMap Was chosen because it stores the memory address where the elements are
     * and has no duplicates.
     * The complexity of this method is O(50N)
     * Since the file does not fit in memory, processing will be done line by line.
     *
     * @return HashMap<String, Integer> return HashMap with the phrases and their ocurrency
     */
    protected HashMap<String, Integer> readFile() throws IOException {

        HashMap<String, Integer> mapFrequencyPhrase = new HashMap<>();
        BufferedReader reader = null;

        try {
            String line;
            //The enconding used will be UTF-8
            reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            //Reading file line per line
            while ((line = reader.readLine()) != null) {
                //Separate all phrases into array
                String[] linePhrases = line.split(PHRASE_SEPARATOR);
                //Put all phrases on HashMap
                //The complexity generally will be O(50) in this case
                for (String phrase : linePhrases) {
                    //The get and put on hashMap will be O(1)
                    String phraseTrim = phrase.trim();
                    if (mapFrequencyPhrase.containsKey(phraseTrim)) {
                        mapFrequencyPhrase.put(phraseTrim, mapFrequencyPhrase.get(phraseTrim) + 1);
                    } else {
                        mapFrequencyPhrase.put(phraseTrim, 1);
                    }
                }

            }

        } catch (IOException exception) {
            System.out.println(exception.toString());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return mapFrequencyPhrase;
    }

}

/**
 * This class is used to store and sort phrases.
 * The sort is based on value.
 */
class PhraseObject implements Comparable<PhraseObject> {

    private String phrase;
    private int value;

    public PhraseObject(String phrase, int value) {
        this.phrase = phrase;
        this.value = value;
    }

    public String getPhrase() {
        return phrase;
    }

    @Override
    public int compareTo(PhraseObject o) {
        return o.value - this.value;
    }

    @Override
    public String toString() {
        return String.format("Frequency: %d , Phrase: %s", value, phrase);
    }
}