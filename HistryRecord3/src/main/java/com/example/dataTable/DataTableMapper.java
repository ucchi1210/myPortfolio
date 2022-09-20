package com.example.dataTable;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataTableMapper {
	/**データ登録*/
	public int insertOne(DataTable dataTable);

	/**データ全件取得*/
	public List<DataTable> findMany(DataTable dataTable);

	/**データ一件取得*/
	public DataTable findOneDataTable(String uniqueId);

	/**データテーブル更新（1件）*/
	public void updateOne(@Param("uniqueId") String uniqueId,
			@Param("title") String title,
			@Param("siteUrl") String siteUrl,
			@Param("notificationTime") String notificationTime);

	/**データ削除（1件）*/
	public int deleteOne(@Param("uniqueId") String uniqueId);
}
