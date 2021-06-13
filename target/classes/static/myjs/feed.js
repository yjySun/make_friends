
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

/**
 * 文本框根据输入内容自适应高度
 */
// var autoTextarea = function (elem, extra, maxHeight) {
//     extra = extra || 0;
//     var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
//         isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
//         addEvent = function (type, callback) {
//             elem.addEventListener ?
//                 elem.addEventListener(type, callback, false) :
//                 elem.attachEvent('on' + type, callback);
//         },
//         getStyle = elem.currentStyle ? function (name) {
//             var val = elem.currentStyle[name];
//
//             if (name === 'height' && val.search(/px/i) !== 1) {
//                 var rect = elem.getBoundingClientRect();
//                 return rect.bottom - rect.top -
//                     parseFloat(getStyle('paddingTop')) -
//                     parseFloat(getStyle('paddingBottom')) + 'px';
//             };
//
//             return val;
//         } : function (name) {
//             return getComputedStyle(elem, null)[name];
//         },
//         minHeight = parseFloat(getStyle('height'));
//
//     elem.style.resize = 'none';
//
//     var change = function () {
//         var scrollTop, height,
//             padding = 0,
//             style = elem.style;
//
//         if (elem._length === elem.value.length) return;
//         elem._length = elem.value.length;
//
//         if (!isFirefox && !isOpera) {
//             padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
//         };
//         scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
//
//         elem.style.height = minHeight + 'px';
//         if (elem.scrollHeight > minHeight) {
//             if (maxHeight && elem.scrollHeight > maxHeight) {
//                 height = maxHeight - padding;
//                 style.overflowY = 'auto';
//             } else {
//                 height = elem.scrollHeight - padding;
//                 style.overflowY = 'hidden';
//             };
//             style.height = height + extra + 'px';
//             scrollTop += parseInt(style.height) - elem.currHeight;
//             document.body.scrollTop = scrollTop;
//             document.documentElement.scrollTop = scrollTop;
//             elem.currHeight = parseInt(style.height);
//         };
//     };
//
//     addEvent('propertychange', change);
//     addEvent('input', change);
//     addEvent('focus', change);
//     change();
// };
// var text = document.getElementById("textarea");
// autoTextarea(text);// 调用

/**
 * 文本框根据输入内容自适应高度
 */

$("button#editFeed").click(function () {
    var trendsId = $(this).attr("trendsId");
    $("textarea[trendsId="+trendsId+"]").attr("disabled",false);
});

/*删除内容*/
$("button#deleteFeed").click(function () {
    $.ajax({
        url: "/deleteTrends",
        type: "get",
        data: {
            "trendsId": $(this).attr("trendsId")
        },
        success: function (data) {
            if(data == "deleteSuccess"){
                layer.msg("内容删除成功");
                setTimeout(function (){
                    window.location.reload();
                },2000);
            }else {
                layer.msg("内容删除失败");
            }
        }
    })
})

/*修改动态内容焦点离开后保存*/
$("textarea#textarea").blur(function (){
    $("textarea#textarea").attr("disabled",true);
    $.ajax({
        url: "/editTrends",
        type: "get",
        data: {
            "trendsId": $(this).attr("trendsId"),
            "content": $(this).val()
        },
        success: function (data) {
            if(data == "editSuccess"){
                layer.msg("内容修改成功");
            }else {
                layer.msg("内容修改失败，请检查修改内容");
            }
        }
    })
});

/*搜索动态*/
function search(){
    let searchContent = $("input#searchInfo").val();
    if(searchContent == ""){
        $("textarea#textarea").each(function(){
            $(this).parents("div#trendsDiv").show();
        });
        return;
    }
    $("textarea#textarea").each(function(){
        let content = $(this).val();
        if(content.search(searchContent) == -1){
            $(this).parents("div#trendsDiv").hide();
        }else{
            $(this).parents("div#trendsDiv").show();
        }
    });
};
$("input#searchInfo").keyup(function (){
    search();
});

/*点赞*/
$("a#praise").click(function () {
    var mythis = $(this);
    $.ajax({
        url: "/addPraise",
        type: "get",
        data: {
            "praiseUser": $(this).attr("praiseUser"),
            "praiseTrends": $(this).attr("praiseTrends")
        },
        success: function (data) {
            if(data == "addSuccess"){
                mythis.children("div#editColor").removeClass("text-black");
                mythis.children("div.editColor").addClass("text-pink-500");
                window.location.reload();
            }else {
                layer.msg("您已经点过赞，不可重复点赞");
            }
        }
    })
});

/*添加评论*/
$("a#addComment").click(function () {
    let mythis = $(this);
    if (mythis.parent("div").prev("input.commentContent").val() == ''){
        layer.msg('评论内容为空');
    }else{
        $.ajax({
            url: "/addComment",
            type: "get",
            data: {
                "commentContent": mythis.parents("div").prev("input.commentContent").val(),
                "trendsId": mythis.attr("trendsId"),
                "userId": mythis.attr("userId"),
                "userImages": mythis.attr("userImages")
            },
            success: function (data) {
                if(data == "addSuccess"){
                    window.location.reload();
                }else {
                    layer.msg("评论失败");
                }
            }
        })
    }
});

$("input.commentContent").keydown(function (e) {
    var mythis = $(this);
    if (e.keyCode == 13){
        if (mythis.val() == ''){
            layer.msg('评论内容为空');
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
                        window.location.reload();
                    }else {
                        layer.msg("评论失败");
                    }
                }
            })
        }
    }
});

$("button#addTrends").click(function () {
    console.log("我是内容："+$("textarea#content").val());
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
    console.log($(this).attr('commentTime'))
    index = layer.tips($(this).attr('commentTime'), '#'+$(this).attr('id'), {
        tips: 1,
        time: 0
    });
});
$("div.CommentContentDiv").mouseleave(function () {
    layer.close(index);
});



