//部门信息


$(function () {
    $.ajax({
        url : '../student_info',
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
        $('#tbodys').append(
            '<tr>' +
            '<td>'+obj.sid+'</td>'+
            '<td>'+obj.stuName+'</td>'+
            '<td>'+obj.stuClass+'</td>'+
            '<td>'+obj.stuTel+'</td>'+
            '<td>'+obj.department.department+'</td>'+
            '<td><button name="select-btn" class="btn btn-info select-btn btndelete" value="" data-id="'+obj.sid+'">删除</button><button name="select-btn" class="btn btn-info select-btn btnsid" value="" data-id="'+obj.sid+'" style="margin-left: 10px;">查看</button></td>'+
            '</tr>'


        );
    });
}

//分页
function page(pageInfo){
    $("#page").pagination(pageInfo.total, { //第一个参数指定共多少条记录
        items_per_page:pageInfo.pageSize, // 每页显示多少条记录
        next_text:">", //下一页按钮图标
        prev_text:"<", //上一页按钮图标
        num_display_entries:5,//主体页数
        num_edge_entries: 2, //边缘页数
        callback: function(index){//定义一个回调函数，用于每次点击页码发起分页查询请求
            //index为当前页码，只不过下标是从0开始，因此需要+1操作
            var pageNum = ++index;
            $.ajax({
                url : '../student_info',
                method : 'get',
                data : {'pageNum' : pageNum, 'pageSize' : 5},
                success : function(result){
                    //渲染表格
                    addTable(result.data);
                }
            });
        }
    });


    //查看信息
    $(document).on("click",".btnsid",function(){
        $.ajax({
            url:"../get_student_id",
            type:"get",
            data:"sid="+$(this).attr("data-id"),
            success:function(result){
                if(result.code==200){
                    $(".sid").val(result.data.sid);
                    $(".stuName").val(result.data.stuName);
                    $(".image_head").attr("src","/image/"+result.data.stuImage);
                    $(".stuSex").val(result.data.stuSex);
                    if(result.data.stuSex=="女"){
                        $("#sex_man").prop("checked", "checked");
                    }else{
                        $("#sex_woman").prop("checked", "checked");
                    }
                    $(".stuEmail").val(result.data.stuEmail);
                    $(".stuAge").val(result.data.stuAge);
                    $(".stuCard").val(result.data.stuCard);
                    $(".stuTel").val(result.data.stuTel);
                    $(".stuBirthday").val(result.data.stuBirthday);
                    $(".stuNation").val(result.data.nation);
                    $(".stuNationPlace").val(result.data.nationPlace);
                    $(".stuPoliticid").val(result.data.politicid);
                    $(".stuQq").val(result.data.qq);
                    $(".stuPosition").val(result.data.position.stuPosition);
                    $(".stuDepartment").val(result.data.department.department);
                    $(".stuClass").val(result.data.stuClass);
                }
            }
        })
        $("#modals").modal("show");

    })


    //模糊查询
    $(document).on("click",".btnsubmit",function(){
        $.ajax({
            url : '../get_student_find',
            type : 'get',
            data: "findValue="+$('#searchInput').val(),
            success : function(result){
                if(result.code == 200){
                    $('#all_list tr:not(:first)').remove();
                    $.each(result.data, function(index, obj){
                        $('#tbodys').append(
                            '<tr>' +
                            '<td>'+obj.sid+'</td>'+
                            '<td>'+obj.stuName+'</td>'+
                            '<td>'+obj.stuClass+'</td>'+
                            '<td>'+obj.stuTel+'</td>'+
                            '<td>'+obj.department.department+'</td>'+
                            '<td><button name="select-btn" class="btn btn-info select-btn btndelete" value="" data-id="'+obj.sid+'">删除</button><button name="select-btn" class="btn btn-info select-btn btnsid" value="" data-id="'+obj.sid+'" style="margin-left: 10px;" >查看</button></td>'+
                            '</tr>'

                        );
                    });

                }else{
                    alert(result.message);

                }
            }
        });
    })


    //删除
    $(document).on("click",".btndelete",function(){
        $.ajax({
            url : '../delete_student_id',
            type : 'get',
            data: "sid="+$(this).attr("data-id"),
            success : function(result){
                if(result.code == 200){

                    $.message({
                        message:"删除成功",
                        type:'success',
                        duration:'3000',

                    })
                    window.location.href="department.html";


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