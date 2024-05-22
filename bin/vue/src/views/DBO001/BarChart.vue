<script>
import axios from 'axios';
import { Bar } from "vue-chartjs";

export default {
  extends: Bar,
  mounted() {
    let strArr=[];
    axios.get('/back/SCR003003/Read',{
				params: {
          clm_product_id: this.$session.get('clm_product_id'),
					clm_seq: this.$session.get('clm_seq'), 
				}
			}).then(
				response => {
            console.log(response.data);
            if(response.status == '200'){
                for(var x = 0 ; x < response.data.length; x++) {
                  strArr.push(response.data[x].clm_average);
                }
                var length = 30-Number(response.data.length);
                for(var y=0 ; y <length; y++) {
                  strArr.push('0');
                }
                console.log(strArr);
            }
				}
      )
      .catch(error => {
        console.log(error)
      })
    let date = new Date();
    var year =date.getFullYear();
    
    setTimeout(() => {
      this.renderChart(
        {
          labels: [
            "1일",
            "2일",
            "3일",
            "4일",
            "5일",
            "6일",
            "7일",
            "8일",
            "9일",
            "10일",
            "11일",
            "12일",
            "13일",
            "14일",
            "15일",
            "16일",
            "17일",
            "18일",
            "19일",
            "20일",
            "21일",
            "22일",
            "23일",
            "24일",
            "25일",
            "26일",
            "27일",
            "28일",
            "29일",
            "30일",
          ],
          datasets: [
            {
              label: "",
              backgroundColor: "#f87979",
              data:strArr
            }
          ]
        },
        { responsive: true, maintainAspectRatio: false }
      );
    }, 700)
  }
};
</script>
