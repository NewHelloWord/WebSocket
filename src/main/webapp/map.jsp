<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <title>MyMap</title>



    <style type="text/css">
        html{height:100%}
        body{height:100%;margin:0px;padding:0px}
        #container{height:100%}

        .high{
            width: 200px;
            min-height: 200px;
            border:1px solid #2e8ded;
            z-index: 2;
            position: absolute;
            left: 5%;
            top: 10px;
            padding: 10px;;
            background-color: beige;
        }
        .msg{
            margin-left: 30px;
            color: red;
        }
        .labelFont{
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="high">

        <label>IP:</label>
        <input id="IP" style="width: 50%">
        <input id="check" style="margin: 10px 0 10px 30px;" type="button" value="精确定位">
        <div style="clear: both"></div>
        <span class="msg smsg" style="display: none">精确定位成功</span>
        <span class="msg emsg" style="display: none">精确定位失败</span>
        <br>
        <label class="labelFont">经度坐标：</label>
        <span id="lng" style="display: block;margin-left: 30px"></span>
        <label class="labelFont">纬度坐标：</label>
        <span id="lat" style="display: block;margin-left: 30px"></span>
        <label class="labelFont">定位结果半径：</label>
        <span id="radius" style="display: block;margin-left: 30px"></span>
        <label class="labelFont">定位结果可信度：</label>
        <span id="confidence" style="display: block;margin-left: 30px"></span>
        <label class="labelFont">精确地址：</label>
        <span id="formatted_address" style="display: block;margin-left: 30px"></span>
    </div>
    <div id="container"></div>


    <script src="res/js/jquery.min.js"></script>
    <script src="res/js/jquery.cookie.js"></script>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=ipv3MEbj6h4wEBFsn73alReWPCLTbUeg&callback=initialize" type="text/javascript"></script>
<script>

    var lng = 120.1444;  //经度
    var lat = 30.249976; //纬度
    $(function(){
        $('#check').on('click',function(){
            $('.smsg').hide();
            $('.emsg').hide();
            check();
        })
    });

    function check(){
        var IP = $.trim($('#IP').val());
        if(IP == null || IP == ""){
            return false;
        }
        $.ajax({
            url:"getAddress.htm",
            dataType : "JSON",
            type : "post",
            data : {"IP":IP},
            async:false,
            success:function(data){
                if(data.error == 161){
                    $('.smsg').show();
                    $('#lat').text(data.lat);
                    $('#lng').text(data.lng);
                    $('#radius').text(data.radius);
                    $('#confidence').text(data.confidence);
                    $('#formatted_address').text(data.formatted_address);

                    lng = data.lng;
                    lat = data.lat;
                    initialize();

                }else{
                    $('.emsg').show();
                }
            },
            error:function(data){
            }
        });
    }


    function initialize() {
        var map = new BMap.Map("container");  // 创建地图实例
        var point = new BMap.Point(lng, lat); // 创建点坐标
        map.centerAndZoom(point, 16);  // 初始化地图，设置中心点坐标和地图级别
        map.addControl(new BMap.NavigationControl());
        map.addControl(new BMap.ScaleControl());
        map.addControl(new BMap.OverviewMapControl());
//        map.addControl(new BMap.MapTypeControl());
//        map.setCurrentCity("北京"); // 仅当设置城市信息时，MapTypeControl的切换功能才能可用

        var marker = new BMap.Marker(point);        // 创建标注
        map.addOverlay(marker);                     // 将标注添加到地图中
//        marker.addEventListener("click", function(){
//            alert("您点击了标注");
//        });

        marker.enableDragging();   //标记移动
        marker.addEventListener("dragend", function(e){
            alert("当前位置：" + e.point.lng + ", " + e.point.lat);
        })


//        var opts = {
//            width : 250,     // 信息窗口宽度
//            height: 100,     // 信息窗口高度
//            title : "Hello"  // 信息窗口标题
//        }
//        var infoWindow = new BMap.InfoWindow("<span style='color: red'>Word</span>", opts);  // 创建信息窗口对象
//        map.openInfoWindow(infoWindow, map.getCenter());      // 打开信息窗口


//        var polyline = new BMap.Polyline([                //画线
//                    new BMap.Point(116.399, 39.910),
//                    new BMap.Point(116.405, 39.920)
//                ],
//                {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5}
//        );
//        map.addOverlay(polyline);

        map.enableScrollWheelZoom();   //开启滚轮滑动模式


        //自定义覆盖物------------------------------------------------------------------------------------
//        // 定义自定义覆盖物的构造函数
//        function SquareOverlay(center, length, color){
//            this._center = center;
//            this._length = length;
//            this._color = color;
//        }
//        // 继承API的BMap.Overlay
//        SquareOverlay.prototype = new BMap.Overlay();
//
//
//        // 实现初始化方法
//        SquareOverlay.prototype.initialize = function(map){
//// 保存map对象实例
//            this._map = map;
//            // 创建div元素，作为自定义覆盖物的容器
//            var div = document.createElement("div");
//            div.style.position = "absolute";
//            // 可以根据参数设置元素外观
//            div.style.width = this._length + "px";
//            div.style.height = this._length + "px";
//            div.style.background = this._color;
//// 将div添加到覆盖物容器中
//            map.getPanes().markerPane.appendChild(div);
//// 保存div实例
//            this._div = div;
//// 需要将div元素作为方法的返回值，当调用该覆盖物的show、
//// hide方法，或者对覆盖物进行移除时，API都将操作此元素。
//            return div;
//        }
//
//
//
//        // 实现绘制方法
//        SquareOverlay.prototype.draw = function(){
//// 根据地理坐标转换为像素坐标，并设置给容器
//            var position = this._map.pointToOverlayPixel(this._center);
//            this._div.style.left = position.x - this._length / 2 + "px";
//            this._div.style.top = position.y - this._length / 2 + "px";
//        }
//
//        // 添加自定义覆盖物
//        var mySquare = new SquareOverlay(map.getCenter(), 100, "red");
//        map.addOverlay(mySquare);

        //自定义覆盖物------------------------------------------------------------------------------------


        //交通流量图层
//        var traffic = new BMap.TrafficLayer();        // 创建交通流量图层实例
//        map.addTileLayer(traffic);                    // 将图层添加到地图上



    }

</script>
</body>
</html>
