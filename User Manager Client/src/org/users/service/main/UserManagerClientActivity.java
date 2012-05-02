package org.users.service.main;

import org.users.service.logic.R;
import org.users.service.logic.UserManagerLogic;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Represents UI
 * 
 * @author Andrey Kovrigin
 * @since 2012
 * @version 1.0 04/25/2012
 */
public class UserManagerClientActivity extends Activity implements
		OnClickListener {
	
	/** 
	 * Provides application business logic. 
	 */
	UserManagerLogic userManagerLogic = new UserManagerLogic();

	/** 
	 * Called when the activity is first created. 
	 * 
	 * @param savedInstanceState savedInstanceState
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		View searchButton = findViewById(R.id.search);
		searchButton.setOnClickListener(this);
	}

	/**
	 * Calls when the button is pressed 
	 * 
	 * @param view view
	 */
	public void onClick(View view) {
		EditText editFieldUserId = (EditText) findViewById(R.id.editId);
		TextView resultField = (TextView) findViewById(R.id.result);
		resultField.setText(Html.fromHtml(userManagerLogic.processRequest(editFieldUserId)));
	}

}