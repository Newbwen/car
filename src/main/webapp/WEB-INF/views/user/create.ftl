[@override name="title"]平台用户管理-创建平台用户[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/user/terrace/list'/]">平台用户管理</a></li>
<li class="active">创建平台用户</li>
[/@override]

[@override name="pageHeaderTitle"]
创建平台用户
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/user/terrace/create" class="form-horizontal" method="post">

            [@spring.bind "command.userName"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户名* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="permissionName" value="${command.userName!}"
                           placeholder="权限名称" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "userName"/]
                </div>
            </div>

            [@spring.bind "command.password"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 密码* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="password" value="${command.password!}"
                           placeholder="描述" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "password"/]
                </div>
            </div>

            [@spring.bind "command.email"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 邮箱* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="email" value="${command.email!}"
                           placeholder="描述" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "email"/]
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

            [@spring.bind "command.status"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 状态* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" name="status" required>
                        [#assign status = (command.status!)?default("") /]
                        <option value="">请选择</option>
                        <option value="ENABLE" [@mc.selected status "ENABLE"/]>启用</option>
                        <option value="DISABLE" [@mc.selected status "DISABLE"/]>禁用</option>
                    </select>
                    [@spring.showErrors "status"/]
                </div>
            </div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-4">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        创建
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
                console.log(result);

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
                $("#user-role").chosen();
            }
        })
    }

    initTypeDate();
</script>
[/@override]

[@extends name="/decorator.ftl"/]