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
import com.itfactory.solution.Mapper.POSTGRES_MES.SYSTEM_CHECK_MESMapper;

@Service
@Transactional
public class SYSTEM_CHECK_MESService {

	@Autowired
	SYSTEM_CHECK_MESMapper SYSTEM_CHECK_MESMapper;

	public void ERROR_CONFIRM(HashMap<String, String> map) throws Exception {
		SYSTEM_CHECK_MESMapper.ERROR_CONFIRM(map);
	}
}
