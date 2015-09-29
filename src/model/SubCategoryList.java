package model;

import java.util.ArrayList;

public class SubCategoryList {
	
	public String catId;
	public String catTitle;
	
    public ArrayList<SubSubCategoryList> subsubcategory;
    
    @Override
	public String toString() {
		return catTitle.toString();
	}

}
