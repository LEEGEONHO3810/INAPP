import Vue from 'vue'
import Router from 'vue-router'
import axios from 'axios'
import VueSession from 'vue-session'

Vue.use(Router)
var sessionOptions = {
  persist: true
}

Vue.use(VueSession, sessionOptions)

const Main = () => {
	return import("./views/Main.vue")
}

const SCR001001 = () => {
	return import("./views/SCR001/SCR001001.vue")	
}
const SCR001002 = () => {
	return import("./views/SCR001/SCR001002.vue")	
}
const SCR001003 = () => {
	return import("./views/SCR001/SCR001003.vue")	
}
const SCR001004 = () => {
	return import("./views/SCR001/SCR001004.vue")	
}
const BarChart = () => {
  return import("./views/DBO001/BarChart.vue")
}
const PieChart = () => {
  return import("./views/DBO001/PieChart.vue")
}
const LineChart = () => {
  return import("./views/DBO001/LineChart.vue")
}
const AreaChart = () => {
  return import("./views/DBO001/AreaChart.vue")
}

export const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes : [
    {
      path: '/',
      name: 'main',
      component: Main,
    },

    {
      path: '/SCR001001',
      name: 'scr001001',
      component: SCR001001
    },

    {
      path: '/SCR001002',
      name: 'scr001002',
      component: SCR001002
    },

    {
      path: '/SCR001003',
      name: 'scr001003',
      component: SCR001003
    },

    {
      path: '/SCR001004',
      name: 'scr001004',
      component: SCR001004
    },
    {
      path: '/BarChart',
      name: 'barchart',
      component: BarChart
    },
    {
      path: '/PieChart',
      name: 'piechart',
      component: PieChart
    },
    {
      path: '/LineChart',
      name: 'linechart',
      component: LineChart
    },
    {
      path: '/AreaChart',
      name: 'areachart',
      component: AreaChart
    },
  ]
});

router.beforeEach(function (to, from, next) {
  console.log('변경하려는 경로 : ' + to.name);
  console.log('이전 경로 : ' + from.name);
  
  var authority_yn = 'N';
  var select_yn = 'N';
  var update_yn = 'N';
  var delete_yn = 'N';
  var screen_name = '';
  var from_location = from.name;
  var to_location = to.name;
  // if(to.name == 'main' || to.name == 'mob001000'|| to.name == 'mob002000'){
  //   to.query.authority_yn = 'Y';
  //   to.query.select_yn = 'Y';
  //   to.query.update_yn = 'Y';
  //   to.query.delete_yn = 'Y';
  //   to.query.screen_name = '';
  //   to.query.from_location = from_location;
  //   to.query.to_location = to_location;
  //   next({ query: to.query })
  // }else{
    authority_yn = 'Y';
      select_yn = 'Y';
      update_yn = 'Y';
      delete_yn = 'Y';
      screen_name = '';
      
      // axios.get('/back/Common/Authority',{
      //   params: {
      //       user_id: router.app.$session.get('user_id'),
      //       to: to.name 
      //     }
      //   })
      //   .then(response => {
          // to.query.screen_name = response.data.clm_screen_name;
        // });

      to.query.authority_yn = authority_yn;
      to.query.select_yn = select_yn;
      to.query.update_yn = update_yn;
      to.query.delete_yn = delete_yn;
      to.query.screen_name = screen_name;
      to.query.from_location = from_location;
      to.query.to_location = to_location;
      
      next({ query: to.query })
  // }
});