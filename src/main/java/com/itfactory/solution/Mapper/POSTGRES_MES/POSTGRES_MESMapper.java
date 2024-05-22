package com.itfactory.solution.Mapper.POSTGRES_MES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface POSTGRES_MESMapper {
	public void RegHumidityAlarm(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ServerList(HashMap<String, String> map) throws Exception;
	public void Delete(HashMap<String, String> map) throws Exception;
	public void Save(HashMap<String, String> map) throws Exception;
	public void SaveAccount(HashMap<String, String> map) throws Exception;
	public void Update(HashMap<String, String> map) throws Exception;
	public void SeqUpdate(HashMap<String, String> map) throws Exception;
	public String ExistYn(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> FileList(HashMap<String, String> map) throws Exception;
	public void FileUpload(HashMap<String, String> map) throws Exception;
	public String FileExistYn(HashMap<String, String> map) throws Exception;
	public void FileDelete(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> LocationList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> AccountList(HashMap<String, String> map) throws Exception;
	public void AccountSave(HashMap<String, String> map) throws Exception;
	public void AccountUpdate(HashMap<String, String> map) throws Exception;
	public void CheckSeq(HashMap<String, String> map) throws Exception;
	public void UnCheckSeq(HashMap<String, String> map) throws Exception;
	public void AccountDelete(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> kakaoError(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> kakaoPhone(HashMap<String, String> map) throws Exception;
	public void AllDeletePhone(HashMap<String, String> map) throws Exception;
	public void PhoneReg(HashMap<String, String> map) throws Exception;
	public void PhoneUpdate(HashMap<String, String> map) throws Exception;
	public void ErrorSave(HashMap<String, String> map) throws Exception;
	public void ErrorUpdate(HashMap<String, String> map) throws Exception;
	public void PhoneDelete(HashMap<String, String> map) throws Exception;
	public void ErrorDelete(HashMap<String, String> map) throws Exception;
	public void UpdateAlarmYn(HashMap<String, String> map) throws Exception;
}