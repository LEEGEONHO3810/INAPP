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
	/* tbody tr{
		height: 76.6px !important;
	} */
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
	input {
		color-scheme: dark;
 		border-width: 0;
	}
	input:focus {
		outline: none;
  		border-width: 0;
	}
	/* .gauge {
    position: relative;
    border-radius: 50%/100% 100% 0 0;
    background-color: var(--color, rgb(88, 92, 103));
    overflow: hidden;
	}
	.gauge:before{
		content: "";
		display: block;
		padding-top: 50%;   
	}
	.gauge .chart {
	overflow: hidden;
	}
	.gauge .mask {
	position: absolute;
	left: 20%;
	right: 20%;
	bottom: 0;
	top: 40%;
	background-color: #fff;
	border-radius: 50%/100% 100% 0 0;
	}
	.gauge .percentage {
		position:  absolute;
		top: -1px;
		left: -1px;
		bottom: 0;
		right: -1px;
		background-color: var(--background, #aaa);
		transform:rotate(var(--rotation)); 
		transform-origin: bottom center; 
		transition-duration: 600;
	}
	.gauge:hover {
	--rotation: 100deg;
	}
	.gauge .value {
	position:absolute; bottom:0%; left:0;   
	width:100%; 
	text-align: center;
	}

	.gauge .min {
	position:absolute; 
	bottom:0; left:5%;   
	}
	.gauge .max {
	position:absolute; 
	bottom:0; right:5%;   
	} */
	.logo{
		width:220px;
		height: auto;
		padding: 10px;
   	 	background-color: white;
		border: 1px solid black;
	}

	
	.switch {
		position: relative;
		display: inline-block;
		width: 50px;
		height: 24px;
	}

	.switch input {
		display: none;
	}

	.slider {
		position: absolute;
		cursor: pointer;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		background-color: #ccc;
		-webkit-transition: 0.4s;
		transition: 0.4s;
	}

	.slider:before {
		position: absolute;
		content: "";
		height: 16px;
		width: 16px;
		left: 3px;
		bottom: 4px;
		background-color: white;
		-webkit-transition: 0.4s;
		transition: 0.4s;
	}

	input:checked + .slider {
		background-color: #4dc95d;
	}

	input:focus + .slider {
		box-shadow: 0 0 1px #4dc95d;
	}

	input:checked + .slider:before {
		-webkit-transform: translateX(26px);
		-ms-transform: translateX(26px);
		transform: translateX(26px);
	}

	.slider.round {
		border-radius: 24px;
	}

	.slider.round:before {
		border-radius: 50%;
	}
</style>
<style scoped>
	.v-data-table >>> .v-data-table-header th {
		text-align: center !important;
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
	/* .tableTH {
		background-color: rgb(95, 155, 225);
		text-align: center;
		height: 20px;
		color:white;
		font-size: 20px;
		font-weight: 550;
		width: 25% !important;
	} */
	/* .v-select {
		height: 30px;
		margin-top: 3px;
		padding-top: 9px;
	} */
	/* .v-select >>> div {
		color: white !important;
	} */
	.v-input {
		margin-top: 0px;
		padding-top: 0px;
		height: 37px;
	}
	.testselect >>> .v-select__selection {
		padding-top:3px;
		padding-bottom:3px;
		margin-top:-3px;
		margin-bottom:-3px;
	}
</style>

<style>
	.style-red {
		background-color: red;
	}
	.style-white {
	background-color: white;
	}
	.background-red.color-white:hover {
		color: black; 
		background-color: #ffcccc; 
	}
	.background-white.color-black:hover {
		color: white; 
		background-color: #666666; 
	}



</style>
<template style="height: 100%;">
	<v-layout style="width:100%;height: 100%;">
		<div class="menu_div" >
			<div @click="$router.push('/')" style="cursor:pointer;width:220px; font-size:26px; font-weight:bold;color:white;" class="fontDefault"><img class="logo" src="../../assets/it_ci.png"></img></div>
			<div style="height:40px;"></div>
			<div><v-btn width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('/')" v-on:click="fnc_router_go('/')">URL 정리</v-btn></div>
			<div width="220px" style="height:25px;"></div>
			<div><v-btn width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('scr001002')" v-on:click="fnc_router_go('scr001002')">메모장</v-btn></div>
			<div width="220px" style="height:25px;"></div>
			<div><v-btn style="color:red !important;" width="220px" class="buttonMenu">에러코드</v-btn></div>
			<div width="220px" style="height:25px;"></div>
			<div><v-btn style="" width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('scr001004')" v-on:click="fnc_router_go('scr001004')">캘린더</v-btn></div>
			<div width="220px" style="height:25px;"></div>
		</div>
		<div style="margin-bottom:20px; width: calc(100% - 220px);position: absolute;top:0; right: 0;">
			<v-row style="margin:18px 0 0 0; padding: 0 15px 0 15px !important;" justify="center">
				<v-col xl="9" md="9" sm="9" cols="9" align-self="center" style="padding-bottom:0px;margin-bottom:0px;">
					<v-text-field v-model="kakaoVal" class="fontDefault" label="검색" single-line @keypress.enter.prevent="getKakaoErrorList"></v-text-field>
				</v-col>
				<v-col xl="1" md="1" sm="1" cols="1" align-self="center">
					<v-btn width="100%" color="green" v-on:click="kakaoError" style="color:white;">에러코드등록</v-btn>
				</v-col>
				<v-col xl="1" md="1" sm="1" cols="1" align-self="center">
					<v-btn width="100%" color="orange" v-on:click="kakaoPhone" style="color:white;">연락처등록</v-btn>
				</v-col>
				<v-col xl="1" md="1" sm="1" cols="1" align-self="center">
					<v-btn width="100%" color="primary" v-on:click="getKakaoErrorList">검색</v-btn>
				</v-col>
			</v-row>
			<v-row style="padding-left: 15px;padding-right: 15px;">
				<v-col xl="6" md="6" sm="6" cols="6">
					<v-data-table  class="elevation-1" :footer-props="{ 'items-per-page-text': '페이지 당 행',  pageText: '총 {2} 항목 중 {0}-{1}'}" 
						@click:row="ka_onClickRow" @dblclick:row="ka_ondbClickRow" :headers="ka_headers" :items="ka_document" @pagination="updatePage" :itemsPerPage="list_page" :loading="loading" :item-class="select_code_color" key="clm_code_id" :row-props="select_code_color" >
						<template slot="item" slot-scope="props">
							<tr @dblclick="ka_ondbClickRow('', props)" @click="ka_onClickRow(props.item, props.item.num_index)"  :item-class="select_code_color">
								<td>{{ props.item.clm_code_name }}</td>
								<td>{{ props.item.clm_error_gubun_name }}</td>
								<td>{{ props.item.clm_code_text }}</td>
								<!-- <td>{{ props.item.clm_alarm_yn }}</td> -->
								<td style="align-self: center; text-align:center;"><div class="panel-body">  
									<label class="switch">
										<input type="checkbox" v-model="props.item.clm_toggle_yn" @click="saveToggle(props.item.clm_code_id, props.item.clm_toggle_yn)">
										<div class="slider round"></div>
										<p style="width:150px; padding-left:55px; margin-bottom: 0px; font-size:15px; font-weight:bold;"></p>
									</label>
								</div></td>
							</tr>
						</template>
					</v-data-table>
				</v-col>
				<v-col xl="6" md="6" sm="6" cols="6">
					<v-data-table class="elevation-1" :footer-props="{ 'items-per-page-text': '페이지 당 행',  pageText: '총 {2} 항목 중 {0}-{1}'}" 
						@click:row="phone_onClickRow" :headers="phone_headers" :items="phone_document" @pagination="updatePage" :itemsPerPage="list_page" :loading="loading2" >
						<template slot="items" slot-scope="props">
							<td>{{ props.item.num }}</td>
						</template>
					</v-data-table>
				</v-col>
			</v-row>
			<v-row justify="center">
				<v-dialog v-model="ErrorRegmodal" persistent max-width="850">
					<v-card>
					<v-toolbar class="fontDefault" color="light-blue" dark>에러 코드 등록</v-toolbar>
					<v-card-text style="padding-bottom:0px;">
						<v-row style="padding-top:25px;">
							<v-col xl="12" md="12" sm="12" cols="12"  align-self="center"  >
								<span style="color:red; float: left;">*</span><v-text-field label="에러 코드명" v-model="clm_m_error_code_name" />
							</v-col>
						</v-row>
						<v-row >
							<v-col xl="12" md="12" sm="12" cols="12"  align-self="center"  >
								<span style="color:red; float: left;">*</span><v-text-field label="에러 코드 설명" v-model="clm_m_error_code_text" />
							</v-col>
						</v-row>
						<v-row>
							<v-col xl="3" md="3" sm="3" cols="3" align-self="center">
								<span style="color:red; float: left;">*</span><v-select 
									v-model="clm_error_gubun" 
									:items="clm_error_gubun_items" 
									item-text="text"
									item-value="value"
									class="testselect"
									open-on-clear 
									persistent-hint
									label="에러 코드 구분"> 
								</v-select>
							</v-col>
							<v-col xl="9" md="9" sm="9" cols="9"  align-self="center"  >
								<span style="color:red; float: left;">*</span><v-text-field label="체크 내용" v-model="clm_m_error_code_check" />
							</v-col>
						</v-row>
						<template v-if="this.clm_error_gubun == 'Q'">
							<v-row>
								<v-col xl="4" md="4" sm="4" cols="4"  align-self="center"  >
									<span style="color:red; float: left;">*</span><v-text-field label="DB Host" v-model="clm_m_error_code_url" />
								</v-col>
								<v-col xl="4" md="4" sm="4" cols="4"  align-self="center"  >
									<span style="color:red; float: left;">*</span><v-text-field label="DB명" v-model="clm_m_error_code_dbName" />
								</v-col>
								<v-col xl="4" md="4" sm="4" cols="4"  align-self="center"  >
									<span style="color:red; float: left;">*</span><v-text-field label="PORT" v-model="clm_m_error_code_port" />
								</v-col>
							</v-row>
							<v-row>
								<v-col xl="6" md="6" sm="6" cols="6"  align-self="center"  >
									<span style="color:red; float: left;">*</span><v-text-field label="DB User" v-model="clm_m_error_code_dbId" />
								</v-col>
								<v-col xl="6" md="6" sm="6" cols="6"  align-self="center"  >
									<span style="color:red; float: left;">*</span><v-text-field label="DB PW" v-model="clm_m_error_code_dbPw" />
								</v-col>
							</v-row>
						</template>
						<v-row>
							<v-col xl="4" md="4" sm="4" cols="4" align-self="center">
								<span style="color:red; float: left;">*</span><v-select 
									v-model="clm_alarm_yn" 
									:items="clm_alarm_use_items" 
									item-text="text"
									item-value="value"
									class="testselect"
									open-on-clear 
									persistent-hint
									label="알람 사용여부"> 
								</v-select>
							</v-col>
							<v-col xl="4" md="4" sm="4" cols="4" align-self="center"  >
								<span style="color:red; float: left;">*</span><v-select 
									v-model="clm_alarm_daily_count" 
									:items="clm_alarm_count_items" 
									item-text="text"
									item-value="value"
									class="testselect"
									open-on-clear 
									persistent-hint
									label="알림 일일 횟수"> 
								</v-select>
							</v-col>
							<v-col xl="4" md="4" sm="4" cols="4" align-self="center"  >
								<span style="color:red; float: left;">*</span><v-select 
									v-model="clm_alarm_cycle" 
									:items="clm_alarm_cycle_items" 
									item-text="text"
									item-value="value"
									class="testselect"
									open-on-clear 
									persistent-hint
									label="알람 주기"> 
								</v-select>
							</v-col>
						</v-row>
					</v-card-text>
					<v-card-actions class="justify-end">
						<template v-if="this.$router.app.$route.query.update_yn=='Y'">
							<template v-if="this.clm_m_error_code_id == ''">
							<v-btn text @click="ErrorSave" >등록</v-btn>
							</template>
							<template v-else-if="this.clm_m_error_code_id != ''">
							<v-btn text @click="ErrorDelete" >삭제</v-btn>
							<v-btn text @click="ErrorSave" >수정</v-btn>
							</template>
						</template>
						<v-btn text @click="ErrorRegmodal = !ErrorRegmodal">닫기</v-btn>
					</v-card-actions>
					</v-card>
				</v-dialog>
			</v-row>

			<v-row justify="center">
				<v-dialog  v-model="PhoneModal" persistent max-width="850">
					<v-card>
						<template v-if="this.clm_kep_id =='' ">
							<v-toolbar class="fontDefault" color="light-blue" dark>연락처 등록</v-toolbar>
						</template>
						<template v-else>
							<v-toolbar class="fontDefault" color="light-blue" dark>연락처 수정</v-toolbar>
						</template>
					<v-card-text style="padding-bottom:0px;">
						<v-row >
							<v-col xl="4" md="4" sm="4" cols="4" align-self="center" style="padding-bottom:0px;margin-bottom:10px;padding-top:32px;" >
								<span style="color:red; float: left; ">*</span><v-text-field label="에러 코드 이름" readonly v-model="clm_m_error_name" @click="errorModalOpen" />
							</v-col>
							<v-col xl="4" md="4" sm="4" cols="4" align-self="center" style="padding-bottom:0px;margin-bottom:10px;padding-top:32px;" >
								<span style="color:red; float: left; ">*</span><v-text-field label="이름" v-model="clm_m_user_name" />
							</v-col>
							<v-col xl="4" md="4" sm="4" cols="4" align-self="center" style="padding-bottom:0px;margin-bottom:10px;padding-top:32px;" >
								<span style="color:red; float: left; ">*</span><v-text-field label="연락처" v-model="clm_m_phone_number" />
							</v-col>
						</v-row>
					</v-card-text>
					<v-card-actions class="justify-end">
							<template v-if="this.clm_kep_id =='' ">
								<v-btn text @click="PhoneSave" >등록</v-btn>
							</template>
							<template v-else>
								<v-btn text @click="PhoneDelete" >삭제</v-btn>
								<v-btn text @click="PhoneSave" >수정</v-btn>
							</template>
						<v-btn text @click="PhoneModal = !PhoneModal">닫기</v-btn>
					</v-card-actions>
					</v-card>
				</v-dialog>
			</v-row>
			<v-row justify="center">
				<v-dialog v-model="ErrorListmodal" persistent max-width="1250" @keydown.esc="ErrorListmodal= false">
					<v-card>
					<v-toolbar class="fontDefault" color="light-blue" dark>에러코드</v-toolbar>
					<v-card-text style="padding-top:15px;">
						<v-row>
							<v-col xl="10" md="10" sm="10" cols="10" align-self="center">
								<v-text-field label="검색" v-model="clm_m_s_error_name" @keypress.enter.prevent="getErrorModalList" />
							</v-col>
							<v-col xl="2" md="2" sm="2" cols="2" align-self="center">
								<v-btn width="100%" color="primary" v-on:click="getErrorModalList">검색</v-btn>
							</v-col>
						</v-row>
						<v-row style="padding-left:17px;">
							<v-data-table no-data-text="조회된 에러코드가 없습니다."  :footer-props="{ 'items-per-page-text': '페이지 당 행',  pageText: '총 {2} 항목 중 {0}-{1}'}"  mobile-breakpoint="0" class="elevation-1 clienttable" :headers="modal_headers" :items="m_document" @click:row="onClickErrorModalRow" :itemsPerPage="5" :loading="loading" style="width:98%;" >
								<template slot="items" slot-scope="props">
									<td>{{ props.item.num }}</td>
								</template>
							</v-data-table>
						</v-row>
					</v-card-text>
					<v-card-actions class="justify-end">
						<v-btn text @click="ErrorListmodal = !ErrorListmodal">닫기</v-btn>
					</v-card-actions>
					</v-card>
				</v-dialog>
			</v-row>
        </div>
    </v-layout>
</template>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
import axios from 'axios';
import Swal from 'sweetalert2';


export default {
	data() {
		return {
			user_agent: String(navigator.userAgent).toLowerCase(),
			user_id: this.$session.get('user_id'),
			user_name: this.$session.get('user_name'),
			MessageModal: false,
			bell_gubun: '',
			bell_items: [],
			bell_index: '0',
			msg_id: '',
			msg_sender_name: '',
			msg_sender_datetime: '',
			msg_title: '',
			msg_text: '',
			nowTime: '',
			keyword: '',
			start_date: '',
			end_date: '',
			/* Grid 변수 */
			loading: false,
			loading2:false,
			
			ka_headers: [
				{ text: '에러 코드 이름', align: 'start',  value: 'clm_code_name' , width: 120 },
				{ text: '에러 코드 구분', align: 'start', value: 'clm_error_gubun_name' , width: 100 },	
				{ text: '에러 코드 내용', align: 'start', value: 'clm_code_text' , width: 150 },	
				{ text: '알림 사용여부', align: 'start', value: 'clm_alarm_yn' , width: 90 },	
			],
			phone_headers: [
				{ text: '이름', align: 'center',  value: 'clm_user_name' , width: 100 },
				{ text: '연락처', align: 'center',  value: 'clm_user_phone' , width: 100 },
			],
			modal_headers: [
				{ text: '에러 코드 이름', align: 'center',  value: 'clm_code_name' , width: 100 },
				{ text: '에러 코드 내용', align: 'center', value: 'clm_code_text' , width: 100 },
			],
			document: [],
			ka_document: [],
			phone_document: [],
			m_document: [],
			options: {
				multiSort: false,
				sortBy: [],
				sortDesc: [],
				page: 1,
				itemsPerPage: 5,
			},
			list_page:10,
			save_yn:'N',
			ErrorRegmodal: false,
			ErrorListmodal: false,
			PhoneModal: false,
			
			// kakaoError
			clm_m_error_code_id : '',
			clm_m_error_code_name : '',
			clm_m_error_code_text : '',
			clm_m_error_code_url : '',
			clm_m_error_code_dbName : '',
			clm_m_error_code_port : '',
			clm_m_error_code_dbId : '',
			clm_m_error_code_dbPw : '',
			clm_m_error_code_check: '',


			// kakaoPhone
			code_id: '',
			kakaoVal: '',
			clm_kep_id : '',
			clm_m_code_id : '',
			clm_m_s_error_name : '',
			clm_m_error_name : '',
			clm_m_user_name : '',
			clm_m_phone_number :  '',

			clm_error_gubun_items: [
				{ text:"쿼리", value:"Q" },
				{ text:"웹사이트", value:"W" },
				{ text:"프로그램", value:"P" },
			],
			clm_error_gubun: '',

			// 에러코드 알람
			clm_alarm_yn: 'Y',
			clm_alarm_use_items: [
				{ text:"사용", value:"Y" },
				{ text:"미사용", value:"N" },
			],

			
			//알림 횟수
			clm_alarm_daily_count: '9999',
			clm_alarm_count_items:[
				{text: '1회', value: '1'},
				{text: '3회', value: '3'},
				{text: '5회', value: '5'},
				{text: '10회', value: '10'},
				{text: '무제한', value: '9999'},
			],
			//알림 주기
			clm_alarm_cycle: '3 hours',
			clm_alarm_cycle_items:[
				{text: '30분', value: '30 minutes'},
				{text: '1시간', value: '1 hour'},
				{text: '3시간', value: '3 hours'},
				{text: '6시간', value: '6 hours'},
				{text: '12시간', value: '12 hours'},
			],

			// 토글
			Toggle_Save: true,
			clm_toggle_yn: '',
		}
	},
	components: {
	},
	beforeCreate: function () {
	},
	mounted() {
		
		this.save_yn='N';
		this.getKakaoErrorList();
	},
	watch: {
		
	},
	methods: {
		fnc_router_go(location){
			this.$router.push(location);
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
					this.$session.set('user_sabun', response.data.clm_user_sabun);
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
		LogOut() {
			Swal.fire({
				title:"로그아웃 하시겠습니까?",
				text:  '',
				icon: 'question',
				showCancelButton: true,
				allowOutsideClick: false,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: '예',
				cancelButtonText: '아니요'
			}).then((result) => {
				if (result.isConfirmed) {
					this.$session.destroy();
					this.$router.push('mob001000');
				}
			})
		},
		updatePage(page){
			this.list_page=Number(page.itemsPerPage);
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
		
		ErrorSave() {	
			var url = this.clm_m_error_code_url	;
			var name = this.clm_m_error_code_dbName;
			var port = this.clm_m_error_code_port;
			var id = this.clm_m_error_code_dbId;
			var pw = this.clm_m_error_code_dbPw;

			if(this.loading_yn == 'Y'){
				Swal.fire({
					title: '저장중입니다. 잠시만 기다려주세요.',
					icon: 'error'
				});
				return;
			}

			this.loading_yn = 'Y';


			if(this.clm_m_error_code_name == ''){
				Swal.fire({
					title: '에러 코드명을 입력해주시기 바랍니다.',
					icon: 'error'
				});
				this.loading_yn='N';
				return;
			}else if(this.clm_m_error_code_text == ''){
				Swal.fire({
					title: '에러 코드 설명을 입력해주시기 바랍니다.',
					icon: 'error'
				});
				this.loading_yn='N';
				return;
			}else if (this.clm_error_gubun == '') {
				Swal.fire({
					title: '에러 코드 구분을 선택해주시기 바랍니다.',
					icon: 'error',
				});
				this.loading_yn = 'N';
				return;
			} else if (this.clm_m_error_code_check == '') {
				Swal.fire({
					title: '체크 내용을 입력해주시기 바랍니다.',
					icon: 'error',
				});
				this.loading_yn = 'N';
				return;
			} 

			var db_info = '';
			if(this.clm_error_gubun == 'Q'){
				if(this.clm_m_error_code_url == ''){
					Swal.fire({
						title: 'DB Host를 입력해주시기 바랍니다.',
						icon: 'error'
					});
					this.loading_yn='N';
					return;
				}else if(this.clm_m_error_code_dbName == ''){
					Swal.fire({
						title: 'DB명을 입력해주시기 바랍니다.',
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
						title: 'URL,  DB명, PORT, DB User, DB PW에는 쉼표가 들어갈 수 없습니다.',
						icon: 'error'
					});
					this.loading_yn='N';
					return;
					}
				}

				db_info = this.clm_m_error_code_url+","+this.clm_m_error_code_dbName+","+this.clm_m_error_code_port+","+this.clm_m_error_code_dbId+","+this.clm_m_error_code_dbPw;
			}
			
			axios.post('/back/POSTGRES_MES/ErrorSave', null, { params: {
				clm_error_code_id: this.clm_m_error_code_id,
				clm_error_code_name: this.clm_m_error_code_name,
				clm_error_code_text: this.clm_m_error_code_text,
				clm_code_db_info: db_info,
				clm_error_gubun: this.clm_error_gubun,
				clm_error_code_check: this.clm_m_error_code_check,
				clm_alarm_yn: this.clm_alarm_yn,
				clm_alarm_cycle: this.clm_alarm_cycle,
				clm_alarm_daily_count: this.clm_alarm_daily_count,
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
				title: this.clm_m_error_code_name +' 에러코드를 정말 삭제하시겠습니까?',
				text: '',
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: '삭제',
				cancelButtonText: '취소',
				width:650
			}).then((result) => {
				if (result.value) {
					axios.post('/back/POSTGRES_MES/ErrorDelete', null, { params: {
							clm_code_id: this.clm_m_error_code_id
							}})
							.then(response => {
							if (response.status == '200') {
								this.loading_yn='N';
								Swal.fire({
									title: '정상적으로 삭제되었습니다.',
									icon: 'success'
								});
								this.ErrorRegmodal= false;
								this.getKakaoErrorList();
								this.clm_screen_id = '3';
							}else{
								this.loading_yn='N';
								Swal.fire({
									title: '삭제에 실패했습니다.',
									icon: 'error'
								});
							}
						})
					}
				})
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
			this.clm_error_gubun = data.item.clm_error_gubun,
			this.clm_alarm_yn = data.item.clm_alarm_yn,
			this.clm_alarm_cycle = data.item.clm_alarm_cycle,
			this.clm_alarm_daily_count = data.item.clm_alarm_daily_count,
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

			if(this.clm_error_gubun == 'Q') {
				this.clm_m_error_code_check= data.item.clm_code_query_check
			}else if(this.clm_error_gubun == 'W') {
				this.clm_m_error_code_check= data.item.clm_code_website_check
			}else {
				this.clm_m_error_code_check= data.item.clm_method_program_check
			}

			this.ErrorRegmodal = true;
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
					title: '에러 코드를 선택해주시기 바랍니다.',
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

						if(this.clm_m_code_id != '') {
							let items = axios.get('/back/POSTGRES_MES/kakaoPhone',{
								params: {
									schVal: this.kakaoVal,
									clm_code_id: this.clm_m_code_id,
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
						}


					}else{
						this.loading_yn='N';
						Swal.fire({
							title: '등록에 실패했습니다.',
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
				title: this.clm_m_user_name + ' 연락처를 정말 삭제하시겠습니까?',
				text: '',
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: '삭제',
				cancelButtonText: '취소',
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

							if(this.clm_m_code_id != '') {
								let items = axios.get('/back/POSTGRES_MES/kakaoPhone',{
									params: {
										schVal: this.kakaoVal,
										clm_code_id: this.clm_m_code_id,
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
							}
						}else{
							this.loading_yn='N';
							Swal.fire({
								title: '삭제에 실패했습니다.',
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

		kakaoError(){
			
			this.clm_alarm_yn = 'Y';
			this.clm_alarm_cycle = '3 hours';
			this.clm_alarm_daily_count = '9999';
			this.clm_error_gubun = '';
			this.clm_m_error_code_id = '';
			this.clm_m_error_code_name = '';
			this.clm_m_error_code_text = '';
			this.clm_m_error_code_url = '';
			this.clm_m_error_code_dbName = '';
			this.clm_m_error_code_port = '';
			this.clm_m_error_code_dbId = '';
			this.clm_m_error_code_dbPw = '';
			this.clm_m_error_code_check= '';
			this.clm_m_error_code_check = '';
			this.clm_m_error_code_check = '';
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
		onClickErrorModalRow(event, data){
			this.clm_m_code_id = data.item.clm_code_id;
			this.clm_m_error_name = data.item.clm_code_name;
			this.ErrorListmodal = false;
		},
		
		saveToggle(value, value2) {
			value2 = !value2
			this.$emit('setCheckboxVal', value2)
			
			var alarm_yn = 'N';
			if(value2 == true) {
				alarm_yn = 'Y';
			}
			console.log('value2: ' + value2 + ', alarm_yn: ' +alarm_yn);
			
			axios.post('/back/POSTGRES_MES/UpdateAlarmYn', null, { params: {
				clm_code_id: value,
				clm_alarm_yn: alarm_yn,
			}})
			.then(response => {
				if (response.status == '200') {					
					const Toast = Swal.mixin({
						toast: true,
						position: 'center-center',
						showConfirmButton: false,
						timer: 1500,
						timerProgressBar: true,
						didOpen: (toast) => {
							toast.addEventListener('mouseenter', Swal.stopTimer)
							toast.addEventListener('mouseleave', Swal.resumeTimer)
						}
					})
					if(alarm_yn == 'Y') {
						Toast.fire({
							icon: 'success',
							width:450,
							title: '알림 사용을 시작합니다.'
						})
					}else {
						Toast.fire({
							icon: 'success',
							width:450,
							title: '알림 사용을 중지합니다.'
						})
					}
					
				}
			})
			.catch(error => {
				console.log(error)
			})
		},

		select_code_color: function (item) {
			console.log('check')
			return (item.clm_code_id == this.clm_m_code_id) ? 'style-red color-white' : 'style-white color-black'
		},
	}
}
</script>