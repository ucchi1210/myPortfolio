package com.example.dataTable.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dataTable.DataTable;
import com.example.dataTable.DataTableService;
import com.example.dataTable.form.DataDetailForm;

@Controller
public class DataTableDetailController {
	@Autowired
	private DataTableService dataTableService;
	@Autowired
	private ModelMapper modelMapper;

	/**データ詳細画面を表示*/
	@GetMapping("/detail/{uniqueId}")
	public String getData(DataDetailForm form, Model model, @PathVariable("uniqueId") String uniqueId) {
		DataTable dataTable = dataTableService.getDataTableOne(uniqueId);

		//Datatableをformに変換
		form = modelMapper.map(dataTable, DataDetailForm.class);

		//modelに登録
		model.addAttribute("dataDetailForm", form);
		
		return "detail";

	}
	/**データ更新処理*/
	@PostMapping(value="/detail",params="update")
	public String UpdateData(DataDetailForm form,Model model) {
		//データを更新
		dataTableService.updateDataOne(form.getUniqueId(), form.getTitle(), form.getSiteUrl());
		//データ一覧画面にリダイレクト
		return"redirect:/datalist";
	}
	/**データ削除処理*/
	@PostMapping(value="/detail",params="delete")
	public String deleteData(DataDetailForm form ,Model model) {
		//Data削除
		dataTableService.deleteDataOne(form.getUniqueId());
		return"redirect:/datalist";
	}
	
	
}
