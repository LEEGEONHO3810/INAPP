package com.itfactory.solution.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalendarDto {
	
	
		@JsonProperty("clm_check_schedule_id")
		private String clmCheckScheduleId;
	
		@JsonProperty("clm_check_schedule_file_id")
		private String clmCheckScheduleFileId;
		
		@JsonProperty("clm_check_sub_schedule_id")
		private String clmCheckSubScheduleId;

		@JsonProperty("clm_client_name")
	    private String clmClientName;
	    
	    @JsonProperty("clm_all_day_yn")
	    private String clmAllDayYn;
	    
	    @JsonProperty("clm_check_emergency_yn")
	    private String clmCheckEmergencyYn;
	    
	    @JsonProperty("clm_user_id")
	    private String clmUserId;
	    
	    @JsonProperty("clm_start_date")
	    private String clmStartDate;
	    
	    @JsonProperty("clm_start_time")
	    private String clmStartTime;
	    
	    @JsonProperty("clm_end_date")
	    private String clmEndDate;
	    
	    @JsonProperty("clm_end_time")
	    private String clmEndTime;
	    
	    @JsonProperty("clm_comment")
	    private String clmComment;
	    
	    @JsonProperty("clm_user")
	    private String clmUser;
	    
	    @JsonProperty("clm_color")
	    private String color;

		public String getClmClientName() {
			return clmClientName;
		}

		public void setClmClientName(String clmClientName) {
			this.clmClientName = clmClientName;
		}

		public String getClmAllDayYn() {
			return clmAllDayYn;
		}

		public void setClmAllDayYn(String clmAllDayYn) {
			this.clmAllDayYn = clmAllDayYn;
		}

		public String isClmCheckEmergencyYn() {
			return clmCheckEmergencyYn;
		}
		
		public String getClmCheckEmergencyYn() {
			return clmCheckEmergencyYn;
		}

		public void setClmCheckEmergencyYn(String clmCheckEmergencyYn) {
			this.clmCheckEmergencyYn = clmCheckEmergencyYn;
		}

		public String getClmUserId() {
			return clmUserId;
		}

		public void setClmUserId(String clmUserId) {
			this.clmUserId = clmUserId;
		}

		public String getClmStartDate() {
			return clmStartDate;
		}

		public void setClmStartDate(String clmStartDate) {
			this.clmStartDate = clmStartDate;
		}

		public String getClmStartTime() {
			return clmStartTime;
		}

		public void setClmStartTime(String clmStartTime) {
			this.clmStartTime = clmStartTime;
		}

		public String getClmEndDate() {
			return clmEndDate;
		}

		public void setClmEndDate(String clmEndDate) {
			this.clmEndDate = clmEndDate;
		}

		public String getClmEndTime() {
			return clmEndTime;
		}

		public void setClmEndTime(String clmEndTime) {
			this.clmEndTime = clmEndTime;
		}

		public String getClmComment() {
			return clmComment;
		}

		public void setClmComment(String clmComment) {
			this.clmComment = clmComment;
		}

		public String getClmUser() {
			return clmUser;
		}

		public void setClmUser(String clmUser) {
			this.clmUser = clmUser;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

	 	public String getClmCheckScheduleId() {
			return clmCheckScheduleId;
		}

		public void setClmCheckScheduleId(String clmCheckScheduleId) {
			this.clmCheckScheduleId = clmCheckScheduleId;
		}
		
		public String getClmCheckScheduleFileId() {
			return clmCheckScheduleFileId;
		}

		public void setClmCheckScheduleFileId(String clmCheckScheduleFileId) {
			this.clmCheckScheduleFileId = clmCheckScheduleFileId;
		}
	
		public String getClmCheckSubScheduleId() {
			return clmCheckSubScheduleId;
		}

		public void setClmCheckSubScheduleId(String clmCheckSubScheduleId) {
			this.clmCheckSubScheduleId = clmCheckSubScheduleId;
		}
	    
	    
}
