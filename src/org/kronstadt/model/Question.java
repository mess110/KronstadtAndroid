package org.kronstadt.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Question {

	public String question;
	public Answer answer1 = null;
	public Answer answer2 = null;
	public Answer answer3 = null;
	public Answer answer4 = null;

	public Question() {
	}

	public void addAnswer(JSONObject answerJSON) {
		Answer answer = new Answer();
		try {
			answer.id = answerJSON.getString("id");
			answer.text = answerJSON.getString("text");

			if (answer1 == null) {
				answer1 = answer;
			} else if (answer2 == null) {
				answer2 = answer;
			} else if (answer3 == null) {
				answer3 = answer;
			} else if (answer4 == null) {
				answer4 = answer;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
