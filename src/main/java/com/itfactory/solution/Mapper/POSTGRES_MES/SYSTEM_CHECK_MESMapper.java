package com.itfactory.solution.Mapper.POSTGRES_MES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SYSTEM_CHECK_MESMapper {
	public void ERROR_CONFIRM(HashMap<String, String> map) throws Exception;
}
