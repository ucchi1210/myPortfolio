package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.example.application.urlFile.UrlFileManager;
import com.example.dataTable.DataTable;

class UrlFileManagerTest {
	DataTable dataTable;

	@Test
	void test() {
		dataTable.setFilePath("C://testfile/");
		dataTable.setSiteUrl("");
		try {
			assertEquals(true, new UrlFileManager().isSameStream(dataTable),"");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
