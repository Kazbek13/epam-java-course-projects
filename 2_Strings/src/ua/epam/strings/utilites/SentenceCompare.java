package ua.epam.strings.utilites;

import java.util.Comparator;

import ua.epam.strings.entities.Sentence;

public class SentenceCompare implements Comparator<Sentence> {

	@Override
	public int compare(Sentence s1, Sentence s2) {
		Integer size1 = s1.getSentenceWords().size();
		Integer size2 = s2.getSentenceWords().size();
		return new Integer(size1.compareTo(size2));
	}

}
