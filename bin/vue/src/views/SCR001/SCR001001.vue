<style scoped>

	.v-data-table >>> .v-data-table-header th {
		text-align: center !important;
	}
	.cursortext >>> input{
		cursor:pointer;
	}
    #loading_img{
        position: relative;
        top:22%;
        left:25%;
    }
	.v-data-footer{
		zoom:70%;
	}
	.income_date >>> input{
		text-align: start !important;
		padding-left: 0px;
		padding-right: 0px;
	}
	.daytable {
		width:20%;
		border:1px solid black;
		display:table;
		border-spacing:0;
	}
	.daytable >>> th {
		height:30px;
		font-size:20px;
		font-weight:600;
		font-weight:700;
	}
	.daytable >>> td {
		border-top: 0.5px solid black;
		height:60px;
		font-size:20px;
		font-weight:600;
	}
	.daytable >>> input {
		text-align:end;
	}
	.daytable >>> th {
		background-color:rgb(219, 251, 255) !important;
	}
	.hour24_table {
		width:100%;
		border:1px solid black;
		display:table;
		border-spacing:0;
	}
	.hour24_table >>> td {
		border: 0.5px solid black;
		height:35px;
		font-size:15px;
		font-weight:600;
		text-align:center;
	}
	.hour24_table >>> input {
		width: 100%;
		padding-right:5px;
	}
</style>
<style>
	input:focus {outline: 2px solid #d50000;} /* outline 테두리 속성 수정 */
	input:focus {outline: none;} /* outline 테두리 없애기 */
	input[type="number"]::-webkit-outer-spin-button,
	input[type="number"]::-webkit-inner-spin-button {
		-webkit-appearance: none;
		margin: 0;
	}
	.swal2-title{
		font-size:22px !important;
	}
	.v-data-footer__pagination{
		margin: 0px !important;
	}

</style>
<template>
	<v-layout wrap row>
		<v-flex xs12 sm12 md12 style="margin-bottom:20px;">
			<v-card xs12 sm12 md12>
				<v-card-title xs12 sm12 md12 class="fontDefault">
					<!-- {{ this.$router.app.$route.query.screen_name }} -->
					습도 관리
				</v-card-title>
			</v-card>
		</v-flex>
		<v-flex xs12 sm12 md12>
			<v-card>
				<v-card-text>
					<v-row>
						<v-col xl="2" md="2" sm="2" cols="2" align-self="center" style="padding-top:0px; padding-bottom:0px;">
							<v-select
								v-model="clm_year" 
								:items="clm_year_items" 
								item-text="text"
								item-value="value"
								v-on:change="changeSelectbox"
								open-on-clear
								label="검색 연도">
							</v-select>
						</v-col>
						<v-col xl="2" md="2" sm="2" cols="2" align-self="center" style="padding-top:0px; padding-bottom:0px;">
							<v-select
								v-model="clm_month" 
								:items="clm_month_items"  
								item-text="text"
								item-value="value"
								v-on:change="changeSelectbox"
								open-on-clear
								label="검색 월">
							</v-select>
						</v-col>
					</v-row>
					<v-row>
						<v-col xl="12" md="12" sm="12" cols="12" align-self="center" style="padding-top:0px; padding-bottom:30px;">
							<table class="daytable" style="float:left;">
								<tr>
									<th colspan="2" style="width:50%;">일자</th>
									<th style="width:50%;border-left:0.5px solid black;">습도</th>
								</tr>
								<tr v-for="(DayItem, index) in DayItems"  v-bind:key="index">
									<template v-if="index<7">
										<td style="text-align:end;width:25%;padding-right: 8px;" @click="clickLabel(index)">{{ DayItem.clm_date }}</td>
										<td style="text-align:start;width:25%;padding-left: 8px;" @click="clickLabel(index)">일</td>
										<td style="text-align:center;width:50%;border-left:0.5px solid black;" @mousedown="clickValue(index)">
											<input style="width:80%;" v-model="DayItem.clm_temp_humidity" type="number" @change="changeValue(index)"/>
										</td>
									</template>
								</tr>
							</table>
							<table class="daytable" style="float:left;">
								<tr>
									<th colspan="2" style="width:50%;">일자</th>
									<th style="width:50%;border-left:0.5px solid black;">습도</th>
								</tr>
								<tr v-for="(DayItem, index) in DayItems"  v-bind:key="index">
									<template v-if="index>=7 && index<14">
										<td style="text-align:end;width:25%;padding-right: 8px;" @click="clickLabel(index)">{{ DayItem.clm_date }}</td>
										<td style="text-align:start;width:25%;padding-left: 8px;" @click="clickLabel(index)">일</td>
										<td style="text-align:center;width:50%;border-left:0.5px solid black;" @mousedown="clickValue(index)">
											<input style="width:80%;" v-model="DayItem.clm_temp_humidity" type="number" @change="changeValue(index)"/>
										</td>
									</template>
								</tr>
							</table>
							<table class="daytable" style="float:left;">
								<tr>
									<th colspan="2" style="width:50%;">일자</th>
									<th style="width:50%;border-left:0.5px solid black;">습도</th>
								</tr>
								<tr v-for="(DayItem, index) in DayItems"  v-bind:key="index">
									<template v-if="index>=14 && index<21">
										<td style="text-align:end;width:25%;padding-right: 8px;" @click="clickLabel(index)">{{ DayItem.clm_date }}</td>
										<td style="text-align:start;width:25%;padding-left: 8px;" @click="clickLabel(index)">일</td>
										<td style="text-align:center;width:50%;border-left:0.5px solid black;" @mousedown="clickValue(index)">
											<input style="width:80%;" v-model="DayItem.clm_temp_humidity" type="number" @change="changeValue(index)"/>
										</td>
									</template>
								</tr>
							</table>
							<table class="daytable" style="float:left;">
								<tr>
									<th colspan="2" style="width:50%;">일자</th>
									<th style="width:50%;border-left:0.5px solid black;">습도</th>
								</tr>
								<tr v-for="(DayItem, index) in DayItems"  v-bind:key="index">
									<template v-if="index>=21 && index<28">
										<td style="text-align:end;width:25%;padding-right: 8px;" @click="clickLabel(index)">{{ DayItem.clm_date }}</td>
										<td style="text-align:start;width:25%;padding-left: 8px;" @click="clickLabel(index)">일</td>
										<td style="text-align:center;width:50%;border-left:0.5px solid black;" @mousedown="clickValue(index)" >
											<input style="width:80%;" v-model="DayItem.clm_temp_humidity" type="number" @change="changeValue(index)"/>
										</td>
									</template>
								</tr>
							</table>
							<table class="daytable" style="float:left;" v-show="this.DayItems.length>28">
								<tr>
									<th colspan="2" style="width:50%;">일자</th>
									<th style="width:50%;border-left:0.5px solid black;">습도</th>
								</tr>
								<tr v-for="(DayItem, index) in DayItems"  v-bind:key="index">
									<template v-if="index>=28">
										<td style="text-align:end;width:25%;padding-right: 8px;" @click="clickLabel(index)">{{ DayItem.clm_date }}</td>
										<td style="text-align:start;width:25%;padding-left: 8px;" @click="clickLabel(index)">일</td>
										<td style="text-align:center;width:50%;border-left:0.5px solid black;" @mousedown="clickValue(index)" >
											<input style="width:80%;" v-model="DayItem.clm_temp_humidity" type="number" @change="changeValue(index)"/>
										</td>
									</template>
								</tr>
							</table>
						</v-col>
					</v-row>
					<v-row style="width:100%;padding-top:0px;margin-top:0px;height:350px;" v-show="this.chart_date_id!=''">
						<v-col xl="12" md="12" sm="12" cols="12" >
							<div id="chart_area_div" style="width:98%; margin-top:10px; float:left;position: absolute;" :key="updateHistoriesComponent">
								<!-- <TempLineChart id="linechart" v-bind:chart_date_id="chart_date_id" style="height:310px;" /> -->
							</div>
						</v-col>
					</v-row>	
					<v-row justify="center">
						<v-dialog v-model="humiditySettingModal" max-width="1100" @keydown.esc="humiditySettingModal = !humiditySettingModal">
							<v-card>
								<v-toolbar class="fontDefault" color="light-blue" dark>습도 관리</v-toolbar>
								<v-card-text style="padding-top:20px; padding-bottom:0px;">
									<v-row>
										<v-col xl="12" md="12" sm="12" cols="12" >
											<table class="hour24_table">
												<tr style="background-color:rgb(219, 251, 255)">
													<td>시간</td>
													<td>1시</td>
													<td>2시</td>
													<td>3시</td>
													<td>4시</td>
													<td>5시</td>
													<td>6시</td>
													<td>7시</td>
													<td>8시</td>
													<td>9시</td>
													<td>10시</td>
													<td>11시</td>
													<td>12시</td>
													<td>13시</td>
													<td>14시</td>
													<td>15시</td>
													<td>16시</td>
													<td>17시</td>
													<td>18시</td>
													<td>19시</td>
													<td>20시</td>
													<td>21시</td>
													<td>22시</td>
													<td>23시</td>
													<td>24시</td>
												</tr>
												<tr>
													<td style="width:4%;">습도</td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity1" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity2" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity3" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity4" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity5" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity6" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity7" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity8" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity9" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity10" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity11" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity12" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity13" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity14" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity15" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity16" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity17" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity18" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity19" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity20" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity21" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity22" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity23" ></td>
													<td style="width:4%;"><input type="number" style="text-align: end;" v-model="clm_humidity24" ></td>
												</tr>
											</table>
										</v-col>
									</v-row>
								</v-card-text>
								<v-card-actions class="justify-end">
									<v-btn text @click="humiditySettingReg" style="color:red;">저장</v-btn>
									<v-btn text @click="humiditySettingModal = !humiditySettingModal">닫기</v-btn>
								</v-card-actions>
							</v-card>
						</v-dialog>
					</v-row>
					<v-row justify="center">
						<v-dialog v-model="humiditySettingOneModal" persistent max-width="550" @keydown.esc="humiditySettingOneModal = !humiditySettingOneModal">
							<v-card>
							<v-toolbar class="fontDefault" color="light-blue" dark>습도 관리</v-toolbar>
							<v-card-text style="padding-top:10px; padding-bottom:0px;">
								<v-row>
									<v-col xl="6" md="6" sm="6" cols="6" align-self="center" style="padding-bottom:10px;padding-left:0px;">
										<v-text-field label="시간" v-model="clm_time" />
									</v-col>
									<v-col xl="6" md="6" sm="6" cols="6" align-self="center" style="padding-bottom:10px;padding-left:0px;">
										<span style="color:red; float: left; padding-top:5px;">*</span><v-text-field label="습도" v-model="clm_humidity" />
									</v-col>
								</v-row>
							</v-card-text>
							<v-card-actions class="justify-end">
								<v-btn text @click="humiditySettingOneReg()" style="font-size:20px; font-weight:bold; padding-bottom:0px; color:blue;">저장</v-btn>
								<v-btn text @click="humiditySettingOneModal = !humiditySettingOneModal" style="font-size:20px; font-weight:bold; padding-right:0px; padding-bottom:0px;">닫기</v-btn>
							</v-card-actions>
							</v-card>
						</v-dialog>
					</v-row>
				</v-card-text>
			</v-card>
		</v-flex>
	</v-layout>
</template>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
import axios from 'axios';
import Swal from 'sweetalert2';
import { EventBus } from '../DBO001/event-bus';

export default {
	data() {
		return {
			loading_yn:'N',
			/* 검색 설정 */
			clm_year:'',
			clm_year_items:[],
			clm_month:'',
			clm_month_items:[
				{ text: '1월',  value: '1' },
				{ text: '2월',  value: '2' },
				{ text: '3월',  value: '3' },
				{ text: '4월',  value: '4' },
				{ text: '5월',  value: '5' },
				{ text: '6월',  value: '6' },
				{ text: '7월',  value: '7' },
				{ text: '8월',  value: '8' },
				{ text: '9월',  value: '9' },
				{ text: '10월',  value: '10' },
				{ text: '11월',  value: '11' },
				{ text: '12월',  value: '12' }],
			DayItems:[{}],
			clm_before_humidity:'',
			updateHistoriesComponent:0,
			chart_date_id:'',
			selected_date:'',
			/* 24시간 모달 */
			clm_humidity:'',
			humiditySettingModal:false,
			clm_humidity1 :'',
			clm_humidity2 :'',
			clm_humidity3 :'',
			clm_humidity4 :'',
			clm_humidity5 :'',
			clm_humidity6 :'',
			clm_humidity7 :'',
			clm_humidity8 :'',
			clm_humidity9 :'',
			clm_humidity10:'',
			clm_humidity11:'',
			clm_humidity12:'',
			clm_humidity13:'',
			clm_humidity14:'',
			clm_humidity15:'',
			clm_humidity16:'',
			clm_humidity17:'',
			clm_humidity18:'',
			clm_humidity19:'',
			clm_humidity20:'',
			clm_humidity21:'',
			clm_humidity22:'',
			clm_humidity23:'',
			clm_humidity24:'',
			/* 시간 변경 */ 
			clm_time:'',
			clm_humidity:'',
			humiditySettingOneModal:false,
		}
	},
	components: {
	},
	created() {
	},
	mounted() {
	},
	watch: {
		options: {
			deep: true,
		},
	},
	methods: {
		updateNow() {
			axios.get('/back/SCR001001/List',{
				params: {
					clm_year: this.clm_year,
					clm_month: this.clm_month,
					clm_user: this.$session.get('user_id'),
				}
			}).then(
				response => {
					if (response.status == '200') {
						for(var x=0;x<response.data.length;x++){
							this.DayItems[x].clm_temp_humidity = Number(response.data[x].clm_temp_humidity);
						}
					}
				}
			)
		}, 
		changeSelectbox(){
			this.DayItems= [{}];
			this.DayItems.splice(0,Object.keys(this.DayItems).length);
			var days_length = new Date(this.clm_year,this.clm_month,0).getDate();
			for(var x=0; x<Number(days_length); x++){
				this.DayItems.push({
					clm_date : String(x+1),
					clm_temp_humidity : 0,
				})
			}
			this.updateNow();
		},
		clickLabel(index){
			if(this.selected_date=='' || this.selected_date!= this.DayItems[index].clm_date){
				this.selected_date = this.DayItems[index].clm_date;
				axios.post('/back/SCR001001/Check24HoursExist', null, { params: {
					clm_year: this.clm_year,
					clm_month: this.clm_month,
					clm_date : this.DayItems[index].clm_date,
					clm_temp_humidity : this.DayItems[index].clm_temp_humidity,
					clm_user: this.$session.get('user_id')
				}})
				.then(response => {
					if (response.status == '200') {
						this.chart_date_id=this.clm_year + '-'+this.clm_month+ '-'+this.DayItems[index].clm_date;
						this.updateHistoriesComponent++;
					}
				})
				.catch(error => {
					console.log(error)
				})
			}
		},
		clickValue(index){
			this.clm_before_humidity = this.DayItems[index].clm_temp_humidity;
			if(this.selected_date=='' || this.selected_date!= this.DayItems[index].clm_date){
				this.selected_date = this.DayItems[index].clm_date;
				axios.post('/back/SCR001001/Check24HoursExist', null, { params: {
					clm_year: this.clm_year,
					clm_month: this.clm_month,
					clm_date : this.DayItems[index].clm_date,
					clm_temp_humidity : this.DayItems[index].clm_temp_humidity,
					clm_user: this.$session.get('user_id')
				}})
				.then(response => {
					if (response.status == '200') {
						this.chart_date_id=this.clm_year + '-'+this.clm_month+ '-'+this.DayItems[index].clm_date;
						this.updateHistoriesComponent++;
					}
				})
				.catch(error => {
					console.log(error)
				})	
			}
		},
		changeValue(index){
			this.loading_yn='N';
			this.DayItems[index].clm_temp_humidity = Number(Number(this.DayItems[index].clm_temp_humidity).toFixed(1));
			this.selected_date = this.DayItems[index].clm_date;
			if(this.DayItems[index].clm_temp_humidity != '' && Number(this.DayItems[index].clm_temp_humidity)<0){
				Swal.fire({
					title: '습도는 음수가 될 수 없습니다.',
					icon: 'error'
				});
				this.DayItems[index].clm_temp_humidity = this.clm_before_humidity;
				return;
			}else{
				axios.post('/back/SCR001001/Update', null, { params: {
					clm_year: this.clm_year,
					clm_month: this.clm_month,
					clm_date : this.DayItems[index].clm_date,
					clm_temp_humidity : this.DayItems[index].clm_temp_humidity,
					clm_last_day : this.DayItems.length,
					clm_user: this.$session.get('user_id')
				}})
				.then(response => {
					if (response.status == '200') {
						this.updateHistoriesComponent++;
					}else{
					}
				})
				.catch(error => {
					console.log(error)
				})		
			}
		},
		humiditySettingReg(){
			if(this.loading_yn=='Y'){
				Swal.fire({
					title: '저장중입니다.잠시만 기다려주세요.',
					icon: 'error'
				});
				return;
			}
			this.loading_yn='Y';
			//24시간 등록
			if(Number(this.clm_humidity1)<0 || Number(this.clm_humidity2)<0|| Number(this.clm_humidity3)<0|| Number(this.clm_humidity4)<0|| Number(this.clm_humidity5)<0
			|| Number(this.clm_humidity6)<0|| Number(this.clm_humidity7)<0|| Number(this.clm_humidity8)<0|| Number(this.clm_humidity9)<0|| Number(this.clm_humidity10)<0
			|| Number(this.clm_humidity11)<0|| Number(this.clm_humidity12)<0|| Number(this.clm_humidity13)<0|| Number(this.clm_humidity14)<0|| Number(this.clm_humidity15)<0
			|| Number(this.clm_humidity16)<0|| Number(this.clm_humidity17)<0|| Number(this.clm_humidity18)<0|| Number(this.clm_humidity19)<0|| Number(this.clm_humidity20)<0
			|| Number(this.clm_humidity21)<0|| Number(this.clm_humidity22)<0|| Number(this.clm_humidity23)<0|| Number(this.clm_humidity24)<0){
				Swal.fire({
					title: '습도가 음수입니다.',
					icon: 'error'
				});
				this.loading_yn='N';
				return;
			}else{
				axios.post('/back/SCR001001/RegHumiditySetting', null, { params: {
					clm_company_key : this.$session.get('company_key'),
					clm_year: this.clm_year,
					clm_month: this.clm_month,
					clm_date : this.selected_date,
					clm_humidity1  : this.clm_humidity1 ,
					clm_humidity2  : this.clm_humidity2 ,
					clm_humidity3  : this.clm_humidity3 ,
					clm_humidity4  : this.clm_humidity4 ,
					clm_humidity5  : this.clm_humidity5 ,
					clm_humidity6  : this.clm_humidity6 ,
					clm_humidity7  : this.clm_humidity7 ,
					clm_humidity8  : this.clm_humidity8 ,
					clm_humidity9  : this.clm_humidity9 ,
					clm_humidity10 : this.clm_humidity10,
					clm_humidity11 : this.clm_humidity11,
					clm_humidity12 : this.clm_humidity12,
					clm_humidity13 : this.clm_humidity13,
					clm_humidity14 : this.clm_humidity14,
					clm_humidity15 : this.clm_humidity15,
					clm_humidity16 : this.clm_humidity16,
					clm_humidity17 : this.clm_humidity17,
					clm_humidity18 : this.clm_humidity18,
					clm_humidity19 : this.clm_humidity19,
					clm_humidity20 : this.clm_humidity20,
					clm_humidity21 : this.clm_humidity21,
					clm_humidity22 : this.clm_humidity22,
					clm_humidity23 : this.clm_humidity23,
					clm_humidity24 : this.clm_humidity24,
					clm_user:this.$session.get('user_id')
				}})
				.then(response => {
					if (response.status == '200') {
						this.loading_yn='N';
						Swal.fire({
							title: '정상적으로 등록 되었습니다.',
							icon: 'success'
						});
						this.humiditySettingModal=false;
						this.updateHistoriesComponent++;
					}
				})
				.catch(error => {
					console.log(error)
				})
			}
		},
	},
}
</script>