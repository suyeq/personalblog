// 加载动画
function loading_start(item) {
    var loading_tmp = '<div class="loader"><div class="dot"></div><div class="dot"></div><div class="dot"></div></div>';
	item.append(loading_tmp);
}
function loading_finish(item) {
    item.children('.loader').remove();
}

// 评论样式
function commentLine() {
	$('.giligili-comments>li, .new-comment>li').each(function() {
		var tmp=$(this).find('.children');
		if(tmp.length>0) {
			$(this).children('.children').addClass('comment-line');
		} else {
		   	$(this).children('.commentator-comment').addClass('comment-line');
		}
	});
}

// 消息推送
function createMessage(message) {
    var time=1000
	if ($(".message").length > 0) {
		$(".message").remove();
	}
	$("body").append('<div class="message"><p class="message-info">' + message + '</p></div>');
	setTimeout("$('.message').remove()", time);
}

// 复制到剪切板
function CopyToClipboard(input) {
    var textToClipboard = input;
    var success = true;
    if (window.clipboardData) {
        window.clipboardData.setData ("Text", textToClipboard);
    }
    else {
        var forExecElement = CreateElementForExecCommand (textToClipboard);
        SelectContent (forExecElement);
        var supported = true;
        try {
            if (window.netscape && netscape.security) {
                netscape.security.PrivilegeManager.enablePrivilege ("UniversalXPConnect");
            }
            success = document.execCommand ("copy", false, null);
        } catch (e) {
            success = false;
        }
        document.body.removeChild (forExecElement);
    }
    if (success) {
        createMessage("复制成功！你可以直接粘贴！");
    } else {
        createMessage("抱歉，你的浏览器不支持使用剪切板!");
    }
}
function CreateElementForExecCommand (textToClipboard) {
    var forExecElement = document.createElement ("div");
    forExecElement.style.position = "absolute";
    forExecElement.style.left = "-10000px";
    forExecElement.style.top = "-10000px";
    forExecElement.textContent = textToClipboard;
    document.body.appendChild (forExecElement);
    forExecElement.contentEditable = true;
    return forExecElement;
}
function SelectContent (element) {
    var rangeToSelect = document.createRange ();
    rangeToSelect.selectNodeContents (element);
    var selection = window.getSelection ();
    selection.removeAllRanges ();
    selection.addRange (rangeToSelect);
}

// 获取meta信息
function getMetaContentByName(name) {
  	return (document.getElementsByName(name)[0] || 0).content;
}

// 移除touch二次点击
function removeTouch() {
    $('.no-touch').on('touchstart', function() {
      	$(this).removeClass('no-touch');
    }); 
}

// 阅读更多
function pageReadMore() {
	$('#index-pagination a').on('click',function(e) {
	    console.log('kkkk')
        e.preventDefault();
        $(this).hide();
        var href = $(this).attr("href");
        loading_start($('#index-pagination'));
        if (href != undefined) {
          	$.ajax({ 
            	url: href,
                type: "get",
                error: function(request) {
                    alert('加载错误!请联系网站管理员！');
                },
                success: function(data) {
                    loading_finish($('#index-pagination'));
                    var $result = $(data).find("#main-content .giligili-item");
                    console.log($result.length)
                    $('#main-content').append($result.fadeIn(1000));
                    var nexthref = $(data).find("#index-pagination a").attr("href");
                   if ($result.length>=1){
                       $("#index-pagination a").show();
                       $("#index-pagination a").text('(｡・`ω´･)点我查看更多！');
                       $("#index-pagination a").attr("href", nexthref);
                   } else {
                       $("#index-pagination a").remove();
                       $("#index-pagination").html('<p>你已到达了世界的尽头(｡・`ω´･)！</p>');
                   }

                    // if (nexthref != undefined) {
                    //   $("#index-pagination a").show();
                    //   $("#index-pagination a").text('(｡・`ω´･)点我查看更多！');
                    //   $("#index-pagination a").attr("href", nexthref);
                    // } else {
                    //   $("#index-pagination a").remove();
                    //   $("#index-pagination").html('<p>你已到达了世界的尽头(｡・`ω´･)！</p>');
                    // }
                    removeTouch();
                }
          	});
        }
   }); 
}

function fancyboxinit() {
    $('#main-content .post img:not(.OwO-img, .avatar)').each(function(i){
        if (! this.parentNode.href) {
            $(this).wrap("<a href='"+this.src+"' data-fancybox='gallery' class='no-des'></a>");
            $(this.parentNode).wrap("<div></div>");
        }else {
            $(this.parentNode).attr("data-fancybox","gallery");
          	$(this.parentNode).addClass("no-des");
            $(this.parentNode).wrap("<div></div>");
        }
    });
}