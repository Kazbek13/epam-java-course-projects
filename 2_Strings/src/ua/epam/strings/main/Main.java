package ua.epam.strings.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ua.epam.strings.entities.Sentence;
import ua.epam.strings.entities.Text;

public class Main {

	public static void main(String[] args) {
		String absolutePath = new File("").getAbsolutePath();
		String filePath = absolutePath.concat("\\resources\\programming_book_text.txt");
		Text text = new Text(filePath);
		try {
			text.loadText();
		} catch (IOException e) {
			System.err.println("Failed to load a text");
			e.printStackTrace();
		}
		
		System.out.println("=== Given text ===");
		System.out.println(text.getText());
		
		System.out.println("=== Sentences of text ===");
		System.out.println(text.getSentences());
		
		System.out.println("\n=== Total number of sentences in text ===");
		System.out.println(text.getSentences().size());
		
		System.out.println("\n=== Words of sentences ===");
		for (Sentence s: text.getSentences()) {
			System.out.println(s.getSentenceWords());
		}
		
		List<Sentence> sorted = text.sortSentencesByWordNumber();
		
		System.out.println("\n=== Sentences of text sorted by number of words in them ascending ===");
		for (Sentence s: sorted) {
			System.out.println(s);
		}
	}

}
