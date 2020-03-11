//资料存放

$(function () {
    $.ajax({
        url : '../list_journal',
        type : 'get',
        data : {'pageNum' : 1, 'pageSize' : 5},
        success : function(result){
            if(result.code == 500){
                alert(result.message);
            }else{
                //渲染表格
                addTable(result.data);
                //渲染分页
                page(result.data);
            }
        }
    });
})

//渲染表格
function addTable(pageInfo){
    $('#all_list tr:not(:first)').remove();
    $.each(pageInfo.list, function(index, obj){
        index = index +1;
        $('#tbodys').append(
            '<tr>' +
            '<td>'+index+'</td>'+
            '<td>'+obj.stuOperator+'</td>'+
            '<td>'+obj.stuOperation+'</td>'+
            '<td>'+obj.operatorDate+'</td>'+
            '<td><a href="../file_downloads?fileName='+obj.operatorDate+'">下载</a> </td>'+
            '<td><button name="select-btn" class="btn btn-info select-btn btndelete" value="" data-id="'+obj.jid+'" data-filter-name="'+obj.operatorDate+'">删除</button></td>'+
            '</tr>'


        );
    });
}

//分页
function page(pageInfo) {
    $("#page").pagination(pageInfo.total, { //第一个参数指定共多少条记录
        items_per_page: pageInfo.pageSize, // 每页显示多少条记录
        next_text: ">", //下一页按钮图标
        prev_text: "<", //上一页按钮图标
        num_display_entries: 5,//主体页数
        num_edge_entries: 2, //边缘页数
        callback: function (index) {//定义一个回调函数，用于每次点击页码发起分页查询请求
            //index为当前页码，只不过下标是从0开始，因此需要+1操作
            var pageNum = ++index;
            $.ajax({
                url: '../list_journal',
                method: 'get',
                data: {'pageNum': pageNum, 'pageSize': 5},
                success: function (result) {
                    //渲染表格
                    addTable(result.data);
                }
            });
        }
    });

    //模糊查询
    $(document).on("click",".btnsubmit",function(){
        $.ajax({
            url : '../get_journal',
            type : 'get',
            data: {'pageNum' : 1, 'pageSize' : 5,'findValue':$('#searchInput').val()},
            success : function(result){
                if(result.code == 200){
                    //渲染表格
                    addTable(result.data);
                    //渲染分页
                    page(result.data);


                }else{
                    alert(result.message);

                }
            }
        });
    })

    //删除
    $(document).on("click",".btndelete",function(){

        $.ajax({
            url : '../delete_journal_id',
            type : 'get',
            data: {'fileId':$(this).attr("data-id"),'fileName':$(this).attr("data-filter-name")},
            success : function(result){
                if(result.code == 200){

                    $.message({
                        message:"删除成功",
                        type:'success',
                        duration:'3000',

                    })
                    window.location.href="fileupload.html";


                }else{
                    $.message({
                        message:result.message,
                        type:'error',
                        duration:'3000',
                    });

                }
            }
        });
    })


}
