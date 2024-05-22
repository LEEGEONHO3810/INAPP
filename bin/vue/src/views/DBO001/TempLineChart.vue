<script>
import axios from 'axios';
import { Line } from "vue-chartjs";
import { EventBus } from './event-bus'

export default {
  name: 'TempLineChart',
  props: ["chart_date_id"],
  extends: Line,
  data() {
		return {
      clm_standard_temp: 0,
      clm_min_temp: 0,
      clm_max_temp: 0,
    }
  },
  mounted() {
    var searchArr =this.chart_date_id.split('-');
    var strArr = [];
    var strArr2 = [];
    var strLabel = [];
    
    var min_value =99999;
    var max_value =0;
    axios.get('/back/SCR001001/GetHumidity24Hours',{
				params: {
          year: searchArr[0],
          month: searchArr[1],
          date: searchArr[2],
				}
			}).then(
      response => {
        strArr = [];
        strLabel = [];
        if(response.status == '200'){
          var humidityArr =response.data.clm_humidity.split('-');
          for(var x=0;x<humidityArr.length;x++){
            // if(min_value>Number(humidityArr[x])){
            //   min_value = Number(humidityArr[x]);
            // }
            if(max_value < Number(humidityArr[x])){
              max_value = Number(humidityArr[x]);
            }
            strArr.push(Number(humidityArr[x]));
            strLabel.push(String(x+1)+'시');
          }
          max_value= String(Number(max_value).toFixed());
          max_value = Number(max_value) +  10-Number(max_value.charAt(max_value.length - 1));
          // if(min_value==99999){
          //   min_value =0;
          // }
        }
      }
    )
    .catch(error => {
      console.log(error)
    })
    axios.get('/back/SCR001001/GetHumidityReal',{
				params: {
          year: searchArr[0],
          month: searchArr[1],
          date: searchArr[2],
				}
			}).then(
      response => {
        if(response.status == '200'){
          for(var y=0;y<24;y++){
            var exist_yn='N';
            for(var x=0;x<response.data.length;x++){
              if(Number(response.data[x].clm_hour) == (y+1)){
                exist_yn='Y';
                if(max_value < Number(response.data[x].clm_humid)){
                  max_value = Number(response.data[x].clm_humid);
                }
                strArr2.push(Number(response.data[x].clm_humid));
              }
            }
            if(exist_yn=='N'){
                strArr2.push(0);
            }
          }
          max_value= String(Number(max_value).toFixed());
          max_value = Number(max_value) +  10-Number(max_value.charAt(max_value.length - 1));
        }
      }
    )
    .catch(error => {
      console.log(error)
    })

    setTimeout(() => {
      this.renderChart(
        {
          labels:strLabel,
          datasets: [
            {
              label: "습도 프로세스",
              data: strArr,
              tension: 0.1,
              backgroundColor: "transparent",
              borderColor: "rgba(36, 119, 239, 1)",
              pointBackgroundColor: "white"
            },
            {
              label: "실제 습도",
              data: strArr2,
              tension: 0.1,
              backgroundColor: "transparent",
              borderColor: "rgba(46, 209, 73, 1)",
              pointBackgroundColor: "white"
            }
          ],
        },
        { 
          responsive: true,
          maintainAspectRatio: false,
          scales: {
              yAxes: [{ // y축 라벨
                ticks: {
                  fontSize : 16,
                  min: 0,
                  // min: min_value,
                  max: max_value,
                }
              }],
              xAxes: [{ // x축 라벨
                ticks: {
                  fontSize : 16
                }
              }]
          },
          onClick: function(e) {
            var click_y_yn = 'N'
            var click_x_yn = 'N'
            var click_x_number = ''
            var x_once_width = Number(this.scales['x-axis-0'].width) / (Number((this.scales['x-axis-0'].max).replace('시',''))-1);
            
            console.log('> 클릭한 x 축 : ' + e.offsetX)
            console.log('> 클릭한 y 축 : ' + e.offsetY)

            if(Number(e.offsetY) > Number(this.scales['y-axis-0'].bottom)){
              click_y_yn = 'Y'
            }
            var calc_x = (Number(e.offsetX)-Number(this.scales['x-axis-0'].left))/Number(x_once_width)
            var number_calc_x = Number(calc_x).toFixed(0)

            if(String(number_calc_x).length == 1){
              if(Math.abs(Number(number_calc_x) - Number(calc_x)) < 0.1){
                click_x_yn = 'Y'
                click_x_number = String(Number(number_calc_x)+1)
              }
            }else if(String(number_calc_x).length == 2){
              if(Math.abs(Number(number_calc_x) - Number(calc_x)) < 0.13){
                click_x_yn = 'Y'
                click_x_number = String(Number(number_calc_x)+1)
              }
            }else if(String(number_calc_x).length == 3){
              if(Math.abs(Number(number_calc_x) - Number(calc_x)) < 0.18){
                click_x_yn = 'Y'
                click_x_number = String(Number(number_calc_x)+1)
              }
            }
            // && click_x_yn == 'Y'
            if(click_y_yn == 'Y'){
              var Temp = String(strLabel[Number(click_x_number)-1]);
              if(Temp == 'undefined'){
                Temp = ''
              }

              EventBus.$emit('onClickGraphSetting', click_x_number + '▦' + Temp);
            }
          },
        },
      )
    }, 400)   
  }
};
</script>