package ua.epam.strings.entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

public class Sentence {
	
	private String sentenceValue;
	private List<Word> sentenceWords;
	
	public Sentence(String sentenceValue) {
		this.sentenceValue = sentenceValue;
	}

	public String getSentenceValue() {
		return sentenceValue;
	}

	public void setSentenceValue(String sentenceValue) {
		this.sentenceValue = sentenceValue;
	}
	
	/**
	 * Parses sentence and returns all words of sentence
	 * @return List<Word>
	 */
	public List<Word> getSentenceWords() {
		Properties parseProperties = new Properties();

		try {
			parseProperties.load(new FileInputStream("resources/parse.properties"));
		} 
		catch (FileNotFoundException e) {
			System.err.println("Can't find properties file");
			e.printStackTrace();
		} 
		catch (IOException e) {
			System.err.println("Can't read properties file");
			e.printStackTrace();
		}
		
		if (sentenceWords == null) {
			sentenceWords = new ArrayList<>();
			StringTokenizer wordTokenizer = new StringTokenizer(sentenceValue, parseProperties.getProperty("parsing.wordTokenizer"));
			
			while (wordTokenizer.hasMoreTokens()) {
				Word word = new Word(wordTokenizer.nextToken().trim());
				sentenceWords.add(word);
			}
		}
		
		return sentenceWords;
	}
	
	@Override
	public String toString() {
		return sentenceValue;
	}
}
