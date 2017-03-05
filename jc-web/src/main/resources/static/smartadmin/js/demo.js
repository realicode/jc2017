$('#main')
//.append('<div class="demo"><span id="demo-setting"><i class="fa fa-cog txt-color-blueDark"></i></span> <form><legend class="no-padding margin-bottom-10">Layout Options</legend><section><label><input name="subscription" id="smart-fixed-header" type="checkbox" class="checkbox style-0"><span>Fixed Header</span></label><label><input type="checkbox" name="terms" id="smart-fixed-navigation" class="checkbox style-0"><span>Fixed Navigation</span></label><label><input type="checkbox" name="terms" id="smart-fixed-ribbon" class="checkbox style-0"><span>Fixed Ribbon</span></label><label><input type="checkbox" name="terms" id="smart-fixed-footer" class="checkbox style-0"><span>Fixed Footer</span></label><label><input type="checkbox" name="terms" id="smart-fixed-container" class="checkbox style-0"><span>Inside <b>.container</b> <div class="font-xs text-right">(non-responsive)</div></span></label><label style="display:block;"><input type="checkbox" id="smart-topmenu" class="checkbox style-0"><span>Menu on <b>top</b></span></label><label style="display:block;"><input type="checkbox" id="colorblind-friendly" class="checkbox style-0"><span>For Colorblind <div class="font-xs text-right">(experimental)</div></span></label> <span id="smart-bgimages"></span></section><section><h6 class="margin-top-10 semi-bold margin-bottom-5">Clear Localstorage</h6><a href="javascript:void(0);" class="btn btn-xs btn-block btn-primary" id="reset-smart-widget"><i class="fa fa-refresh"></i> Factory Reset</a></section> <h6 class="margin-top-10 semi-bold margin-bottom-5">SmartAdmin Skins</h6><section id="smart-styles"><a href="javascript:void(0);" id="smart-style-0" data-skinlogo="img/logo.png" class="btn btn-block btn-xs txt-color-white margin-right-5" style="background-color:#4E463F;"><i class="fa fa-check fa-fw" id="skin-checked"></i>Smart Default</a><a href="javascript:void(0);" id="smart-style-1" data-skinlogo="img/logo-white.png" class="btn btn-block btn-xs txt-color-white" style="background:#3A4558;">Dark Elegance</a><a href="javascript:void(0);" id="smart-style-2" data-skinlogo="img/logo-blue.png" class="btn btn-xs btn-block txt-color-darken margin-top-5" style="background:#fff;">Ultra Light</a><a href="javascript:void(0);" id="smart-style-3" data-skinlogo="img/logo-pale.png" class="btn btn-xs btn-block txt-color-white margin-top-5" style="background:#f78c40">Google Skin</a></section></form> </div>')
    .append('<div class="demo"><span id="demo-setting"><i class="fa fa-cogs fa-spin txt-color-blueDark"></i></span> <form><section><h6 class="margin-top-10 semi-bold margin-bottom-5">快捷菜单设置</h6><a href="javascript:void(0);" class="btn btn-xs btn-block btn-primary" id="reset-smart-widget"><i class="fa fa-refresh"></i> Factory Reset</a></section> <h6 class="margin-top-10 semi-bold margin-bottom-5">SmartAdmin Skins</h6><section id="smart-styles"><a href="javascript:void(0);" id="smart-style-0" data-skinlogo="img/logo.png" class="btn btn-block btn-xs txt-color-white margin-right-5" style="background-color:#4E463F;"><i class="fa fa-check fa-fw" id="skin-checked"></i>Smart Default</a><a href="javascript:void(0);" id="smart-style-1" data-skinlogo="img/logo-white.png" class="btn btn-block btn-xs txt-color-white" style="background:#3A4558;">Dark Elegance</a><a href="javascript:void(0);" id="smart-style-2" data-skinlogo="img/logo-blue.png" class="btn btn-xs btn-block txt-color-darken margin-top-5" style="background:#fff;">Ultra Light</a><a href="javascript:void(0);" id="smart-style-3" data-skinlogo="img/logo-pale.png" class="btn btn-xs btn-block txt-color-white margin-top-5" style="background:#f78c40">Google Skin</a><a href="javascript:void(0);" id="smart-style-4" data-skinlogo="img/logo-pale.png" class="btn btn-xs btn-block txt-color-white margin-top-5" style="background: #bbc0cf; border: 1px solid #59779E; color: #17273D !important;">PixelSmash</a> <a href="javascript:void(0);" id="smart-style-5" data-skinlogo="img/logo-pale.png" class="btn btn-xs btn-block txt-color-white margin-top-5" style="background: rgba(153, 179, 204, 0.2); border: 1px solid rgba(121, 161, 221, 0.8); color: #17273D !important;">Glass </a><a href="javascript:void(0);" id="smart-style-6" data-skinlogo="img/logo-pale.png" class="btn btn-xs btn-block txt-color-white margin-top-6" style="background: #2196F3; border: 1px solid rgba(0, 0, 0, 0.3); color: #FFF !important;">MaterialDesign <sup>beta</sup> </a></section></form> </div>');

// hide bg options
var smartbgimage = "<h6 class='margin-top-10 semi-bold'>Background</h6><img src='img/pattern/graphy-xs.png' data-htmlbg-url='img/pattern/graphy.png' width='22' height='22' class='margin-right-5 bordered cursor-pointer'><img src='img/pattern/tileable_wood_texture-xs.png' width='22' height='22' data-htmlbg-url='img/pattern/tileable_wood_texture.png' class='margin-right-5 bordered cursor-pointer'><img src='img/pattern/sneaker_mesh_fabric-xs.png' width='22' height='22' data-htmlbg-url='img/pattern/sneaker_mesh_fabric.png' class='margin-right-5 bordered cursor-pointer'><img src='img/pattern/nistri-xs.png' data-htmlbg-url='img/pattern/nistri.png' width='22' height='22' class='margin-right-5 bordered cursor-pointer'><img src='img/pattern/paper-xs.png' data-htmlbg-url='img/pattern/paper.png' width='22' height='22' class='bordered cursor-pointer'>";
$("#smart-bgimages")
    .fadeOut();

$('#demo-setting')
    .click(function () {
        //console.log('setting');
        $('.demo')
            .toggleClass('activate');
    })
/*
 * FIXED HEADER
 */
$('input[type="checkbox"]#smart-fixed-header')
    .click(function () {
        if ($(this)
                .is(':checked')) {
            //checked
            $.root_.addClass("fixed-header");
        } else {
            //unchecked
            $('input[type="checkbox"]#smart-fixed-ribbon')
                .prop('checked', false);
            $('input[type="checkbox"]#smart-fixed-navigation')
                .prop('checked', false);

            $.root_.removeClass("fixed-header");
            $.root_.removeClass("fixed-navigation");
            $.root_.removeClass("fixed-ribbon");

        }
    });

if (localStorage.getItem('sm-setmenu') == 'top') {
    $('#smart-topmenu')
        .prop('checked', true);
} else {
    $('#smart-topmenu')
        .prop('checked', false);
}

/*
 * REFRESH WIDGET
 */
$("#reset-smart-widget")
    .bind("click", function () {
        $('#refresh')
            .click();
        return false;
    });

/*
 * STYLES
 */
$("#smart-styles > a")
    .on('click', function () {
        var $this = $(this);
        var $logo = $("#logo img");
        $.root_.removeClassPrefix('smart-style')
            .addClass($this.attr("id"));
        $('html').removeClassPrefix('smart-style')
            .addClass($this.attr("id"));
        $logo.attr('src', $this.data("skinlogo"));
        $("#smart-styles > a #skin-checked")
            .remove();
        $this.prepend("<i class='fa fa-check fa-fw' id='skin-checked'></i>");
    });


function real_g_success(msg) {
    console.log(msg);
    $('#realalertinfo').text(msg);
    $('#realalert').fadeTo(2000, 500).delay(5000).slideUp(500, function () {
    });
    $.smallBox({
        title: msg,
        content: "<i class='fa fa-clock-o'></i> <i>2 seconds ago...</i>",
        color: "#296191",
        iconSmall: "fa fa-thumbs-up bounce animated",
        timeout: 4000
    });
    $('#article_real_entity').hide();
    $('#dt_basic_real').DataTable().columns().checkboxes.deselect();
    $('#dt_basic_real').DataTable().draw();
}

function real_g_fail(msg) {
    $.SmartMessageBox({
        title: "出错了!",
        content: msg,
        buttons: '[确定]'
    })
}
function realalert(msg) {
    BootstrapDialog.alert({
        title: '系统提示信息：',
        message: msg,
        type: BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
        closable: true, // <-- Default value is false
        buttonLabel: '确定', // <-- Default value is 'OK',
        callback: function (result) {
        }
    });
}
// function realloadpage(url, wapper, div) {
//     console.log("url2:" + url);
//     //wapper.show();
//     $('#article_real_entity').show();
//     $('#realeditdiv').load(url, function () {
//     });
//     // div.load(url, function () {
//     // });
//     $('html, body').animate({scrollTop: 0}, 'normal');
// }

function realloadpage(url) {
    console.log("new url:" + url);
    $('#article_real_entity').show();
    $('#realeditdiv').load(url, function () {
    });
    $('html, body').animate({scrollTop: 0}, 'normal');
}

function real_g_del(msg, url) {
    var rows_selected = $('#dt_basic_real').DataTable().column(0).checkboxes.selected();
    if (rows_selected.length <= 0 || rows_selected.length > 1) {
        var msg_in;
        if (rows_selected.length <= 0)
            msg_in = "请选择一个待删除的" + msg;
        else
            msg_in = "请求操作不支持多选，请选择一个并且只选择一个待删除的" + msg;
        realalert(msg_in);
    } else {
        BootstrapDialog.show({
            title: '系统提示信息',
            message: '请确认是否删除选定数据.',
            buttons: [{
                label: '确认删除',
                action: function (dialog) {
                    dialog.close();
                    $("#updateflag").attr("value", 'deldel');
                    $.each(rows_selected, function (index, rowId) {
                        console.log("id: " + rowId);
                        $.ajax({
                            url: url + rowId,
                            type: 'GET',
                            success: function (response) {
                                if (response.substring(0, 2) == "ok") {
                                    real_g_success("——删除" + msg + "成功！");

                                    $('#dt_basic_real').DataTable().columns().checkboxes.deselect();
                                }
                                else {
                                    real_g_fail(response);
                                }
                            }
                        });
                    });
                }
            }, {
                label: '取消',
                action: function (dialogItself) {
                    dialogItself.close();
                }
            }]
        });
    }
}

window.downloadFile = function (sUrl) {

    //iOS devices do not support downloading. We have to inform user about this.
    if (/(iP)/g.test(navigator.userAgent)) {
        alert('Your device does not support files downloading. Please try again in desktop browser.');
        return false;
    }

    //If in Chrome or Safari - download via virtual link click
    if (window.downloadFile.isChrome || window.downloadFile.isSafari) {
        //Creating new link node.
        var link = document.createElement('a');
        link.href = sUrl;

        if (link.download !== undefined) {
            //Set HTML5 download attribute. This will prevent file from opening if supported.
            var fileName = sUrl.substring(sUrl.lastIndexOf('/') + 1, sUrl.length);
            link.download = fileName;
        }

        //Dispatching click event.
        if (document.createEvent) {
            var e = document.createEvent('MouseEvents');
            e.initEvent('click', true, true);
            link.dispatchEvent(e);
            return true;
        }
    }

    // Force file download (whether supported by server).
    if (sUrl.indexOf('?') === -1) {
        sUrl += '?download';
    }

    window.open(sUrl, '_self');
    return true;
}

window.downloadFile.isChrome = navigator.userAgent.toLowerCase().indexOf('chrome') > -1;
window.downloadFile.isSafari = navigator.userAgent.toLowerCase().indexOf('safari') > -1;
realaicy_g_var_tableopt_lang = {
    "processing": "处理中...",
    "lengthMenu": "每页显示 _MENU_ 项结果",
    "zeroRecords": "没有匹配结果",
    "info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
    "infoFiltered": "(由 _MAX_ 项结果过滤)",
    "infoPostFix": "",
    "search": "搜索:",
    "searchPlaceholder": "搜索...",
    "url": "",
    "emptyTable": "表中数据为空",
    "loadingRecords": "载入中...",
    "infoThousands": ",",
    "paginate": {
        "first": "首页",
        "previous": "上页",
        "next": "下页",
        "last": "末页"
    },
    "aria": {
        paginate: {
            first: '首页',
            previous: '上页',
            next: '下页',
            last: '末页'
        },
        "sortAscending": ": 以升序排列此列",
        "sortDescending": ": 以降序排列此列"
    },
    "decimal": "-",
    "thousands": "."
};
realaicy_g_var_dlurl = "http://111.30.31.179:32003/download/file/";



