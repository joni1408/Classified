package model;

import java.util.ArrayList;

public class Categorylist {
	
	
	
	public String catId;
	public String catTitle;
	
    public ArrayList<SubCategoryList> subcategory;
    
    @Override
	public String toString() {
		return catTitle.toString();
	}


}
