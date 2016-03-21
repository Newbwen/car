/**
 * Created by YJH on 2016/3/21.
 */
$.fn.areaCascade = function () {
    var ajax = function (parentId) {
        $.ajax({
            type: "post",
            url: "/area/search_by_parent",
            data: "parentId=" + parentId,
            dataType: "json",
            success: function (data) {
                if (typeof data == 'object') {
                    data = data;
                } else {
                    data = JSON.parse(data);
                }
                if(data.code == "0"){
                    console.log(data.data);
                }
            }
        })
    }
    ajax("");
}