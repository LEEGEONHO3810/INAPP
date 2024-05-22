<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@0.7.0"></script>
<script>
import axios from 'axios';
import { Pie } from "vue-chartjs";

export default {
  extends: Pie,
  mounted() {
    let strArr=[];
    axios.get('/back/DBO001002/List',{
				params: {
				}
			}).then(
				response => {
          console.log(response.data)
					if(response.status == '200'){
              strArr.push(Number(response.data[0].before_produce_count));
              strArr.push(Number(response.data[0].producing_count));
              strArr.push(Number(response.data[0].complete_count));
              strArr.push(Number(response.data[0].release_count));
					}
				}
      )
      .catch(error => {
        console.log(error)
      })
    this.gradient = this.$refs.canvas
      .getContext("2d")
      .createLinearGradient(0, 0, 0, 450);
    this.gradient2 = this.$refs.canvas
      .getContext("2d")
      .createLinearGradient(0, 0, 0, 450);
    this.gradient3 = this.$refs.canvas
      .getContext("2d")
      .createLinearGradient(0, 0, 0, 450);
    this.gradient4 = this.$refs.canvas
      .getContext("2d")
      .createLinearGradient(0, 0, 0, 450);

    this.gradient.addColorStop(0, "rgba(255, 0,0, 0.5)");
    this.gradient.addColorStop(0.5, "rgba(255, 0, 0, 0.25)");
    this.gradient.addColorStop(1, "rgba(255, 0, 0, 0)");

    this.gradient2.addColorStop(0, "rgba(0, 231, 255, 0.9)");
    this.gradient2.addColorStop(0.5, "rgba(0, 231, 255, 0.25)");
    this.gradient2.addColorStop(1, "rgba(0, 231, 255, 0)");

    this.gradient3.addColorStop(0, "rgba(193, 250, 25, 98)");
    this.gradient3.addColorStop(0.5, "rgba(113, 223, 11, 0.25)");
    this.gradient3.addColorStop(1, "rgba(0, 231, 255, 0)");
    
    this.gradient4.addColorStop(0, "rgba(172, 125, 250, 98)");
    this.gradient4.addColorStop(0.5, "rgba(208, 84, 222, 0.25)");
    this.gradient4.addColorStop(1, "rgba(0, 231, 255, 0)");


    setTimeout(() => {
      this.renderChart(
        {
          labels: ["생산대기", "생산중", "생산완료", "출고완료"],
          datasets: [
            {
              pointStyle: 'Rounded',
              backgroundColor: [this.gradient, this.gradient2, this.gradient3,this.gradient4],
              data: strArr,
            }
          ],
        },
        { responsive: true,
          maintainAspectRatio: false,
        },
      )
    },400)
  },
};
</script>