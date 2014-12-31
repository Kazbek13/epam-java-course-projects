package ua.epam.strings.entities;

public class Word {
	
	private String wordValue;

	public Word(String wordValue) {
		this.wordValue = wordValue;
	}
	
	public String getWordValue() {
		return wordValue;
	}

	public void setWordValue(String wordValue) {
		this.wordValue = wordValue;
	}

	@Override
	public String toString() {
		return wordValue;
	}
}
