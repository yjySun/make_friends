/*上传头像*/
$("input#uploadInput").change(function () {
    var formData = new FormData();
    formData.append('uploadFile', $(this)[0].files[0]);
    formData.append('id',$(this).attr("userId"));
    formData.append('originalImages',$(this).attr("originalImages"));
    if ($(this)[0].files[0]){
        $.ajax({
            url:"/upload",
            type:"post",
            data: formData,
            cache: false, //上传文件不需要缓存
            processData: false, // 告诉jQuery不要去处理发送的数据
            contentType: false, // 告诉jQuery不要去设置Content-Type请求头
            success: function (data) {
                if(data == "uploadSuccess"){
                    layer.msg("头像上传成功");
                    setTimeout(function (){
                        window.location.reload();
                    },2000);
                }else {
                    layer.msg("头像上传失败");
                }
            },
            error: function (data) {
                layer.msg("头像上传失败");
            }
        })
    }
});

/*点击左上角图片页面返回顶部*/
$("a#topClick").click(function () {
    console.log("top")
    $("html,body").animate({
        scrollTop: 0,
        screenLeft: 0,
    }, 400);
})
