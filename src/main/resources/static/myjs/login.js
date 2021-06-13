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

    // 变换颜色
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

var index = 0;
function userLogin(){
    var usernameValue = $("input#username").val();
    var passwordValue = $("input#password").val();
    if(usernameValue == "" || passwordValue == ""){
        layer.msg("请输入用户名或密码");
    }else{
        index = layer.open({
            type: 1,
            title: false,
            skin: 'layui-layer-demo', //样式类名
            closeBtn: 0, //不显示关闭按钮
            anim: 2,
            shadeClose: true, //开启遮罩关闭
            content: $('#validation')
        });

    }
}

$("button#loginButton").click(function (){
    userLogin();
});

$("input#username").keydown(function (e){
    if(e.keyCode == 13){
        userLogin();
    }
});

$("input#password").keydown(function (e){
    if(e.keyCode == 13){
        userLogin();
    }
})

$(function () {
    if($.cookie('name')){
        $("input#checkbox1").attr('checked', true);
        $("input#username").val($.cookie('name'));
    }
    if($.cookie('password')){
        $("input#password").val($.cookie('password'));
    }
})

//滑动验证框
$('#captcha').sliderCaptcha({
    repeatIcon: 'fa fa-redo',
    setSrc: function () {
        return 'http://images.sdgxgz.com/Pic' + Math.round(Math.random() * 136) + '.jpg';
    },
    onSuccess: function () {
        $.ajax({
            url: "/userLogin",
            type: "post",
            data: {
                "account": $("input#username").val(),
                "password": $("input#password").val(),
                "remember": $("input#checkbox1").prop('checked')
            },
            success: function (data) {
                if(data == "loginFailure"){
                    layer.close(index);
                    layer.msg("账号或者密码错误");
                    setTimeout(function (){
                        window.location.reload();
                    },1500)
                }else if (data == 'resetPassword'){
                    window.location.href = '/resetPassword';
                }else{
                    window.location.href = '/index';
                }
            }
        });
    }
});
