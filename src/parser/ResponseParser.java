package parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utill.Utill;

import model.Categorylist;
import model.ClassifiedSearchData;
import model.ClassifiedSearchList;
import model.ClassifiedSearchListData;
import model.GetschemaList;
import model.SubCategoryList;
import model.SubSubCategoryList;

public class ResponseParser {

	public static ArrayList<GetschemaList> getSchemaList(String data) {

		ArrayList<GetschemaList> list = new ArrayList<GetschemaList>();
		ArrayList<Categorylist> catlist = new ArrayList<Categorylist>();
		
		

		try {
			JSONObject jsonObject = new JSONObject(data);
			GetschemaList ll = new GetschemaList();
			ll.status = jsonObject.getBoolean("status");
			ll.errorCode = jsonObject.getInt("errorCode");
			ll.errrorMessage = jsonObject.getString("errrorMessage");
			JSONArray ja = jsonObject.getJSONArray("data");

			for (int i = 0; i < ja.length(); i++) {
				JSONObject jsonObject1 = ja.getJSONObject(i);
				Categorylist cat = new Categorylist();
				cat.catId = jsonObject1.getString("catId");
				cat.catTitle = jsonObject1.getString("catTitle");
				JSONArray ja1 = jsonObject1.getJSONArray("subCategory");
				ArrayList<SubCategoryList> subcatlist = new ArrayList<SubCategoryList>();
				for (int j = 0; j < ja1.length(); j++) {
					
					JSONObject jsonObject2 = ja1.getJSONObject(j);
					SubCategoryList subcat = new SubCategoryList();
					subcat.catId = jsonObject2.getString("catId");
					subcat.catTitle = jsonObject2.getString("catTitle");
					JSONArray ja2 = jsonObject2.getJSONArray("subsubCategory");
					ArrayList<SubSubCategoryList> subsubcatlist = new ArrayList<SubSubCategoryList>();
					for (int k = 0; k < ja2.length(); k++) {
						JSONObject jsonObject3 = ja2.getJSONObject(k);
						SubSubCategoryList subSubCat = new SubSubCategoryList();
						subSubCat.catId = jsonObject3.getString("catId");
						subSubCat.catTitle = jsonObject3.getString("catTitle");
						subsubcatlist.add(subSubCat);
						subcat.subsubcategory = subsubcatlist;
						
					}
					
					subcatlist.add(subcat);
					cat.subcategory = subcatlist;

				}
				//cat.subcategory = subcatlist;
				catlist.add(cat);
				ll.category = catlist;
				list.add(ll);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utill.schemalist=list;
		return list;
	}

	public static ClassifiedSearchList getClassifiedSearch(String data) {
		ClassifiedSearchList search = new ClassifiedSearchList();
		
		
		try {
			JSONObject jsonObject = new JSONObject(data);
			search.status = jsonObject.getBoolean("status");
			search.errorCode =jsonObject.getInt("errorCode");
			search.errorMessage =jsonObject.getString("errorMessage");
			JSONObject jsonObject1 = jsonObject.getJSONObject("data");
			JSONObject jsonObject2 = jsonObject1.getJSONObject("pagination");
			ClassifiedSearchData paginationdata = new ClassifiedSearchData();
			
			paginationdata.total = jsonObject2.getInt("total");
			paginationdata.per_page = jsonObject2.getInt("per_page");
			paginationdata.current_page = jsonObject2.getInt("current_page");
			paginationdata.last_page = jsonObject2.getInt("last_page");
			paginationdata.from = jsonObject2.getInt("from");
			paginationdata.to = jsonObject2.getInt("to");
			
			JSONArray jsonArray = jsonObject1.getJSONArray("data");
			ArrayList<ClassifiedSearchListData> list = new ArrayList<ClassifiedSearchListData>();
			for (int i = 0; i < jsonArray.length(); i++) {
				
				JSONObject jsonObject3 = jsonArray.getJSONObject(i);
				ClassifiedSearchListData classified = new ClassifiedSearchListData();
				classified.id =  jsonObject3.getString("id");
				classified.name = jsonObject3.getString("name");
				classified.uid =  jsonObject3.getString("uid");
				classified.price = jsonObject3.getString("price");
				classified.state =  jsonObject3.getString("state");
				classified.details = jsonObject3.getString("details");
				classified.show_mobile =  jsonObject3.getString("show_mobile");
				classified.show_email = jsonObject3.getString("show_email");
				classified.cid =  jsonObject3.getString("cid");
				classified.L2 = jsonObject3.getString("L2");
				classified.L3 =  jsonObject3.getString("L3");
				classified.model = jsonObject3.getString("model");
				classified.year =  jsonObject3.getString("year");
				classified.bio = jsonObject3.getString("bio");
				classified.created_at =  jsonObject3.getString("created_at");
				classified.updated_at = jsonObject3.getString("updated_at");
				classified.shortlist =  jsonObject3.getString("shortlist");
				classified.CategoryName = jsonObject3.getString("CategoryName");
				classified.L2CategoryName =  jsonObject3.getString("L2CategoryName");
				classified.L3CategoryName = jsonObject3.getString("L3CategoryName");
				
				
				list.add(classified);
				
			}
			
			search.paginationdata =paginationdata;
			search.listdata= list;
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return search;
	}

}
