<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>WebSocket聊天室</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="res/css/bootstrap.min.css">
    <link rel="stylesheet" href="res/css/font-awesome.min.css">

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
        .chat{
            margin-top: 5px;
            padding: 7px;
            word-wrap: break-word;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            background-color: #cfffcf;
            float: left;
        }
        .myChat{
            margin-top: 5px;
            padding: 7px;
            word-wrap: break-word;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            background-color: #ffe6b8;
            float: right;
        }
        .clear{
            color: gray;
            clear: both;
            margin-left: 20px;
            padding-top: 5px;
        }
        .leftChat{
            width: 15%;
            min-height: 200px;
            background-color: #f7f2bc;
            border-radius: 6px;
            position: absolute;
            left: 15%;
        }
        .title{
            width: 100%;
            height: 10%;
            text-align: center;
            font-size: 30px;
            font-weight: bold;
            color: #019e66;
        }
        .m10{
            margin-right: 10px;
        }

    </style>
</head>
<body style="min-height: 800px;font-family: 'Microsoft YaHei',Arial;">

    <div class="leftChat">
        <div class="title" style="margin-bottom: 20px;">
            myChat
        </div>
        <div class="input-group">
            <span class="input-group-addon">你的昵称</span>
            <input id="inp_nickname" type="text" class="form-control" maxlength="10" value="谢春花">
            <span id="btn_getnick" class="input-group-btn">
                <button class="btn btn-default" type="button">
                    <i style="color: orange;" class="icon-refresh"></i>
                </button>
            </span>
        </div>

        <div id="user_count" class="list-group-item list-group-item-success" style="cursor:pointer;">
            <span id="nowusers_count" class="badge" style="background-color:green;">3</span>
            <i class="icon-comment m10"></i>当前在线人数
        </div>

        <div id="user_list" class="list-group" style="overflow-y: auto; height: 316px;">
            <a class="list-group-item" style="cursor: pointer;" >
                <i class=" icon-github-alt m10"></i>
                <span>街边的梧桐</span>
            </a>


            <a class="list-group-item" style="cursor: pointer;" >
                <i class=" icon-github-alt m10"></i>
                <span>冬天的风</span>
            </a>

        </div>

    </div>

    <div style="width: 40%;height: 700px;margin: 20px auto;border: 1px solid #84a4ea;border-radius: 7px;position: relative">
        <div style="width: 100%;height: 8%;background-color: #95afea;border-bottom: 1px solid #84a4ea;border-radius: 5px 5px 0 0;">
            <span style="height: 100%;line-height: 70px;font-size: 21px;padding-left: 20px;">正在制作漂亮的界面。。。</span>
        </div>

        <div id="content" style="overflow-y: auto;height: 75%;width: 100%;padding: 15px;border: 1px solid #eeeeee">

            <!---------------- 聊天框别人的消息模板 start --------------------->
            <div style="float: left">
                <div style="text-align: left">
                   <span style="font-weight: 900;color: #6d6d6d;">谢春花</span> &nbsp;
                    <span style="color: #d2d2d2;">21:37:20</span>
                </div>
                <div class="chat">
                    荏苒冬春去
                </div>
            </div>
            <div class="clear"></div>
            <!---------------- 聊天框别人的消息模板 end --------------------->

            <!---------------- 聊天框my的消息模板 start --------------------->
            <div style="float: right">
                <div style="text-align: right">
                    <span style="font-weight: 900;color: #6d6d6d;">谢春花</span>
                    <span style="color: #d2d2d2;">21:37:20</span>
                </div>
                <div class="myChat">
                    <span>借我</span>
                </div>
            </div>
            <div class="clear"></div>
            <!---------------- 聊天框my的消息模板 end --------------------->


        </div>

        <div style="width: 100%;height: 17%">
            <textarea id="sendMsg" maxlength="500" placeholder="请在此输入聊天信息" style="resize: none; width: 100%; height: 65%;padding: 6px 12px;font-size: 16px;border-radius: 4px;"></textarea>
            <button id="send" type="button" class="btn btn-primary right" style="width: 200px;height: 30%;">发送</button>
        </div>
    </div>



    <script src="res/js/jquery.min.js"></script>
    <script src="res/js/bootstrap.min.js"></script>
    <script src="res/layer/layer.js"></script>

    <%--<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8" charset="utf-8"></script>--%>
    <%--<script type="text/javascript">--%>
        <%--//var ip = returnCitySN['cip'];--%>
        <%--//var cityName = returnCitySN['cname'];--%>
    <%--</script>--%>
    <script>
    $(function(){
        
        $.ajax({
            url : "getNick.htm",
            dataType : "JSON",
            type : "post",
            data : {"word":"你好"},
            async:true,
            success : function(data){
//                alert(data.nick);
                $('#inp_nickname').val(data.nick);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }

        });

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
            alert('当前浏览器 Not support webocket')
        }

        //连接发生错误的回调方法
        websocket.onerror = function () {
            setMessageInnerHTML("WebSocket连接发生错误");
        };

        //连接成功建立的回调方法
        websocket.onopen = function () {
            var data = {
                'nick': $('#inp_nickname').val()
//                'city':returnCitySN['cname']
            };
            sendJson('join',data);
            layer.msg('在火炉旁，找个位子随便坐');
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            var data = JSON.parse(event.data);
            var act = data.act;

            if(act == "join"){
                //alert(data.nick);
                //alert(data.nickMap.length);
                for(var key in data.nickMap){
                    //alert(o);
                    //alert(data.nickMap[o]);
                   //alert(key+':'+ data.nickMap[key]);
                }
            }else if(act == "join"){

            }

            //setMessageInnerHTML(data.nick+': '+data.message);
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


    function sendJson(act,data){
        websocket.send(JSON.stringify({
            'act':act,
            'data':data
        }));
    }

    //发送消息
    function send() {
        var msg = $.trim($('#sendMsg').val());
        if(msg != null){
            var data = {
                'msg':msg,
            };
            sendJson('sendMsg',data);
            $('#sendMsg').val('');
            appendMy('谢春花','20:20:20',msg);
        }
    }

    function keepBottom() {
        var height = 0;
        $('.chatBody').each(function(){
            height += $(this).height();
        });
        $(".chatDiv").scrollTop(height);
    }

    function appendMy(name,time,msg){
        var html = "<div style=\"float: right\">"
                        +"<div style=\"text-align: right\">"
                            +"<span style=\"font-weight: 900;color: #6d6d6d;\">"+name+"</span> &nbsp;"
                            +"<span style=\"color: #d2d2d2;\">"+time+"</span>"
                        +"</div>"
                        +"<div class=\"myChat\">"
                            +"<span>"+msg+"</span>"
                        +"</div>"
                    +"</div>"
                    +"<div class=\"clear\"></div>";
        $('#content').append(html);
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



</script>
</body>
</html>