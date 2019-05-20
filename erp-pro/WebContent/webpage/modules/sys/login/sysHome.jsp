<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/webpage/include/taglib.jsp"%> 
<html>
<head>
<meta charset="utf-8">
<title>index</title>
<script type="text/javascript" src="${ctxStatic}/common/js/jquery.js"></script>
<link rel="stylesheet" href="${ctxStatic}/common/css/comon0.css">
<link rel="shortcut icon" href="${ctxStatic}/favicon.ico">
</head>
	<script>
	$(window).load(function(){  
             $(".loading").fadeOut()
            })  
			
/****/
$(document).ready(function(){
	var whei=$(window).width()
	$("html").css({fontSize:whei/20})
	$(window).resize(function(){
		var whei=$(window).width()
	 $("html").css({fontSize:whei/20})
});
	});
	</script>
	<script type="text/javascript" src="${ctxStatic}/common/js/e.js"></script>
<body style="background:#000d4a url(${ctxStatic}/common/images/bg1.jpg) center top;">
<!-- <div class="canvas" style="opacity: .2">
	<iframe frameborder="0" src="js/index.html" style="width: 100%; height: 100%"></iframe>
	</div> -->
<div class="loading">
  <div class="loadbox"> <img src="${ctxStatic}/common/images/loading.jpg"> 页面加载中... </div>
</div>
<div class="head">
  <h1>可视化看板</h1>
  <div class="weather"><!--<img src="picture/weather.png"><span>多云转小雨</span>--><span id="showTime"></span></div>
	
	<script>
var t = null;
    t = setTimeout(time,1000);//開始运行
    function time()
    {
       clearTimeout(t);//清除定时器
       dt = new Date();
		var y=dt.getFullYear();
		var mt=dt.getMonth()+1;
		var day=dt.getDate();
       var h=dt.getHours();//获取时
       var m=dt.getMinutes();//获取分
       var s=dt.getSeconds();//获取秒
       document.getElementById("showTime").innerHTML = y+"年"+mt+"月"+day+"-"+h+"时"+m+"分"+s+"秒";
       t = setTimeout(time,1000); //设定定时器，循环运行     
    } 

</script>
	
	
</div>
<div class="mainbox">
  <ul class="clearfix">
    <li>
      <div class="boxall" style="height: 3.2rem">
        <div class="alltitle">销售订单情况</div>
        <div class="allnav" id="chart1"></div>
        <div class="boxfoot"></div>
      </div>
      <div class="boxall" style="height: 3.2rem">
        <div class="alltitle">生产进度情况</div>
        <div class="allnav" id="chart2"></div>
        <div class="boxfoot"></div>
      </div>
      <div class="boxall" style="height: 3.2rem">
        <div class="alltitle">临时采购情况</div>
        <div class="allnav" id="chart3"></div>
        <div class="boxfoot"></div>
      </div>
    </li>
    <li>
      <div class="bar">
        <div class="barbox">
          <ul class="clearfix">
              <li class="pulll_left counter" id="oldNumber">0</li>
            <li class="pulll_left counter" id="newNumber">0</li>
          </ul>
        </div>
        <div class="barbox2">
          <ul class="clearfix">
         <li class="pulll_left" id="oldOrder">2018订单数 </li>
            <li class="pulll_left" id="newOrder">2019订单数 </li>
          </ul>
        </div>
      </div>
      <div class="map">
        <div class="map1"><img src="${ctxStatic}/common/images/lbx.png"></div>
        <div class="map2"><img src="${ctxStatic}/common/images/jt.png"></div>
        <div class="map3"><img src="${ctxStatic}/common/images/map.png"></div>
        <div class="map4" id="map_1"></div>
      </div>
    </li>
    <li>
          <div class="boxall" style="height:3.4rem">
        <div class="alltitle">出入库信息</div>
        <div class="allnav" id="chart4"></div>
        <div class="boxfoot"></div>
      </div>
      <div class="boxall" style="height: 3.2rem">
        <div class="alltitle">领料单情况</div>
        <div class="allnav" id="chart5"></div>
        <div class="boxfoot"></div>
      </div>
      <div class="boxall" style="height: 3rem">
        <div class="alltitle">问题统计</div>
        <div class="allnav" id="chart6"></div>
        <div class="boxfoot"></div>
      </div>
    </li>
  </ul>
</div>
<div class="back"></div>


<script type="text/javascript" src="${ctxStatic}/common/js/china.js"></script>
<script language="JavaScript" src="${ctxStatic}/common/js/js.js"></script>
</body>
</html>