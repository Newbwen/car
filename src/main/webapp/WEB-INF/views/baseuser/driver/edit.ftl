[@override name="title"]司机用户管理-修改司机用户[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/user/driver/list'/]">司机用户管理</a></li>
<li class="active">修改公司用户</li>
[/@override]

[@override name="pageHeaderTitle"]
修改公司用户
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/user/driver/edit" class="form-horizontal" method="post">

            <input type="hidden" name="id" value="${driver.id!command.id}" />
            <input type="hidden" name="version" value="${driver.version!command.version}" />

            [@spring.bind "command.name"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 司机名称* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="name" value="${driver.name!command.name}"
                           placeholder="司机名称" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "name"/]
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 电话* </label>

                <div class="col-sm-9">
                    <input type="telephone" id="form-field-1" name="telephone"
                           placeholder="电话" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>



            [@spring.bind "command.email"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 邮箱* </label>

                <div class="col-sm-9">
                    <input type="email" id="form-field-1" name="email" value="${driver.email!command.email}"
                           placeholder="邮箱" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "email"/]
                </div>
            </div>



            [@spring.bind "command.sex"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 性别* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" name="sex" required>
                        [#assign status = (driver.sex!command.sex)?default("") /]
                        <option value="">请选择</option>
                        <option value="MAN" [@mc.selected status "MAN"/]>男</option>
                        <option value="WOMAN" [@mc.selected status "WOMAN"/]>女</option>
                    </select>
                    [@spring.showErrors "sex"/]
                </div>
            </div>

            [@spring.bind "command.driverType"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 司机类型* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" name="driverType" required>
                        [#assign status = (driver.driverType!command.driverType)?default("") /]
                        <option value="">请选择</option>
                        <option value="GENERATION" [@mc.selected status "GENERATION"/]>代驾</option>
                        <option value="LIMOUSINE" [@mc.selected status "LIMOUSINE"/]>专车</option>
                        <option value="TAXI" [@mc.selected status "TAXI"/]>出租车</option>
                    </select>
                    [@spring.showErrors "driverType"/]
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 身份证* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="identity "
                           placeholder="身份证" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 驾驶证* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="Drive "
                           placeholder="驾驶证" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 驾照类型* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" name="driverType" required>
                        <option value="">请选择</option>
                        <option>C2</option>
                        <option>B1</option>
                        <option>B2</option>
                        <option>A1</option>
                        <option>A2</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 发驾照时间* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name=" drivingTime"
                           placeholder="发驾照时间" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 行驶证* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="travel "
                           placeholder="行驶证" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 从业资格证* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="practitioners "
                           placeholder="从业资格" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 运营许可证* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="operation "
                           placeholder="运营许可" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>



            <div class="clearfix form-actions">
                <div class="col-md-offset-4">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        修改
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
</script>
[/@override]

[@extends name="/decorator.ftl"/]