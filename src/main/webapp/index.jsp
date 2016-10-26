<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>WebSocket聊天室</title>

    <!-- Bootstrap -->
    <link href="res/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        .left{
            float: left;
        }
        .right{
            float: right;
        }
        .clear:after{
            content:".";
            display:block;
            height:0;
            clear:both;
            visibility:hidden;

        }

    </style>
</head>
<body style="min-height: 800px;font-family: 'Microsoft YaHei',Arial;">

    <div style="width: 40%;height: 900px;margin: 20px auto;border: 1px solid #84a4ea;border-radius: 7px;">
        <div style="width: 100%;height: 8%;background-color: #95afea;border-bottom: 1px solid #84a4ea;border-radius: 5px 5px 0 0;">
            <span style="height: 100%;line-height: 70px;font-size: 21px;padding-left: 20px;">正在制作漂亮的界面。。。</span>
        </div>

        <div style="overflow-y: auto;height: 75%;width: 100%;padding: 15px;border: 1px solid #eeeeee">

        </div>

        <div style="width: 100%;height: 17%">
            <textarea maxlength="500" placeholder="请在此输入聊天信息" style="resize: none; width: 100%; height: 65%;padding: 6px 12px;font-size: 16px;border-radius: 4px;"></textarea>
            <button type="button" class="btn btn-primary right" style="width: 200px;height: 30%;">发送</button>
        </div>
    </div>



<script src="res/js/jquery.min.js"></script>
<script src="res/js/bootstrap.min.js"></script>
<%--<script>
    $(function(){

        var websocket = null;

        initWebSocket();

        $('#send').on('click',function(){
            send();
        });

    });

    function initWebSocket() {
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
//          websocket = new WebSocket("ws://yl12345.vicp.net:15699/chat");
            websocket = new WebSocket("ws://localhost:8080/chat");
        }
        else {
            alert('当前浏览器 Not support websocket')
        }

        //连接发生错误的回调方法
        websocket.onerror = function () {
            setMessageInnerHTML("WebSocket连接发生错误");
        };

        //连接成功建立的回调方法
        websocket.onopen = function () {
//            setMessageInnerHTML("WebSocket连接成功");
            setMessageInnerHTML("欢迎");
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            var data = JSON.parse(event.data);
            setMessageInnerHTML(data.nick+': '+data.message);
        }

        //连接关闭的回调方法
        websocket.onclose = function () {
            setMessageInnerHTML("WebSocket连接关闭");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            closeWebSocket();
        }

        //将消息显示在网页上
        function setMessageInnerHTML(innerHTML) {
            $('.chatDiv').append('<div class="chatBody left"><span class="left">'+innerHTML+'</span></div>');
            keepBottom();
//            document.getElementById('message').innerHTML += innerHTML + '<br/>';
        }

        //关闭WebSocket连接
        function closeWebSocket() {
            websocket.close();
        }
    }


    //发送消息
    function send() {
        var msg = $('#sendMsg').val();
        var message = '不要回复，不要回复........';
        if(msg != null){
            websocket.send(msg);
            $('.chatDiv').append('<div class="chatBody right"><span class="right">'+msg+'</span></span></div>');
            $('#sendMsg').val("");
            keepBottom();
        }
    }

    function keepBottom() {
        var height = 0;
        $('.chatBody').each(function(){
            height += $(this).height();
        });
        $(".chatDiv").scrollTop(height);
    }

    //当前日期加时间(如:2009-06-12 12:00)
    function CurentTime(){
        var now = new Date();
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
        var clock = year + "-";
        if(month < 10)
            clock += "0";
        clock += month + "-";
        if(day < 10)
            clock += "0";
        clock += day + " ";
        if(hh < 10)
            clock += "0";
        clock += hh + ":";
        if (mm < 10) clock += '0';
        clock += mm;
        return(clock);
    }



</script>--%>
</body>
</html>