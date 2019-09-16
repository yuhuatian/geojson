
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD//XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!DOCTYPE html>

<head>
    <title>jquery DataTables插件自定义分页ajax实现</title>
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css"/>

    <style>
        .table>tbody>tr>td,
        .table>tbody>tr>th,
        .table>tfoot>tr>td,
        .table>tfoot>tr>th,
        .table>thead>tr>td,
        .table>thead>tr>th {
            border-top: 1px solid #e7eaec;
            line-height: 1.42857;
            padding: 2px 16px;
            vertical-align: middle;
            text-align: center;
        }

        table tr td{padding-bottom: 0;}
        .btn-1 {margin: 0px; padding: 1px; width: 100%;}
        table{padding: 0; font-size: 14px;}
        #myAddModal .modal-body, #myAddModal td{
            padding: 0;
        }

        .clear{ clear:both}
        #myAddModal .modal-content ul {margin: 0 auto 2%;}

        #myAddModal .modal-content ul li{
            list-style: none;
            float: left;
            margin: 0 auto;
        }

        .margin-right-16{margin-right: 16px;}

        #myModal .modal-content{
            width: 360px;
            margin-top: 120px;
            margin-left: 160px;
            color:#FFFFFF;
            background: #FFFFFF;
            border-top: 4px #1a7bb9 solid;
            border-bottom: 2px #1a7bb9 solid;
            border-left: 3px #1a7bb9 solid;
            border-right: 1px #1a7bb9 solid;
            box-shadow: 4px 4px 12px #C0C0C0;
            border-radius:8px;
        }

        #myModal .modal-content .modal-header{
            background:#1a7bb9;
            color: #000000;
            font-family: "微软雅黑";
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            border-radius:4px;

        }

        #myModal .modal-content .modal-body{
            color: #000000;
            font-family: "微软雅黑";
            font-weight: bold;
            text-align: center;
            line-height:32px;
        }

        #myModal .modal-content .modal-header .glyphicon{
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
    <table id="example" class="display table-striped table-bordered table-hover table-condensed" cellspacing="0" width="100%">
        <thead>
        <tr>
            <td>试卷名称</td>
            <td>试卷类型</td>
            <td>试卷状态</td>
            <td>操作</td>
        </tr>

        </thead>

    </table>
    <div>
        <a href="javascript:history.go(-1)">返回上一页</a>
    </div>
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
<input type="text" value="${userType}" id="userType"/>
<div id="main" style="width:1000px;height: 600px">

</div>

<script src="http://cdn.bootcss.com/datatables/1.10.11/js/jquery.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script src="http://cdn.bootcss.com/datatables/1.10.11/js/dataTables.bootstrap.min.js"></script>
<script src="../../js/handlebars-v3.0.1.js"type="text/javascript" charset="utf-8"></script>
<script id="tpl" type="text/x-handlebars-template">
    {{#each func}}
    <button type="button" class="btn btn-{{this.type}} btn-sm" onclick="{{this.fn}}">{{this.name}}</button>
    {{/each}}
</script>

<%--<script type="text/javascript">
    var jsondata={};
    var nTrs = $("#submit").fnGetNodes();//fnGetNodes获取表格所有行，nTrs[i]表示第i行tr对象
    for(var i = 0; i < nTrs.length; i++){
        if($(nTrs[i]).hasClass('selected')){
           // console.log('[获取数据]' + table.fnGetData(nTrs[i]));//fnGetData获取一行的数据
            jsondata.data={table.},;
        }
    }

    $.ajax({
        type: "POST",
        url: "/get/testdata",
        cache: false,  //禁用缓存
        data: jsondata,  //传入组装的参数
        dataType: "json",
        success:function(data){
            if(data) {

                $("#myAddModal").modal('hide');
                $("#myModal  #mess").text("操作成功！");
                $("#myModal").modal('show');
                //
                $('#example').DataTable().ajax.reload(null, false);


            } else {
                $("#myAddModal").modal('hide');
                $("#myModal  #mess").text("操作失败！");
                $("#myModal").modal('show');
                $('#example').DataTable().ajax.reload(null, false);
            }
        }
    });
</script>--%>
<script type="text/javascript">

    $(function () {
        auto();
    });
    $("#userType").hide();

    function query(examnationId) {

        $.ajax({
            type: "POST",
            url: "/examnation/get/examnationdetail",
            cache: false,  //禁用缓存
            data: {"examnationId":examnationId},  //传入组装的参数
            dataType: "json",
            success:function (result) {
                if(result!=null){
                    window.location.href=result.data;
                   // alert(result.data);
                }
                else{
                    alert(result.msg);
                }
            },
            err:function () {
                alert(url);
            }

        });
    }
    function del(examnationId) {
        //console.log(userId);
        $.ajax({
            url: "/examnation/get/delete",
            type: "POST",
            dataType:"json",
            data: {
                "examnationId": examnationId
            }, success: function (result) {
                if(result.code==0) {
                    $("#myAddModal").modal('hide');
                    $("#myModal  #mess").text("操作成功！");
                    $("#myModal").modal('show');
                    //
                    $('#example').DataTable().ajax.reload(null, false);


                } else {
                    $("#myAddModal").modal('hide');
                    $("#myModal  #mess").text("操作失败！");
                    $("#myModal").modal('show');
                    $('#example').DataTable().ajax.reload(null, false);
                }
            }
        });
    }

    function auto() {


        var table;
        var editFlag = false;
        var tpl = $("#tpl").html();
        //预编译模板
        var template = Handlebars.compile(tpl);
        //提示信息
        var lang = {
            "sProcessing": "处理中...",
            "sLengthMenu": "每页 _MENU_ 项",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
            "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页",
                "sJump": "跳转"
            },
            /* "oAria": {
                 "sSortAscending": ": 以升序排列此列",
                 "sSortDescending": ": 以降序排列此列"
             }*/
        };
        var userType=$("#userType").val();
        var context={};
        //初始化表格
        var table = $("#example").dataTable({
            language:lang,  //提示信息
            autoWidth: false,  //禁用自动调整列宽
            stripeClasses: ["odd", "even"],  //为奇偶行加上样式，兼容不支持CSS伪类的场合
            processing: true,  //隐藏加载提示,自行处理
            serverSide: true,  //启用服务器端分页
            searching: false,  //禁用原生搜索
            orderMulti: false,  //启用多列排序
            order: [],  //取消默认排序查询,否则复选框一列会出现小箭头
            renderer: "bootstrap",  //渲染样式：Bootstrap和jquery-ui
            pagingType: "simple_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
            columnDefs: [{
                "targets": 'nosort',  //列的样式名
                "orderable": false    //包含上样式名‘nosort’的禁止排序
            }],
            ajax: function (data, callback, settings) {
                //封装请求参数
                var param = {};
                param.limit = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
                param.start = data.start;//开始的记录序号
                param.page = (data.start / data.length)+1;//当前页码
                //console.log(param);
                //ajax请求数据
                $.ajax({
                    type: "POST",
                    url: "/examnation/get/examnation",
                    cache: false,  //禁用缓存
                    data: param,  //传入组装的参数
                    dataType: "json",
                    success: function (result) {
                        //console.log(result);
                        //setTimeout仅为测试延迟效果
                        setTimeout(function () {
                            //封装返回数据
                            var returnData = {};
                            returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                            returnData.recordsTotal = result.total;//返回数据全部记录
                            returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                            returnData.data = result.data;//返回的数据列表
                            //console.log(returnData);
                            //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                            //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                            callback(returnData);
                        }, 200);
                    }
                });
            },
            //列表表头字段
            columns: [
                { "data": "examnationName"},
                { "data": "examnationType" },
                { "data": "examnationStatus" },
                { "data": null},
            ],
            columnDefs: [

                {
                    "targets": 3,
                    "render": function (a, b, c, d) {
                        if (userType==0){
                            var context =
                                {

                                    func: [//fn:表示函数；edit():对应的函数
                                       {"name": "开始考试", "fn": "query(\'" + c.examnationId + "\')", "type": "primary"}
                                    ]
                                };
                        }else {
                            var context =
                                {

                                    func: [//fn:表示函数；edit():对应的函数
                                        {"name": "删除", "fn": "del(\'" + c.examnationId + "\')", "type": "danger"},
                                        {"name": "查看详情", "fn": "query(\'" + c.examnationId + "\')", "type": "primary"}
                                    ]
                                };
                        }

                        var html = template(context);
                        return html;
                    }
                }

            ]
        }).api();}
    //此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

</script>


</body>
</html>

