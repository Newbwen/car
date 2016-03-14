[@override name="title"]评价管理-修改评价信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/evaluate/list'/]">评价管理</a></li>
<li class="active">修改评价</li>
[/@override]

[@override name="pageHeaderTitle"]
修改评价信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/evaluate/edit" class="form-horizontal" method="post">

            <input type="hidden" name="id" value="${evaluate.id!command.id}" />
            <input type="hidden" name="version" value="${evaluate.version!command.version}" />

            [@spring.bind "command.evaluateUser"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 评价人* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="permissionName" value="${evaluate.evaluateUser!command.evaluateUser}"
                           placeholder="评价人" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "evaluateUser"/]
                </div>
            </div>

            [@spring.bind "command.order"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 订单* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="order" value="${evaluate.order!command.order}"
                           placeholder="订单" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "order"/]
                </div>
            </div>

            [@spring.bind "command.content"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 内容* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="order" value="${evaluate.content!command.content}"
                           placeholder="内容" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "content"/]
                </div>
            </div>

            [@spring.bind "command.level"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 评级* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="order" value="${evaluate.level!command.level}"
                           placeholder="评级" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "level"/]
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