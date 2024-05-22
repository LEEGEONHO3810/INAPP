<script>
import axios from 'axios';
import { Line } from "vue-chartjs";

export default {
  extends: Line,
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
    var month =date.getMonth()+1;

    var month_label =[];
    var day =new Date(year,month,0).getDate();
    // axios.get('/back/DBO001003/List2',{
		// 		params: {
		// 		}
		// 	}).then(
		// 		response => {
		// 			if(response.status == '200'){
    //         console.log(response.data)
    //         if(day==29){
    //           for(var x=0;x<29;x++){
    //             var exist_yn='N';
    //             var release_count= 0;
    //             for(var y=0; y<response.data.length;y++){
    //               if(x+1==Number(response.data[y].now_date)){
    //                 exist_yn='Y';
    //                 release_count=Number(response.data[y].clm_release_count);
    //               }
    //             }
    //             if(exist_yn!='Y'){
    //               strArr.push(0);
    //             }else{
    //               strArr.push(release_count);
    //             }
    //           }
    //         }else if(day==30){
    //           for(var x=0;x<30;x++){
    //             var exist_yn='N';
    //             var release_count= 0;
    //             for(var y=0; y<response.data.length;y++){
    //               if(x+1==Number(response.data[y].now_date)){
    //                 exist_yn='Y';
    //                 release_count=Number(response.data[y].clm_release_count);
    //               }
    //             }
    //             if(exist_yn!='Y'){
    //               strArr.push(0);
    //             }else{
    //               strArr.push(release_count);
    //             }
    //           }
    //         }else{
    //           for(var x=0;x<31;x++){
    //             var exist_yn='N';
    //             var release_count= 0;
    //             for(var y=0; y<response.data.length;y++){
    //               if(x+1==Number(response.data[y].now_date)){
    //                 exist_yn='Y';
    //                 release_count=Number(response.data[y].clm_release_count);
    //               }
    //             }
    //             if(exist_yn!='Y'){
    //               strArr.push(0);
    //             }else{
    //               strArr.push(release_count);
    //             }
    //           }
    //         }

    //         console.log(strArr)
		// 			}
		// 		}
    //   )
    //   .catch(error => {
    //     console.log(error)
    //   })
    
    if(day==29){
      month_label= [
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
          ];
    }else if(day==30){
      month_label= [
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
          ];
    }else{
      month_label= [
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
            "31일",
          ];
      }
    setTimeout(() => {
      this.renderChart(
        {
          labels:month_label,
          datasets: [
            {
              label: "",
              data: strArr,
              backgroundColor: "transparent",
              borderColor: "rgba(1, 116, 188, 0.50)",
              pointBackgroundColor: "rgba(171, 71, 188, 1)"
            }
          ]
        },
        {
          responsive: true,
          maintainAspectRatio: false,
          // title: {
          //   display: true,
          //   text: year+"년 출고량"
          // }
        }
      )
    }, 400)
  }
};
</script>