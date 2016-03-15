[@override name="title"]举报管理-查看举报信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/report/list'/]">举报管理</a></li>
<li class="active">查看举报信息</li>
[/@override]

[@override name="pageHeaderTitle"]
查看举报信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 举报订单</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${report.order!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 说明</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${report.description!}</span>
                </div>
            </div>


            [@spring.bind "command.status"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 状态 </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" name="status" required>
                        [#assign status = (command.status!)?default("") /]
                        <option value="">请选择</option>
                        <option value="PENDING" [@mc.selected status "PENDING"/]>待处理</option>
                        <option value="IN_PROCESS" [@mc.selected status "IN_PROCESS"/]>正在处理</option>
                        <option value="FIGURE_OUT" [@mc.selected status "FIGURE_OUT"/]>处理完成</option>
                    </select>
                    [@spring.showErrors "status"/]
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <button class="btn btn-info" type="submit">
                <i class="icon-ok bigger-110"></i>
                修改状态
            </button>
            <a href="/report/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]