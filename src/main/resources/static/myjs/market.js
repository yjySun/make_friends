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

/*上传头像*/
$("button#lostButton").click(function () {

    var lostName = $("input#lostName").val();
    var description = $("textarea#description").val();
    var contactName = $("input#contactName").val();
    var contactPhone = $("input#contactPhone").val();

    var formData = new FormData();

    formData.append('lostImages1', $("input#lostImages1")[0].files[0]);
    formData.append('lostImages2', $("input#lostImages2")[0].files[0]);
    formData.append('lostImages3', $("input#lostImages3")[0].files[0]);

    formData.append('lostName',lostName);
    formData.append('description',description);
    formData.append('contactName',contactName);
    formData.append('contactPhone',contactPhone);
    formData.append('userId',$(this).attr("userId"));

    if(lostName == "" || description == "" || contactName == "" || contactPhone == ""){
        layer.msg("请将信息填写完整");
    }else{
        $.ajax({
            url:"/lost",
            type:"post",
            data: formData,
            cache: false, //上传文件不需要缓存
            processData: false, // 告诉jQuery不要去处理发送的数据
            contentType: false, // 告诉jQuery不要去设置Content-Type请求头
            success: function (data) {
                if(data == "addSuccess"){
                    layer.msg("信息上传成功");
                    setTimeout(function (){
                        window.location.reload();
                    },2000);
                }else {
                    layer.msg("信息上传失败");
                }
            },
            error: function (data) {
                layer.msg("信息上传失败");
            }
        })
    }
});

$("a#clickLost").click(function (){
    var mythis =  $(this);
    $.ajax({
        url:"/addLookCount",
        type:"post",
        data: {
            'id': $(this).attr('lostId')
        },
        success:function (data) {
            var newCount = parseInt(mythis.find("span#countDisplay").text())+1;
            mythis.find("span#countDisplay").html(newCount);
            mythis.parents("li.sortFrame").attr("lookCount",newCount);
        }
    })
});

$("button#deleteLost").click(function (){
    var mythis =  $(this);
    $.ajax({
        url:"/deleteLost",
        type:"get",
        data: {
            'uuid': mythis.attr('uuid')
        },
        success:function (data) {
            layer.msg('删除成功');
            setTimeout(function () {
                window.location.reload();
            },2000);
        }
    })
});

/*浏览数量排序*/
$("a#lookSort").click(function () {
    var sequence = $(this).attr("sequence");
    var ic = $("ul#sortUL");
    var divs = ic.find("li.sortFrame");
    var arr = divs.get();
    if (sequence.indexOf("asc") == 0) {
        arr.sort(function (a, b) {
            var ai = parseFloat($(a).attr("lookCount"), 10);
            var bi = parseFloat($(b).attr("lookCount"), 10);
            if (ai > bi) {
                return 1;
            } else if (ai < bi) {
                return -1;
            } else {
                return 0;
            }
        });
        ic.append(arr);
        $(this).attr("sequence", "desc");
    }else {
        arr.sort(function (a, b) {
            var ai = parseFloat($(a).attr("lookCount"), 10);
            var bi = parseFloat($(b).attr("lookCount"), 10);
            if (ai > bi) {
                return -1;
            } else if (ai < bi) {
                return 1;
            } else {
                return 0;
            }
        });
        ic.append(arr);
        $(this).attr("sequence", "asc");
    }
});

/*时间排序*/
$("a#timeSort").click(function () {
    var sequence = $(this).attr("sequence");
    var ic = $("ul#sortUL");
    var divs = ic.find("li.sortFrame");
    var arr = divs.get();
    if (sequence.indexOf("asc") == 0) {
        arr.sort(function (a, b) {
            var ai = parseFloat($(a).attr("pushTime"), 10);
            var bi = parseFloat($(b).attr("pushTime"), 10);
            if (ai > bi) {
                return 1;
            } else if (ai < bi) {
                return -1;
            } else {
                return 0;
            }
        });
        ic.append(arr);
        $(this).attr("sequence", "desc");
    }else {
        arr.sort(function (a, b) {
            var ai = parseFloat($(a).attr("pushTime"), 10);
            var bi = parseFloat($(b).attr("pushTime"), 10);
            if (ai > bi) {
                return -1;
            } else if (ai < bi) {
                return 1;
            } else {
                return 0;
            }
        });
        ic.append(arr);
        $(this).attr("sequence", "asc");
    }
});