package net;

public interface ResponseListener {
	public static final int REQUEST_LISTCATEGORY = 1;
	public static final int REQUEST_POSTAD = 2;
	public static final int REQUEST_CLASSIFIEDSEARCH = 3;
	
	
	public void onResponse(Response response, int rid);
}
