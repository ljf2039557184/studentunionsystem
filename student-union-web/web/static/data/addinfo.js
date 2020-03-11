//录入信息

$(function(){

    var formdata = new FormData();
    var file;
//上传
    var dragImgUpload = new DragImgUpload("#drop_area",{
        callback:function (files) {
            //回调函数，可以传递给后台等等
            file = files[0];
            formdata.append("files",file);

        }



    })

    $("#fileUpdate").on("click",function(){
        $("#modals").modal("hide");
    })
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }


    $(document).on("click","#btnAdd",function(){

        var parm = $("#addStudent").serializeObject();
            parm = JSON.stringify(parm);



        formdata.append("jsonStr",parm);

        $.ajax({
            url:"../add_sutdent_info",
            type:"post",
            data:formdata,
            cache: false,
            processData : false,
            contentType : false,
            success:function(result){

                if(result.code==200){
                    $.message({
                        message:"录入成功",
                        type:'success',
                        duration:'3000',

                    });
                    window.location.href="department.html";
                }else{
                    $.message({
                        message:result.message,
                        type:'error',
                        duration:'3000',
                    });
                }
            }
        })
    });

    loadPosition();
    //加载职位
    function loadPosition(){
        $.ajax({
            url:"../list_position",
            method:"get",
            success:function(result){
                if(result.code==200){
                    $.each(result.data,function(index,obj){
                        $(".stuPosition").append("<option value='"+obj.pid+"'>"+obj.stuPosition+"</option>")
                    })
                }else{
                    alert(result.message);
                }
            }
        })
    }

    loadDepartment();
    //加载部门
    function loadDepartment(){
        $.ajax({
            url:"../list_department",
            method:"get",
            success:function(result){
                if(result.code==200){
                    $.each(result.data,function(index,obj){
                        $(".stuDepartment").append("<option value='"+obj.did+"'>"+obj.department+"</option>")
                    })
                }else{
                    alert(result.message);
                }
            }
        })
    }
})


