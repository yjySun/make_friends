function resetPassword() {
    var resetPassword = $("input#resetPassword").val();
    var confirmResetPassword = $("input#confirmResetPassword").val();

    if(resetPassword == "" || confirmResetPassword == ""){
        layer.msg("请填写密码或者确认密码");
    }else if (resetPassword != confirmResetPassword){
        layer.msg("两次密码输入不一致");
    }else if(confirmResetPassword.length < 6){
        layer.msg("为了安全密码长度必须超过6位");
    }else{
        $.ajax({
            url: "/resetUserPassword",
            type: "post",
            data: {
                "resetPassword": confirmResetPassword
            },
            success: function (data) {
                if(data == "resetFailure"){
                    layer.msg("重置密码失败，请稍后再试");
                }else{
                    layer.msg("密码已重置，请登录");
                    setTimeout(function () {
                        window.location.href = '/login';
                    },1500);
                }
            }
        })
    }
}

$("button#resetButton").click(function (){
    resetPassword();
});

$("input#confirmResetPassword").keydown(function (e){
    if(e.keyCode == 13){
        resetPassword();
    }
});

$("input#resetPassword").keydown(function (e){
    if(e.keyCode == 13){
        resetPassword();
    }
});

