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


//提交修改的信息
$("button#saveInfo").click(function () {
    var id = $(this).attr("userID");
    var username = $("input#username").val();
    var sex = $("select#sex").val();
    var college = $("input#college").val();
    var email = $("input#email").val();
    var professional = $("input#professional").val();
    var introduce = $("textarea#introduce").val();
    if(username == ""){
        layer.msg("名字不可为空");
    }else{
        $.ajax({
            url: "/editUser",
            type: "get",
            data: {
                "id": id,
                "username": username,
                "sex": sex,
                "college": college,
                "email":  email,
                "professional": professional,
                "introduce": introduce
            },
            success: function (data) {
                if(data == "usernameExist"){
                    layer.msg("名字与他人重复，请修改名字");
                }else if(data == "editFailure"){
                    layer.msg("修改失败，请检查数据");
                } else{
                    layer.msg("修改成功");
                    setTimeout(function () {
                        window.location.href = '/index';
                    }, 1500);
                }
            }
        })
    }
});


