[@override name="title"]站内信息管理-添加[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/message/list'/]">信息管理</a></li>
<li class="active">添加信息</li>
[/@override]

[@override name="pageHeaderTitle"]
创建站内信息
[/@override]

[@override name="subContent"]
<script>

    function initTypeDate(){
        var typeId = $("#user-role").attr("data-id");
        $.ajax({
            url: "/role/all_list",
            type: "POST",
            dataType: "JSON",
            success: function (result) {
                if (typeof result == 'object') {
                    result = result.data;
                } else {
                    result = JSON.parse(result.data);
                }

                $("#user-role").empty();
                $("#user-role").append("<option value=''>请选择</option>");

                var typeData = result;
                $.each(typeData, function (index, data) {
                    if(data.id == typeId){
                        $("#user-role").append("<option value='"+data.id+"' selected>"+data.roleName+"</option>");
                    }else{
                        $("#user-role").append("<option value='"+data.id+"'>"+data.roleName+"</option>");
                    }
                });

                $("#user-role").trigger("chosen:updated");
            }
        })
    }
    initTypeDate();
</script>
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/message/create" class="form-horizontal" method="post">
            [@spring.bind "command.content"/]
            <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 信息内容* </label>

            <div class="col-sm-9">
                <textarea id="content" name="command.content"  class="col-xs-10 col-sm-5" style="height: 200px;" maxlength="500" placeholder="在此编辑信息"></textarea>
                [@spring.showErrors "content"/]
            </div>
        </div>
            [@spring.bind "command.userRole"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户角色* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" id="user-role" name="userRole" data-id="${command.userRole!}" required>

                    </select>
                    [@spring.showErrors "userRole"/]
                </div>
            </div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-4">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        发送
                    </button>
                    <button class="btn" type="reset">
                        <i class="icon-undo bigger-110"></i>
                        重置
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]