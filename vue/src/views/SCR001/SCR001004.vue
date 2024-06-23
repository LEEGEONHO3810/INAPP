<style>
   @keyframes bell-effect {
      50% {
         opacity: 0;
      }
   }

   .bell {
      animation: bell-effect 1s step-end infinite;
   }

   .v-card__text {
      font-size: 25px;
   }

   .modal_message_close {
      position: relative;
      left: 82%;
      font-size: 180%;
   }

   .modal_close {
      position: relative;
      left: 88.5%;
      font-size: 180%;
   }

   .modal_close2 {
      position: relative;
      left: 89.5%;
      font-size: 180%;
   }
   .v-card{
  	 height: 105%;
   }
   .modal_user_close {
      position: relative;
      left: 82%;
      font-size: 180%;
   }

   .modal_stock_close {
      position: relative;
      left: 93%;
      font-size: 180%;
   }

   .modal_payment_close {
      position: relative;
      left: 92%;
      font-size: 180%;
   }

   .modal_material_close {
      position: relative;
      left: 86.8%;
      font-size: 180%;
   }

   .buttonMenu {
      height: 50px !important;
      color: black !important;
      font-weight: 700 !important;
      font-size: 20px !important;
      border: 2px solid black;
      border-color: black;
      background-color: #cdcdcd !important;
   }

   .swal2-title {
      font-size: 22px !important;
   }
</style>
<style scoped>
   .v-data-table>>>.v-data-table-header th {
      text-align: center !important;
   }

   #loading_img {
      position: relative;
      top: 32%;
      left: 43%;
   }

   .testselect>>>.v-select__selection {
      padding-top: 3px;
      padding-bottom: 3px;
      margin-top: -3px;
      margin-bottom: -3px;
   }

   .buttonMenu>>>span {
      font-weight: 800;
   }

   .menu_div {
      position: absolute;
      top: 0;
      left: 0;
      width: 220px;
      height: 100%;
      text-align: center;
      background-color: #5d5d5d;
   }
</style>
<template style="height: 100%;">
   <v-layout style="width:100%;">
     <div class="menu_div" style="position:fixed;">
       <div style="display: flex;">
         <div @click="$router.push('/')" style="cursor:pointer;width:220px; font-size:26px; font-weight:bold;color:white;" class="fontDefault">APP</div>
         <div @click="$router.push('/')" style="cursor:pointer;width:220px; font-size:26px; font-weight:bold;color:white;" class="fontDefault">Login</div>
       </div>
<!--         <div style="height:40px;"></div>-->
<!--         <div><v-btn width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('/')" v-on:click="fnc_router_go('/')">URL 정리</v-btn></div>-->
         <div width="220px" style="height:25px;"></div>
         <div><v-btn style="" width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('SCR001002')" v-on:click="fnc_router_go('SCR001002')">메모장</v-btn></div>
<!--         <div width="220px" style="height:25px;"></div>-->
<!--         <div><v-btn style="" width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('SCR001003')" v-on:click="fnc_router_go('SCR001003')">에러코드</v-btn></div>-->
         <div width="220px" style="height:25px;"></div>
         <div><v-btn style="color:red !important;" width="220px" class="buttonMenu">캘린더</v-btn></div>
         <div width="220px" style="height:25px;"></div>
      </div>
      <div style="margin-bottom:20px; width: calc(100% - 220px);position: absolute;top:0; right: 0;">
         <v-row style="margin:18px 0 0 0; padding: 0 15px 0 15px !important;" justify="center">
            <v-col xl="12" md="12" sm="12" cols="12" align-self="center" style="padding-bottom:0px;margin-bottom:0px;">
               <template>
                  <v-layout wrap row>
                     <v-flex xs12 sm12 md12>
                        <v-card>
                        <v-card-text>
                           <v-row>
                           <v-col cols="12" align-self="center">
                              <v-sheet height="900">
                              <div class="text-center">
                                 <v-btn  icon @click="prevMonth">
                                 <v-icon style="font-size: 30px;">mdi-chevron-left</v-icon>
                                 </v-btn>
                                 <span style="font-size: 30px;">{{ formattedDate }}</span>
                                 <v-btn icon @click="nextMonth">
                                 <v-icon style="font-size: 30px;">mdi-chevron-right</v-icon>
                                 </v-btn>
                              </div>
                              <v-calendar
                                 ref="calendar"
                                 :events="events"
                                 :value="calendarDate"
                                 :event-more="true"
                                 type="month"
                                 color="primary"
                                 @click:event="openEvent"
                                 @click:day="openDate"
                                 @click:more="onMoreEvent"
                              ></v-calendar>
                              </v-sheet>
                           </v-col>
                           </v-row>
                        </v-card-text>
                        </v-card>
                     </v-flex>
                     <v-row justify="center">
                        <v-dialog v-model="regModal" max-width="1000">
                           <v-card>
                              <v-toolbar class="fontDefault" color="light-blue" dark>일정 관리</v-toolbar>
                              <v-card-text style="padding-bottom:0px; padding-top: 30px;">
                                 <v-row >
                                    <v-col xl="8" md="8" sm="8" cols="8"  align-self="center"  style="padding-bottom:0px;margin-bottom:0px;padding-top:0px;">
                                      <v-text-field label="할 일" v-model="clm_comment" />
                                    </v-col>
                                    <v-col xl="2" md="2" sm="2" cols="2"  align-self="center"  style="padding-bottom:0px;margin-bottom:0px;padding-top:0px;">
                                       <v-select 
                                          v-model="clm_schedule_cycle" 
                                          :items="clm_schedule_cycle_items" 
                                          item-text="text"
                                          item-value="value"
                                          class="testselect"
                                          open-on-clear
                                          label="반복">
                                       </v-select>
                                    </v-col>
                                    <v-col xl="2" md="2" sm="2" cols="2"  align-self="center"  style=" text-align: center; padding-bottom:0px;margin-bottom:0px;padding-top:0px;">
                                       <v-swatches v-model="color"
                                       :swatch-size="18"
                                       :max-height = "40"
                                       v-on="listeners" >
                                       </v-swatches>
                                    </v-col>
                                 </v-row>
                                 <template v-if="this.clm_all_day_yn == 'N' && this.clm_check_schedule_id == '' ">
                                    <div style="display: flex;">
                                       <div style="width: 43%; margin-right: 20px;">
                                          <v-text-field label="시작일자" type="date" v-model="clm_start_date" />
                                       </div>
                                       <div style="width: 43%; margin-right: 20px;">
                                         <v-text-field label="종료일자" type="date" v-model="clm_end_date" />
                                       </div>
                                       <span style="font-weight: bold; margin:auto;">시간:</span><div style="margin:auto; ">
                                          <input type="checkbox" v-model="dateTodayYn" @change="checkDateResult">
                                       </div>
                                    </div>
                                 </template>
                                 <template v-else-if="this.clm_all_day_yn == 'Y' && this.clm_check_schedule_id == ''">
                                    <div style="display: flex;">
                                       <div style="width: 20%; margin-right: 20px;">
                                          <v-text-field label="시작일자" type="date" v-model="clm_start_date" />
                                       </div>
                                       <div style="width: 20%; margin-right: 20px;">
                                          <v-text-field label="시작시간" type="time" v-model="clm_start_time" />
                                       </div>
                                       <div style="width: 20%; margin-right: 20px; padding-top:17px;">
                                          <div><span style="font-size:12px; position: absolute; top:37%; left:46%; font-weight:500">종료일자</span><input type="date" v-model="clm_end_date"  onfocus="this.showPicker()" style="width:100%; font-size:27px;border-bottom: 0.5px solid grey; outline: none; color:black" /></div>
                                       </div>
                                       <div style="width: 20%; margin-right: 20px;">
                                          <v-text-field label="종료시간" type="time" v-model="clm_end_time" />
                                       </div>
                                       <span style="font-weight: bold; margin:auto;">시간:</span><div style="margin:auto; ">
                                          <input type="checkbox" v-model="dateTodayYn" @change="checkDateResult">
                                       </div>
                                    </div>
                                 </template>
                                 <template v-if="this.clm_schedule_cycle == 'none'">
                                    <v-row>
                                       <v-col xl="12" md="12" sm="12" cols="12"  align-self="center"  style="padding-bottom:0px;margin-bottom:0px;padding-top:0px;">
                                          <v-file-input multiple label="파일 첨부" v-model="UploadFile"
                                          prepend-icon="mdi-paperclip"
                                          @click="fileUpDown" >
                                          </v-file-input>
                                       </v-col>
                                    </v-row>
                                 </template>
                              </v-card-text>
                              <v-card-actions class="justify-end">
                                 <v-btn text @click="Reg">저장</v-btn>
                                 <v-btn text @click="regModal = !regModal">닫기</v-btn>
                              </v-card-actions>   
                           </v-card>            
                        </v-dialog>
                     </v-row>
                     <v-row justify="center">
                        <v-dialog v-model="viewModal" max-width="900">
                           <v-card>
                              <v-toolbar class="fontDefault" color="light-blue" dark>일정 관리</v-toolbar>
                              <template v-if="this.clm_check_after_management == '' && this.clm_check_complete_value == ''">
                                 <v-card-text style="padding-bottom:0px; padding-top: 30px;">
                                 <v-row >
                                   <v-col xl="8" md="8" sm="8" cols="8"  align-self="center"  style="padding-bottom:0px;margin-bottom:0px;padding-top:0px;">
                                     <v-text-field label="할 일" v-model="clm_comment" />
                                   </v-col>
                                   <v-col xl="2" md="2" sm="2" cols="2"  align-self="center"  style="padding-bottom:0px;margin-bottom:0px;padding-top:0px;">
                                     <v-select
                                         v-model="clm_schedule_cycle"
                                         :items="clm_schedule_cycle_items"
                                         item-text="text"
                                         item-value="value"
                                         class="testselect"
                                         open-on-clear
                                         label="반복">
                                     </v-select>
                                   </v-col>
                                   <v-col xl="2" md="2" sm="2" cols="2"  align-self="center"  style=" text-align: center; padding-bottom:0px;margin-bottom:0px;padding-top:0px;">
                                     <v-swatches v-model="color"
                                                 :swatch-size="18"
                                                 :max-height = "40"
                                                 v-on="listeners" >
                                     </v-swatches>
                                   </v-col>
                                 </v-row>
                                 <template v-if="this.clm_all_day_yn == 'N' && this.clm_check_schedule_id != '' ">
                                    <div style="display: flex;">
                                       <div style="width: 43%; margin-right: 20px;">
                                          <v-text-field label="시작일자" type="date" v-model="clm_start_date" />
                                       </div>
                                       <div style="width: 43%; margin-right: 20px;">
                                          <v-text-field label="종료일자" type="date" v-model="clm_end_date" />
                                       </div>
                                       <span style="font-weight: bold; margin:auto;">시간:</span><div style="margin:auto; ">
                                          <input type="checkbox" v-model="dateTodayYn" @change="checkDateResult">
                                       </div>
                                    </div>
                                 </template>
                                 <template v-else-if="this.clm_all_day_yn == 'Y' && this.clm_check_schedule_id != ''">
                                    <div style="display: flex;">
                                       <div style="width: 20%; margin-right: 20px;">
                                          <v-text-field label="시작일자" type="date" v-model="clm_start_date" />
                                       </div>
                                       <div style="width: 20%; margin-right: 20px;">
                                          <v-text-field label="시작시간" type="time" v-model="clm_start_time" />
                                       </div>
                                       <div style="width: 20%; margin-right: 20px;">
                                          <v-text-field label="종료일자" type="date" v-model="clm_end_date" />
                                       </div>
                                       <div style="width: 20%; margin-right: 20px;">
                                          <v-text-field label="종료시간" type="time" v-model="clm_end_time" />
                                       </div>
                                       <span style="font-weight: bold; margin:auto;">시간:</span><div style="margin:auto; ">
                                          <input type="checkbox" v-model="dateTodayYn" @change="checkDateResult">
                                       </div>
                                    </div>
                                 </template>
                                 <template v-if="this.clm_schedule_cycle == 'none'">
                                    <v-row>
                                       <v-col xl="12" md="12" sm="12" cols="12"  align-self="center"  style="padding-bottom:0px;margin-bottom:0px;padding-top:0px;">
                                          <v-file-input multiple label="파일 첨부" v-model="UploadFile"
                                          prepend-icon="mdi-paperclip"
                                          @click="fileUpDown" >
                                          </v-file-input>
                                       </v-col>
                                    </v-row>
                                 </template>
                              </v-card-text>
                              <v-card-actions class="justify-end">
                                 <v-btn text @click="Update">수정</v-btn>
                                 <v-btn text @click="Delete">삭제</v-btn>
                                 <v-btn text @click="viewModal = !viewModal">닫기</v-btn>
                              </v-card-actions>   
                              </template>
                           </v-card>            
                        </v-dialog>
                     </v-row>

                     <v-row justify="center">
                        <v-dialog v-model="FileModal" max-width="1100" @keyup.esc="FileModal = false">
                           <v-card>
                              <v-toolbar class="fontDefault" color="light-blue" dark> {{ this.clm_company_gubun_name }} 파일 업로드 </v-toolbar>
                              <v-card-text style="padding-top:25px;padding-bottom: 5px; zoom:100%">
                                 <v-row>
                                    <v-col xl="12" md="12" sm="12" cols="12"  align-self="center" style="padding-bottom:0px;margin-bottom:0px;padding-top:20px;">
                                       <v-file-input multiple accept="*" prepend-icon="mdi-folder-upload" counter show-size outlined small-chips v-model="UploadFile" ref="UploadFile" @change="CheckFileComment"></v-file-input>
                                    </v-col>
                                 </v-row>
                                 <template v-if="this.UploadFile != ''">
                                    <v-row>
                                       <v-col xl="9" md="9" sm="9" cols="9"  align-self="center" style="padding-top:10px; padding-bottom:15px;margin-top:10px;">
                                          <span style="font-weight:800;font-size:20px;  margin-bottom:10px; padding-left:9px; padding-top:0px">파일 비고 입력</span>
                                       </v-col>
                                    </v-row>
                                    <v-row>
                                       <v-col xl="6" md="6" sm="6" cols="6" style="padding-bottom:0px;margin-bottom:0px;margin-top:-10px;"   v-for="(item, index) in UploadFile" v-bind:key="index">
                                          <div style="width:100%">
                                             <span style="padding-top:0px;">{{ item.clm_file_name }} </span>
                                             <v-text-field v-model="item.clm_file_comment" style="padding-top:0px; margin-left: -5px; margin-top:-5px;">
                                             </v-text-field>
                                          </div>
                                       </v-col>
                                    </v-row>
                                    <v-row>
                                       <v-col xl="9" md="9" sm="9" cols="9"  align-self="center" style="padding-top:10px; padding-bottom:15px;margin-top:10px;">
                                       </v-col>
                                       <v-col xl="2" md="2" sm="2" cols="2" align-self="center" style="padding-top:10px; padding-bottom:15px;margin-top:10px; text-align:end;">
                                          <span style="font-weight:800;font-size:20px;  margin-bottom:10px; padding-top:0px" @click="ClickDownLoad()">다운로드</span>
                                       </v-col>
                                       <v-col xl="1" md="1" sm="1" cols="1" align-self="center" style="padding-top:10px; padding-bottom:15px;margin-top:10px; text-align:end;">
                                          <span style="font-weight:800;font-size:20px;  margin-bottom:10px; padding-top:0px" @click="FileDelete(selected_items)">삭제</span>
                                       </v-col>
                                    </v-row>
                                 </template>
                                 <v-row>
                                    <v-col>
                                       <v-data-table no-data-text="업로드된 파일이 없습니다." class="elevation-1" v-model="selected_items" :single-select="false" show-select item-key="clm_check_schedule_file_id" :headers="file_headers" @click:row="onClickFileRow" :items="file_document" :loading="loading" :footer-props="{ 'items-per-page-text': '페이지 당 행',  pageText: '총 {2} 항목 중 {0}-{1}' }">
                                          <template slot="item.clm_download_action" slot-scope="props">
                                             <v-btn class="mx-2" icon>
                                                <v-btn width="80%" color="primary" style="color:white; margin-left:-10px" v-on:click="FileDownLoad(props.item)">다운로드</v-btn>
                                             </v-btn>
                                          </template>
                                          <template slot="item.FileDelete" slot-scope="props">
                                             <v-btn class="mx-2" icon>
                                                <v-btn width="80%" color="red" style="color:white; margin-left:-10px" v-on:click="FileDelete(props.item)">삭제</v-btn>
                                             </v-btn>
                                          </template>
                                       </v-data-table>
                                    </v-col>
                                 </v-row>
                              </v-card-text>
                              <v-card-actions class="justify-end">
                                 <v-btn text style="font-size:15px; font-weight:bold;" @click="handleFileUpload">파일 저장</v-btn>
                                 <v-btn text style="font-size:15px; font-weight:bold;" @click="FileModal = !FileModal">닫기</v-btn>
                              </v-card-actions>
                           </v-card>
                        </v-dialog>
                     </v-row>
                  </v-layout>
               </template>
              <template>
                <div>
                  <Modal :visible="modalVisible" @close="modalVisible = false">
                    <h3>할 일 목록{{ date }}</h3>
                    <ul>
                      <li v-for="event in events" :key="event.id" @click="modalClick(event)">{{ event.name }}</li>
                    </ul>
                  </Modal>
                </div>
              </template>

              <template>
                <div>
                  <modal :visible="UpdateModal" @close="UpdateModal = false">
                     <div style="align-items: start;">
                        <div style="display: flex; ">
                           <div >
                              <input type="checkbox" :checked="optionToday" @change="selectOnly('today')">
                           </div>
                           <span style="font-weight: bold; margin:auto;">해당 일정</span>
                        </div>
                        <div style="display: flex;">
                           <div >
                              <input type="checkbox" :checked="optionNext" @change="selectOnly('next')">
                           </div>
                           <span style="font-weight: bold; margin:auto;">이후 일정까지</span>
                        </div>
                        <div style="display: flex;">
                           <div>
                              <input type="checkbox" :checked="optionAll" @change="selectOnly('all')">
                           </div>
                           <span style="font-weight: bold; margin:auto;">모든 일정</span>
                        </div>
                     </div>
                    <template v-if="this.updateType == 'del' ">
                      <button style="margin-right: 10%" @click="DeleteRoot()">삭제</button>
                    </template>
                    <template v-else-if="this.updateType == 'update' ">
                      <button style="margin-right: 10%" @click="UpdateRoot()">수정</button>
                    </template>
                  </modal>
                </div>
              </template>

            </v-col>
         </v-row>
        </div>
    </v-layout>
</template>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11">
</script>
<script>
import axios from 'axios';
import Swal from 'sweetalert2'
import VSwatches from "vue-swatches";
import "vue-swatches/dist/vue-swatches.css";
import Modal from './Modal/Modal.vue';

export default {
   name: "e-colorpicker",
   components: { VSwatches, Modal},

   data() {
      return {

        options: {
            multiSort: false,
            sortBy: [],
            sortDesc: [],
            page: 1,
            itemsPerPage: 5,
         },
         footerOptions: {
            'items-per-page-options': [5, 10, 25, 50, 100],
         },
         totalCount: 0,
         loading: false,
         regModal:false,
         schVal: '',
         schVal2: '',
         save_yn:'N',
         list_page:10,
         loading_yn:'N',
         /* 작업자 모달 */
         user_document:[],
         user_headers: [
            { text: '순번', align: ' d-none', value: 'num', width: 60 },
            { text: '유저ID', align: 'center', value: 'clm_user_id', width: 250 },
            { text: '유저명', align: 'center', value: 'clm_user_name', width: 250 },
         ],
         file_headers: [
            { text: '파일명', align: 'center', value: 'clm_file_name', width: 250 },
            { text: '파일비고', align: 'center', value: 'clm_file_comment', width: 250 },
            { text: '파일다운로드', align: 'center', value: 'clm_download_action', width: 250 },
            { text: '파일삭제', align: 'center', value: 'FileDelete', width: 250 },
            { text: 'clm_check_schedule_file_id', align: ' d-none', value: 'clm_check_schedule_file_id', width: 250 },
         ],

         /* 달력 모달 */
         clm_schedule_cycle: 'none',
         clm_comment: '',
         clm_start_date: '',
         clm_start_time: '',
         clm_end_date: '',
         clm_end_time: '',
         clm_start_datetime: '',
         clm_end_datetime: '',
         dateTodayYn: false,
         clm_all_day_yn: 'N',
         clm_check_schedule_id: '',
         clm_check_sub_schedule_id: '',
         color : '',
         clm_file_comment : '',
         UploadFile : null,
         DownLoadFile : null,
         FileModal : false,
         FileName : '',
         formData: new FormData(),

        //moreEvent
        clickedMore : false,

         clm_check_emergency_items: [
            { text: "Y", value: "Y", },
            { text: "N", value: "N", },
         ],
         
         clm_schedule_cycle_items: [
            { text: "없음", value: "none",},
            { text: "매일", value: "day",},
            { text: "주중", value: "weekday",},
            { text: "주말", value: "weekend",},
            { text: "월말", value: "endMonth",},
         ],

         clm_check_complete_value: '',
         clm_check_after_management: '',

         viewModal: false,

        events: [],

        calendarDate: new Date().toISOString().substr(0, 10),

         // 업로드, 다운

         clm_check_schedule_file_id : '',
         clm_company_gubun_name : '',
         clm_file_location: '',
         clm_file_name : '',
         file_comment_items : '',
         selected_items:[],
         fileList_items:[],
         file_document:[],


        // more-event
        date: '', // 클릭된 날짜 정보를 여기에 저장
        modalVisible: false, // 모달 표시 여부

        // 수정 , delete modal
        optionAll : '',
        optionToday : '',
        optionNext : '',
        UpdateModal: false,
        updateType :'',


      }
   },
   computed:{
      listeners() {
         return {
            ...this.$listeners,
            input: this.updateColorValue
         };
      },
      checkYnResult: function() {
         return this.dateTodayYn ? 'Y' : 'N'; // 실시간 check박스 값
      },
      formattedDate() {
         return this.calendarDate ? this.formatDate(this.calendarDate) : '';
      }
   },
   mounted() {
      this.getList();
   },
   watch: {
      options: {

         handler() {

            this.getList()

         },

         deep: true,
      },
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
      
      formatDate(date) {
         // date를 'YYYY년 MM월 DD일' 형식으로 포맷
         return new Date(date).toLocaleDateString('ko-KR', {
         year: 'numeric',
         month: 'long'
         });
      },

      nextMonth() {
         if (this.$refs.calendar) {
            this.$refs.calendar.next();
            let newDate = new Date(this.calendarDate);
            newDate.setMonth(newDate.getMonth() + 1);
            this.calendarDate = newDate.toISOString().substr(0, 10);
         } else {
            console.log('error');
         }
      },
      prevMonth() {
         if (this.$refs.calendar) {
            this.$refs.calendar.prev();
            let newDate = new Date(this.calendarDate);
            newDate.setMonth(newDate.getMonth() - 1);
            this.calendarDate = newDate.toISOString().substr(0, 10);
         } else {
            console.log('error');
         }

      },

      openDate({date}){
          if (this.clickedMore) {
            // '더 보기' 버튼 클릭 후 날짜 핸들러 처리 방지
            this.clickedMore = false;
            return;
          }
         if(this.viewModal != true) {

            this.clm_schedule_cycle='none';
            this.clm_comment='';
            this.color ='#1CA085';
            
            this.dateTodayYn = false;
            this.clm_all_day_yn = 'N';

            this.clm_check_schedule_id = '';

            this.clm_start_date=date;
            this.clm_start_time = '';

            this.clm_end_date= '';
            this.clm_end_time = '';
            // file 관련 초기화
            this.UploadFile = null;
            this.formData=new FormData();
            this.file_document = [];
            this.selected_items = [];

            
            this.regModal =true;

         }
      },

      openEvent({event}){
          if (this.clickedMore) {
            this.clickedMore = false;
            return;
          }
         const vm = this
         this.loading = true
         this.save_yn='Y';
         this.clm_schedule_cycle= event.clm_schedule_cycle;
         this.clm_comment= event.name;
         this.clm_check_schedule_id = event.clm_check_schedule_id;
         this.clm_check_sub_schedule_id = event.clm_check_sub_schedule_id;
         this.clm_check_after_management = event.clm_check_after_management;
         this.clm_check_complete_value = event.clm_check_complete_value;
         this.clm_all_day_yn = event.clm_all_day_yn;
         this.color = event.color;
         this.UploadFile = null;
         if(this.clm_all_day_yn == 'Y'){

            this.dateTodayYn = true;

         }else if(this.clm_all_day_yn == 'N'){

            this.dateTodayYn = false;
         }

         const start = event.start.split(' ');
         
         this.clm_start_date = start[0]; // 날짜
         this.clm_start_time = start[1]; // 시간
         
         const end = event.end.split(' ');
         
         this.clm_end_date = end[0];   // 날짜
         this.clm_end_time = end[1];   // 시간


         this.loadFileList().then(() => {
            this.selected_items = [];
            this.viewModal = true;
            this.loading = false;
         }).catch(error => {
            this.loading = false;
         });
         
         this.viewModal =true;
                  
      },
      
      loadFileList() {
         const vm = this;
         return axios.get('/back/SCR001/FileList', {
            params: { clm_check_schedule_id: vm.clm_check_schedule_id, 
				 clm_check_sub_schedule_id: vm.clm_check_sub_schedule_id }
         }).then(response => {
            vm.file_document = response.data; 
            vm.selected_items = response.data.map(file => ({
               clm_file_location: file.clm_file_location,
               clm_file_name: file.clm_file_name,
               clm_check_schedule_file_id: file.clm_check_schedule_file_id
            }));
            if (vm.selected_items.length > 0) {
               vm.save_yn = 'N';
            }
         }).catch(error => {
            console.error('Error fetching file list:', error);
            throw error; 
         });

         this.selected_items = [];
      },

      Reg() {
        if(this.clm_comment == '' || this.clm_comment == 'undefined') {
          Swal.fire({
            title: '할 일을 입력해주세요.',
            icon: 'error'
          });
          return;
        } else if(this.clm_start_date == '' || this.clm_start_date == 'undefined') {
            Swal.fire({
               title: '시작 일자를 선택해 주세요.',
               icon: 'error'
            });
            return;
         }else if(this.clm_end_date == '' || this.clm_end_date == 'undefined') {
            Swal.fire({
               title: '종료 일자를 선택해 주세요.',
               icon: 'error'
            });
            return;
         }

         if(this.checkYnResult == 'Y'){
            if(this.clm_start_time == '' || this.clm_start_time == 'undefined') {
               Swal.fire({
                  title: '시작 시간을 선택해 주세요.',
                  icon: 'error'
               });
               return;
            }else if(this.clm_end_time == '' || this.clm_end_time == 'undefined'){
               Swal.fire({
                  title: '종료 시간을 선택해 주세요.',
                  icon: 'error'
               });
            }

         }else if(this.checkYnResult == 'N'){
            this.clm_start_time = '00:00';
            this.clm_end_time = '00:00';
         }


         if(this.clm_schedule_cycle != 'none' ){
            this.formData.append('files', '');
            this.formData.append("comment", '');
         }


         if (!this.UploadFile || this.UploadFile.length === 0) {
            // 빈 파일 파라미터 추가
            this.formData.append('files', '');
            this.formData.append("comment", '');

         }

         // this.clm_schedule_cycle 에 따라 특정 날짜마다 반복? 가능하면 따로 들어가자
         const otherData = {

            clm_all_day_yn: this.clm_all_day_yn,
            clm_start_date: this.clm_start_date,
            clm_start_time: this.clm_start_time,
            clm_end_date: this.clm_end_date,
            clm_end_time: this.clm_end_time,
            clm_color: this.color,
            clm_comment: this.clm_comment,
            clm_user:this.$session.get('user_id')

         };
         
         if(this.clm_schedule_cycle  != '' ||  this.clm_schedule_cycle  == 'none'){
            this.formData.append('cycle', this.clm_schedule_cycle);
         }

         this.formData.append('data', JSON.stringify(otherData));


         axios.post('/back/SCR001/Reg', this.formData, {
            headers: {
               'Content-Type': 'multipart/form-data'
            }
         })
         .then(response => {
            if (response.status == '200') {
               this.loading_yn='N';
               Swal.fire({
                  title: '정상적으로 등록되었습니다.',
                  icon: 'success'
               });

               this.regModal=false;
               this.getList();

            }else{
               this.loading_yn='N';
               Swal.fire({
                  title: '오류가 발생하였습니다.',
                  icon: 'error'
               });
            }
         })
         .catch(error => {
            console.log(error)
         })
      },

      Update() {

        this.optionAll = '';
        this.optionToday = '';
        this.optionNext = '';
        this.updateType = 'update';

        if(this.clm_schedule_cycle == 'none'){

          this.UpdateModal = false;
          this.UpdateRoot();
        }else{
          this.UpdateModal = true;
        }

      },

     UpdateRoot() {
       let option = '';

       if(this.clm_schedule_cycle == 'none'){
            option = 'optionToday';
       }
       if (this.optionAll) {
         option = "optionAll";
       } else if (this.optionToday) {
         option = "optionToday";
       } else if (this.optionNext) {
         option = "optionNext";
       }
       console.log(option);

       if (this.clm_comment === '' || this.clm_comment === 'undefined') {
         Swal.fire({
           title: '할 일을 입력해 주세요.',
           icon: 'error'
         });
         return;
       } else if (this.clm_start_date === '' || this.clm_start_date === 'undefined') {
         Swal.fire({
           title: '시작 일자를 선택해 주세요.',
           icon: 'error'
         });
         return;
       } else if (this.clm_end_date === '' || this.clm_end_date === 'undefined') {
         Swal.fire({
           title: '종료 일자를 선택해 주세요.',
           icon: 'error'
         });
         return;
       }

       if (option === '') {
         Swal.fire({
           title: '수정 옵션을 선택해주세요',
           icon: 'error'
         });
         return;
       }

       this.formData = new FormData(); // 매번 새로운 FormData 객체를 생성합니다.

       if (!this.UploadFile || this.UploadFile.length === 0) {
         // 빈 파일 파라미터 추가
         this.formData.append('files', '');
         this.formData.append("comment", '');
       } else {
         // 파일이 있을 경우 파일 추가
         for (let file of this.UploadFile) {
           this.formData.append('files', file);
         }
       }

       const otherData = {
         clm_check_schedule_id: this.clm_check_schedule_id,
         clm_check_sub_schedule_id: this.clm_check_sub_schedule_id,
         clm_check_schedule_file_id: this.clm_check_schedule_file_id,
         clm_schedule_cycle: this.clm_schedule_cycle,
         clm_all_day_yn: this.clm_all_day_yn,
         clm_start_date: this.clm_start_date,
         clm_start_time: this.clm_start_time,
         clm_end_date: this.clm_end_date,
         clm_end_time: this.clm_end_time,
         clm_color: this.color,
         clm_comment: this.clm_comment,
         clm_user: this.$session.get('user_id')
       };

       this.formData.append('data', JSON.stringify(otherData));

       axios.post('/back/SCR001/Update', this.formData, {
         headers: {
           'Content-Type': 'multipart/form-data'
         },
         params: {
           "option": option
         }
       })
         .then(response => {
           this.loading_yn = 'N';
           if (response.status === 200) {
             Swal.fire({
               title: '정상적으로 수정되었습니다.',
               icon: 'success'
             });
             this.viewModal = false;
             this.UpdateModal = false;
             this.getList();
           } else {
             Swal.fire({
               title: '오류가 발생하였습니다.',
               icon: 'error'
             });
           }
         })
         .catch(error => {
           this.loading_yn = 'N';
           this.UpdateModal = false;
           console.log(error);
           Swal.fire({
             title: '오류가 발생하였습니다.',
             icon: 'error'
           });
           this.getList();
         });
     },

     Delete() {
        // 삭제
        this.optionAll = '';
        this.optionToday = '';
        this.optionNext = '';
        this.updateType = 'del';
        if(this.clm_schedule_cycle == 'none'){
          this.UpdateModal = false;
          this.DeleteRoot();
        }else{
          this.UpdateModal = true;
        }

      },

     DeleteRoot(){
       let option = '';

        if(this.clm_schedule_cycle == 'none'){
            option = 'optionToday';
        }

        if(this.optionAll){
          option ="optionAll";
        }else if(this.optionToday){
          option = "optionToday";
        }else if(this.optionNext){
          option = "optionNext";
        }

       if(option == ''){
         Swal.fire({
           title: '삭제 할 옵션을 선택해주세요',
           icon: 'error'
         });
         return;
       }
       Swal.fire({

         title: '일정을 삭제하시겠습니까?',
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
           axios.post('/back/SCR001/Delete', null, { params: {
               option : option,
               clm_start_date : this.clm_start_date,
               clm_check_schedule_id: this.clm_check_schedule_id,
               clm_check_sub_schedule_id: this.clm_check_sub_schedule_id,
               clm_user: this.$session.get('user_id'),
             }})
               .then(response => {
                 if(response.status == '200') {
                   Swal.fire({
                     title:'정상적으로 삭제되었습니다.',
                     icon: 'success'
                   });
                   this.loading_yn = 'N';
                   this.viewModal=false;
                   this.UpdateModal=false;
                   this.getList();
                 }else{
                   Swal.fire({
                     title:'오류가 발생하였습니다.',
                     icon: 'error'
                   });
                   this.loading_yn='N';
                   this.viewModal=false;
                   this.UpdateModal=false;
                   this.getList();
                 }
               })
               .catch(error => {
                 console.log(error)
               })
          }
       })
     },
     selectOnly(option) {

         this.optionAll = (option === 'all');
         this.optionToday = (option === 'today');
         this.optionNext = (option === 'next');
     },

      deleteItem(){
      },
      getList() {
         const vm = this
         this.loading = true
         this.save_yn='Y';
         
         return new Promise((resolve, reject) => {
            const { sortBy, sortDesc, page, itemsPerPage } = this.options
            let sort = []
            let TsortBy = ''
            let TsortDesc = ''
            if (sortBy.length > 0) {
               // eslint-disable-next-line
               sortBy.forEach((value, index) => {
                  TsortBy = value
                  if (sortDesc[index]){
                     TsortDesc = ''
                  }else{
                     TsortDesc = ''
                  }
               })
            } else {
               TsortBy = ''
               TsortDesc = ''
               
            }
            let items = axios.get('/back/SCR001/ScheduleList',{
                  params: {
                     sortBy: TsortBy,
                     sortDesc: TsortDesc,
                     search: this.schVal,
                     search2: this.schVal2,
                     page: page,
                     clm_user: this.$session.get('user_id'),
                     PerPage: itemsPerPage
                    }
               }).then(
               response => {

                  // items = response.data
                  setTimeout(() => {
                     this.events = response.data; // Apply 'red' color to each event
                     this.save_yn='N';
                     vm.loading = false
                  }, 500)
               },
            )
         })
      },
      
      checkDateResult(){

         if(this.checkYnResult == 'Y'){

            this.clm_all_day_yn = 'Y';

         }else if(this.checkYnResult == 'N'){

            this.clm_all_day_yn = 'N';

         }
      },

      updateColorValue(event) {

         this.$emit("input", event);

      },

      fileUpDown(){
         this.FileModal = true;

      },

      handleFileUpload() {

         if (!this.UploadFile) return;

         for (let i = 0; i < this.UploadFile.length; i++) {
            this.formData.append('files', this.UploadFile[i]);

            if (typeof this.UploadFile[i].clm_file_comment !== "undefined" && this.UploadFile[i].clm_file_comment !== '') {
               this.formData.append("comment", this.UploadFile[i].clm_file_comment);

            } else {
               this.formData.append("comment", "");

            }
         }
         this.FileModal = false;

      },

      CheckFileComment(){
         if(this.UploadFile != '') {
            this.file_comment_items = [];
            this.file_comment_items.splice(0,(this.file_comment_items.length));
            for(var x = 0; x < this.UploadFile.length; x ++) {
               this.file_comment_items.push({
                  clm_file_name: this.UploadFile[x].name,
                  clm_file_comment: '',
               })
            }
         }
      },
      async FileDownLoad(item) {

         try {
            const response = await axios.get("/back/SCR001/FileDownLoad", {params: {

               clm_file_location : item.clm_file_location,
               clm_file_name :  item.clm_file_name,

            }});

            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement("a");
            link.href = url;
            link.setAttribute("download", item.clm_file_name);
            document.body.appendChild(link);
            link.click();

         } catch (error) {
            console.error(error);
         }

      },

      // 크롬은 기본적으로 여러파일 자동 다운로드 막혀있긴함 일단은 만듬
      async ClickDownLoad() {
         const downloadPromises = [];

         const leng = this.selected_items.length;

         for (let i = 0; i < leng; i++) {
            const item = this.selected_items[i];
            const downloadPromise = new Promise(async (resolve, reject) => {
               try {
                  const response = await axios.get("/back/SCR001/FileDownLoad", {
                     params: {
                        clm_file_location: item.clm_file_location,
                        clm_file_name: item.clm_file_name,
                     },
                     responseType: 'blob'
                  });
                  resolve(response.data);
               } catch (error) {
                  reject(error);
               }
            });

            downloadPromises.push(downloadPromise);
         }

         try {
            const responses = await Promise.all(downloadPromises);

            for (let i = 0; i < leng; i++) {
               const data = responses[i];
               const filename = this.selected_items[i].clm_file_name;
               const blob = new Blob([data]);
               const url = window.URL.createObjectURL(blob);
               const link = document.createElement("a");
               link.href = url;
               link.setAttribute("download", filename);
               document.body.appendChild(link);
               link.click();
            }
         } catch (error) {
            console.error(error);
         }
      },

      FileDelete(item) {
         const leng = this.selected_items.length;
            if(leng > 0){
                  Swal.fire({
                  title: '선택한 파일을 삭제하시겠습니까?',
                  text:  '',
                  icon: 'question',
                  showCancelButton: true,
                  allowOutsideClick: false,
                  confirmButtonColor: '#3085d6',
                  cancelButtonColor: '#d33',
                  confirmButtonText: '예',
                  cancelButtonText: '아니요',
                  width: 600,
               }).then((result) => {
                  if (result.isConfirmed) {
                     for(var i = 0; i < leng; i++){

                        item = this.selected_items[i];

                        axios.post('/back/SCR001/FileDelete', null, { params: {
                           clm_check_schedule_file_id: item.clm_check_schedule_file_id,
                           clm_check_schedule_id: item.clm_check_schedule_id
                        }})
                     .then(response => {
                           if (response.status == '200') {
                              Swal.fire({
                                 title:'정상적으로 삭제되었습니다.',
                                 icon: 'success'
                              });
                              this.FileModal = false;
                              this.loadFileList();

                           }else{
                              this.loading_yn='N';
                           }
                        })
                        .catch(error => {
                           console.log(error)
                        })
                     }

                  }else{
                     this.loading_yn='N';
                  }
               })
         }else{
            Swal.fire({
               title: item.clm_file_name + ' 파일을\n 정말 삭제하시겠습니까?',
               text:  '',
               icon: 'question',
               showCancelButton: true,
               allowOutsideClick: false,
               confirmButtonColor: '#3085d6',
               cancelButtonColor: '#d33',
               confirmButtonText: '예',
               cancelButtonText: '아니요',
               width: 600,
            }).then((result) => {
               if (result.isConfirmed) {
                  axios.post('/back/SCR001/FileDelete', null, { params: {
                     clm_check_schedule_file_id: item.clm_check_schedule_file_id,
                     clm_check_schedule_id: item.clm_check_schedule_id
                  }})
                  .then(response => {
                     if (response.status == '200') {
                        Swal.fire({
                           title:'정상적으로 삭제되었습니다.',
                           icon: 'success'
                        });
                        this.FileModal = false;
                        this.loadFileList();

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
      },
      //
      onClickFileRow(event,data) {
        console(data);
      },

     async onMoreEvent({ date}) {
       this.clickedMore = true;
       if (date) {
         this.date = date; // 날짜 값을 설정합니다.
         await this.fetchEvents(date,this.events); // 날짜에 해당하는 이벤트를 가져오는 함수
         this.modalVisible = true; // 모달을 표시합니다.

       } else {
         console.error('Invalid date:', date);
       }
     },
     async fetchEvents(date) {
       this.loading = true;
       this.save_yn = 'Y';

       const { sortBy, sortDesc, page, itemsPerPage } = this.options;
       let TsortBy = sortBy.length > 0 ? sortBy[0] : '';
       let TsortDesc = sortBy.length > 0 && sortDesc[0] ? 'desc' : 'asc';

       try {

         const response = await axios.get('/back/SCR001/ScheduleList', {
           params: {
             targetDate: date,
             sortBy: TsortBy,
             sortDesc: TsortDesc,
             search: this.schVal,
             search2: this.schVal2,
             page: page,
             clm_user: this.$session.get('user_id'),
             PerPage: itemsPerPage
           }
         });
         this.events = response.data;
       } catch (error) {
         console.error(error);
       } finally {
         this.save_yn = 'N';
         this.loading = false;
       }
     },
     modalClick(event){
       // 모달 닫기
       this.modalVisible = false;

       if (this.clickedMore) {
         this.clickedMore = false;
         return;
       }
       const vm = this
       this.loading = true
       this.save_yn='Y';
       this.clm_schedule_cycle= event.clm_schedule_cycle;
       this.clm_comment= event.name;
       this.clm_check_schedule_id = event.clm_check_schedule_id;
       this.clm_check_sub_schedule_id = event.clm_check_sub_schedule_id;
       this.clm_check_after_management = event.clm_check_after_management;
       this.clm_check_complete_value = event.clm_check_complete_value;
       this.clm_all_day_yn = event.clm_all_day_yn;
       this.color = event.color;
       this.UploadFile = null;

       if(this.clm_all_day_yn == 'Y'){

         this.dateTodayYn = true;

       }else if(this.clm_all_day_yn == 'N'){

         this.dateTodayYn = false;
       }

       const start = event.start.split(' ');

       this.clm_start_date = start[0]; // 날짜
       this.clm_start_time = start[1]; // 시간

       const end = event.end.split(' ');

       this.clm_end_date = end[0];   // 날짜
       this.clm_end_time = end[1];   // 시간


       this.loadFileList().then(() => {
         this.selected_items = [];
         this.viewModal = true;
         this.loading = false;
       }).catch(error => {
         this.loading = false;
       });

       this.viewModal =true;
     },
   },
}
</script>