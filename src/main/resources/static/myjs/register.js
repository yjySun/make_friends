(function (window, document, undefined) {
    'use strict';
    if (!('localStorage' in window)) return;
    var nightMode = localStorage.getItem('gmtNightMode');
    if (nightMode) {
        document.documentElement.className += ' dark';
    }
})(window, document);


(function (window, document, undefined) {

    'use strict';

    // Feature test
    if (!('localStorage' in window)) return;

    // Get our newly insert toggle
    var nightMode = document.querySelector('#night-mode');
    if (!nightMode) return;

    // When clicked, toggle night mode on or off
    nightMode.addEventListener('click', function (event) {
        event.preventDefault();
        document.documentElement.classList.toggle('dark');
        if (document.documentElement.classList.contains('dark')) {
            localStorage.setItem('gmtNightMode', true);
            return;
        }
        localStorage.removeItem('gmtNightMode');
    }, false);

})(window, document);

/*按钮60秒倒计时*/
var wait = 60;
function time(o) {
    if (wait == 0) {
        $(o).attr("disabled", false);
        $(o).html("获取验证码");
        wait = 60;
    } else {
        $(o).attr("disabled", true);
        o.html(wait + "秒后重新发送");
        wait--;
        setTimeout(function () {time(o);},1000);
    }
}


/*发送验证码*/
$("button#submitCode").click(function () {
    var email = $("input#registerEmail").val();
    if (email == ""){
        layer.msg("请输入邮箱地址");
    }else{
        $.ajax({
            url: "/sendEmail",
            type: "get",
            data: {
                "email": email
            },
            success: function (data) {
                if(data == "sendFailure"){
                    layer.msg("验证码发送失败，请检查邮箱地址");
                }else if(data == "emailExists"){
                    layer.msg("邮箱已存在");
                }else{
                    layer.msg("验证码发送成功，60秒后可重新发送");
                    time($('button#submitCode'));
                }
            }
        })
    }
});

$("button#registerButton").click(function () {
    var name = $("input#registerName").val();
    var email = $("input#registerEmail").val();
    var code = $("input#registerCode").val();
    var password = $("input#registerPassword").val();
    var confirmPassword = $("input#confirmPassword").val();
    if(name == "" || email == "" || code == "" || password == "" || confirmPassword == ""){
        layer.msg("请填写全部信息");
    }else if(password != confirmPassword){
        layer.msg("两次密码输入不一致");
    }else if(password.length < 6){
        layer.msg("为了安全密码长度必须超过6位");
    } else{
        $.ajax({
            url: "/registerUser",
            type: "post",
            data: {
                "username": name,
                "password": password,
                "email": email,
                "code": code
            },
            success: function (data) {
                if(data == "codeError"){
                    layer.msg("验证码输入错误");
                }else if(data == "nameExists"){
                    layer.msg("用户名已存在");
                }else if (data == "addSuccess"){
                    layer.msg("恭喜您，账号注册成功");
                    setTimeout(function (){
                        window.location.href = '/login'
                    },3000);
                }
            }
        })
    }
});

