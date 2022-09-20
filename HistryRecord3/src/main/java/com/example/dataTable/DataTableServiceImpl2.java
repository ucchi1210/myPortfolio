package com.example.dataTable;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MUser;
import com.example.application.urlFile.UrlFileManager;
import com.example.repository.DataTableRepository;
import com.example.security.MuserDetails;

@Service
@Primary
public class DataTableServiceImpl2 implements DataTableService {
	@Autowired
	 UrlFileManager testUrlGetter;
	
	
	private MUser mUser = new MUser();

	@Autowired
	private DataTableRepository repository;
	

	/**ユーザー登録*/
	@Transactional
	@Override
	public void register(DataTable dataTable) {
		// ユーザーが一致しているURLの存在チェック
		MuserDetails mDetails = (MuserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mUser = mDetails.getMUser();
		String userName = mUser.getUserId();
		List<DataTable> data = repository.findByUserId(userName);
		boolean exists = false;
		for (int i = 0; i < data.size(); i++) {
			DataTable dt = data.get(i);
			String siteUrl = dt.getSiteUrl();
			exists = siteUrl.equals(dataTable.getSiteUrl());

			if (exists) {
				break;
			}
		}
		if (exists) {
			throw new DataAccessException("登録済みURLです。") {
			};
		}
		String uuid = UUID.randomUUID().toString();
		try {
		dataTable.setFilePath(testUrlGetter.getUrlSave(uuid,dataTable.getSiteUrl()));

		}catch (Exception e) {
			// TODO: handle exception
		} {
			
		}
		dataTable.setUserId(userName);
		dataTable.setUniqueId(uuid);
		
	
		repository.save(dataTable);
	}

	/**データリスト全件取得*/
	@Override
	public List<DataTable> getData(DataTable dataTable) {
		//ExampleMatcher matcher = ExampleMatcher
			//	.matching()//and条件	
				//.withStringMatcher(StringMatcher.CONTAINING)//Like句
				//.withIgnoreCase();//大文字・小文字の両方
		//return repository.findAll(Example.of(dataTable,matcher));
		MuserDetails mDetails = (MuserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mUser = mDetails.getMUser();
		String userName = mUser.getUserId();
		return repository.findByUserId(userName);
	}

	/**データ一件取得*/
	@Override
	public DataTable getDataTableOne(String uniqueId) {
		Optional<DataTable> option = repository.findById(uniqueId);
		DataTable data = option.orElse(null);
		return data;
	}
	/**データ一件更新*/
	@Transactional
	@Override
	public void updateDataOne(String uniqueId, String title, String siteUrl) {
		repository.updateDataOne(uniqueId, title, siteUrl);
		
	}
	/**データ削除*/
	
	@Override
	public void deleteDataOne(String uniqueId) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteById(uniqueId);
		
	}
	/**データ取得（ユーザー）*/
	@Override
	public List<DataTable> findByUserId(String userId) {
		
		return repository.findByUserId(userId);
		
	}
	

}
