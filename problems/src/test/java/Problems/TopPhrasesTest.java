package Problems;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TopPhrasesTest {

    TopPhrases topPhrases;

    @Before
    public void setUp() {
        topPhrases = new TopPhrases("/home/CIT/pbenavalli/", "file.txt");
    }

    @Test
    public void testReadFileKey() throws IOException {
        HashMap<String, Integer> map =  topPhrases.readFile();
        assertTrue(map.containsKey("Microsoft Bing"));
        assertTrue(map.containsKey("CNET"));
        assertTrue(map.containsKey("Foobar Candy"));
    }

    @Test
    public void testReadFileFrequency() throws IOException {
        HashMap<String, Integer> map =  topPhrases.readFile();
        assertEquals((int) map.get("Microsoft Bing"), 3);
        assertEquals((int) map.get("CNET"), 3);
        assertEquals((int) map.get("Foobar Candy"), 4);
    }

    @Test
    public void testSortObject() throws IOException {
        PhraseObject[] phraseObjects = topPhrases.listTopPhrases();
        assertFalse(phraseObjects.length == 0);
        assertEquals(phraseObjects[0].getPhrase(), "Foobar Candy");
        assertEquals(phraseObjects[1].getPhrase(), "Microsoft Bing");
        assertEquals(phraseObjects[2].getPhrase(), "CNET");
        assertEquals(phraseObjects[3].getPhrase(), "PGA");
    }

    @Test
    public void testReadFileCalled() throws IOException {
        TopPhrasesMock mock = new TopPhrasesMock("/home/CIT/pbenavalli/", "file.txt");
        mock.listTopPhrases();
        assertTrue(mock.readFileCalled);
    }

    @Test
    public void testMethodsNotCalled() throws IOException {
        TopPhrasesMock mock = new TopPhrasesMock("/home/CIT/pbenavalli/", "null.txt");
        mock.listTopPhrases();
        assertFalse(mock.loadPathCalled);
        assertTrue(mock.readFileCalled);
    }

    public class TopPhrasesMock extends TopPhrases {

        public boolean loadPathCalled = false;
        public boolean readFileCalled = false;

        public TopPhrasesMock(String x, String y) {
            super(x, y);
        }

        @Override
        protected void loadPath() {
            loadPathCalled = true;
            super.loadPath();
        }

        @Override
        protected HashMap<String, Integer> readFile() throws IOException {
            readFileCalled = true;
            return super.readFile();
        }
    }

}