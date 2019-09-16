
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
<form action="${pageContext.request.contextPath}/getlogin" method="post">
    <table class="display table-striped table-bordered table-hover table-condensed" cellspacing="0" width="30%" style="width: 400px;margin-left:auto;margin-right:auto;margin-top:100px; ">
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password" ></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="reset" value="重置" class="btn btn-primary">
                <input type="submit" value="提交" class="btn btn-primary">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
