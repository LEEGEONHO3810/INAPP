package com.itfactory.solution.Mapper.POSTGRES_MES.SCR001;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface SCR001004Mapper {
	public List<HashMap<String, String>> ScheduleList(HashMap<String, String> map) throws Exception;
	public String FileSize(HashMap<String, String> map) throws Exception;
	public int Reg(HashMap<String, String> map) throws Exception;
	public void Update(HashMap<String, String> map) throws Exception;
	public void Delete(HashMap<String, String> map) throws Exception;
	public void DeleteToday(HashMap<String, String> map) throws Exception;
	public void SaveSubCheck(HashMap<String, String> map) throws Exception;
	public void Complete(HashMap<String, String> map) throws Exception;
	public void FileUpload(HashMap<String, String> fileMap) throws Exception;
	public String SelectMaxSeq() throws Exception;
	public String SubSelectMaxSeq() throws Exception;
	public List<HashMap<String, String>> FileDownLoad(String clm_check_schedule_id) throws Exception;
	public List<HashMap<String, String>> FileList(HashMap<String, String> map) throws Exception;
	public void FileDelete(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> UserList(HashMap<String, String> map) throws Exception;
	public void SubReg(HashMap<String, String> subMap) throws Exception;
	public void SubUpdate(HashMap<String, String> map) throws Exception;

	public void DeleteAll(HashMap<String, String> map) throws Exception;

	public void DeleteNext(HashMap<String, String> map) throws Exception;

	public int SubListCountN(String clmCheckScheduleId) throws Exception;

	public void UpdateAll(HashMap<String, String> map) throws Exception;

	public void UpdateNext(HashMap<String, String> map) throws Exception;

	public void UpdateToday(HashMap<String, String> map) throws Exception;
}
