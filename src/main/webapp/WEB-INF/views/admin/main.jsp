
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <style type="text/css">
        .test ul{
            list-style-type: none;

        }

        .test ul li{

            float:left;

            /*以下设置仅为方便查看效果*/
            width:200px;
            height:30px;
            border:1px solid black;
            text-align: center;
        }

        .test ul li ul{
            display: none;
        }

        .test li:hover ul{
            display:block;
        }

    </style>
</head>
<body>
<div class="test">

    <ul>
        <li><a href="logout">注销</a></li>
    </ul>
    <ul>
        <li>试卷管理
            <ul>
                <li><a href="/examnation/get/examnationjsp">查看试卷</a></li>
                <c:if test="${userType!='0'}">
                    <li><a href="/examnation/get/add">添加试卷</a></li>
                </c:if>

            </ul>
        </li>
    </ul>
    <ul>
        <li>试题管理
            <ul>
                <li><a href="/subject/get/subjectsjsp">查询试题</a></li>
                <c:if test="${userType!='0'}">
                    <li><a href="/subject/get/add">添加试题</a></li>
                </c:if>

            </ul>
        </li>
    </ul>
    <ul>
        <li><a href="/grade/get/gradejsp">查看成绩</a></li>
    </ul>
    <ul>
        <c:if test="${userType=='2'}">
            <li><a href="/getuserjsp">查询用户</a></li>
            <li><a href="/register">添加用户</a> </li>
        </c:if>
    </ul>
</div>
</body>
</html>
