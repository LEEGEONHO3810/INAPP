package com.itfactory.solution.Controller.POSTGRES_MES;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.itfactory.solution.Service.POSTGRES_MES.SYSTEM_CHECK_MESService;

@RestController
@RequestMapping("/back/SYSTEM_CHECK")
public class SYSTEM_CHECK_MESController {

	@Autowired
	SYSTEM_CHECK_MESService SYSTEM_CHECK_MESService;

	@PostMapping(path = "/ERROR_CONFIRM")
	public void ERROR_CONFIRM(HttpServletRequest req) throws Exception {
		String code_id = (req.getParameter("code_id") == null? "": req.getParameter("code_id"));
		String error_yn = (req.getParameter("error_yn") == null? "": req.getParameter("error_yn"));
		
		
		System.out.println(" @@@@@@@@@@ ERROR_CONFIRM     code_id ["+code_id+"]     error_yn   ["+error_yn+"]  ");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("code_id", code_id);
		map.put("error_yn", error_yn);
		
		SYSTEM_CHECK_MESService.ERROR_CONFIRM(map);
	}
}
