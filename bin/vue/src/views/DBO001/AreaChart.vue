<script>
import axios from 'axios';
import { getMaxListeners } from 'process';
import { Bar } from "vue-chartjs"; // 차트 모듈 임포트
import { Line } from "vue-chartjs";

export default {
  extends: Bar,
  // extends: Line,
  // props: ['yTicks'],
  // computed: {
  //   currentLegend() {
  //     switch (this.legend) {
  //       case "d": // 일간
  //         return {
  //           unit: "hour", // 각 x축의 시계열 데이터를 어떤 시간 단위 기준으로 나열할 것인지
  //           round: "minute", // 시계열 데이터를 반올림할 단위
  //           unitStepSize: 4, // 몇 개만큼의 unit을 하나의 tick으로 계산할 것인지
  //           displayFormats: {
  //             hour: "H", // 날짜 포맷 스트링
  //           },
  //         };

  //       case "w": // 주간
  //         return {
  //           unit: "day",
  //           round: "hour",
  //           unitStepSize: 1,
  //           displayFormats: {
  //             day: "dd",
  //           },
  //         };

  //       case "m": // 월간
  //         return {
  //           unit: "day",
  //           round: "hour",
  //           unitStepSize: 7,
  //           displayFormats: {
  //             day: "D",
  //           },
  //         };

  //       default:
  //         return {};
  //     }
  //   },
        // ticks() {
    //   switch (this.legend) {
    //     case "d": // 일간
    //       return {
    //         callback: (tick, index, _array) => {
    //           // 다음날 0시를 일간 그래프 x축에 나타내기
    //           // index가 6인 이유는 unitStepSize를 4로 설정하였기 때문에
    //           // 6번째 오는 인덱스는 항상 다음날 0시를 나타냄
    //           if (index === 6) {
    //             return 24;
    //           }
    //           return tick;
    //         },
    //       };

    //     case "m": // 월간
    //       return {
    //         // 매달 1일은 틱으로 나타내지 않음
    //         callback: (tick, index, _array) => {
    //           if (index === 0) {
    //             return "";
    //           }
    //           return `${tick}일`;
    //         }
    //       };
    //     case "w": // 주간
    //     default:
    //       return {};
    //   }
    // },
  // },

  data() {
    return {
      gradient: null,
      gradient2: null,
     // gradient3: null,
    }
  },
  mounted() {
    this.gradient = this.$refs.canvas
      .getContext("2d")
      .createLinearGradient(450, 450, 450, 450);
    this.gradient2 = this.$refs.canvas
      .getContext("2d")
      .createLinearGradient(450, 450, 450, 450);
    // this.gradient3 = this.$refs.canvas
    //   .getContext("2d")
    //   .createLinearGradient(450, 450, 450, 450);

    // this.gradient.addColorStop(0, "rgba(255, 0,0, 0.5)");
    // this.gradient.addColorStop(0.5, "rgba(255, 0, 0, 0.25)");
    // this.gradient.addColorStop(1, "rgba(255, 0, 0, 0)");

    // this.gradient2.addColorStop(0, "rgba(0, 231, 255, 0.9)");
    // this.gradient2.addColorStop(0.5, "rgba(0, 231, 255, 0.25)");
    // this.gradient2.addColorStop(1, "rgba(0, 231, 255, 0)");
      this.getList();

  },
  methods: {
    async getList(){
        let strArr=[];
        let strArr2=[];
        let safeArr=[];

        var schvalArr = '';
        var button = '';
        var schVal = '';
        var schVal1 = '';
        var schVal2 = '';
        var schVal3 = '';
        var schVal4 = '';
        var schVal5 = '';

        // console.log(this.$session.get('schvalArr'));

        if (this.$session.get('schvalArr') != null && this.$session.get('schvalArr') != '' && typeof this.$session.get('schvalArr') != 'undefined') {
          schvalArr = this.$session.get('schvalArr');
          // alert('material_id 확인 ' + material_id);
          for(var i = 0; i < schvalArr.length; i ++) {
            //console.log(schvalArr[i].value)
            if(schvalArr[i].value != null && schvalArr[i].value != '' && schvalArr[i].value != 'undefined') {
              if (i == 0) {
                schVal = schvalArr[i].value;
              }else if (i == 1) {
                schVal1 = schvalArr[1].value;
              }else if (i == 2) {
                schVal2 = schvalArr[i].value;
              }else if (i == 3) {
                schVal3 = schvalArr[i].value;
              }else if (i == 4) {
                schVal4 = schvalArr[i].value;
              }else if (i == 5) {
                schVal5 = schvalArr[i].value;
              }
            }
          }
          button = schvalArr[6].value;
      }
      // 안전재고
      await axios.get('/back/DBO001004/List2',{ 
        params: {
            schval: schVal,
            schval1: schVal1,
            schval2: schVal2,
            schval3: schVal3,
            schval4: schVal4,
            schval5: schVal5, 
            button: button,         
              }
        }).then(
          response => {  
            if(response.status == '200'){
            console.log('response.data!!!!!!!!!!!!!!');
            console.log(response.data); // response.data 자체가 data
            
              for(var x=0;x<12;x++){
                var exist_yn='N';
               // var cube_size = 0; // 재적
               // var safe_stock = 0; // 수량
                  exist_yn='Y';
                if(exist_yn != 'Y') {
                  safeArr.push(0);
                }else if (button==1) { // 여기에 재적 체크 조건문
                  // for(var y=0; y<response.data.length;y++) {
                    // if(x+1==Number(response.data[y].month)){
                      safeArr.push(response.data); // 재적
                      // }
                  // }
                }else if (button==2) { // 여기에 수량 체크 조건문
                  // for(var y=0; y<response.data.length;y++) {
                    // if(x+1==Number(response.data[y].month)){
                      safeArr.push(response.data); // 수량
                      // }
                  // }
                }
              }
            }
          }
        )
        .catch(error => {
          console.log(error)
        })

      // 사용량
      await axios.get('/back/DBO001004/List3',{ 
          params: {
            schval: schVal,
            schval1: schVal1,
            schval2: schVal2,
            schval3: schVal3,
            schval4: schVal4,
            schval5: schVal5, 
            button: button,         
              }
        }).then(
          response => {  
            if(response.status == '200'){
            console.log(response.data);
              for(var x=0;x<12;x++){
                var exist_yn='N';
                var cube_size = 0; // 재적
                var total_count= 0; // 수량
                for(var y=0; y<response.data.length;y++){
                  if(x+1==Number(response.data[y].month)){
                    exist_yn='Y';
                    cube_size = Number(response.data[y].clm_cube_size);
                    total_count = Number(response.data[y].clm_use_count); // 그래프 수치
                  }
                }
                if(exist_yn != 'Y') {
                  strArr.push(0);
                }

                else if (button==1) { // 여기에 재적 체크 조건문
                  for(var y=0; y<response.data.length;y++) {
                    if(x+1==Number(response.data[y].month)){
                      strArr.push(cube_size); // 재적
                      }
                  }
                }
                else if (button==2) { // 여기에 수량 체크 조건문
                  for(var y=0; y<response.data.length;y++) {
                    if(x+1==Number(response.data[y].month)){
                      strArr.push(total_count); // 수량
                      }
                  }
                }
              }
            }
          }
        )
        .catch(error => {
          console.log(error)
        })

        // 매입량 
        await axios.get('/back/DBO001004/List4', {			 	
        params: {
            schval: schVal,
            schval1: schVal1,
            schval2: schVal2,
            schval3: schVal3,
            schval4: schVal4,
            schval5: schVal5,   
            button: button,  
            }
        }).then(
          response => {
            if(response.status == '200'){
            console.log(response.data);
                for(var x=0;x<12;x++){
                  var exist_yn='N';
                  var cube_size = 0;
                  var income_count = 0; 
                  for(var y=0; y<response.data.length;y++){
                    if(x+1==Number(response.data[y].month)){
                      exist_yn='Y';
                      cube_size= Number(response.data[y].clm_cube_size); 
                      income_count= Number(response.data[y].clm_income_count); 
                    }
                  }

                  // data 여부 확인해서 있으면 그대로 출력, 아니면 0 처리
                  if(exist_yn != 'Y') {
                    strArr2.push(0);
                  }

                  else if (button==1) { // 여기에 재적 체크 조건문
                    for(var y=0; y<response.data.length;y++) {
                      if(x+1==Number(response.data[y].month)){
                        strArr2.push(cube_size); // 재적
                        }
                    }
                  }
                  else if (button==2) { // 여기에 수량 체크 조건문
                    for(var y=0; y<response.data.length;y++) {
                      if(x+1==Number(response.data[y].month)){
                        strArr2.push(income_count); // 수량
                        }
                    }
                  }
                }
            }
          }
        )
        .catch(error => {
          console.log(error)
        })
        setTimeout(() => {
        this.renderChart(
          {
            // x축 라벨
            labels: [
              "1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"
            ],
            datasets: [
              {
                label: "안전재고",
                type: "line",
                borderColor: "#F80C23",
                borderWidth: 4,
                pointBorderColor: "#F80C23",
                backgroundColor: 'transparent',
                data: safeArr,
                
              },
              {
                label: "매입량",
                type: "bar",
                borderColor: "#F59CD4",
                borderWidth: 1.5,
                pointBorderColor: "#F59CD4",
                backgroundColor: "#F59CD4",
                data: strArr2,
              },
              {
                label: "사용량",
                type: "bar",
                borderColor: "#0CDDF8",
                pointBorderColor: "#0CDDF8",
                borderWidth: 1.5,
                backgroundColor: "#0CDDF8",
                data: strArr,
              },
              
            ],
          },
          { // options 위치(별도 표기 없으나 여기에 그래프 option 작성 시 적용됨)
            responsive: true, 
            maintainAspectRatio: false,
            scales: {
              yAxes: [{ // y축 라벨
                ticks: {
                  beginAtZero: true,
                  fontSize : 15,
                  callback: (strArr, index) => {
                    return strArr !== 0 || strArr2 !== 0
                    ? strArr.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") || strArr2.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
                    : "";
                  },
                }
              }], 
            }
          }
        )
      }, 500) 
    }
  }
}

</script>