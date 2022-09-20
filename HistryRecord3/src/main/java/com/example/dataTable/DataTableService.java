package com.example.dataTable;

import java.util.List;

public interface DataTableService {
	/**データ登録*/
	public void register(DataTable dataTable);
	
	/**データ取得*/
	public List<DataTable> getData(DataTable dataTable);
	
	/**データ取得（一件）*/
	public DataTable getDataTableOne(String uniqueId); 
	
	/**データ更新（一件）*/
	public void updateDataOne(String uniqueId, String title,String siteUrl);
	
	/**データ削除（1件）*/
	public void  deleteDataOne(String uniqueId);
	/**データ取得（ユーザー）*/
	public List<DataTable> findByUserId(String userId);

}
