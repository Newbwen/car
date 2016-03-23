[@override name="title"]计费-创计费信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/billing/list'/]">计费管理</a></li>
<li class="active">创建计费</li>
[/@override]

[@override name="pageHeaderTitle"]
创建计费信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/billing/create" class="form-horizontal" method="post">

            [@spring.bind "command.KMBilling"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 根据公里计费* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="carName" value="${command.KMBilling!}"
                           placeholder="根据公里计费" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "KMBilling"/]
                </div>
            </div>

            [@spring.bind "command.minuteBilling"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 根据分钟计费* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="minuteBilling" value="${command.minuteBilling!}"
                           placeholder="根据分钟计费" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "minuteBilling"/]
                </div>
            </div>

            [@spring.bind "command.area"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 区域* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="area" value="${command.area!}"
                           placeholder="区域" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "area"/]
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

[/@override]

[@extends name="/decorator.ftl"/]