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

//页面刷新指定到原先滚动条位置
window.onbeforeunload = function() {
    var scrollPos;
    if(typeof window.pageYOffset != 'undefined') {
        scrollPos = window.pageYOffset;
    } else if(typeof document.compatMode != 'undefined' &&
        document.compatMode != 'BackCompat') {
        scrollPos = document.documentElement.scrollTop;
    } else if(typeof document.body != 'undefined') {
        scrollPos = document.body.scrollTop;
    }
    document.cookie = "scrollTop=" + scrollPos; //存储滚动条位置到cookies中
}

window.onload = function() {
    if(document.cookie.match(/scrollTop=([^;]+)(;|$)/) != null) {
        var arr = document.cookie.match(/scrollTop=([^;]+)(;|$)/); //cookies中不为空，则读取滚动条位置
        document.documentElement.scrollTop = parseInt(arr[1]);
        document.body.scrollTop = parseInt(arr[1]);
    }
}

/*添加评论*/
$("a#addComment").click(function () {
    var mythis = $(this);
    if (mythis.parents("div").prev("input#commentContent").val() == ''){
        layer.msg("评论内容为空");
    }else{
        $.ajax({
            url: "/addComment",
            type: "get",
            data: {
                "commentContent": mythis.parents("div").prev("input#commentContent").val(),
                "trendsId": mythis.attr("trendsId"),
                "userId": mythis.attr("userId"),
                "userImages": mythis.attr("userImages")
            },
            success: function (data) {
                if(data == "addSuccess"){
                    layer.msg("评论成功");
                    setTimeout(function () {
                        window.location.reload();
                    },1500)

                }else {
                    layer.msg("评论失败");
                }
            }
        })
    }
});

$("input.commentContent").keydown(function (e) {
    var mythis = $(this);
    if(e.keyCode == 13){
        if (mythis.val() == ''){
            layer.msg("评论内容为空");
        }else{
            $.ajax({
                url: "/addComment",
                type: "get",
                data: {
                    "commentContent": mythis.val(),
                    "trendsId": mythis.attr("trendsId"),
                    "userId": mythis.attr("userId"),
                    "userImages": mythis.attr("userImages")
                },
                success: function (data) {
                    if(data == "addSuccess"){
                        layer.msg("评论成功");
                        setTimeout(function () {
                            window.location.reload();
                        },1500)
                    }else {
                        layer.msg("评论失败");
                    }
                }
            });
        }
    }
});

/*点赞*/
$("a#praise").click(function () {
    var mythis = $(this);
    var uuid = mythis.attr("uuid");
    $.ajax({
        url: "/addPraise",
        type: "get",
        data: {
            "praiseUser": $(this).attr("praiseUser"),
            "praiseTrends": $(this).attr("praiseTrends")
        },
        success: function (data) {
            if(data == "addSuccess"){
                //模态窗口的点赞变颜色
                mythis.children("div.editColor").children("i.editColor").remove();
                mythis.children("div.editColor").children("ion-icon.editColor").css("display","block");
                mythis.children("div.editColor").addClass("text-pink-500");
                //探索首页的点赞变颜色和数量
                $("ion-icon#"+uuid).addClass("text-pink-500");
                $("span#"+uuid).text($(this).text()+1);
                $("span#"+uuid).addClass("text-pink-500");
                layer.msg("点赞成功");
                setTimeout(function () {
                    window.location.reload();
                },1500)
            }else {
                layer.msg("您已经点过赞，不可重复点赞");
            }
        }
    })
});

/*时间排序*/
$("a#timeSort").click(function () {
    var sequence = $(this).attr("sequence");
    var ic = $("div#sortDiv");
    var divs = ic.find("div.sortFrame");
    var arr = divs.get();
    if (sequence.indexOf("asc") == 0) {
        arr.sort(function (a, b) {
            var ai = parseFloat($(a).attr("trendsTime"), 10);
            var bi = parseFloat($(b).attr("trendsTime"), 10);
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
            var ai = parseFloat($(a).attr("trendsTime"), 10);
            var bi = parseFloat($(b).attr("trendsTime"), 10);
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

/*点赞排序*/
$("a#praiseSort").click(function () {
    var sequence = $(this).attr("sequence");
    var ic = $("div#sortDiv");
    var divs = ic.find("div.sortFrame");
    var arr = divs.get();
    if (sequence.indexOf("asc") == 0) {
        arr.sort(function (a, b) {
            var ai = parseFloat($(a).attr("praiseCount"), 10);
            var bi = parseFloat($(b).attr("praiseCount"), 10);
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
            var ai = parseFloat($(a).attr("praiseCount"), 10);
            var bi = parseFloat($(b).attr("praiseCount"), 10);
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

/*评论排序*/
$("a#commentSort").click(function () {
    var sequence = $(this).attr("sequence");
    var ic = $("div#sortDiv");
    var divs = ic.find("div.sortFrame");
    var arr = divs.get();
    if (sequence.indexOf("asc") == 0) {
        arr.sort(function (a, b) {
            var ai = parseFloat($(a).attr("commentCount"), 10);
            var bi = parseFloat($(b).attr("commentCount"), 10);
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
            var ai = parseFloat($(a).attr("commentCount"), 10);
            var bi = parseFloat($(b).attr("commentCount"), 10);
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

/*发布动态*/
$("button#addTrends").click(function () {
    if($("textarea#content").val() == ""){
        layer.msg("内容为空，不可发布")
    }else{
        $.ajax({
            url: "/addTrends",
            type: "get",
            data: {
                "content": $("textarea#content").val(),
                "userId": $("textarea#content").attr("userId")
            },
            success: function (data) {
                if(data == "addSuccess"){
                    layer.msg("动态发布成功");
                    setTimeout(function () {
                        window.location.reload();
                    },2000);
                }else {
                    layer.msg("动态发布失败");
                }
            }
        })
    }
});

/*评论时间提示*/
var index = 0;
$("div.CommentContentDiv").hover(function () {
    index = layer.tips($(this).attr('commentTime'), '#'+$(this).attr('id'), {
        tips: 1,
        time: 0
    });
});
$("div.CommentContentDiv").mouseleave(function () {
    layer.close(index);
});