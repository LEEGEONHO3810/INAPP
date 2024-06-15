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

    .v-container {
      margin-top: 100px;
    }

    .v-card {
      padding: 20px;
    }
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
<template>
  <v-layout style="width:100%;">
    <div class="menu_div" style="position:fixed;">
      <div style="display: flex;">
        <div @click="$router.push('/')" style="cursor:pointer;width:220px; font-size:26px; font-weight:bold;color:white;" class="fontDefault">APP</div>
        <div @click="$router.push('/login')" style="cursor:pointer;width:220px; font-size:26px; font-weight:bold;color:white;" class="fontDefault">Login</div>
      </div>
      <div width="220px" style="height:25px;"></div>
      <div><v-btn width="220px" color="gray" class="buttonMenu" @touchstart.prevent="fnc_router_go('SCR001002')" v-on:click="fnc_router_go('SCR001002')">메모장</v-btn></div>
      <div width="220px" style="height:25px;"></div>
      <div><v-btn style="color:red !important;" width="220px" class="buttonMenu">캘린더</v-btn></div>
      <div width="220px" style="height:25px;"></div>
    </div>
    <v-container>
        <v-row style="margin:18px 0 0 0; padding: 0 15px 0 15px !important;" justify="center">
        <v-col cols="12" md="8">
          <v-card>
            <v-card-title>
              <span class="headline">회원가입</span>
            </v-card-title>
            <v-card-text>
              <v-form @submit.prevent="submitForm">
                <v-row>
                  <v-col cols="12" md="6">
                    <v-text-field
                        v-model="form.name"
                        label="이름"
                        required
                        :rules="nameRules"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-text-field
                        v-model="form.nickName"
                        label="닉네임"
                        required
                        :rules="nickNameRules"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12" md="6">
                    <v-text-field
                        v-model="form.password"
                        label="비밀번호"
                        type="password"
                        required
                        :rules="passwordRules"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-text-field
                        v-model="form.passwordCheck"
                        label="비밀번호 확인"
                        type="password"
                        required
                        :rules="passwordCheckRules.concat(passwordConfirmationRule)"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12" md="12">
                    <v-text-field
                        v-model="form.email"
                        label="이메일"
                        required
                    ></v-text-field>
                  </v-col>
                </v-row>



                <v-btn type="submit" color="primary">회원가입</v-btn>
              </v-form>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-layout>
</template>


<script src="//cdn.jsdelivr.net/npm/sweetalert2@11">
</script>
<script>
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
  data: () => ({
    form: {
      name: '',
      email: '',
      nickName: '',
      password: '',
      passwordCheck: '',
    },
    email : "",
    emailRules: [
      v => !!v || '이메일을 작성해주세요',
      v => /.+@.+\..+/.test(v) || '이메일 형식으로 작성해주세요',
    ],
    nickName : "",
    nickNameRules: [
      v => !!v || '닉네임을 작성해주세요',
      v => (v && v.length <= 10) || '닉네임을 10글자를 넘을 수 없습니다.',
    ],
    password : "",
    passwordRules: [
      v => !!v || '비밀번호을 작성해주세요',
      v => (v && v.length >= 5) || '비밀번호는 5글자 이상 작성해주세요',
      v => /(?=.*\d)/.test(v) || '숫자를 포함해야합니다',
      v => /([!@$%])/.test(v) || '특수문자를 포함해야합니다 [!@#$%]'
    ],
    passwordCheck : "",
    passwordCheckRules: [
      v => !!v || '비밀번호을 작성해주세요',
      v => (v && v.length >= 5) || '비밀번호는 5글자 이상 작성해주세요',
      v => /(?=.*\d)/.test(v) || '숫자를 포함해야합니다',
      v => /([!@$%])/.test(v) || '특수문자를 포함해야합니다 [!@#$%]',
    ],

  }),
  methods: {
    submitForm() {
      console.log(this.form);
      axios.post('/api/register', this.form)
          .then(response => {
            Swal.fire({
              title: 'Success',
              text: '회원가입이 완료되었습니다!',
              icon: 'success'
            });
          })
          .catch(error => {
            Swal.fire({
              title: 'Error',
              text: '회원가입에 실패했습니다.',
              icon: 'error'
            });
          });
    },
    fnc_router_go(route) {
      this.$router.push(`/${route}`);
    }
  },
  computed:{
    passwordConfirmationRule() {
      return () =>
          this.password !== this.confirmPassword || "패스워드가 일치하지 않습니다";
    }
  },
};
</script>
