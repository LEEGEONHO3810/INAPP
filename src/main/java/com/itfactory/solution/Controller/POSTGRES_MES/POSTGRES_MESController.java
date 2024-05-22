package com.itfactory.solution.Controller.POSTGRES_MES;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.itfactory.solution.Service.POSTGRES_MES.POSTGRES_MESService;


import java.net.URLEncoder;

@RestController
@RequestMapping("/back/POSTGRES_MES")
public class POSTGRES_MESController {

	@Autowired
	POSTGRES_MESService POSTGRES_MESService;

	@GetMapping(path = "/LocationList")
	public List<HashMap<String, String>> LocationList(HttpServletRequest req) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		return POSTGRES_MESService.LocationList(map);
	}
	
	@GetMapping(path = "/ServerList")
	public List<HashMap<String, String>> ServerList(HttpServletRequest req) throws Exception {
		String schVal = (req.getParameter("schVal") == null? "": req.getParameter("schVal"));
		String schVal2 = (req.getParameter("schVal2") == null? "": req.getParameter("schVal2"));
		String schVal3 = (req.getParameter("schVal3") == null? "": req.getParameter("schVal3"));
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("search", schVal);
		map.put("search2", schVal2);
		map.put("search3", schVal3);
		return POSTGRES_MESService.ServerList(map);
	}
	
	@GetMapping(path = "/AccountList")
	public List<HashMap<String, String>> AccountList(HttpServletRequest req) throws Exception {
		String schVal = (req.getParameter("schVal") == null? "": req.getParameter("schVal"));
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("search",schVal);
		return POSTGRES_MESService.AccountList(map);
	}
	
	@GetMapping(path = "/kakaoError")
	public List<HashMap<String, String>> kakaoError(HttpServletRequest req) throws Exception {
		String schVal = (req.getParameter("schVal") == null? "": req.getParameter("schVal"));
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("search", schVal);
		
		return POSTGRES_MESService.kakaoError(map);
	}
	
	@PostMapping(path = "/ErrorDelete")
	public void ErrorDelete(HttpServletRequest req) throws Exception {
		
		HashMap<String, String> map = new HashMap<String, String>();
		String clm_code_id = (req.getParameter("clm_code_id") == null? "": req.getParameter("clm_code_id"));

		map.put("clm_code_id",clm_code_id);
		
		POSTGRES_MESService.ErrorDelete(map);
	}
	
	@GetMapping(path = "/kakaoPhone")
	public List<HashMap<String, String>> kakaoPhone(HttpServletRequest req) throws Exception {
		String schVal = (req.getParameter("schVal") == null? "": req.getParameter("schVal"));
		String code_id = (req.getParameter("clm_code_id") == null? "": req.getParameter("clm_code_id"));
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("search", schVal);
		map.put("code_id",code_id);
		return POSTGRES_MESService.kakaoPhone(map);
	}
	
	@PostMapping(path = "/PhoneDelete")
	public void PhoneDelete(HttpServletRequest req) throws Exception {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("clm_kep_id",req.getParameter("clm_kep_id"));
		map.put("clm_code_id",req.getParameter("clm_code_id"));
		POSTGRES_MESService.PhoneDelete(map);
	}
	
	@PostMapping(path = "/Delete")
	public void Delete(HttpServletRequest req) throws Exception {
		String clm_server_id = (req.getParameter("clm_server_id") == null? "": req.getParameter("clm_server_id"));
		String clm_user = (req.getParameter("clm_user") == null? "": req.getParameter("clm_user"));
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("clm_server_id", clm_server_id);
		map.put("clm_user", clm_user);
		POSTGRES_MESService.Delete(map);
	}

	@PostMapping(path = "/Save")
	public String Save(HttpServletRequest req) throws Exception {
		String clm_server_id = (req.getParameter("clm_server_id") == null? "": req.getParameter("clm_server_id"));
		String clm_user = (req.getParameter("clm_user") == null? "": req.getParameter("clm_user"));
		String clm_company_name = (req.getParameter("clm_company_name") == null? "0": req.getParameter("clm_company_name"));
		String clm_company_server_gubun= (req.getParameter("clm_company_server_gubun") == null? "": req.getParameter("clm_company_server_gubun"));
		String clm_inner_port= (req.getParameter("clm_inner_port") == null? "": req.getParameter("clm_inner_port"));
		String clm_login_id= (req.getParameter("clm_login_id") == null? "": req.getParameter("clm_login_id"));
		String clm_login_pw= (req.getParameter("clm_login_pw") == null? "": req.getParameter("clm_login_pw"));
		String clm_login_url= (req.getParameter("clm_login_url") == null? "": req.getParameter("clm_login_url"));
		String clm_outer_port= (req.getParameter("clm_outer_port") == null? "": req.getParameter("clm_outer_port"));
		String clm_program_gubun= (req.getParameter("clm_program_gubun") == null? "": req.getParameter("clm_program_gubun"));
		String clm_server_inner_location= (req.getParameter("clm_server_inner_location") == null? "": req.getParameter("clm_server_inner_location"));
		String clm_server_location= (req.getParameter("clm_server_location") == null? "": req.getParameter("clm_server_location"));
		String clm_comment= (req.getParameter("clm_comment") == null? "": req.getParameter("clm_comment"));
		String clm_use_yn= (req.getParameter("clm_use_yn") == null? "": req.getParameter("clm_use_yn"));
		String clm_order_seq = (req.getParameter("clm_order_seq") == null? "0": req.getParameter("clm_order_seq"));
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("clm_server_id", clm_server_id);
		map.put("clm_user", clm_user);
		map.put("clm_company_name", clm_company_name);
		map.put("clm_company_server_gubun", clm_company_server_gubun);
		map.put("clm_inner_port", clm_inner_port);
		map.put("clm_login_id", clm_login_id);
		map.put("clm_login_pw", clm_login_pw);
		map.put("clm_login_url", clm_login_url);
		map.put("clm_outer_port", clm_outer_port);
		map.put("clm_program_gubun", clm_program_gubun);
		map.put("clm_server_inner_location", clm_server_inner_location);
		map.put("clm_server_location", clm_server_location);
		map.put("clm_comment", clm_comment);
		map.put("clm_order_seq", clm_order_seq);
		map.put("clm_use_yn", clm_use_yn);
		String exist_yn = POSTGRES_MESService.ExistYn(map);
		if(clm_server_id.equals("")) { // 첫 등록
			if(exist_yn.equals("N")) { 
				POSTGRES_MESService.Save(map);				
			}
		}else { // 업데이트
			if(exist_yn.equals("N")) { 
				POSTGRES_MESService.Update(map);		
			}
		}

		return exist_yn;
	}
	
	@PostMapping(path = "/ErrorSave")
	public String ErrorSave(HttpServletRequest req) throws Exception {
				
		String clm_code_id = (req.getParameter("clm_error_code_id") == null? "": req.getParameter("clm_error_code_id"));
		String clm_code_name = (req.getParameter("clm_error_code_name") == null? "": req.getParameter("clm_error_code_name"));
		String clm_code_text = (req.getParameter("clm_error_code_text") == null? "": req.getParameter("clm_error_code_text"));
		String clm_code_db_info = (req.getParameter("clm_code_db_info") == null? "": req.getParameter("clm_code_db_info"));
		String clm_error_code_check = (req.getParameter("clm_error_code_check") == null? "": req.getParameter("clm_error_code_check"));
		String clm_error_gubun = (req.getParameter("clm_error_gubun") == null? "": req.getParameter("clm_error_gubun"));

		String clm_alarm_yn = (req.getParameter("clm_alarm_yn") == null? "": req.getParameter("clm_alarm_yn"));
		String clm_alarm_cycle = (req.getParameter("clm_alarm_cycle") == null? "": req.getParameter("clm_alarm_cycle"));
		String clm_alarm_daily_count = (req.getParameter("clm_alarm_daily_count") == null? "": req.getParameter("clm_alarm_daily_count"));
		
		String clm_code_query_check = "";
		String clm_code_web_check = "";
		String clm_code_program_check = "";
		
		if(clm_error_gubun.equals("Q")) {
			clm_code_query_check = clm_error_code_check;
		}else if(clm_error_gubun.equals("W")) {
			clm_code_web_check = clm_error_code_check;
		}else {
			clm_code_program_check = clm_error_code_check;
		}
	
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("clm_code_id", clm_code_id);
		map.put("clm_code_name", clm_code_name);
		map.put("clm_code_text", clm_code_text);
		map.put("clm_code_db_info", clm_code_db_info);
		map.put("clm_code_query_check", clm_code_query_check);
		map.put("clm_code_web_check", clm_code_web_check);
		map.put("clm_code_program_check", clm_code_program_check);
		map.put("clm_alarm_yn", clm_alarm_yn);
		map.put("clm_alarm_cycle", clm_alarm_cycle);
		map.put("clm_alarm_daily_count", clm_alarm_daily_count);

		if(clm_code_id.equals("")) { 
			
			POSTGRES_MESService.ErrorSave(map);
			
		}else if(!clm_code_id.equals("")){
			
			POSTGRES_MESService.ErrorUpdate(map);
			
		}
		
		return "N";				
		
	}
	@PostMapping(path = "/PhoneReg")
	public String PhoneReg(HttpServletRequest req) throws Exception {
		
		String clm_kep_id = (req.getParameter("clm_kep_id") == null? "": req.getParameter("clm_kep_id"));
		String clm_code_id = (req.getParameter("clm_code_id") == null? "": req.getParameter("clm_code_id"));
		String clm_user_name = (req.getParameter("clm_user_name") == null? "": req.getParameter("clm_user_name"));
		String clm_phone_number = (req.getParameter("clm_phone_number") == null? "": req.getParameter("clm_phone_number"));
		String clm_user = (req.getParameter("clm_user") == null? "admin": req.getParameter("clm_user"));
		System.out.println(clm_kep_id);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("clm_kep_id", clm_kep_id);
		map.put("clm_code_id", clm_code_id);
		map.put("clm_user_name", clm_user_name);
		map.put("clm_phone_number", clm_phone_number);
		map.put("clm_user", clm_user);
		
		if(clm_kep_id.equals("")) { 
			POSTGRES_MESService.PhoneReg(map);
		}else if(!clm_kep_id.equals("")){
			POSTGRES_MESService.PhoneUpdate(map);
		}

		return "N";				
	
	}
	
	@GetMapping(path = "/FileList")
	public List<HashMap<String, String>> FileList(HttpServletRequest req) throws Exception {
		String clm_server_id = (req.getParameter("clm_server_id") == null? "": req.getParameter("clm_server_id"));
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("clm_server_id", clm_server_id);
		return POSTGRES_MESService.FileList(map);
	}
	
	@PostMapping(path = "/FileUpload")
	public void FileUpload(@RequestParam(value="upload_file", required=false) List<MultipartFile> upload_file, @RequestParam("clm_server_id") String clm_server_id, @RequestParam("clm_company_gubun_name") String clm_company_gubun_name, @RequestParam("clm_file_comment") String[] clm_file_comment) throws Exception {
		String fileLocation = "C:\\INAPP_FILE\\" + clm_company_gubun_name;
		String FileOriginalName = "";
		String FileSize = "";
		File Folder = new File(fileLocation);
		
		if(!Folder.exists()) {
			if(Folder.mkdirs()) {
//				System.out.println("폴더 생성 성공");
			}
		}else {
//				System.out.println("폴더 이미 존재함");
		}
		
		int index = 0; 
		if(upload_file != null) {
			for (MultipartFile multipartFile : upload_file) {
	        	if(multipartFile.getOriginalFilename() != null && multipartFile.getOriginalFilename() != "") {
	    			HashMap<String, String> map = new HashMap<String, String>();
	    			FileOriginalName = multipartFile.getOriginalFilename();
	    			FileSize = Integer.toString(Integer.parseInt(Long.toString(multipartFile.getSize()))/1000);
	    			String file_comment = "";
	    			if(clm_file_comment.length > 0) {
	    				file_comment = (clm_file_comment[index] == null? "": clm_file_comment[index]);
	    			}
	    			map.put("clm_file_comment", file_comment);
	    			map.put("clm_file_location", fileLocation);
	    			map.put("clm_file_name", FileOriginalName);
	    			map.put("clm_file_size", FileSize);
	    			map.put("clm_server_id", clm_server_id);
	            	FileOutputStream fos = new FileOutputStream(fileLocation +"\\" + multipartFile.getOriginalFilename());
	                InputStream is = multipartFile.getInputStream();
	                int readCount = 0;
	                byte[] buffer = new byte[1024];
	                while((readCount = is.read(buffer)) != -1){
	                	fos.write(buffer,0,readCount);
	                }
	                is.close();
	                fos.close();
	                index++;
	                
	                String exist_yn = POSTGRES_MESService.FileExistYn(map);
	                if(exist_yn.equals("N")) {
	                	POSTGRES_MESService.FileUpload(map);
	                }else {
	                	POSTGRES_MESService.FileUpdate(map);
	                }
	                
	                
	        	}
			}
		}		
	}
	
	@PostMapping(path = "/ContentSave")
	public String ContentSave(HttpServletRequest req) throws Exception {
		String clm_note_id = (req.getParameter("clm_note_id") == null? "": req.getParameter("clm_note_id"));
		String clm_title = (req.getParameter("clm_title") == null? "": req.getParameter("clm_title"));
		String clm_content = (req.getParameter("clm_content") == null? "": req.getParameter("clm_content"));
		String clm_content_seq = (req.getParameter("clm_content_seq") == null? "": req.getParameter("clm_content_seq"));
		String clm_user = (req.getParameter("clm_user") == null? "": req.getParameter("clm_user"));
		
		HashMap<String, String> map = new HashMap<String, String>();
		String exist_yn = "";

		map.put("clm_note_id", clm_note_id);
		map.put("clm_title", clm_title);
		map.put("clm_content", clm_content);
		map.put("clm_content_seq", clm_content_seq);
		map.put("clm_user", clm_user);
		System.out.println(clm_note_id);
		if(clm_note_id.equals("")) { // 첫 등록
			
			POSTGRES_MESService.AccountSave(map);				
			exist_yn = "N";
		}else { // 업데이트
			POSTGRES_MESService.AccountUpdate(map);	
			exist_yn = "N";
		}
		
		return exist_yn;
	}
	
	
	@PostMapping(path = "/DeleteContent")
	public void DeleteContent(HttpServletRequest req) throws Exception {
		String clm_note_id = (req.getParameter("clm_note_id") == null? "": req.getParameter("clm_note_id"));
		String clm_content_seq = (req.getParameter("clm_content_seq") == null? "": req.getParameter("clm_content_seq"));
		String clm_user = (req.getParameter("clm_user") == null? "": req.getParameter("clm_user"));
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("clm_note_id", clm_note_id);
		map.put("clm_content_seq", clm_content_seq);
		map.put("clm_user", clm_user);
		
		POSTGRES_MESService.AccountDelete(map);
	}
	
	@PostMapping(path = "/OrderSeq")
	public String OrderSeq(HttpServletRequest req) throws Exception {
		String clm_note_id = (req.getParameter("clm_note_id") == null? "": req.getParameter("clm_note_id"));
		String clm_content_seq = (req.getParameter("clm_content_seq") == null? "": req.getParameter("clm_content_seq"));
		String Result = "";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("clm_note_id", clm_note_id);
		map.put("clm_content_seq", clm_content_seq);
		System.out.println(map);
		// 체크 여부에 따라
		if(clm_content_seq.equals("0")) {
			POSTGRES_MESService.CheckSeq(map);
			Result ="Z";
		}else if(clm_content_seq.equals("1")) {
			POSTGRES_MESService.UnCheckSeq(map);
			Result ="O";
		}
		
		return Result;
	}
	
	

	@PostMapping(path = "/FileDelete")
	public void FileDelete(HttpServletRequest req) throws Exception {
		String clm_server_id = (req.getParameter("clm_server_id") == null? "": req.getParameter("clm_server_id"));
		String clm_file_name = (req.getParameter("clm_file_name") == null? "": req.getParameter("clm_file_name"));
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("clm_server_id", clm_server_id);
		map.put("clm_file_name", clm_file_name);
		POSTGRES_MESService.FileDelete(map);
	}
	
	@GetMapping(path = "/FileDownLoad")
	public void FileDownLoad(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String filename = req.getParameter("clm_file_name");
		String clm_file_location = req.getParameter("clm_file_location");
		
		FileInputStream fis = null;
		BufferedOutputStream bos = null;

		String NewFileName = clm_file_location + "//" +filename ;
		File File = new File(NewFileName);

		resp.setContentType("application/pdf");
		resp.addHeader("Content-Disposition", "attachment; filename="+File.getName()+".pdf");

		fis = new FileInputStream(File);
		int size = fis.available(); 
		byte[] buf = new byte[size]; 
		int readCount = fis.read(buf);

		resp.flushBuffer();
		bos = new BufferedOutputStream(resp.getOutputStream());
		bos.write(buf, 0, readCount);
		bos.flush();
		  
		if (fis != null) fis.close();
		if (bos != null) bos.close();
	}
	
	// UpdateAlarmYn
	@PostMapping(path = "/UpdateAlarmYn")
	public void UpdateAlarmYn(HttpServletRequest req) throws Exception {
		String clm_code_id = (req.getParameter("clm_code_id") == null? "": req.getParameter("clm_code_id"));
		String clm_alarm_yn = (req.getParameter("clm_alarm_yn") == null? "": req.getParameter("clm_alarm_yn"));
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("clm_code_id", clm_code_id);
		map.put("clm_alarm_yn", clm_alarm_yn);
		POSTGRES_MESService.UpdateAlarmYn(map);
	}

}
