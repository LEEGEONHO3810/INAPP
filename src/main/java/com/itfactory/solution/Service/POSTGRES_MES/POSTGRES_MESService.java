package com.itfactory.solution.Service.POSTGRES_MES;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itfactory.solution.Mapper.POSTGRES_MES.POSTGRES_MESMapper;

@Service
@Transactional
public class POSTGRES_MESService {

	@Autowired
	POSTGRES_MESMapper POSTGRES_MESMapper;

	public List<HashMap<String, String>> ServerList(HashMap<String, String> map) throws Exception {
		return POSTGRES_MESMapper.ServerList(map);
	}

	public void Delete(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.Delete(map);
	}

	public void Save(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.SeqUpdate(map);
		POSTGRES_MESMapper.Save(map);
	}
	
	public void SaveAccount(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.Save(map);
	}

	public void Update(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.Update(map);
	}

	public String ExistYn(HashMap<String, String> map) throws Exception {
		return POSTGRES_MESMapper.ExistYn(map);
	}

	public List<HashMap<String, String>> FileList(HashMap<String, String> map) throws Exception {
		return POSTGRES_MESMapper.FileList(map);
	}

	public void FileUpload(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.FileUpload(map);
	}

	public String FileExistYn(HashMap<String, String> map) throws Exception {
		return POSTGRES_MESMapper.FileExistYn(map);
	}

	public void FileUpdate(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.FileDelete(map);
		POSTGRES_MESMapper.FileUpload(map);
		
	}

	public void FileDelete(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.FileDelete(map);
	}

	public List<HashMap<String, String>> LocationList(HashMap<String, String> map) throws Exception {
		return POSTGRES_MESMapper.LocationList(map);
	}

	public List<HashMap<String, String>> AccountList(HashMap<String, String> map) throws Exception {
		return POSTGRES_MESMapper.AccountList(map);
	}

	public void AccountSave(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.AccountSave(map);
		
	}

	public void AccountUpdate(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.AccountUpdate(map);
	}

	public void CheckSeq(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.CheckSeq(map);
	}

	public void UnCheckSeq(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.UnCheckSeq(map);
	}

	public void AccountDelete(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.AccountDelete(map);
	}

	public List<HashMap<String, String>> kakaoError(HashMap<String, String> map) throws Exception {
		
		return POSTGRES_MESMapper.kakaoError(map);
	}

	public List<HashMap<String, String>> kakaoPhone(HashMap<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return POSTGRES_MESMapper.kakaoPhone(map);
	}

	public void PhoneReg(HashMap<String, String> map)  throws Exception {
		POSTGRES_MESMapper.PhoneReg(map);
		
	}

	public void PhoneUpdate(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.PhoneUpdate(map);
	}

	public void ErrorSave(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.ErrorSave(map);
	}

	public void ErrorUpdate(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.ErrorUpdate(map);
	}

	public void PhoneDelete(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.PhoneDelete(map);
	}

	public void ErrorDelete(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.ErrorDelete(map);
		POSTGRES_MESMapper.AllDeletePhone(map);
	}

	public void UpdateAlarmYn(HashMap<String, String> map) throws Exception {
		POSTGRES_MESMapper.UpdateAlarmYn(map);
	}
	
}
