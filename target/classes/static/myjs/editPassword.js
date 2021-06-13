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


$("button#savePassword").click(function () {
    var id = $(this).attr("userID");
    var originalPassword = $("input#originalPassword").val();
    var password = $("input#password").val();
    var confirmPassword = $("input#confirmPassword").val();

    if (originalPassword == "" || password == "" || confirmPassword == "") {
        layer.msg("输入的密码不能为空");
    } else if (password != confirmPassword) {
        layer.msg("新密码两次输入不一致");
    } else if (password.length < 6) {
        layer.msg("为了安全密码长度必须超过6位");
    } else {
        $.ajax({
            url: "/editPassword",
            type: "post",
            data: {
                "id": id,
                "password": originalPassword,
                "confirmPassword": password
            },
            success: function (data) {
                if (data == "editFailure") {
                    layer.msg("密码修改失败，原密码输入错误");
                } else {
                    layer.msg("密码修改成功");
                    setTimeout(function () {
                        window.location.href = '/index';
                    }, 1500);
                }
            }
        });
    }
});
