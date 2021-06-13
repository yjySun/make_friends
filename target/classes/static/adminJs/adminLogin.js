function adminLogin() {
    var name = $("input#name").val();
    var password = $("input#password").val();
    if(name == '' || password == ''){
        layer.msg("请输入用户名或账户");
    }else{
        $.ajax({
            url: "/adminLogin",
            type: "post",
            data: {
                "adminName": name,
                "password": password
            },
            success: function (data) {
                if(data == "loginFailure"){
                    layer.msg("用户名或密码输入错误");
                }else {
                    window.location.href = '/adminIndex';
                }
            }
        })
    }
}
$("button#loginButton").click(function (){
    adminLogin();
});

$("input#name").keydown(function (e) {
    if(e.keyCode == 13){
        adminLogin();
    }
});

$("input#password").keydown(function (e) {
    if(e.keyCode == 13){
        adminLogin();
    }
});