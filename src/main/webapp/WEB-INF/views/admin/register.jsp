
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
    <script src="/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src=/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/jquery.dataTables.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/dataTables.bootstrap.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/getregister" method="post">
    <table class="display table-striped table-bordered table-hover table-condensed" cellspacing="0" width="100%" >
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="userPassword" ></td>
        </tr>
        <tr>
            <td>班级：</td>
            <td><input type="text" name="userClass"></td>
        </tr>
        <tr>
            <td>类型：</td>
            <td><input type="radio" value="0" name="userType">学生
            <input type="radio" value="1" name="userType">教室
            <input type="radio" value="2" name="userType">管理员</td>
        </tr>
        <tr>
            <td align="center" colspan="2">
                <input type="reset" value="重置" class="btn btn-primary">
                <input type="submit" value="提交" class="btn btn-primary">
            </td>

        </tr>
    </table>
    <div>
        <a href="javascript:history.go(-1)">返回上一页</a>
    </div>
</form>
</body>
</html>
