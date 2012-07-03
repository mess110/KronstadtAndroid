package org.kronstadt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kronstadt.model.Answer;
import org.kronstadt.model.Question;
import org.kronstadt.util.HTTPClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TriviaActivity extends Activity {

	private HTTPClient http;

	private final int ID_MENU_EXIT = 0;
	private final int ID_MENU_REFRESH = 1;
	private final int ID_MENU_FINISH = 2;

	private Button answerA, answerB, answerC, answerD;
	private TextView question;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_MENU_FINISH, Menu.NONE, "finish");
		menu.add(Menu.NONE, ID_MENU_REFRESH, Menu.NONE, "refresh");
		menu.add(Menu.NONE, ID_MENU_EXIT, Menu.NONE, "exit");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ID_MENU_EXIT:
			this.finish();
			break;
		case ID_MENU_REFRESH:
			populateQuestionContent();
			break;
		case ID_MENU_FINISH:
			finishQuestion();
			break;
		default:
			break;
		}
		return false;
	}

	private void finishQuestion() {
		http.finishQuestion();
		populateQuestionContent();
	}

	private void populateQuestionContent() {
		Question q = new Question();
		String triviaQuestion = http.getQuestion();
		JSONObject json = null;
		try {
			json = new JSONObject(triviaQuestion);
			String questionString = json.getString("question");
			String questionText = new JSONObject(questionString)
					.getString("text");

			q.question = questionText;

			JSONArray answers = json.getJSONArray("answers");
			for (int i = 0; i < answers.length(); i++) {
				JSONObject answer = (JSONObject) answers.get(i);
				q.addAnswer(answer);
			}

			question.setText(q.question);

			prepareAnswerButton(answerA, q.answer1);
			prepareAnswerButton(answerB, q.answer2);
			prepareAnswerButton(answerC, q.answer3);
			prepareAnswerButton(answerD, q.answer4);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void prepareAnswerButton(Button button, final Answer answer) {
		button.setText(answer.text);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				http.answer(answer);
			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trivia);

		http = new HTTPClient(getApplicationContext());

		question = (TextView) findViewById(R.id.question);

		answerA = (Button) findViewById(R.id.a_button);
		answerB = (Button) findViewById(R.id.b_button);
		answerC = (Button) findViewById(R.id.c_button);
		answerD = (Button) findViewById(R.id.d_button);
		
		populateQuestionContent();
	}
}
