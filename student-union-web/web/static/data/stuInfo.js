//学生信息
//indexName  导航栏名字
//indeximage 导航栏图片
$(function(){
    $("#tologout").on("click",function(){
        var result = confirm("确定要退出吗？");
        if(result){
            location.href="../tologout";
        }
    })

    $.ajax({
        url:"../get_student_info",
        type:"get",
        success:function(result){
            if(result.code==200){
               $(".indexName").html(result.data.stuName);
               $(".side-department").attr("data-id",result.data.department.department);
               $(".indeximage").attr("src","/image/"+result.data.stuImage);
            }
        }
    })
})