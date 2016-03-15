[@override name="title"]车辆管理-修改车辆信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/car/list'/]">车辆管理</a></li>
<li class="active">修改车辆</li>
[/@override]

[@override name="pageHeaderTitle"]
修改车辆信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/car/edit" class="form-horizontal" method="post">

            <input type="hidden" name="id" value="${car.id!command.id}" />
            <input type="hidden" name="version" value="${car.version!command.version}" />

            [@spring.bind "command.carName"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 车辆名称* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="carName" value="${car.carName!command.carName}"
                           placeholder="车辆名称" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "carName"/]
                </div>
            </div>

            [@spring.bind "command.carNumber"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 车牌号* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="carNumber" value="${car.carNumber!command.carNumber}"
                           placeholder="车牌号" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "carNumber"/]
                </div>
            </div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-4">
                    <button class="btn btn-info" type="submit"><i class="icon-ok bigger-110"></i>修改</button>
                    <button class="btn" type="reset"><i class="icon-undo bigger-110"></i>重置</button>
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