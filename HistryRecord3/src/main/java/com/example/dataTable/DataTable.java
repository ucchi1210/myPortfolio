package com.example.dataTable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "data_table")
public class DataTable {
	@Id
	private	String uniqueId;
	private String userId;
	private String title;
	private String siteUrl;
	private String filePath;
}
