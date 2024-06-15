package com.itfactory.solution.Service.POSTGRES_MES.SCR001;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.itfactory.solution.Mapper.POSTGRES_MES.SCR001.SCR001004Mapper;

@Service
@Transactional
public class SCR001004Service {

	@Autowired
	SCR001004Mapper SCR001004Mapper;
	
	public List<HashMap<String, String>> ScheduleList(HashMap<String, String> map) throws Exception{
		return SCR001004Mapper.ScheduleList(map);
	}

	public List<HashMap<String, String>> FileList(HashMap<String, String> map) throws Exception{
		
		return SCR001004Mapper.FileList(map);
	}

	public void Reg(HashMap<String, String> map, List<MultipartFile> files , String comment , String cycle) throws Exception {
	    
	    String MaxSeq = SCR001004Mapper.SelectMaxSeq();
        String SubMaxSeq = "";

	    map.put("clm_check_schedule_id", MaxSeq);
	    
	    SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
	   
	    
	    SCR001004Mapper.Reg(map);

	    if (cycle.equals("none")) {
	        SubMaxSeq = SCR001004Mapper.SubSelectMaxSeq();
	        map.put("clm_check_sub_schedule_id", SubMaxSeq);
	        SCR001004Mapper.SubReg(map);
	    } else if (cycle.equals("day")) {
	        handleDayCycle(map, fDate, cal);
	    } else if (cycle.equals("endMonth")) {
	        handleEndMonthCycle(map, fDate, cal);
	    } else if (cycle.equals("weekday")) {
	    	handleWeekdayCycle(map, fDate, cal);
		} else if (cycle.equals("weekend")) {
			handleWeekendCycle(map, fDate, cal);
		}
	    
	    if(files != null && !files.isEmpty()) {
		   FileUpload(map,files,comment);
		}
	    
	    
	}
	
	// 매일
	private void handleDayCycle(HashMap<String, String> map, SimpleDateFormat fDate, Calendar cal) throws Exception {
	    String SDate = map.get("clm_start_date");
	    String EDate = map.get("clm_end_date");
	
	    Date StartDate = fDate.parse(SDate);
	    Date EndDate = fDate.parse(EDate);
	
	    cal.setTime(StartDate);
	    int startYear = cal.get(Calendar.YEAR);
	    int totalDays = calculateTotalDays(startYear, 3); // 3년으로 계산
	
	    for (int z = 1; z <= totalDays; z++) {
	        createSubSchedule(map, fDate, cal, StartDate, EndDate, z);
	    }
	}
	
	// 월말 
	private void handleEndMonthCycle(HashMap<String, String> map, SimpleDateFormat fDate, Calendar cal) throws Exception {
	    String SDate = map.get("clm_start_date");
	    String EDate = map.get("clm_end_date");
	
	    Date StartDate = fDate.parse(SDate);
	    Date EndDate = fDate.parse(EDate);
	
	    cal.setTime(StartDate);
	
	    while (!cal.getTime().after(EndDate)) {
	        String SubMaxSeq = SCR001004Mapper.SubSelectMaxSeq();
	        map.put("clm_check_sub_schedule_id", SubMaxSeq);
	        
	        // 월말로 변경
	        int lastDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        cal.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
	        Date newFullDate = cal.getTime();
	
	        cal.add(Calendar.MONTH, 1);
	        map.put("clm_start_date", fDate.format(newFullDate));
	        map.put("clm_end_date", fDate.format(newFullDate));
	        SCR001004Mapper.SubReg(map);
	        
	    }
	}
	
	// 주중 (평일)
	// 2 = 월
	// 3 = 화
	// 4 = 수
	// 5 = 목
	// 6 = 금
	private void handleWeekdayCycle(HashMap<String, String> map, SimpleDateFormat fDate, Calendar cal) throws Exception {
		String SDate = map.get("clm_start_date");
		String EDate = map.get("clm_end_date");
		
		Date StartDate = fDate.parse(SDate);
		Date EndDate = fDate.parse(EDate);
		
		cal.setTime(StartDate);
		
		while (!cal.getTime().after(EndDate)) {
			
			String SubMaxSeq = SCR001004Mapper.SubSelectMaxSeq();
			map.put("clm_check_sub_schedule_id", SubMaxSeq);
			
			int dayNum = cal.get(Calendar.DAY_OF_WEEK);
			
			if(dayNum != 1 && dayNum !=7) {
				
				Date newFullDate = cal.getTime();
				
		        map.put("clm_start_date", fDate.format(newFullDate));
		        map.put("clm_end_date", fDate.format(newFullDate));
		        SCR001004Mapper.SubReg(map);

			}
			
			cal.add(Calendar.DAY_OF_WEEK, 1);

		}
	}
	
	// 주말
	// 1 = 토
	// 7 = 일
	private void handleWeekendCycle(HashMap<String, String> map, SimpleDateFormat fDate, Calendar cal) throws Exception {
		String SDate = map.get("clm_start_date");
		String EDate = map.get("clm_end_date");
		
		Date StartDate = fDate.parse(SDate);
		Date EndDate = fDate.parse(EDate);
		
		cal.setTime(StartDate);
		
		while (!cal.getTime().after(EndDate)) {
			
			String SubMaxSeq = SCR001004Mapper.SubSelectMaxSeq();
			map.put("clm_check_sub_schedule_id", SubMaxSeq);
			
			int dayNum = cal.get(Calendar.DAY_OF_WEEK);
			
			if(dayNum == 1 || dayNum == 7) {

				Date newFullDate = cal.getTime();
				
		        map.put("clm_start_date", fDate.format(newFullDate));
		        map.put("clm_end_date", fDate.format(newFullDate));
		        SCR001004Mapper.SubReg(map);

			}
			
			cal.add(Calendar.DAY_OF_WEEK, 1);

		}
	}
		
		
	
	private int calculateTotalDays(int startYear, int years) {
	    int totalDays = 0;
	    // 윤년 계산
	    for (int z = 1; z <= years; z++) {
	        if ((startYear % 4 == 0 && startYear % 100 != 0) || (startYear % 400 == 0)) {
	            totalDays += 366;
	        } else {
	            totalDays += 365;
	        }
	        startYear++;
	    }
	    return totalDays;
	}
	
	private void createSubSchedule(HashMap<String, String> map, SimpleDateFormat fDate, Calendar cal, Date StartDate, Date EndDate, int z) throws Exception {
	    
		String SubMaxSeq = SCR001004Mapper.SubSelectMaxSeq();
	    map.put("clm_check_sub_schedule_id", SubMaxSeq);
	
	    cal.setTime(StartDate);
	    cal.add(Calendar.DATE, z);
	    Date newStartDate = cal.getTime();
	
	    cal.setTime(EndDate);
	    cal.add(Calendar.DATE, z);
	    Date newEndDate = cal.getTime();
	
	    map.put("clm_start_date", fDate.format(newStartDate));
	    map.put("clm_end_date", fDate.format(newEndDate));
	    SCR001004Mapper.SubReg(map);
	    
	}
	
	public void SubReg(HashMap<String, String> subMap) throws Exception {
		
	    String maxSeq = SCR001004Mapper.SubSelectMaxSeq();
	    
	    subMap.put("clm_check_sub_schedule_id", maxSeq);
	    
	    
	    SCR001004Mapper.SubReg(subMap);
		
	}
	
	public void Update(HashMap<String, String> map, List<MultipartFile> files , String comment , String option) throws Exception {
		System.out.println(map);
		System.out.println(option);
		if(option.equals("optionAll")){
			SCR001004Mapper.UpdateAll(map);
		}else if(option.equals("optionToday")){
			SCR001004Mapper.UpdateToday(map);
		}else if(option.equals("optionNext")){
			SCR001004Mapper.UpdateNext(map);
		}
		if(files != null && !files.isEmpty()) {
	    	FileUpload(map,files,comment);
	    }
	}
	
	private void FileUpload(HashMap<String, String> map, List<MultipartFile> files , String comment) throws Exception{
		
		HashMap<String, String> fileMap = new HashMap<String, String>();
		int i = 0;
        String[] comments = comment.split(",");
      

        for(MultipartFile file : files) {
       
        	String filename = file.getOriginalFilename();
       
        	long fileSize = file.getSize();
        
            String contentType = file.getContentType();
            String storage = "C:\\Calendar\\"+0+"\\";
            String fileLocation = storage;
            String fileComment = (i < comments.length) ? comments[i] : "";		            

            File Folder = new File(storage);

            // 폴더 없으면 폴더 생성
            if(!Folder.exists()) {
                Folder.mkdirs();
            }
            
            File targetFile = new File(fileLocation, filename);
        
            if(!targetFile.exists()) {
                if(targetFile.length() != fileSize ) {
                    targetFile.delete();
                    file.transferTo(new File(storage, filename));
                    
                } else {
                    
                    // 새 파일 저장
                    file.transferTo(targetFile);
                }
            }
        
            fileMap.put("clm_file_name",filename);
            fileMap.put("clm_file_size",Long.toString(fileSize));
            fileMap.put("clm_file_location",targetFile.toString());
            fileMap.put("clm_check_schedule_id", map.get("clm_check_schedule_id"));
            fileMap.put("clm_check_sub_schedule_id",map.get("clm_check_sub_schedule_id"));
            fileMap.put("fileComment",fileComment);
            
            SCR001004Mapper.FileUpload(fileMap);
            i++;
        }
	}


	// 이전 일정 --> 키값 보다 작은걸로 처리? ex)headid)0000001  sub_id) S0000003이 값보다 작거나 같은 애들
	// 해당 일정 --> 해당 키값으로 조건 처리 ex)headid)0000001  sub_id) S0000003
	// 이후 일정 --> 해당 키값보다 큰애들? ex)headid)0000001  sub_id) S0000003이 값보다 큰크거나 같은
	public void Delete(HashMap<String, String> map) throws Exception {
		String option = map.get("option");
		String clm_check_schedule_id = map.get("clm_check_schedule_id");

		//일정 별 처리 방법
		if(option.equals("optionAll")){
			SCR001004Mapper.DeleteAll(map);
		}else if(option.equals("optionToday")){
			SCR001004Mapper.DeleteToday(map);
		}else if(option.equals("optionNext")){
			SCR001004Mapper.DeleteNext(map);
		}

		// 해당 일정의 모든 일정이 삭제되면 head도 삭제
		int countY = SCR001004Mapper.SubListCountN(clm_check_schedule_id);

		if(countY == 0){
			SCR001004Mapper.Delete(map);
		}
	}

	public void Complete(HashMap<String, String> map) throws Exception {
		
		SCR001004Mapper.Complete(map);

		String[] arraycheckData = map.get("CheckData").split("▒");

		if(arraycheckData.length > 0 && ! map.get("CheckData").equals("")) {
			for(int i=0; i<arraycheckData.length; i++) {
				String[] tmpCheck = arraycheckData[i].split("▦");
				HashMap<String, String> tmpMap = new HashMap<String, String>();
				tmpMap.put("clm_check_schedule_id",map.get("clm_check_schedule_id"));
				tmpMap.put("clm_machine_id", map.get("clm_machine_id"));
				tmpMap.put("clm_machine_seq",Integer.toString(i+1));
				tmpMap.put("clm_check_result_yn", tmpCheck[0].toString());
				tmpMap.put("clm_user", map.get("clm_user"));
				SCR001004Mapper.SaveSubCheck(tmpMap);
			}
		}
	}

	public void FileDelete(HashMap<String, String> map) throws Exception {
		SCR001004Mapper.FileDelete(map);
	}

	public List<HashMap<String, String>> UserList(HashMap<String, String> map) throws Exception {
		return SCR001004Mapper.UserList(map);
	}

}
