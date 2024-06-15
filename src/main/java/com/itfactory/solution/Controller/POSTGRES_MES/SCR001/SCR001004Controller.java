package com.itfactory.solution.Controller.POSTGRES_MES.SCR001;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfactory.solution.Dto.CalendarDto;
import com.itfactory.solution.Service.POSTGRES_MES.SCR001.SCR001004Service;


import ch.qos.logback.core.subst.Token.Type;


@RestController
@RequestMapping("/back/SCR001")
public class SCR001004Controller {
	@Autowired
	SCR001004Service SCR001004Service;

	@GetMapping(path = "/ScheduleList")
	public List<HashMap<String, String>> ScheduleList(HttpServletRequest req) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String targetDate = (req.getParameter("targetDate") == null? "": req.getParameter("targetDate"));

		map.put("search", req.getParameter("search"));
		map.put("search2", req.getParameter("search2"));
		map.put("sortBy", req.getParameter("sortBy"));
		map.put("sortDesc", req.getParameter("sortDesc"));
		map.put("page", req.getParameter("page"));
		map.put("PerPage", req.getParameter("PerPage"));
		map.put("targetDate", targetDate);

		return SCR001004Service.ScheduleList(map);

	}
	@GetMapping(path = "/UserList")
	public List<HashMap<String, String>> UserList(HttpServletRequest req) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("search", req.getParameter("search"));
		map.put("clm_user", req.getParameter("clm_user"));
		return SCR001004Service.UserList(map);
	}

	
	
	@GetMapping(path = "/FileList")
	public List<HashMap<String, String>> FileList(HttpServletRequest req) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();

		String clm_check_schedule_id = (req.getParameter("clm_check_schedule_id") == null? "": req.getParameter("clm_check_schedule_id"));
		String clm_check_sub_schedule_id = (req.getParameter("clm_check_sub_schedule_id") == null? "": req.getParameter("clm_check_sub_schedule_id"));
		map.put("clm_check_schedule_id", clm_check_schedule_id);
		map.put("clm_check_sub_schedule_id", clm_check_sub_schedule_id);
		return SCR001004Service.FileList(map);
	}
	
	@PostMapping(path = "/Reg")
	public void Reg(
	        @RequestPart(name = "files", required = false) List<MultipartFile> files,
	        @RequestParam("data") String data,
	        @RequestParam("comment") String comment,
	        @RequestParam("cycle") String cycle) throws Exception {
	
	    
		if(comment == null) {
			comment = "";
		}
		
	    // 파일이 선택되지 않은 경우 빈 리스트
	    if (files == null) {
	        files = new ArrayList<>();
	    }
	    
	    // 흠..............  설정.......... cycle에 따라 주기 설정....흠
	    // if 2024-05-02 ~ 2024-05-10
	    // 	없음	= none => 그대로 =>  2024-05-02 ~ 2024-05-10
		//	매일 = day => 3년 365 * 3 =>  2027-05-02 ~ 2027-05-10
		//	1주 = 1week	=> 7일 =>  2024-05-09 ~ 2024-05-17
		//	2주 = 2week	=> 14일 =>  2024-05-16 ~ 2024-05-24
		//	한달 = month	=> 이번에하고 다음달까지 =>  2027-06-02 ~ 2027-06-10
		//	월말 = endMonth	=>   2024-05-34
		//	1년 = year =>  2025-05-02 ~ 2025-05-10
	    
	    // 주의 날짜-> 계산 잘해야할듯?? 자바에 날짜 계산해주는거 쓰면될듯 싶음
	    HashMap<String, String> map = new HashMap<String, String>();

	    ObjectMapper objectMapper = new ObjectMapper();
	    // 받아온 Json Data로 변환
	    CalendarDto CDto = objectMapper.readValue(data, CalendarDto.class);

    	map.put("clm_user", CDto.getClmUser());
    	map.put("clm_schedule_cycle", cycle);
    	map.put("clm_all_day_yn", CDto.getClmAllDayYn());
    	map.put("clm_start_date", CDto.getClmStartDate());
    	map.put("clm_start_time", CDto.getClmStartTime());
    	map.put("clm_end_date", CDto.getClmEndDate());
	    map.put("clm_end_time", CDto.getClmEndTime());
	    map.put("clm_comment", CDto.getClmComment());
	    map.put("clm_color", CDto.getColor());
	    map.put("clm_user", CDto.getClmUser());
    	
	    // 헤더 넣고
	    SCR001004Service.Reg(map, files, comment,cycle);

	   
	}

	@PostMapping(path = "/Update")
	public void Update(
	        @RequestPart(name = "files", required = false) List<MultipartFile> files,
	        @RequestParam("data") String data,
	        @RequestParam("comment") String comment,
			HttpServletRequest req) throws Exception {
		try {

			String option = (req.getParameter("option") == null ? "" : req.getParameter("option"));

			if (comment == null) {
				comment = "";
			}

			// 파일이 선택되지 않은 경우 빈 리스트
			if (files == null) {
				files = new ArrayList<>();
			}

			HashMap<String, String> map = new HashMap<String, String>();

			ObjectMapper objectMapper = new ObjectMapper();

			// 받아온 Json Data로 변환
			CalendarDto CDto = objectMapper.readValue(data, CalendarDto.class);

			map.put("clm_check_schedule_id", CDto.getClmCheckScheduleId());
			map.put("clm_check_sub_schedule_id", CDto.getClmCheckSubScheduleId());
			map.put("clm_check_schedule_file_id", CDto.getClmCheckScheduleFileId());
			map.put("clm_schedule_cycle", CDto.getClmScheduleCycle());
			map.put("clm_all_day_yn", CDto.getClmAllDayYn());
			map.put("clm_start_date", CDto.getClmStartDate());
			map.put("clm_start_time", CDto.getClmStartTime());
			map.put("clm_end_date", CDto.getClmEndDate());
			map.put("clm_end_time", CDto.getClmEndTime());
			map.put("clm_comment", CDto.getClmComment());
			map.put("clm_color", CDto.getColor());
			map.put("clm_user", CDto.getClmUser());

			SCR001004Service.Update(map, files, comment, option);
		}catch (Exception e) {
			// 예외 로그를 남기고, 필요시 클라이언트에 에러 응답을 보냅니다.
			e.printStackTrace();
			throw new Exception("Update 과정에서 오류가 발생했습니다.");
		}
	}
	
	@GetMapping(path = "/FileDownLoad")
	public void FileDownLoad(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	    
	    String filename = req.getParameter("clm_file_name");
	    String clm_file_location = req.getParameter("clm_file_location");
	    
	    File file = new File(clm_file_location);
	    System.out.println("파일 경로: " + file.getAbsolutePath());

	    if (!file.exists()) {
	        // 파일이 존재하지 않으면 에러 처리
	    	System.out.println("에러입니당");
	        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        return;
	    }

	    resp.setContentType("application/pdf");
	    resp.addHeader("Content-Disposition", "attachment; filename=" + filename);

	    try (FileInputStream fis = new FileInputStream(file);
	         BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream())) {

	        byte[] buf = new byte[1024];
	        int bytesRead;
	        while ((bytesRead = fis.read(buf)) != -1) {
	            bos.write(buf, 0, bytesRead);
	        }

	        bos.flush();
	        resp.flushBuffer();
	    } catch (IOException e) {
	        // IOException 처리
	        e.printStackTrace();
	        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    }
	}

	@PostMapping(path = "/Delete")
	public void Delete(HttpServletRequest req) throws Exception {
		String clm_check_schedule_id = (req.getParameter("clm_check_schedule_id") == null? "": req.getParameter("clm_check_schedule_id"));
		String clm_check_sub_schedule_id = (req.getParameter("clm_check_sub_schedule_id") == null? "": req.getParameter("clm_check_sub_schedule_id"));
		String option = (req.getParameter("option") == null? "": req.getParameter("option"));
		String clm_start_date = (req.getParameter("clm_start_date") == null? "": req.getParameter("clm_start_date"));

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("option",  option);
		map.put("clm_check_schedule_id",  clm_check_schedule_id);
		map.put("clm_check_sub_schedule_id",  clm_check_sub_schedule_id);
		map.put("clm_start_date", clm_start_date);
		map.put("clm_user", req.getParameter("clm_user"));

		SCR001004Service.Delete(map);
	}
	
	@PostMapping(path = "/FileDelete")
	public void FileDelete(HttpServletRequest req) throws Exception {
		String clm_check_schedule_file_id = (req.getParameter("clm_check_schedule_file_id") == null? "": req.getParameter("clm_check_schedule_file_id"));
		String clm_check_schedule_id = (req.getParameter("clm_check_schedule_id") == null? "": req.getParameter("clm_check_schedule_id"));
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println(clm_check_schedule_file_id);
		System.out.println(clm_check_schedule_id);
		map.put("clm_check_schedule_file_id", clm_check_schedule_file_id);
		map.put("clm_check_schedule_id", clm_check_schedule_id);
		SCR001004Service.FileDelete(map);
	}
	
	
	
	@PostMapping(path = "/Complete")
	public void Complete(HttpServletRequest req) throws Exception {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("clm_check_schedule_id",  req.getParameter("clm_check_schedule_id"));
		map.put("clm_check_after_management",  req.getParameter("clm_check_after_management"));
		map.put("clm_check_complete_value", req.getParameter("clm_check_complete_value"));
		map.put("clm_machine_id", req.getParameter("clm_machine_id"));
		map.put("CheckData", req.getParameter("CheckData"));
		map.put("clm_user", req.getParameter("clm_user"));
		
		SCR001004Service.Complete(map);
	}
	
	
}
