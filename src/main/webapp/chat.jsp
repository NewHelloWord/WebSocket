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
</head>
<body>
<h1>Hello World!</h1>

<div class="container-fluid">
    <div class="row">
        <div id="msg" class="col-md-6 col-md-offset-3" style="">

        </div>
    </div>
</div>
<input type="text" id="sendMsg" width="25%">
<button id="send" type="button" class="btn btn-primary btn-lg">发送</button>


<script src="res/js/jquery.min.js"></script>
<script src="res/js/bootstrap.min.js"></script>
<script>
    $(function(){
        $('#send').on('click',function(){
//            alert("aaaa");
            send();
        });




        var websocket = null;
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://yl12345.vicp.net:15699/chat");
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
            setMessageInnerHTML("WebSocket连接成功");
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            setMessageInnerHTML(event.data);
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
            $('#msg').append('<div style="color: #d43f3a;font-size: 30px;">'+innerHTML+'</div>')
//            document.getElementById('message').innerHTML += innerHTML + '<br/>';
        }

        //关闭WebSocket连接
        function closeWebSocket() {
            websocket.close();
        }

        //发送消息
        function send() {
//            var message = document.getElementById('text').value;
            var msg = $('#sendMsg').val();
            var message = '不要回复，不要回复........';
            if(msg != null){
                websocket.send(msg);
                $('#msg').append('<div style="color: #5e5e5e;font-size: 30px;">'+msg+'</div>')
                $('#sendMsg').val("");
            }

        }

    });


</script>
</body>
</html>