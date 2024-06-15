<style>
@keyframes bell-effect {
  50% {
    opacity: 0;
  }
}
.bell {
  animation: bell-effect 1s step-end infinite;
}
.v-card__text .v-text-field{
    font-size: 25px;
}
.buttonMenu{
	height: 50px !important;
	color:black !important;
	font-weight:700 !important;
	font-size:20px !important;
	border:2px solid black;
	border-color: black;
	background-color: #cdcdcd !important;
}
	.swal2-title{
		font-size:22px !important;
	}
</style>
<style scoped>
	.v-data-table >>> .v-data-table-header th {
		text-align: center !important;
		height: 32px !important;
	}
	.v-data-table >>> .text-center {
		height: 33.5px !important;
	}
    #loading_img{
        position: relative;
        top:32%;
        left:43%;
    }
	.buttonMenu >>> span {
		font-weight: 800;
	}
	.menu_div{
		position: absolute;
		top: 0;
		left: 0;
		width:220px;
		height: 100%;
		text-align:center;
		background-color: #5d5d5d;
	}
	.tableTH {
		background-color: rgb(95, 155, 225);
		text-align: center;
		height: 40px;
		color:white;
		font-size: 20px;
		font-weight: 600;
		width: 25% !important;
	}
	.tableTD {
		background-color: rgb(217, 230, 255);
		text-align: center;
		color:black;
		height: 40px;
		font-size: 20px;
		font-weight:600;
		width: 25%;
	}
	.error_table{
		font-size: 23px;
		text-align: center;
		border: 1px solid black;
		padding:5px;
		width: calc(100% - 10px);
		margin-left:10px;
		height: 210px;
		border-spacing: 0;
		table-layout:fixed;
	}
	.error_table >>> td{
		border: 1px solid black;
		white-space: nowrap;
		padding-top: 5px;
		padding-bottom: 5px;
	}
	.heater_table{
		table-layout:fixed;
		width:100%;
		padding: 5px;
		/* background-color: rgb( 5, 124, 166); */
		border-spacing: 0;
		display: table;
		text-align: center;
	}
	.heater_table >>> td{
		padding-top: 5px;
		padding-bottom: 5px;
		white-space: nowrap;
		border-right: 1px solid rgb( 5, 124, 166);
		border-left: 1px solid rgb( 5, 124, 166);
	}
	.logo{
		width:220px;
		height: auto;
		padding: 10px;
   	 	background-color: white;
		border: 1px solid black;
	}
</style>
 <template>
</template> 
<template style="height: 100%;">
	<v-layout style="width:100%;height: 100%;">
		<div class="menu_div" >
			<div @click="$router.push('/')" style="position:fixed; cursor:pointer;width:220px; font-size:26px; font-weight:bold;color:white;" class="fontDefault">APP</div>
			<div style="height:40px;"></div>
			<div width="220px" style="height:25px;"></div>
			<div><v-btn style="" width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('scr001002')" v-on:click="fnc_router_go('scr001002')">메모장</v-btn></div>
			<div><v-btn style="" width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('scr001004')" v-on:click="fnc_router_go('scr001004')">캘린더</v-btn></div>
			<div width="220px" style="height:25px;"></div>
		</div>
		<div style="margin-bottom:20px; width: calc(100% - 220px);position: absolute;top:0; right: 0;">
			<v-row style="margin:10px 0 0 0; padding: 0px 15px 0px 15px;" justify="center">
				<v-col xl="1" md="1" sm="1" cols="1" align-self="center">
					<v-select 
						v-model="clm_s_use_yn" 
						:items="clm_use_items" 
						item-text="text"
						item-value="value"
						open-on-clear 
						persistent-hint
						@change="getList"
						label="사용 여부"
						class="testselect"
						style="overflow-x: hidden; padding-top: 20px; margin-top:-6px; font-size:13px;" > 
					</v-select>
				</v-col>
				<v-col xl="2" md="2" sm="2" cols="2" align-self="center">
					<v-select 
						v-model="clm_s_server_location" 
						:items="clm_s_server_items" 
						item-text="clm_server_location"
						item-value="clm_server_location"
						open-on-clear 
						persistent-hint
						@change="getList"
						label="서버 구분"
						class="testselect"
						style="overflow-x: hidden; padding-top: 20px; margin-top:-6px; font-size:13px;" > 
					</v-select>
				</v-col>
				<v-col xl="8" md="8" sm="8" cols="8" align-self="center" >
					<v-text-field label="검색" v-model="schVal" @keypress.enter.prevent="getList" />
				</v-col>
				<v-col xl="1" md="1" sm="1" cols="1" align-self="center">
					<v-btn width="100%" color="primary" v-on:click="getList">검색</v-btn>
				</v-col>
			</v-row>
			<v-row style="margin:0 0 0 0; padding: 0 15px 0 15px !important;" justify="center">
				<v-col xl="12" md="12" sm="12" cols="12" align-self="center" style="width:100%; padding-bottom:0px;margin-bottom:0px;cursor:pointer;padding-top:0px; margin-top:0px;">
					<v-data-table no-data-text="조회된 URL이 없습니다."  disable-sort style="margin-bottom:0px;" @click:row="onClickRow" mobile-breakpoint="0" :headers="headers" :items="document" :loading="loading" :footer-props="{ 'items-per-page-options': [10, -1] }" :items-per-page="10" :page.sync="pagination.page" :itemsPerPage.sync="pagination.itemsPerPage">
						<template v-slot:item="{item, index}">
							<tr @click="onClickRow(item,index)">
								<template v-if="item.clm_change_yn=='N'">
									<td @click="changeDocuments(item,index)">{{ item.clm_company_name }}</td>
									<td @click="changeDocuments(item,index)">{{ item.clm_company_server_gubun }}</td>
									<td @click="changeDocuments(item,index)">{{ item.clm_program_gubun }}</td>
									<td @click="changeDocuments(item,index)">{{ item.clm_server_location }}</td>
									<td @click="changeDocuments(item,index)">{{ item.clm_server_inner_location }}</td>
									<td @click="changeDocuments(item,index)">{{ item.clm_inner_port }}</td>
									<td @click="changeDocuments(item,index)">{{ item.clm_outer_port }}</td>
									<td @click="changeDocuments(item,index)" style="text-align: start; " v-bind:id="'url'+String(index)" :ref="'url' + index">{{ item.clm_login_url }}</td>
									<td @click="changeDocuments(item,index)"> <span><v-icon style="border-left: none;" @click="fnc_icon_click('C', index)">mdi-content-copy</v-icon></span> </td>
									<td @click="changeDocuments(item,index)"> <span><v-icon style="border-left: none;" @click="fnc_icon_click('M', index)" v-bind:id="'move'+String(index)" readonly>mdi-open-in-new</v-icon></span> </td>
									<td @click="changeDocuments(item,index)">{{ item.clm_login_id }}</td>
									<td @click="changeDocuments(item,index)">{{ item.clm_login_pw }}</td>
									<td @click="changeDocuments(item,index)">{{ item.clm_comment }}</td>
								</template>
								<template v-else>
									<td @click="changeDocuments(item,index)" @change="resetYn(item,index)"><v-text-field v-model="item.clm_company_name"/></td>
									<td @click="changeDocuments(item,index)" @change="resetYn(item,index)"><v-text-field v-model="item.clm_company_server_gubun"/></td>
									<td>
										<v-select 
											v-model="item.clm_program_gubun" 
											:items="clm_program_items" 
											item-text="text"
											item-value="value"
											open-on-clear 
											persistent-hint
											@change="resetYn(item,index)"
											class="testselect"
											style="overflow-x: hidden; width:100%; padding: 3px; margin-top:15px; font-size:13px;" > 
										</v-select>
									</td>
									<td @click="changeDocuments(item,index)" @change="resetYn(item,index)"><v-text-field v-model="item.clm_server_location"/></td>
									<td @click="changeDocuments(item,index)" @change="resetYn(item,index)"><v-text-field v-model="item.clm_server_inner_location"/></td>
									<td @click="changeDocuments(item,index)" @change="resetYn(item,index)"><v-text-field v-model="item.clm_inner_port"/></td>
									<td @click="changeDocuments(item,index)" @change="resetYn(item,index)"><v-text-field v-model="item.clm_outer_port"/></td>
									<td @click="changeDocuments(item,index)" @change="resetYn(item,index)" v-bind:id="'url'+String(index)" :ref="'url' + index"><v-text-field v-model="item.clm_login_url"/></td>
									<td> <span><v-icon style="border-left: none;" @click="fnc_icon_click('C', index)">mdi-content-copy</v-icon></span> </td>
									<td> <span><v-icon style="border-left: none;" @click="fnc_icon_click('M', index)" v-bind:id="'move'+String(index)" readonly>mdi-open-in-new</v-icon></span> </td>
									<td @click="changeDocuments(item,index)" @change="resetYn(item,index)"><v-text-field v-model="item.clm_login_id"/></td>
									<td @click="changeDocuments(item,index)" @change="resetYn(item,index)"><v-text-field v-model="item.clm_login_pw"/></td>
									<td @click="changeDocuments(item,index)" @change="resetYn(item,index)"><v-text-field v-model="item.clm_comment"/></td>
								</template>
								<td> 
									<v-select 
										v-model="item.clm_use_yn" 
										:items="clm_use_items" 
										item-text="text"
										item-value="value"
										open-on-clear 
										persistent-hint
										@change="resetYn(item,index)"
										class="testselect"
										style="padding: 3px; margin-top:15px; font-size:13px; overflow-x: hidden" > 
									</v-select>
								</td>
								<td> <span><v-icon style="overflow-x: hidden;overflow-y: hidden;" @click="fnc_icon_click('P', index, item)">mdi-plus</v-icon></span> </td>
								<td> <span><v-icon style="overflow-x: hidden;overflow-y: hidden;" @click="fnc_icon_click('D', index)">mdi-trash-can-outline</v-icon></span> </td>
								<template v-if="item.clm_save_yn == 'N'">
									<td> <span><v-icon style="overflow-x: hidden;overflow-y: hidden;" @click="fnc_icon_click('S', index)">mdi-content-save-outline</v-icon></span> </td>
								</template>
								<template v-else>
									<td> <span><v-icon style="overflow-x: hidden;overflow-y: hidden; color:red;" @click="fnc_icon_click('S', index)">mdi-content-save-outline</v-icon></span> </td>
								</template>
								<td> <span><v-icon style="overflow-x: hidden;overflow-y: hidden;" @click="fnc_icon_click('F', index)">mdi-file-outline</v-icon></span> </td>
							</tr>
							
						</template>
					</v-data-table>	
				</v-col>				
			</v-row>
			<v-row justify="center">
				<v-dialog v-model="FileModal" max-width="1100" @keyup.esc="FileModal = false">
					<v-card>
						<v-toolbar class="fontDefault" color="light-blue" dark> {{ this.clm_company_gubun_name }} 파일 업로드 </v-toolbar>
						<v-card-text style="padding-top:25px;padding-bottom: 5px; zoom:100%">
							<v-row>							
								<v-col xl="12" md="12" sm="12" cols="12"  align-self="center" style="padding-bottom:0px;margin-bottom:0px;padding-top:20px;">
									<v-file-input multiple accept="*" prepend-icon="mdi-folder-upload" counter show-size outlined small-chips v-model="upload_file" ref="upload_file" @change="CheckFileComment"></v-file-input>										
								</v-col>
							</v-row>
							<template v-if="this.upload_file != ''">
								<v-row>
									<span style="font-weight:800;font-size:20px;  margin-bottom:10px; padding-left:9px; padding-top:0px">파일 비고 입력</span>
								</v-row>						
								<v-row>
									<v-col xl="6" md="6" sm="6" cols="6" style="padding-bottom:0px;margin-bottom:0px;margin-top:-10px;"   v-for="(item, index) in file_comment_items" v-bind:key="index">
										<div style="width:100%"><span style="padding-top:0px;">{{ item.clm_file_name }} </span><v-text-field v-model="item.clm_file_comment" style="padding-top:0px; margin-left: -5px; margin-top:-5px;"></v-text-field></div>
									</v-col>
								</v-row>
							</template>
							<v-row>
								<v-col>
									<v-data-table no-data-text="업로드된 파일이 없습니다." class="elevation-1" v-model="selected_items" :single-select="false" show-select item-key="num" :headers="file_headers" @click:row="onClickFileRow" :items="file_document" :loading="loading" :footer-props="{ 'items-per-page-text': '페이지 당 행',  pageText: '총 {2} 항목 중 {0}-{1}' }">
										<template slot="item.clm_download_action" slot-scope="props">
											<v-btn class="mx-2" icon>
												<v-btn width="80%" color="primary" style="color:white; margin-left:-10px" v-on:click="FileDownLoad(props.item)">다운로드</v-btn>
											</v-btn>
										</template>
										<template slot="item.clm_delete_action" slot-scope="props">
											<v-btn class="mx-2" icon>
												<v-btn width="80%" color="red" style="color:white; margin-left:-10px" v-on:click="FileDelete(props.item)">삭제</v-btn>
											</v-btn>
										</template>
									</v-data-table>
								</v-col>
							</v-row>
						</v-card-text>
						<v-card-actions class="justify-end">
							<v-btn text style="font-size:15px; font-weight:bold;" @click="FileUpload">파일 저장</v-btn>
							<v-btn text style="font-size:15px; font-weight:bold;" @click="FileModal = !FileModal">닫기</v-btn>
						</v-card-actions>
					</v-card>
				</v-dialog>
			</v-row>
		</div>
	</v-layout>
</template>
<script src="https://unpkg.com/vue"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script>
import axios from 'axios';
var menu_json = [];
import Swal from 'sweetalert2'

export default {
	data() {
      return {
			updateHistoriesComponent:0,
			username: '',
			password: '',
			items: menu_json,
			dialog: false,
			screen_id:'9',
			document: [],
			a_document: [],
			ka_document: [],
			phone_document: [],
			m_document: [],
			loading: false,
			loading2: false,
			ErrorRegmodal: false,
			ErrorListmodal: false,
			PhoneModal: false,
			loading_yn:'N',
			schVal2: '',		
			schVal3: '',
			schVal4: '',
			schVal5: '',
			clm_this_year_items: [], // 년도 검색용
			list_page: 10,			
			options: {
				multiSort: false,
				sortBy: [],
				sortDesc: [],
				page: 1,
				itemsPerPage: 5,
			},
			
			clm_use_items: [
				{ text:"사용", value:"Y" },
				{ text:"미사용", value:"N" },
				{ text:"불명", value:"C" },
			],
			clm_program_items: [
				{ text:"VUE", value:"VUE" },
				{ text:"JSP", value:"JSP" },
			],

			accountItems: [{}],
			/* 파일 업로드 */
			file_document: [],
			FileModal: false,
			upload_file: [],
			selected_items: [],
			file_headers: [
				{ text: '파일명', align: 'start',  value: 'clm_file_name' , width: 300 },
				{ text: '업로드 일자', align: 'center', value: 'clm_upload_datetime' , width: 180 },
				{ text: '용량(mb)', align: ' d-none', value: 'clm_file_size', width: 100 },
				{ text: '사용여부', align: ' d-none', value: 'clm_use_yn', width: 100},
				{ text: '비고', align: 'start', value: 'clm_file_comment', width: 300 },
				{ text: '다운로드', align: 'center', value: 'clm_download_action', width: 100 },
				{ text: '삭제', align: 'center', value: 'clm_delete_action', width: 100 },
			],
			clm_company_gubun_name: '',
			nowIndex: 0,
			file_comment_items: [],

			/* v-data-table로 변경 */
			headers: [
				{ text: '회사명', align: 'start',  value: 'clm_company_name' , width: 100 },
				{ text: '서버 구분', align: 'center', value: 'clm_company_server_gubun' , width: 100 },
				{ text: '프로그램', align: 'center', value: 'clm_program_gubun', width: 80 },
				{ text: '서버 위치', align: 'center', value: 'clm_server_location', width: 120 },
				{ text: '톰캣주소', align: 'center', value: 'clm_server_inner_location', width: 120 },
				{ text: '내부포트', align: 'center', value: 'clm_inner_port', width: 80 },
				{ text: '외부포트', align: 'center', value: 'clm_outer_port', width: 80 },
				{ text: 'DB Host', align: 'start', value: 'clm_login_url', width: 100 },
				{ text: '', align: 'start', value: '', width: 50 },
				{ text: '', align: 'start', value: '', width: 50 },
				{ text: 'ID', align: 'center', value: 'clm_login_id', width: 50 },
				{ text: 'PW', align: 'center', value: 'clm_login_pw', width: 50 },
				{ text: '비고', align: 'start', value: 'clm_comment', width: 250 },
				{ text: '사용여부', align: 'center', value: 'clm_use_yn', width: 50 },
				{ text: '추가', align: 'center', value: 'clm_add_action', width: 30 },
				{ text: '삭제', align: 'center', value: 'clm_delete_action', width: 30 },
				{ text: '저장', align: 'center', value: 'clm_reg_action', width: 30 },
				{ text: '파일', align: 'center', value: 'clm_file_action', width: 30 },
			],
			ka_headers: [
			{ text: '에러 코드 이름', align: 'center',  value: 'clm_code_name' , width: 100 },
			{ text: '에러 코드 내용', align: 'center', value: 'clm_code_text' , width: 100 },
				
			],
			phone_headers: [
			{ text: '이름', align: 'center',  value: 'clm_user_name' , width: 100 },
			{ text: '연락처', align: 'center',  value: 'clm_user_phone' , width: 100 },
			],

			modal_headers: [
			{ text: '에러 코드 이름', align: 'center',  value: 'clm_code_name' , width: 100 },
			{ text: '에러 코드 내용', align: 'center', value: 'clm_code_text' , width: 100 },
			],

			/* 검색 */
			schVal: '',
			clm_s_server_location: '',
			clm_s_server_items: [],
			clm_s_use_yn: 'Y',

			/* 계정 검색 */
			clm_a_account_list: '',
			clm_a_account_items: [],

			// account
			accountVal: '',
			clm_title : '',
			clm_content : '',

			// kakaoError
			clm_m_error_code_id : '',
			clm_m_error_code_name : '',
			clm_m_error_code_text : '',
			clm_m_error_code_url : '',
			clm_m_error_code_dbName : '',
			clm_m_error_code_port : '',
			clm_m_error_code_dbId : '',
			clm_m_error_code_dbPw : '',
			clm_m_error_code_web_check: '',
			clm_m_error_code_program_check: '',
			clm_m_error_code_query_check: '',


			// kakaoPhone
			code_id: '',
			kakaoVal: '',
			clm_kep_id : '',
			clm_m_code_id : '',
			clm_m_s_error_name : '',
			clm_m_error_name : '',
			clm_m_user_name : '',
			clm_m_phone_number :  '',

			// v-data-table index
			pagination: {
				page: 1,
				itemsPerPage: 10 // 페이지 당 보여주는 행 개수
			},
			page_num: 0,

      }
    },
	created() {
		this.value='1';
		this.loading_yn='N';
	},
    mounted() {
		this.getList();
		this.getLocation();
		
		/*
		if (this.$session.exists()) {
			if(this.$session.exists() && typeof this.$session.get('user_id') == 'undefined'){
				this.$session.destroy();
			}
			axios.get('/back/Common/MenuList',{
			params: {
				authority_id: this.$session.get('user_authority')
				}
			})
			.then(response => {
				if(menu_json.length==0){
					// console.log(response.data);
					var lead_end_menu = '';
					var total_menu_screen_cnt = '';
					var menu_sub_array = [];
					var tmp_index = 0;
					for(var row=0; row<response.data.length; row++){
						total_menu_screen_cnt = response.data[row].total_menu_screen_cnt;
						//lead_end_menu = response.data[row].lead_end_menu_script;
						lead_end_menu = response.data[row].end_menu_script;
						if(row == 0 || response.data[row-1].clm_menu_name != response.data[row].clm_menu_name){
							tmp_index++;
						}
						if(this.$session.get('user_id')!='busan' && response.data[row].clm_screen_id != 'scr010001List' && response.data[row].clm_screen_id != 'scr010002List'
						&& response.data[row].clm_screen_id != 'scr010003List' && response.data[row].clm_screen_id != 'scr010004List' && response.data[row].clm_screen_id != 'scr010005List'
						&& response.data[row].clm_screen_id != 'scr010006List' && response.data[row].clm_screen_id != 'scr010007List' && response.data[row].clm_screen_id != 'scr010008List' && response.data[row].clm_screen_id != 'scr010009List' && response.data[row].clm_screen_id != 'scr010010List' && response.data[row].clm_screen_id != 'scr010011List'){
							if(total_menu_screen_cnt == '1'){
								// console.log('response.data[row].clm_screen_name : ' + response.data[row].clm_screen_name)
								// console.log('response.data[row].clm_screen_id : ' + response.data[row].clm_screen_id)
								menu_json.push({ icon: '', title: response.data[row].clm_screen_name, route: response.data[row].clm_screen_id, index : tmp_index });
							}else{
								menu_sub_array.push(
									{ icon:'', title: response.data[row].clm_screen_name, route: response.data[row].title, index : tmp_index }
								);
		
								if(lead_end_menu != ''){
									menu_json.push({ icon: 'mdi-home', title: response.data[row].clm_menu_name, items: menu_sub_array, index : tmp_index });
									menu_sub_array = [];
								}
							}
						}else if(this.$session.get('user_id')=='busan' && (response.data[row].clm_screen_id == 'scr010001List' || response.data[row].clm_screen_id == 'scr010002List'
						|| response.data[row].clm_screen_id == 'scr010003List'|| response.data[row].clm_screen_id == 'scr010004List' || response.data[row].clm_screen_id == 'scr010005List'
						|| response.data[row].clm_screen_id == 'scr010006List'|| response.data[row].clm_screen_id == 'scr010007List' || response.data[row].clm_screen_id == 'scr010008List' || response.data[row].clm_screen_id == 'scr010009List' || response.data[row].clm_screen_id == 'scr010010List' || response.data[row].clm_screen_id == 'scr010011List')){
							if(total_menu_screen_cnt == '1'){
								// console.log('response.data[row].clm_screen_name : ' + response.data[row].clm_screen_name)
								// console.log('response.data[row].clm_screen_id : ' + response.data[row].clm_screen_id)
								menu_json.push({ icon: '', title: response.data[row].clm_screen_name, route: response.data[row].clm_screen_id, index : tmp_index });
							}else{
								menu_sub_array.push(
									{ icon:'', title: response.data[row].clm_screen_name, route: response.data[row].title, index : tmp_index }
								);
		
								if(lead_end_menu != ''){
									menu_json.push({ icon: 'mdi-home', title: response.data[row].clm_menu_name, items: menu_sub_array, index : tmp_index });
									menu_sub_array = [];
								}
							}
						
						}
					}
					// console.log(menu_json);
				}
			});
		};
		*/
		
	},
	methods: {
		fnc_router_go(location){
			this.$router.push(location);
		},
		updatePage(page){
			this.list_page=Number(page.itemsPerPage);
		}, 
		kakaoError(){
			this.clm_m_error_code_id = '';
			this.clm_m_error_code_name = '';
			this.clm_m_error_code_text = '';
			this.clm_m_error_code_url = '';
			this.clm_m_error_code_dbName = '';
			this.clm_m_error_code_port = '';
			this.clm_m_error_code_dbId = '';
			this.clm_m_error_code_dbPw = '';
			this.clm_m_error_code_web_check= '';
			this.clm_m_error_code_program_check = '';
			this.clm_m_error_code_query_check = '';
			this.ErrorRegmodal = true;
		},

		kakaoPhone(){

			this.clm_kep_id = '';
			this.clm_m_code_id = '';
			this.clm_m_error_name = '';
			this.clm_m_user_name = '';
			this.clm_m_phone_number = '';

			this.PhoneModal = true;
		},

		phone_onClickRow(data, index) {
			this.clm_kep_id = data.clm_kep_id;
			this.clm_m_code_id = data.clm_code_id;
			this.clm_m_error_name = data.clm_code_name;
			this.clm_m_user_name = data.clm_user_name;
			this.clm_m_phone_number = data.clm_user_phone;

			this.PhoneModal = true;

		},

		async getList() {
			await axios.get('/back/POSTGRES_MES/ServerList',{ params: {
				schVal: this.schVal,
				schVal2: this.clm_s_server_location,
				schVal3: this.clm_s_use_yn,
			 } })
			.then(response => {
				if(response.status == '200') {					
					this.document = response.data;
					this.screen_id = '1';
					console.log(this.document);
				}
			});
		},
		async getList2() {
			await  axios.get('/back/POSTGRES_MES/AccountList',{ params: {
				schVal: this.accountVal,
			} })
			.then(response => {
				if(response.status == '200') {

					this.a_document = response.data;
					this.accountItems = response.data;
					this.screen_id = '2';
				}
				for(var i = 0; i < response.data.length; i++){
					this.clm_title = response.data[i].clm_title;
					this.clm_comment = response.data[i].clm_comment;
				}
			});
		},

		async getKakaoErrorList() {
			await  axios.get('/back/POSTGRES_MES/kakaoError',{ params: {
				schVal: this.kakaoVal,
			} })
			.then(response => {
				if(response.status == '200') {

					this.ka_document = response.data;
					this.screen_id = '3';
				}
			});
		},
		changeDocuments(item,index) {		
			item.clm_change_yn='Y';	
		},
		addList() {	
			this.accountItems.push({
				clm_content_seq : '0'

			 });
		},
		// 저장 후 리로딩
		async getKakaoPhoneList(data) {
			await axios.get('/back/POSTGRES_MES/kakaoPhone',{
					params: {
						schVal: this.kakaoVal,
						clm_code_id: data,
					}
				}).then(
				response => {
					if(response.status == '200') {
						this.phone_document = response.data;
						this.screen_id = '3';
					}
				},
			)
			.catch(error => {
				console.log(error)
			})
		},

		changeDocuments(item,index) {		
			item.clm_change_yn='Y';	
		},
		addList() {	
			this.accountItems.push({
				clm_content_seq : '0'

			 });
		},

		LoginConfirm() {
			if(this.username == '' || this.password == ''){
				Swal.fire({
					title:'ID와 비밀번호를 입력해주시기 바랍니다.',
					icon: 'error'
				});
				return;
			}
		
			axios.post('/back/Login/Login', null, { params: {
				clm_user_id: this.username,
				clm_user_pw: this.password
			}})
			.then(response => {
				if (response.status == '200' && response.data.clm_user_id != '') {
					this.$session.start();
					this.$session.set('user_id', response.data.clm_user_id);
					this.$session.set('user_name', response.data.clm_user_name);
					this.$session.set('user_department', response.data.clm_user_department_id);
					this.$session.set('user_department_name', response.data.clm_user_department_name);
					this.$session.set('user_authority', response.data.clm_user_authority_id);
					this.$session.set('user_authority_name', response.data.clm_user_authority_name);
					this.$session.set('company_key',  response.data.clm_company_key);
					this.$session.set('user_sabun', response.data.clm_user_sabun);
					this.$session.set('material_id', '');
					location.reload();
				}else{
					Swal.fire({
						title:'ID와 패스워드를 확인해주시기 바랍니다.',
						icon: 'error'
					});
					this.$session.destroy();
				}
			})
			.catch(error => {
				console.log(error)
			})
		},
		ChangeSrceen(value){
			if(value=='1'){
				this.screen_id='1';
			}else if(value == '2'){
				 axios.get('/back/POSTGRES_MES/AccountList',{ params: {
					schVal: this.accountVal,
				} })
				.then(response => {
					if(response.status == '200') {
						
						this.a_document = response.data;
						this.accountItems = response.data;
						this.screen_id = '2';
					}
					for(var i = 0; i < response.data.length; i++){
						this.clm_title = response.data[i].clm_title;
						this.clm_comment = response.data[i].clm_comment;
					}
				});
			}else if(value == '3'){
				this.getKakaoErrorList();
				this.screen_id = '3';
			}
		},
		getToday() {
			let date = new Date()
			let year = date.getFullYear()
			this.clm_this_year_items = [
				{ text:"전체", value:'' },
				{ text: year-2 + "년", value: year-2 },
				{ text: year-1 + "년", value: year-1 },
				{ text: year + "년", value: year },
			]
			if(String(date.getMonth+1) == '1') {
				this.schVal1 = year-1;
			}else {
				this.schVal1 = year;
			}
		},
		fnc_icon_click(value, index, item) {
			// alert('>'+item.num);
			this.page_num = Number(this.pagination.page - 1) * Number(this.pagination.itemsPerPage) + Number(index);
			var server_id = this.document[this.page_num].clm_server_id;
			this.nowIndex = index;

			if(value == 'C') {
				var copy_link = this.document[this.page_num].clm_login_url;
				navigator.clipboard.writeText(copy_link);
			}else if(value == 'M') {
				var move_link = this.document[this.page_num].clm_login_url;
				window.open(move_link, "_blank");
			}else if(value == 'P') {
				this.document.splice(Number(this.page_num)+1,0,{
					clm_company_name: '',
					clm_company_server_gubun: '',
					clm_inner_port: '',
					clm_login_id: 'admin',
					clm_login_pw: '1234',
					clm_login_url: '',
					clm_outer_port: '',
					clm_program_gubun: '',
					clm_server_id: '',
					clm_server_inner_location: '',
					clm_server_location: '',
					clm_comment: '',
					clm_use_yn: 'Y',
					clm_order_seq: '',
				})
			}else if(value == 'D') {
				if(server_id == '') {
					this.document.splice(this.page_num, 1);
				}else {
					Swal.fire({
						title: this.document[this.page_num].clm_company_name + '(' + this.document[this.page_num].clm_company_server_gubun  + ')' + '을(를) \n 서버 목록에서 정말 삭제하시겠습니까?',
						text:  '',
						icon: 'question',
						showCancelButton: true,
						allowOutsideClick: false,
						confirmButtonColor: '#3085d6',
						cancelButtonColor: '#d33',
						confirmButtonText: '예',
						cancelButtonText: '아니요',
						width: 650
					}).then((result) => {
						if (result.isConfirmed) {
							axios.post('/back/POSTGRES_MES/Delete', null, { params: {
								clm_server_id: server_id,
								clm_user:this.$session.get('user_id'),
							}})
							.then(response => {
								if (response.status == '200') {
									Swal.fire({
										title:'정상적으로 삭제되었습니다.',
										icon: 'success'
									});
									setTimeout(() => {
										location.reload();
									}, 500)
								}else{
									this.loading_yn='N';
								}
							})
							.catch(error => {
								console.log(error)
							})
						}else{
							this.loading_yn='N';
						}

					})
				}
			}else if(value == 'S') {	
				if(this.document[this.page_num].clm_company_name == '') {
					Swal.fire({
						title: '회사명을 입력해 주세요.',
						icon: 'error'
					});
					return;
				}else if(this.document[this.page_num].clm_program_gubun == '') {
					Swal.fire({
						title: '프로그램을 선택해 주세요.',
						icon: 'error'
					});
					return;
				}else if(this.document[this.page_num].clm_login_url == '') {
					Swal.fire({
						title: 'DB Host를 입력해 주세요.',
						icon: 'error'
					});
					return;
				}

				axios.post('/back/POSTGRES_MES/Save', null, { params: {
					clm_server_id: server_id,
					clm_company_name: this.document[this.page_num].clm_company_name,
					clm_company_server_gubun: this.document[this.page_num].clm_company_server_gubun,
					clm_inner_port: this.document[this.page_num].clm_inner_port,
					clm_login_id: this.document[this.page_num].clm_login_id,
					clm_login_pw: this.document[this.page_num].clm_login_pw,
					clm_login_url: this.document[this.page_num].clm_login_url,
					clm_outer_port: this.document[this.page_num].clm_outer_port,
					clm_program_gubun: this.document[this.page_num].clm_program_gubun,
					clm_server_inner_location: this.document[this.page_num].clm_server_inner_location,
					clm_server_location: this.document[this.page_num].clm_server_location,
					clm_comment: this.document[this.page_num].clm_comment,
					clm_use_yn: this.document[this.page_num].clm_use_yn,
					clm_order_seq: String(Number(this.document[Number(this.page_num)-1].clm_order_seq)+1), // 이전 줄의 seq +1 
					clm_user:this.$session.get('user_id'),
				}})
				.then(response => {
					if (response.status == '200') {
						var exist_yn = response.data;
						if(exist_yn == 'N') {
							Swal.fire({
								title:'정상적으로 저장되었습니다.',
								icon: 'success'
							});
							this.getList();
						}else if(exist_yn == 'Y'){
							Swal.fire({
								title:'이미 등록된 서버입니다.',
								icon: 'error'
							});
							this.getList();
						}
					}else{
						this.loading_yn='N';
					}
				})
				.catch(error => {
					console.log(error)
				})

			}else if(value == 'F') {
				this.upload_file = [];
				this.upload_file.splice(0,(this.upload_file.length));
				this.clm_company_gubun_name = this.document[this.page_num].clm_company_name + '(' + this.document[this.page_num].clm_company_server_gubun + ')'
				axios.get('/back/POSTGRES_MES/FileList',{ params: {
					clm_server_id : server_id
				 } })
				.then(response => {
					if(response.status == '200') {
						this.file_document = response.data;
						console.log(this.file_document);
					}
				});
				this.FileModal = true;
			}
		},
		FileUpload() {
			var server_id = this.document[this.nowIndex].clm_server_id
			
			if(this.upload_file == null || this.upload_file == ""){
				Swal.fire({
					title:'등록된 파일이 없습니다.', 
					icon: 'warning'
				});
				return;
			}else {
				const formData = new FormData();
				formData.append('clm_server_id', server_id);
				formData.append('clm_company_gubun_name', this.clm_company_gubun_name);
				for (let i in this.upload_file) {
					formData.append('upload_file', this.upload_file[i]);
					formData.append('clm_file_comment', this.file_comment_items[i].clm_file_comment);
				}

				axios.post('/back/POSTGRES_MES/FileUpload'
					, formData										
					, { headers: { 'Content-Type': 'multipart/form-data' } 
				}).then(response => {
					if (response.status == '200') {
						console.log(formData)
						Swal.fire({
							title: '파일을 업로드했습니다.',
							icon: 'success'
						});
						this.FileModal = false;
						this.getList();
					}else{
						Swal.fire({
							title: '파일 업로드를 실패했습니다.',
							icon: 'error'
						});
						this.FileModal = false;
					}
				}).catch(error => {

				})
				

			}
			
		},
		CheckFileComment(){
			if(this.upload_file != '') {
				this.file_comment_items = [];
				this.file_comment_items.splice(0,(this.file_comment_items.length));
				for(var x = 0; x < this.upload_file.length; x ++) {
					this.file_comment_items.push({
						clm_file_name: this.upload_file[x].name,
						clm_file_comment: '',
					})
				}
			}
		},
		FileDownLoad(data) {
			return new Promise((resolve, reject) => {
				axios.get('/back/POSTGRES_MES/FileDownLoad',{
					params: {
						clm_file_name: data.clm_file_name,
						clm_file_location: data.clm_file_location
					},
					responseType: "blob"
				}).then(
				response => {
					const url = window.URL.createObjectURL(new Blob([response.data]));
					const link = document.createElement("a");
					link.href = url;
					link.setAttribute("download", data.clm_file_name);
					document.body.appendChild(link);
					link.click();
				},
			)
		})
			
		},		
		FileDelete(data) {
			Swal.fire({
				title: data.clm_file_name + ' 파일을 '+ this.clm_company_gubun_name +'에서 \n 정말 삭제하시겠습니까?',
				text:  '',
				icon: 'question',
				showCancelButton: true,
				allowOutsideClick: false,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: '예',
				cancelButtonText: '아니요',
				width: 850,
			}).then((result) => {
				if (result.isConfirmed) {
					axios.post('/back/POSTGRES_MES/FileDelete', null, { params: {
						clm_server_id: data.clm_server_id,
						clm_file_name: data.clm_file_name
					}})
					.then(response => {
						if (response.status == '200') {
							Swal.fire({
								title:'정상적으로 삭제되었습니다.',
								icon: 'success'
							});
							this.FileModal = false;
						}else{
							this.loading_yn='N';
						}
					})
					.catch(error => {
						console.log(error)
					})
				}else{
					this.loading_yn='N';
				}

			})
		},
		resetYn(item,index){
			this.page_num = Number(this.pagination.page - 1) * Number(this.pagination.itemsPerPage) + Number(index);
			this.document[this.page_num].clm_save_yn='Y';
			if(this.document[this.page_num].clm_save_yn == 'Y') {
				Swal.fire({
					title: this.document[this.page_num].clm_company_name + '(' + this.document[this.page_num].clm_company_server_gubun  + ')' + '의 변경사항을 저장하시겠습니까?',
					text:  '',
					icon: 'question',
					showCancelButton: true,
					allowOutsideClick: false,
					confirmButtonColor: '#3085d6',
					cancelButtonColor: '#d33',
					confirmButtonText: '예',
					cancelButtonText: '아니요',
					width: 650
				}).then((result) => {
					if (result.isConfirmed) {
						this.fnc_icon_click('S', index);
						this.document[this.page_num].clm_save_yn='N';
					}else{
						this.loading_yn='N';
					}

				})
			}

		},
		getLocation() {
			axios.get('/back/POSTGRES_MES/LocationList',{ params: { } })
			.then(response => {
				if(response.status == '200') {
					this.clm_s_server_items = response.data;
				}
			});
		},
		onClickRow(data, index) {

		},

		ka_onClickRow(data, index) {
			this.clm_m_code_id = data.clm_code_id;
			const vm = this
			vm.loading2 = true
			let items = axios.get('/back/POSTGRES_MES/kakaoPhone',{
					params: {
						schVal: this.kakaoVal,
						clm_code_id: data.clm_code_id,
					}
				}).then(
				response => {
					if(response.status == '200') {
						this.phone_document = response.data;
						this.screen_id = '3';
					}
					vm.loading2 = false;
				},
			)
			.catch(error => {
				console.log(error)
			})
		},
		
		ka_ondbClickRow(event, data) {
			
			var dataItem = data.item.clm_code_db_info.split(",");
			this.clm_m_error_code_name = data.item.clm_code_name,
			this.clm_m_error_code_id = data.item.clm_code_id,
			this.clm_m_error_code_text = data.item.clm_code_text
			if(dataItem != '' || dataItem !== undefined){
				this.clm_m_error_code_url = dataItem[0],
				this.clm_m_error_code_dbName = dataItem[1],
				this.clm_m_error_code_port = dataItem[2],
				this.clm_m_error_code_dbId = dataItem[3],
				this.clm_m_error_code_dbPw = dataItem[4]
			}
			this.clm_m_error_code_web_check= data.item.clm_code_website_check,
			this.clm_m_error_code_program_check= data.item.clm_method_program_check,
			this.clm_m_error_code_query_check= data.item.clm_code_query_check,

			this.ErrorRegmodal = true;
		},

		onClickFileRow(event,data) {

		},

		SaveContent(item,index){

			if(!item.clm_note_id == undefined ){
				item.clm_note_id = ''

			}else if(!item.clm_title){
				Swal.fire({
					title:(index+1) +'번째 메모장의 제목이 빈칸입니다.',
					icon: 'error'
				});
				return;
			}else if(!item.clm_content){
				Swal.fire({
					title:(index+1) +'번째 메모장의 내용이 빈칸입니다.',
					icon: 'error'
				});
				return;
			}

			axios.post('/back/POSTGRES_MES/ContentSave', null, { params: {

					clm_note_id: item.clm_note_id,
					clm_title: item.clm_title,
					clm_content: item.clm_content,
					clm_content_seq: item.clm_content_seq,
					clm_user:this.$session.get('user_id'),

				}})
				.then(response => {
					if (response.status == '200') {
						var exist_yn = response.data;
						if(exist_yn == 'N') {
							Swal.fire({
								title:'정상적으로 저장되었습니다.',
								icon: 'success'
							});
							this.getList2();
						}else if(exist_yn == 'Y'){
							Swal.fire({
								title:'이미 등록된 서버입니다.',
								icon: 'error'
							});
							
						}
					}else{
						this.loading_yn='N';
					}
					this.getList2();
				})
				.catch(error => {
					console.log(error)
				})

		},

		checkOrderSeq(item,index){
			console.log(item);

			axios.post('/back/POSTGRES_MES/OrderSeq', null, { params: {

				clm_note_id: item.clm_note_id,
				clm_content_seq: item.clm_content_seq,

			}}).then(response => {
				if (response.status == '200') {
					var result = response.data;

				}else{
					this.loading_yn='N';
				}
				this.getList2();
			})
			.catch(error => {
			console.log(error)
			})
		},
		
		DeleteContent(item,index){

			if(!item.clm_note_id == ''){
				
				axios.post('/back/POSTGRES_MES/DeleteContent', null, { params: {
			
				clm_note_id: item.clm_note_id,
				clm_content_seq: item.clm_content_seq,

				}})
				.then(response => {
				if (response.status == '200') {
					var result = response.data;

				}else{
					this.loading_yn='N';
				}
				this.getList2();
				})
				.catch(error => {
				console.log(error)
				})

			}else{
				this.accountItems.splice(index,1)
				
			}
			
		},
		copyText(item,index){
			var textToCopy = item.clm_content;
			var textArea = document.createElement('textarea');

			textArea.value = textToCopy;

			document.body.appendChild(textArea);
			textArea.select();
			document.execCommand('copy');
			document.body.removeChild(textArea);

			Swal.fire({
				title:'내용이 복사되었습니다.',
				icon: 'success'
			});
		},
		select_code_color: function (item) {
			
			return (item.clm_code_id == this.clm_m_code_id) ? 'style-red color-white' : 'style-white color-black'

		},
		
		ErrorSave() {	
			var url = this.clm_m_error_code_url	;
			var name = this.clm_m_error_code_dbName;
			var port = this.clm_m_error_code_port;
			var id = this.clm_m_error_code_dbId;
			var pw = this.clm_m_error_code_dbPw;

			if(this.loading_yn == 'Y'){
				Swal.fire({
					title: '저장중입니다.잠시만 기다려주세요.',
					icon: 'error'
				});
				return;
			}

			this.loading_yn = 'Y';


			if(this.clm_m_error_code_name == ''){
				Swal.fire({
					title: '에러코드명을 입력해주시기 바랍니다.',
					icon: 'error'
				});
				this.loading_yn='N';
				return;
			}else if(this.clm_m_error_code_text == ''){
				Swal.fire({
					title: '에러코드설명을 입력해주시기 바랍니다.',
					icon: 'error'
				});
				this.loading_yn='N';
				return;
			}

			if (this.clm_m_error_code_query_check == '' && this.clm_m_error_code_web_check == '' && this.clm_m_error_code_program_check == '') {
				Swal.fire({
					title: '쿼리 체크, 웹사이트 체크 또는 프로그램 체크 중 하나는 반드시 입력해주시기 바랍니다.',
					icon: 'error'
				});
				this.loading_yn = 'N';
				return;
			} 

			const query_check = this.clm_m_error_code_query_check !== '';
			const web_check = this.clm_m_error_code_web_check !== '';
			const program_check = this.clm_m_error_code_program_check !== '';

			this.clm_m_error_code_query_check != ''
			if ((query_check && web_check)  ||  (web_check && program_check) ||  ( query_check && program_check)  ||  ( query_check && web_check && program_check )) {
				Swal.fire({
					title: '쿼리 체크, 웹사이트 체크 또는 프로그램 체크 중 하나만 입력 가능합니다.',
					icon: 'error'
				});
				this.loading_yn = 'N';
				return;
			} 

			if(this.clm_m_error_code_query_check != ''){

				if(this.clm_m_error_code_url == ''){
					Swal.fire({
						title: 'DB Host을 입력해주시기 바랍니다.',
						icon: 'error'
					});
					this.loading_yn='N';
					return;
				}else if(this.clm_m_error_code_dbName == ''){
					Swal.fire({
						title: 'DB명 입력해주시기 바랍니다.',
						icon: 'error'
					});
					this.loading_yn='N';
					return;
				}else if(this.clm_m_error_code_port == ''){
					Swal.fire({
						title: 'PORT를 입력해주시기 바랍니다.',
						icon: 'error'
					});
					this.loading_yn='N';
					return;
				}else if(this.clm_m_error_code_dbId == ''){
					Swal.fire({
						title: 'DB User를 입력해주시기 바랍니다.',
						icon: 'error'
					});
					this.loading_yn='N';
					return;
				}else if(this.clm_m_error_code_dbPw == ''){
					Swal.fire({
						title: 'DB PW를 입력해주시기 바랍니다.',
						icon: 'error'
					});
					this.loading_yn='N';
					return;
				}else{
					if(url.includes(',') || name.includes(',')
						|| port.includes(',')|| id.includes(',')
						|| pw.includes(',')){
					Swal.fire({
						title: 'URL,  DB명, PORT , DB User, DB PW에는 ","가 들어 갈 수 없습니다.',
						icon: 'error'
					});
					this.loading_yn='N';
					return;
					}
				}
			}
			
			axios.post('/back/POSTGRES_MES/ErrorSave', null, { params: {
				clm_error_code_id	 			: this.clm_m_error_code_id,
				clm_error_code_name	 			: this.clm_m_error_code_name,
				clm_error_code_text	 			: this.clm_m_error_code_text,
				clm_code_db_info 		 		: this.clm_m_error_code_url+","+this.clm_m_error_code_dbName+","+this.clm_m_error_code_port+","+this.clm_m_error_code_dbId+","+this.clm_m_error_code_dbPw,
				clm_error_code_web_check 		: this.clm_m_error_code_web_check,
				clm_error_code_program_check 	 : this.clm_m_error_code_program_check,
				clm_error_code_query_check		: this.clm_m_error_code_query_check,
				clm_user:this.$session.get('user_id')
			}})
			.then(response => {
				if (response.status == '200') {
					if(response.data=='N'){
						this.loading_yn='N';
						Swal.fire({
							title: '정상적으로 등록 되었습니다.',
							icon: 'success'
						});
						this.ErrorRegmodal= false;
						this.getKakaoErrorList();
					}else{
						this.loading_yn='N';
						Swal.fire({
							title: '중복된 코드명이 존재합니다.',
							icon: 'error'
						});
					}
				}
			})
			.catch(error => {
				console.log(error)
			})
		},

		ErrorDelete(){
		Swal.fire({
		  title: '글을 삭제하시겠습니까???',
		  text: "삭제하시면 연락처까지 전부 삭제됩니다.",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '삭제',
		  cancelButtonText: '취소'
		}).then((result) => {

			if (result.value) {

				axios.post('/back/POSTGRES_MES/ErrorDelete', null, { params: {
						clm_code_id: this.clm_m_error_code_id
						}})
						.then(response => {
						if (response.status == '200') {
							this.loading_yn='N';
							Swal.fire({
								title: '삭제 완료.',
								icon: 'success'
							});
							this.ErrorRegmodal= false;
							this.getKakaoErrorList();
							this.getKakaoPhoneList();
							this.clm_screen_id = '3';
						}else{
							this.loading_yn='N';
							Swal.fire({
								title: '삭제 실패.',
								icon: 'error'
							});
						}
					})
				}
			})
		},
		PhoneSave() {
			if(this.loading_yn == 'Y'){
				Swal.fire({
					title: '저장중입니다.잠시만 기다려주세요.',
					icon: 'error'
				});
				return;
			}
			this.loading_yn = 'Y';
			if(this.clm_m_code_id == ""){
				Swal.fire({
					title: '에러코드를 선택해주시기 바랍니다.',
					icon: 'error'
				});
				this.loading_yn='N';
				return;
			}else if(this.clm_m_user_name == ''){
				Swal.fire({
					title: '이름을 입력해주시기 바랍니다.',
					icon: 'error'
				});
				this.loading_yn='N';
				return;
			}else if(this.clm_m_phone_number == ''){
				Swal.fire({
					title: '연락처를 입력해주시기 바랍니다.',
					icon: 'error'
				});
				this.loading_yn='N';
				return;
			}

			axios.post('/back/POSTGRES_MES/PhoneReg', null, { params: {

				clm_kep_id: this.clm_kep_id,
				clm_code_id: this.clm_m_code_id,
				clm_user_name: this.clm_m_user_name,
				clm_phone_number: this.clm_m_phone_number,
				clm_user:this.$session.get('user_id'),

			}})
			.then(response => {
				if (response.status == '200') {
					if(response.data=='N'){
						this.loading_yn='N';
						Swal.fire({
							title: '정상적으로 등록 되었습니다.',
							icon: 'success'
						});
						this.PhoneModal= false;
						this.clm_screen_id = '3';
						this.getKakaoPhoneList(this.clm_m_code_id);
					}else{
						this.loading_yn='N';
						Swal.fire({
							title: '등록 실패',
							icon: 'error'
						});
					}
				}
			})
			.catch(error => {
				console.log(error)
			})
		},


		PhoneDelete(){
			
		Swal.fire({
		  title: '글을 삭제하시겠습니까???',
		  text: "삭제하시면 다시 복구시킬 수 없습니다.",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '삭제',
		  cancelButtonText: '취소'
		}).then((result) => {

			if (result.value) {

				axios.post('/back/POSTGRES_MES/PhoneDelete', null, { params: {

					clm_kep_id: this.clm_kep_id,
					clm_code_id: this.clm_m_code_id

					}})
					.then(response => {
					if (response.status == '200') {
						Swal.fire({
							title:'정상적으로 삭제되었습니다.',
							icon: 'success'
						});
						this.PhoneModal= false;
						this.clm_screen_id = '3';
						this.getKakaoPhoneList(this.clm_m_code_id);
					}else{
							this.loading_yn='N';
							Swal.fire({
								title: '삭제 실패',
								icon: 'error'
							});
						}
					})
					.catch(error => {
					console.log(error)
					})
				}
			})
		},

		errorModalOpen(){

			this.ErrorListmodal = true;
			this.getErrorModalList();
		},

		getErrorModalList() {
			let items = axios.get('/back/POSTGRES_MES/kakaoError',{
				params: {
						schVal: this.clm_m_s_error_name,
					}
				}).then(
				response => {
					items = response.data
					setTimeout(() => {
						this.m_document = items
					}, 500)
				},
			)
		},

		onClickErrorModalRow(event, data){
			this.clm_m_code_id = data.item.clm_code_id;
			this.clm_m_error_name = data.item.clm_code_name;
			this.ErrorListmodal = false;
		},

	}
}
</script>
