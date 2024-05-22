<style>
@keyframes app_bell-effect {
  50% {
    opacity: 0;
  }
}
.app_bell {
  animation: app_bell-effect 1s step-end infinite;
}
.modal_message_close{
	position: relative;
	left:82%;
	font-size:180%;
}
</style>
<style scoped>
	.messagetable >>> .v-data-table-header th{
		text-align: center !important;
	}
	
	.messagetable >>> tbody tr :nth-child(3) {
		padding-left: 50px;
	}
	.messagetable >>> tbody tr :nth-child(4) {
		padding-left: 40px;
	}
</style>
<template>
	<div id="app">
		<v-app id="inspire" v-if="this.web!='Y'">
			<v-app id="inspire">
				<v-navigation-drawer v-if="!isChecked" v-model="drawer" :clipped="$vuetify.breakpoint.lgAndUp" app>
					<v-list dense>
						<template v-if="this.$session.get('user_name') == null">
							<v-subheader class="fontDefault">&nbsp;미 로그인 상태</v-subheader>
							<v-divider></v-divider>
						</template>
						<template v-else>
							<v-subheader class="fontDefault"><v-icon>mdi-account-circle</v-icon>&nbsp;{{this.$session.get('user_name')}} 님 로그인</v-subheader>
							<v-divider></v-divider>
						</template>
					</v-list>
					<template v-if="this.$session.get('user_name') != null">
						<v-list v-for="item in items" :key="item.title" class="py-0">
							<div v-if="!item.items">
								<v-list-item :to="item.route" active-class="light-blue black--text">
									<v-list-item-title class="fontBold">{{item.title}}</v-list-item-title>
								</v-list-item>
							</div>

							<v-list-group v-else no-action>
								<template v-slot:activator>
									<v-list-item-title class="fontBold">{{item.title}}</v-list-item-title>
								</template>

								<div v-for="item in item.items" :key="item.title">
									<v-list-group v-if="item.items" no-action sub-group>
										<template v-slot:activator>
											<v-list-item-content>
												<v-list-item-title class="fontDefault">&nbsp;&nbsp;&nbsp;▶ {{item.title}}</v-list-item-title>
											</v-list-item-content>
										</template>
		
										<v-list-item v-for="(item, i) in item.items" :key="i" :to="item.route" active-class="light-blue black--text">
											<v-list-item-title class="fontDefault">&nbsp;&nbsp;&nbsp;▶ {{item.title}}</v-list-item-title>
										</v-list-item>
									</v-list-group>
		
									<v-list-item v-else :to="item.route" active-class="light-blue black--text">
										<v-list-item-title class="fontDefault">&nbsp;&nbsp;&nbsp;▶ {{item.title}}</v-list-item-title>
									</v-list-item>
								</div>
							</v-list-group>
						</v-list>
					</template>
				</v-navigation-drawer>
				<v-app-bar app color="light-blue" dark class="fontDefault" :clipped-left="$vuetify.breakpoint.lgAndUp">
					<v-app-bar-nav-icon v-on:click="MainDraw"></v-app-bar-nav-icon>
					<v-toolbar-title  v-on:click="goMainURL" class="fontDefault ml-2 pl-4" style="cursor:pointer;">
						아이티팩토리 MONITORING SYSTEM
					</v-toolbar-title>
					<v-spacer></v-spacer>
					<div>
					<v-btn v-if="this.$session.get('user_name') != null" icon>
						<div v-if="this.app_bell_gubun == 'mdi-bell-ring'" id="app_bell_div" class="app_bell"><v-icon @click="fnc_bell_click" >{{ app_bell_gubun }}</v-icon></div>
						<div v-else id="app_bell_div" class=""><v-icon @click="fnc_bell_click" >{{ app_bell_gubun }}</v-icon></div>
					</v-btn>
					</div>
					
					<v-btn v-if="this.$session.get('user_name') != null" icon>
						<v-icon @click="showModal" >mdi-logout</v-icon>
					</v-btn>
					<v-btn v-else icon>
						<v-icon @click="fncLogin" >mdi-login</v-icon>
					</v-btn>
				</v-app-bar>
				<v-main>
					<v-container fluid>
						<router-view></router-view>
					</v-container>
				</v-main>
				<!-- <v-dialog v-model="logoutModal" max-width="300">
					<v-card>
					<v-card-text style="color: black; padding-top:15px;padding-bottom:5px;">
						로그아웃 하시겠습니까?
					</v-card-text>
					<v-card-actions class="justify-center">
						<v-btn text @click="LogOut">예</v-btn>
						<v-btn text @click="CloseclientModal">아니요</v-btn>
					</v-card-actions>
					</v-card>
				</v-dialog> -->
				<v-dialog v-model="MessageModal" persistent max-width="700" @keydown.esc="MessageModal=false">
					<v-card>
						<v-toolbar class="fontDefault" color="light-blue" dark>메시지 확인
						</v-toolbar>
						<v-card-text style="padding-top:15px; padding-bottom:0px;">
							<div style="line-height: 1; font-weight:bold; font-size:15px;">
								<span style="float:left;">{{ app_msg_sender_name }}</span>
								<span style="float:right;">{{ app_msg_sender_datetime }}</span>
							</div>
							<div style="padding-top:30px; line-height: 1; font-weight:bold; font-size:30px;">{{ app_msg_title }}</div>
							<div style="padding-top:20px; font-size:25px; white-space:pre-wrap;">{{ app_msg_text }}</div>	
						</v-card-text>
						<v-card-actions class="justify-end">
							<v-btn text @click="fnc_Message_Confirm()">확인</v-btn>
						</v-card-actions>
					</v-card>
				</v-dialog>
				<v-dialog v-model="MessageLogModal" persistent max-width="1250" @keydown.esc="MessageLogModal=false">
					<v-card>
						<v-toolbar class="fontDefault" color="light-blue" dark>{{this.$session.get('user_name')}} 님 메시지 목록
						</v-toolbar>
						<v-card-text style="padding-top:15px; padding-bottom:0px;">
							<v-row>
								<v-col xl="10" md="10" sm="10" cols="10" align-self="center">
									<v-text-field label="검색" v-model="schVal" @keypress.enter.prevent="getMesaageList" />
								</v-col>
								<v-col xl="2" md="2" sm="2" cols="2" align-self="center">
									<v-btn width="100%" color="primary" v-on:click="getMesaageList">검색</v-btn>
								</v-col>
							</v-row>
							<v-row style="padding-left:17px;">
								<v-data-table no-data-text="조회된 메시지가 없습니다." mobile-breakpoint="0" class="elevation-1 messagetable" :headers="m_headers" item-key="clm_product_id" :items="m_document" :itemsPerPage="5" style="width:99%;" @click:row="onClickMessageRow">
									<template slot="items" slot-scope="props">
										<td>{{ props.item.num }}</td>
										<td>{{ props.item.clm_product_id }}</td>
										<td>{{ props.item.clm_product_name }}</td>
										<td>{{ props.item.clm_width }}</td>
										<td>{{ props.item.clm_height }}</td>
										<td>{{ props.item.clm_thickness }}</td>
										<td>{{ props.item.clm_client_id }}</td>
										<td>{{ props.item.clm_client_name }}</td>
									</template>
								</v-data-table>
							</v-row>
						</v-card-text>
						<v-card-actions class="justify-end">
							<v-btn style="padding-top:15px;" text @click="MessageLogModal=false">닫기</v-btn>
						</v-card-actions>
					</v-card>
				</v-dialog>
			</v-app>
		</v-app>
		<v-app v-else>
			<router-view></router-view>
		</v-app>
	</div>
</template>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
import AppHeader from './components/common/AppHeader';
import HelloWorld from './components/HelloWorld';
import axios from 'axios';
import Swal from 'sweetalert2'
var menu_json = [];
export default {
	name: 'App',
	components: {
		AppHeader,
		HelloWorld
	},
	data() {
		return {
			logoutModal:false,
			drawer: true,
			items: menu_json,
			app_bell_gubun: '',
			app_bell_items: [],
			user_id: this.$session.get('user_id'),
			MessageModal: false,
			app_bell_index: '0',
			app_msg_id: '',
			app_msg_sender_name: '',
			app_msg_sender_datetime: '',
			app_msg_title: '',
			app_msg_text: '',
			web: 'Y',
			mob_yn: 'N',
			/* 메시지목록 모달 */
			schVal:'',
			m_document: [],
			m_headers: [
				{ text: '순번', align: ' d-none', value: 'num', width: 85 },
				{ text: 'ID', align: ' d-none', value: 'clm_message_id' },
				{ text: '제목', align: 'start', value: 'clm_message_title', width: 180 },
				{ text: '내용', align: 'start', value: 'clm_message_text', width: 350 },
				{ text: '발신자', align: 'center', value: 'clm_sender_user_name', width: 150 },
				{ text: '보낸 일시', align: 'center', value: 'clm_sender_datetime', width: 150 },
				{ text: '보내는사람 ID', align: ' d-none', value: 'clm_sender_user_id' },
				{ text: '받는사람 ID', align: ' d-none', value: 'clm_receive_user_id' },
				{ text: '받는사람', align: ' d-none', value: 'clm_receive_user_name', width: 150 },
				{ text: '읽은일시', align: ' d-none', value: 'clm_receive_confirm_datetime', width: 150 },
				{ text: '읽음여부', align: ' d-none', value: 'clm_receive_confirm_yn'}
			],
			MessageLogModal:false,
			isChecked: true,
		}
	},
	beforeCreate: function () {
		this.web='Y';
	},	
	created() {
		if(this.$isMobile()){
			this.drawer = false;
			if(this.$route.path === '/'){
				this.$router.push('/');

				// this.$router.push('scr001001List');
			}
		}
		if (this.$session.exists()) {
			axios.get('/back/Common/MenuList',{
			params: {
				authority_id: this.$session.get('user_authority')
				}
			})
			.then(response => {
				var lead_end_menu = '';
				var total_menu_screen_cnt = '';
				var menu_sub_array = [];
				for(var row=0; row<response.data.length; row++){
					total_menu_screen_cnt = response.data[row].total_menu_screen_cnt;
					//lead_end_menu = response.data[row].lead_end_menu_script;
					lead_end_menu = response.data[row].end_menu_script;
					if(total_menu_screen_cnt == '1'){
						// console.log('response.data[row].clm_screen_name : ' + response.data[row].clm_screen_name)
						// console.log('response.data[row].clm_screen_id : ' + response.data[row].clm_screen_id)
						menu_json.push({ icon: '', title: response.data[row].clm_screen_name, route: response.data[row].clm_screen_id });
					}else{
						menu_sub_array.push(
						{ icon:'', title: response.data[row].clm_screen_name, route: response.data[row].title }
						);

						if(lead_end_menu != ''){
						menu_json.push({ icon: 'mdi-home', title: response.data[row].clm_menu_name, items: menu_sub_array });
						menu_sub_array = [];
						}
					}
				}
				console.log(menu_json);
			});
		}
	},
	mounted() {	
		if (!this.$session.exists()) {
			this.$router.push('/')
		}
	},
	created(){
		if(document.location.pathname === '/Main'){
			this.isChecked = true;
			this.isChecked =false;
		}
	},
	methods: {
		fnc_Message_Confirm(){
			if(typeof this.app_bell_items[Number(this.app_bell_index)] == 'undefined'){
				if(this.MessageModal == true){
					this.MessageModal = false
				}
				this.app_bell_index = '0'
				this.app_bell_gubun = 'mdi-bell'
			}else{
				if(this.MessageModal == false){
					this.MessageModal = true
				}
				this.app_msg_id = this.app_bell_items[Number(this.app_bell_index)].clm_message_id
				this.app_msg_sender_name = this.app_bell_items[Number(this.app_bell_index)].clm_sender_user_name
				this.app_msg_sender_datetime = this.app_bell_items[Number(this.app_bell_index)].clm_sender_datetime
				this.app_msg_title = this.app_bell_items[Number(this.app_bell_index)].clm_message_title
				this.app_msg_text = this.app_bell_items[Number(this.app_bell_index)].clm_message_text
				axios.post('/back/Common/MSGConfirm', null, { params: {
					msg_id:this.app_msg_id
				}})
				.then(response => {
					console.log(response.data)
				});
				this.app_bell_index = String(Number(this.app_bell_index)+1)
			}
		},
		fnc_bell_click(){
			if(this.app_bell_items.length > 0){
				this.fnc_Message_Confirm();
			}else{
				let items = axios.get('/back/SCR003011/List',{
					params: {
						search: encodeURI(this.schVal),
						search1:'',
						search2:'',
						search3:'',
						search4:this.$session.get('user_id'),
					}
					}).then(
					response => {
						items = response.data			
						console.log(items);		
						setTimeout(() => {
							this.m_document = items
						}, 500)
					},
				)
				this.MessageLogModal=true;
				//alert('메시지가 없습니다.');
			}
		},
		getMesaageList(){
			let items = axios.get('/back/SCR003011/List',{
				params: {
					search: encodeURI(this.schVal),
					search1:'',
					search2:'',
					search3:'',
					search4:this.$session.get('user_id'),
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
		onClickMessageRow(event, data){
			if(this.MessageModal == false){
				this.MessageModal = true
			}
			this.app_msg_id = data.item.clm_message_id;
			this.app_msg_sender_name = data.item.clm_sender_user_name;
			this.app_msg_sender_datetime = data.item.clm_sender_datetime;
			this.app_msg_title = data.item.clm_original_message_title;
			this.app_msg_text = data.item.clm_original_message_text;
		},
		goMainURL(){
			this.$router.push('/');
			// this.$router.push('scr001001List');
		},
		MainDraw(){
			if(this.$isMobile()){
				this.drawer = !this.drawer;
			}else{
				this.drawer = !this.drawer;
			}
		},
		fncLogin(){
			this.$router.push('/');
		},
		showModal(){
			this.logoutModal = true;
		},
		LogOut(){
			this.logoutModal = false;
			this.$session.destroy();
			if(this.$route.path !== '/'){
				this.$router.push('/');
				
				// this.$router.push('scr001001List');
			}else{
				location.href=location;
			}
		},
		CloseclientModal() {
			this.logoutModal = false;
		},
	}
};
</script>
<style>
	@import './assets/Default.css';
</style>