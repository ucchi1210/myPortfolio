package com.example.dataTable.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dataTable.DataTable;
import com.example.dataTable.DataTableService;
import com.example.dataTable.form.DataListForm;

@Controller
public class DataTableController {
	@Autowired
	private DataTableService dataTableService;
	@Autowired
	ModelMapper modelMapper;
	/**データ一覧画面を表示*/
	@GetMapping("/datalist")
	public String getDataList(@ModelAttribute DataListForm form, Model model) {
		//データ一覧取得
		DataTable dataTable = modelMapper.map(form,DataTable.class);
		List<DataTable> dataTables = dataTableService.getData(dataTable);
		
		//Modelに登録
		model.addAttribute("dataTables",dataTables);
		return "datalist";
	}
	/**データ検索処理*/
	@PostMapping("/datalist")
	public String postDataList(@ModelAttribute DataListForm form ,Model model) {
		//formをDataTableクラスに変換
		DataTable dataTable = modelMapper.map(form,DataTable.class);
		//データ検索
		List<DataTable> dataTables = dataTableService.getData(dataTable);
		//modelに登録
		model.addAttribute("dataTables",dataTables);
		return "datalist";
	}

}
