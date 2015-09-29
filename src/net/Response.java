package net;

import org.json.JSONObject;

public class Response {
	/**
     * 
     */
	public static final String KEY_MESSAGE = "message";
	protected String msg;
	String data;
	protected String successmsg;
	/**
     * 
     */

	boolean isError = false;

	public Response(String data) {
		this.data = data;
		try {
			JSONObject doc = new JSONObject(data);

			//successmsg = doc.getString("msg");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	

	/**
	 * Creates a new Response object.
	 * 
	 * @param data
	 *            json data
	 */
	public Response(String msg, boolean isError) {

		this.msg = msg;
		this.isError = isError;
	}

	public String getSuccessMessage() {
		return successmsg;
	}

	/**
	 * 
	 * 
	 * @return true if error is returned from server
	 */
	public boolean isError() {
		return isError;
	}

	/**
	 * 
	 * 
	 * @return error message
	 */
	public String getErrorMsg() {

		return msg;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String getData() {
		return data;
	}
}
