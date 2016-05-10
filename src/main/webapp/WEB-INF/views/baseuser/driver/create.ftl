[@override name="title"]司机管理-添加[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/message/list'/]">司机管理</a></li>
<li class="active">司机</li>
[/@override]

[@override name="pageHeaderTitle"]
创建司机
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/user/driver/create" class="form-horizontal" method="post">

            [@spring.bind "command.userName"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户名* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="userName" value="${command.userName!}"
                           placeholder="用户名" minlength="3" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "userName"/]
                </div>
            </div>

            [@spring.bind "command.password"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 密码* </label>

                <div class="col-sm-9">
                    <input type="password" id="form-field-password" name="password" value="${command.password!}"
                           placeholder="密码" minlength="6" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "password"/]
                </div>
            </div>

            [@spring.bind "command.confirmPassword"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 确认密码* </label>

                <div class="col-sm-9">
                    <input type="password" id="form-field-confirmPassword" name="confirmPassword" value="${command.confirmPassword!}"
                           placeholder="确认密码" onchange="checkPasswords()" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "confirmPassword"/]
                </div>
            </div>


            [@spring.bind "command.name"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 司机名称* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="name" value="${command.name!}"
                           placeholder="司机名称" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "name"/]
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 电话* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="telephone"
                           placeholder="电话" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>

            [@spring.bind "command.email"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 邮箱* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="email" value="${command.email!}"
                           placeholder="邮箱" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "email"/]
                </div>
            </div>

            [@spring.bind "command.sex"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 性别* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" name="sex" required>
                        [#assign status = (command.sex)?default("") /]
                        <option value="">请选择</option>
                        <option value="MAN" [@mc.selected status "MAN"/]>男</option>
                        <option value="WOMAN" [@mc.selected status "WOMAN"/]>女</option>
                    </select>
            </div>
            </div>

            [@spring.bind "command.driverType"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 司机类型* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" name="driverType" required>
                    [#assign status = (command.driverType)?default("") /]
                    <option value="">请选择</option>
                    <option value="GENERATION" [@mc.selected status "GENERATION"/]>代驾</option>
                    <option value="LIMOUSINE" [@mc.selected status "LIMOUSINE"/]>专车</option>
                    <option value="TAXI" [@mc.selected status "TAXI"/]>出租车</option>
                </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 身份证* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="identity"
                           placeholder="身份证" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 驾驶证* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="Drive"
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
                    <input type="text" id="form-field-1" name="drivingTime"
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