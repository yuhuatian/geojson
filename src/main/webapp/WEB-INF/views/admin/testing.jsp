<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD//XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>jquery DataTables插件自定义分页ajax实现</title>
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css"/>

    <style>
        .table > tbody > tr > td,
        .table > tbody > tr > th,
        .table > tfoot > tr > td,
        .table > tfoot > tr > th,
        .table > thead > tr > td,
        .table > thead > tr > th {
            border-top: 1px solid #e7eaec;
            line-height: 1.42857;
            padding: 2px 16px;
            vertical-align: middle;
            text-align: center;
        }

        table tr td {
            padding-bottom: 0;
        }

        .btn-1 {
            margin: 0px;
            padding: 1px;
            width: 100%;
        }

        table {
            padding: 0;
            font-size: 14px;
        }

        #myAddModal .modal-body, #myAddModal td {
            padding: 0;
        }

        .clear {
            clear: both
        }

        #myAddModal .modal-content ul {
            margin: 0 auto 2%;
        }

        #myAddModal .modal-content ul li {
            list-style: none;
            float: left;
            margin: 0 auto;
        }

        .margin-right-16 {
            margin-right: 16px;
        }

        #myModal .modal-content {
            width: 360px;
            margin-top: 120px;
            margin-left: 160px;
            color: #FFFFFF;
            background: #FFFFFF;
            border-top: 4px #1a7bb9 solid;
            border-bottom: 2px #1a7bb9 solid;
            border-left: 3px #1a7bb9 solid;
            border-right: 1px #1a7bb9 solid;
            box-shadow: 4px 4px 12px #C0C0C0;
            border-radius: 8px;
        }

        #myModal .modal-content .modal-header {
            background: #1a7bb9;
            color: #000000;
            font-family: "微软雅黑";
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            border-radius: 4px;

        }

        #myModal .modal-content .modal-body {
            color: #000000;
            font-family: "微软雅黑";
            font-weight: bold;
            text-align: center;
            line-height: 32px;
        }

        #myModal .modal-content .modal-header .glyphicon {
            color: red;
        }

    </style>

    <script src="../../js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/jquery.dataTables.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/dataTables.bootstrap.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="row-fluid">
    <!--<h3>JQuery DataTables插件自定义分页Ajax实现</h3> -->
    <form action="#">
        <table id="example" class="display table-striped table-bordered table-hover table-condensed" cellspacing="0"
               width="100%">
            <thead>
            <c:forEach items="${list}" var="i">
                <tr id="${i.subjectId}">
                    <td>${i.subjectName}</td>
                    <td><input type="radio" id="${i.subjectId}" value="A"/>A.${i.subjectAnswera}</td>
                    <td><input type="radio" id="${i.subjectId}" value="B"/>B.${i.subjectAnswerb}</td>
                    <td><input type="radio" id="${i.subjectId}" value="C"/>C.${i.subjectAnswerc}</td>
                    <td><input type="radio" id="${i.subjectId}" value="D"/>D.${i.subjectAnswerd}</td>
                </tr>
            </c:forEach>

            </thead>
            <tr>
                <td colspan="5">
                    <input type="button" onclick="myfunction()" value="提交" class="btn btn-primary"/>
                </td>
            </tr>

        </table>
        <div>
            <a href="javascript:history.go(-1)">返回上一页</a>
        </div>
    </form>
</div>
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true" class="glyphicon glyphicon-remove"></span>
                </button>
                <h3 class="modal-title">提示</h3>
            </div>
            <div class="modal-body">
                <h3 id="mess"></h3>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div id="main" style="width:1000px;height: 600px">

</div>


<script type="text/javascript">

    function myfunction() {
        var jsonarray = [];
        //  var jsondata="jsondata=[";
        var inputs = document.getElementsByTagName("input");
        for (var i = 0; i < inputs.length; i++) {
            if (inputs[i].checked) {
                //         jsondata+='{"subjectId":"'+inputs[i].id+'","subjectAnswert":"'+inputs[i].value+'"},'
                var info = {"subjectId": inputs[i].id, "subjectAnswert": inputs[i].value};
                jsonarray.push(info);
            }


        }
        var json = JSON.stringify(jsonarray);
        /* jsondata=jsondata.substring(0,jsondata.length-1);
         jsondata+=']';*/
        $.ajax({

            url: "/grade/get/addgrade",
            data: {"jsondata": json},
            type: "post",
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (result) {
                if (result.code == 0) {
                    $("#myAddModal").modal('hide');
                    $("#myModal  #mess").text("操作成功！");
                    $("#myModal").modal('show');
                } else {
                    $("#myAddModal").modal('hide');
                    $("#myModal  #mess").text("操作失败！");
                    $("#myModal").modal('show');
                }
            }
        });
    }
</script>

</body>
</html>
