package org.users.service.logic;

import org.ksoap2.serialization.PropertyInfo;
import android.text.Editable;
import android.widget.EditText;

/**
 * Provide business logic of application
 * 
 * @author Andrey Kovrigin
 * @since 2012
 * @version 1.0 04/25/2012
 */
public class UserManagerLogic {

	// production params 
	/*
	private static final String METHOD_NAME = "getUserName"; 
	private static final String NAMESPACE = "http://service.users.org/"; 
	private static final String URL = "http://usersoapservice.appspot.com/service.do";
	*/
	
	// developer params
	private static final String METHOD_NAME = "getUser";
	private static final String NAMESPACE = "http://logic.service.test/";
	private static final String URL = "http://192.168.1.2:8080/WS/calc";

	/**
	 * Search result constant.
	 */
	private static final String SEARCH_RESULT = "Search result: ";

	/**
	 * Incorrect input constant.
	 */
	private static final String INCORRECT_INPUT = "User ID must be a number!";

	/**
	 * Id constant.
	 */
	private static final String ID = "id";

	/**
	 * Empty string constant.
	 */
	private static final String EMPTY_STRING = "";

	/**
	 * Provides soap client logic.
	 * */
	SOAPLogic logic = new SOAPLogic();

	/**
	 * Processes user request
	 * 
	 * @param editFieldUserId
	 *            edit field for user id
	 * @return String string with processes result
	 */
	public String processRequest(EditText editFieldUserId) {
		long userId = getUserId(editFieldUserId);
		if (userId != 0) {
			String result = findUser(userId);
			return SEARCH_RESULT + result;
		} else {
			return INCORRECT_INPUT;
		}
	}

	/**
	 * Return user id from edit field
	 * 
	 * @param editFieldUserId
	 * @return long user id
	 */
	public long getUserId(EditText editFieldUserId) {
		long userId = 0;
		if (editFieldUserId != null) {
			Editable inputText = editFieldUserId.getText();
			if (inputText != null) {
				String userIdStr = inputText.toString();
				if (!EMPTY_STRING.equals(userIdStr) && userIdStr != null) {
					userId = Long.valueOf(inputText.toString());
				}
			}
		}
		return userId;
	}

	/**
	 * Find user
	 * 
	 * @param userId user id
	 * @return String string with user description
	 */
	private String findUser(long userId) {
		PropertyInfo propertyInfo = logic.createProperty(ID,
				PropertyInfo.LONG_CLASS);
		return logic.doRequest(METHOD_NAME, NAMESPACE, URL, propertyInfo,
				userId);
	}
	
}
