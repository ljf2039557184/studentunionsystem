//个人资料数据


$(function(){
    var sid=null;
    $.ajax({
        url:"../get_student_info",
        type:"get",
        success:function(result){
            if(result.code==200){
                $(".sid").val(result.data.sid);
                $(".stuName").val(result.data.stuName);
                $(".image_head").attr("src","/image/"+result.data.stuImage);
                $(".stuSex").val(result.data.stuSex);
                $(".stuAge").val(result.data.stuAge);
                $(".stuCard").val(result.data.stuCard);
                $(".stuTel").val(result.data.stuTel);
                $(".stuBirthday").val(result.data.stuBirthday);
                $(".stuNation").val(result.data.nation);
                $(".stuNationPlace").val(result.data.nationPlace);
                $(".stuPoliticid").val(result.data.politicid);
                $(".stuQq").val(result.data.qq);
                $(".stuClass").val(result.data.stuClass);

            }
        }
    })


    var formdata = new FormData();
    //上传
    var dragImgUpload = new DragImgUpload("#drop_area",{
        callback:function (files) {
            //回调函数，可以传递给后台等等
            var file = files[0];


            formdata.append("files",file);
            formdata.append("sid",$(".sid").val());

        }
    })


    $("#fileUpdate").off("click").on("click",function(){

        $.ajax({
            url : '../ajaxupload',
            type : 'post',
            data : formdata,
            processData : false, //告诉jquery不要去处理请求数据的格式
            contentType : false, //告诉jquery不要设置请求头的类型
            success : function(result) {
                if(result.code==200){
                    $.message({
                        message:"修改成功",
                        type:'success',
                        duration:'3000',
                    })
                    $("#modals").modal("hide");
                    $.each(result.data, function(index, val){
                        $(".image_head").attr("src","/image/"+val);
                        $(".indeximage").attr("src","/image/"+val);
                    })
                }else{
                    $.message({
                        message:"修改失败",
                        type:'error',
                        duration:'3000',
                    });
                }

            }
        });
    })


})