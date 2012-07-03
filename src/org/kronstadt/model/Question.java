package org.kronstadt.model;

public class Question {

	public String question, answer1, answer2, answer3, answer4;
	
	public Question() {
		question = "";
		answer1 = "";
		answer2 = "";
		answer3 = "";
		answer4 = "";
	}

	public void addAnswer(String answer) {
		if (answer1.equals("")) {
			answer1 = answer;
		} else if (answer2.equals("")) {
			answer2 = answer;
		} else if (answer3.equals("")) {
			answer3 = answer;
		} else if (answer4.equals("")) {
			answer4 = answer;
		}
	}
}
