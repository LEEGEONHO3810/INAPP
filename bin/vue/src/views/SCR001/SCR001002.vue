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
<template style="height: 100%;">
	<v-layout style="width:100%;height: 100%;">
		<div class="menu_div" >
			<div @click="$router.push('/')" style="cursor:pointer;width:220px; font-size:26px; font-weight:bold;color:white;" class="fontDefault"><img class="logo" src="../../assets/it_ci.png"></img></div>
			<div style="height:40px;"></div>
			<div><v-btn width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('/')" v-on:click="fnc_router_go('/')">URL 정리</v-btn></div>
			<div width="220px" style="height:25px;"></div>
			<div><v-btn style="color:red !important;" width="220px" class="buttonMenu">메모장</v-btn></div>
			<div width="220px" style="height:25px;"></div>
			<div><v-btn style="" width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('scr001003')" v-on:click="fnc_router_go('scr001003')">에러코드</v-btn></div>
			<div width="220px" style="height:25px;"></div>
			<div><v-btn style="" width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('scr001004')" v-on:click="fnc_router_go('scr001004')">캘린더</v-btn></div>
			<div width="220px" style="height:25px;"></div>
		</div>
		<div style="margin-bottom:20px; width: calc(100% - 220px);position: absolute;top:0; right: 0;">
			<v-row style="margin:2px 0 0 0; padding: 0 15px 0 15px !important;" justify="center">
				<v-col xl="10" md="10" sm="10" cols="10" align-self="center" style="padding-bottom: 0px;">
					<v-text-field label="검색" v-model="accountVal" @keypress.enter.prevent="getList2" />
				</v-col>
				<v-col xl="1" md="1" sm="1" cols="1" align-self="center" style="padding-bottom: 0px;">
					<v-btn width="100%" color="primary" v-on:click="getList2">검색</v-btn>
				</v-col>
				<v-col xl="1" md="1" sm="1" cols="1" align-self="center" style="padding-bottom: 0px;">
					<v-btn width="100%" color="green" style="color:white;" v-on:click="addList">등록</v-btn>
				</v-col>
			</v-row>
			<v-row style="padding-left: 15px;padding-right: 15px;">
				<v-col xl="3" md="3" sm="3" cols="3" v-for="(accountItem, index) in accountItems" :key="index">
					<div  style="width:100%; border: 2px solid; border-bottom: none; background-color: #0a80e3; display: flex;">
						<div style="width: 70%;" >
							<input style="align-self: center; width: 100%;padding-left:5px;" maxlength="10" v-model="accountItem.clm_title" />
						</div> 
						<div v-if="accountItem.clm_content_seq == '1'" style="float: right; text-align: end;width:27%;">
							<i class="mdi mdi-content-save" style="color: springgreen;" v-on:click="SaveContent(accountItem, index)"></i>
							&nbsp;
							<i class="mdi mdi-delete" style="color: red;" v-on:click="DeleteContent(accountItem, index)"></i>
							&nbsp;
							<i class="mdi mdi-pin" style="color: beige;"  v-on:click="checkOrderSeq(accountItem, index)"></i>
							&nbsp;
							<i class="mdi mdi-content-copy" style="color: black;"  v-on:click="copyText(accountItem, index)"></i>
						</div>
						<div v-else-if="accountItem.clm_content_seq =='0'" style="float: right; text-align: end;width:27%;">
							<i class="mdi mdi-content-save" style="color: springgreen;" v-on:click="SaveContent(accountItem, index)"></i>
							&nbsp;
							<i class="mdi mdi-delete" style="color: red;" v-on:click="DeleteContent(accountItem, index)"></i>
							&nbsp;
							<i class="mdi mdi-pin-off" style="color: beige;"  v-on:click="checkOrderSeq(accountItem, index)"></i>
							&nbsp;
							<i class="mdi mdi-content-copy" style="color: black;"  v-on:click="copyText(accountItem, index)"></i>
						</div>	
					</div>
					<textarea v-model="accountItem.clm_content" rows="10" style="border: 2px solid; resize: none; width:100% ;padding-left:5px;"></textarea>
				</v-col>
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
			document: [],
			selected_items:[],
			options: {
				multiSort: false,
				sortBy: [],
				sortDesc: [],
				page: 1,
				itemsPerPage: 5,
			},
			list_page:10,
			save_yn:'N',
			// account
			accountVal: '',
			clm_title : '',
			clm_content : '',

			accountItems: [{}],
		}
	},
	components: {
	},
	beforeCreate: function () {
		if(this.$session.get('user_name') == null || this.$session.get('user_name') == '' || typeof this.$session.get('user_name') == 'undefined'){
			this.$router.push('/scr001002');
		}
	},
	mounted() {
		this.save_yn='N';
		this.getList2();
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
		addList() {	
			this.accountItems.push({
				clm_content_seq : '0'

			 });
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
	}
}
</script>