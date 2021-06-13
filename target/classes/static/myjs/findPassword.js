function findPassword() {
    var email = $("input#emailPassword").val();
    if(email == ''){
        layer.msg("请输入注册账号的邮箱");
    }else{
        $.ajax({
            url: "/emailPassword",
            type: "post",
            data: {
                "email": $("input#emailPassword").val()
            },
            success: function (data) {
                if(data == "notExists"){
                    layer.msg("邮箱不存在");
                }else if(data == 'sendSuccess'){
                    layer.msg("密码已发送您的邮箱，请注意查收");
                    setTimeout(function () {
                        window.location.href = '/login';
                    },1500);
                }else{
                    layer.msg("找回密码失败，请稍后再试");
                }
            }
        })
    }
}

$("button#findButton").click(function (){
    findPassword();
});


$("input#emailPassword").keydown(function (e){
    if(e.keyCode == 13){
        findPassword();
    }
});

