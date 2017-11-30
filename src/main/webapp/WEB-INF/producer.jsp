<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/11/29
  Time: 上午11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>producer</title>
</head>
<body>
<h1>发送消息</h1>
<form action="/sendMessage" method="post">
    text:
    <textarea name="message" id="" cols="30" rows="10">
        测试信息
    </textarea>
    <br>
    <input type="submit" value="提交">
</form>
<h2>
    <a href="/home">返回首页</a>
</h2>

</body>
</html>
