package ua.epam.strings.entities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import ua.epam.strings.utilites.SentenceCompare;

public class Text {

	private String text;
	private String textFilePath;
	private List<Sentence> textSentences;
	
	public Text(String textFilePath) {
		this.textFilePath = textFilePath;
	}
	
	/**
	 * Loads contents of file for further processing
	 * @throws IOException
	 */
	public void loadText() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(textFilePath));
		
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			line.trim();
			
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			
			setText(sb.toString());
		}
		finally {
			br.close();
		}
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Parses text and returns all sentences of text
	 * @return List<Sentence>
	 */
	public List<Sentence> getSentences() {
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
		
		if (textSentences == null) {
			textSentences = new ArrayList<>();
			StringTokenizer sentenceTokenizer = new StringTokenizer(text, parseProperties.getProperty("parsing.sentenceTokenizer"));
			
			while (sentenceTokenizer.hasMoreTokens()) {
				String sentenceText = sentenceTokenizer.nextToken().replaceAll(parseProperties.getProperty("parsing.whitespace"), parseProperties.getProperty("parsing.whitespaceReplacement")).trim();
				Sentence sentence = new Sentence(sentenceText);
				textSentences.add(sentence);
			}
		}
		
		return textSentences;
	}
	
	public List<Sentence> sortSentencesByWordNumber() {
		List<Sentence> sortedSentences = new ArrayList<Sentence>(this.textSentences);
		Collections.sort(sortedSentences, new SentenceCompare());
		
		return sortedSentences;
	}
	
}
