package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.dataTable.DataTable;

public interface DataTableRepository extends JpaRepository<DataTable, String> {

	public List<DataTable> findByUserId(String userId);
	public boolean existsBySiteUrl(String siteUrl);
	
	/**データ一件更新*/
	@Modifying
	@Query("update DataTable"
			+" set"
			+" title = :title ,"
			+" siteUrl = :siteUrl ,"
			+" notificationTime = :notificationTime"
			+" where"
			+" uniqueId = :uniqueId")
	public Integer updateDataOne(@Param("uniqueId") String uniqueId,
			@Param("title") String title,
			@Param("siteUrl") String siteUrl);
}
