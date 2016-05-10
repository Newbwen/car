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
        <form action="" class="form-horizontal" id="form-create" method="post">

            [@spring.bind "command.userName"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户名* </label>

                <div class="col-sm-9">
                    <input type="text" id="userName" name="userName" value="${command.userName!}"
                           placeholder="用户名(手机号)" pattern="^1[345678][0-9]{9}$"
                           class="col-xs-10 col-sm-5"
                           required/>
                    [@spring.showErrors "userName"/]
                </div>
            </div>

            [@spring.bind "command.password"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 密码* </label>

                <div class="col-sm-9">
                    <input type="password" id="password" name="password"
                           placeholder="密码" class="col-xs-10 col-sm-5" minlength="6" required/>
                    [@spring.showErrors "password"/]
                </div>
            </div>

            [@spring.bind "command.confirmPassword"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 确认密码* </label>

                <div class="col-sm-9">
                    <input type="password" id="confirmPassword" name="confirmPassword"
                           placeholder="确认密码" class="col-xs-10 col-sm-5" minlength="6"
                           onchange="checkPasswords()" required/>
                    [@spring.showErrors "confirmPassword"/]
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 电话* </label>

                <div class="col-sm-9">
                    <input type="text" id="phone" name="phone"
                           placeholder="电话号码" pattern="^1[345678][0-9]{9}$" class="col-xs-10 col-sm-5"
                           required/>
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 开始驾驶时间* </label>

                <div class="col-sm-9">
                    <input type="date" id="startDriveDate" name="startDriveDate"
                           placeholder="开始驾驶时间" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 身份证照片* </label>

                <div class="col-sm-9">
                    <button type="button" class="btn btn-sm btn-primary left input-file-hidden"
                            id="identityCardPicUpload">点击上传照片
                    </button>
                    <input type="hidden" id="identityCardPic" name="identityCardPic"
                           placeholder="身份证照片" class="form-control col-xs-10 col-sm-5" required/>
                    <p class="img-box"></p>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 驾驶证照片* </label>

                <div class="col-sm-9">
                    <button type="button" class="btn btn-sm btn-primary left input-file-hidden"
                            id="drivingLicencePicUpload">点击上传照片
                    </button>
                    <input type="hidden" id="drivingLicencePic" name="drivingLicencePic"
                           placeholder="驾驶证照片" class="form-control col-xs-10 col-sm-5" required/>
                    <p class="img-box"></p>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 司机类型* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" id="driverType" name="driverType" required>
                        [#assign status = (command.driverType)?default("") /]
                        <option value="">请选择</option>
                        <option value="GENERATION" [@mc.selected status "GENERATION"/]>代驾</option>
                        <option value="LIMOUSINE" [@mc.selected status "LIMOUSINE"/]>专车</option>
                        <option value="TAXI" [@mc.selected status "TAXI"/]>出租车</option>
                    </select>
                </div>
            </div>

            <div class="form-group hidden">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 行驶证照片* </label>

                <div class="col-sm-9">
                    <button type="button" class="btn btn-sm btn-primary left input-file-hidden"
                            id="travelPicUpload">点击上传照片
                    </button>
                    <input type="hidden" id="travelPic" name="travelPic"
                           placeholder="行驶证照片" class="form-control col-xs-10 col-sm-5" required/>
                    <p class="img-box"></p>
                </div>
            </div>

            <div class="form-group hidden">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 驾驶证类型* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" id="drivingLicenceType" name="drivingLicenceType">
                        <option value="">请选择</option>
                        <option value="A1">A1</option>
                        <option value="A2">A2</option>
                        <option value="B1">B1</option>
                        <option value="B2">B2</option>
                        <option value="C1">C1</option>
                        <option value="C2">C2</option>
                    </select>
                </div>
            </div>

            <div class="form-group hidden">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 营业资格证照片* </label>

                <div class="col-sm-9">
                    <button type="button" class="btn btn-sm btn-primary left input-file-hidden"
                            id="businessPicUpload">点击上传照片
                    </button>
                    <input type="hidden" id="businessPic" name="businessPic"
                           placeholder="营业资格证照片" class="form-control col-xs-10 col-sm-5" required/>
                    <p class="img-box"></p>
                </div>
            </div>

            <div class="form-group hidden">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 从业资格证照片* </label>

                <div class="col-sm-9">
                    <button type="button" class="btn btn-sm btn-primary left input-file-hidden" id="workPicUpload">
                        点击上传照片
                    </button>
                    <input type="hidden" id="workPic" name="workPic"
                           placeholder="从业资格证照片" class="form-control col-xs-10 col-sm-5" required/>
                    <p class="img-box"></p>
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
[#--文件上传进度--]
<div class="file_upload_load"></div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/assets/js/upload/webuploader.js'/]"></script>
<script src="[@spring.url '/resources/assets/js/layer/layer.js'/]"></script>
<script src="[@spring.url '/resources/assets/app/js/driverCreate.js'/]"></script>
<script type="text/javascript">
    function checkPasswords() {
        var pass1 = document.getElementById("password");
        var pass2 = document.getElementById("confirmPassword");

        if (pass1.value != pass2.value)
            pass2.setCustomValidity("两次输入的密码不匹配");
        else
            pass2.setCustomValidity("");
    }

    var $travelPic = $("#travelPic");
    var $businessPic = $("#businessPic");
    var $workPic = $("#workPic");
    var $drivingLicenceType = $("#drivingLicenceType");
    $("#driverType").change(function () {
        if ($(this).val() == "LIMOUSINE") {
            $travelPic.parent().parent().removeClass("hidden");
            $businessPic.parent().parent().addClass("hidden");
            $workPic.parent().parent().addClass("hidden");
            $drivingLicenceType.parent().parent().addClass("hidden");
            bindTravelPic();
        } else if ($(this).val() == "GENERATION") {
            $drivingLicenceType.parent().parent().removeClass("hidden");
            $travelPic.parent().parent().addClass("hidden");
            $businessPic.parent().parent().addClass("hidden");
            $workPic.parent().parent().addClass("hidden");
        } else if ($(this).val() == "TAXI") {
            $travelPic.parent().parent().removeClass("hidden");
            $businessPic.parent().parent().removeClass("hidden");
            $workPic.parent().parent().removeClass("hidden");
            $drivingLicenceType.parent().parent().addClass("hidden");
            bindWorkPic();
            bindBusinessPic();
            bindTravelPic();
        } else {
            $travelPic.parent().parent().addClass("hidden");
            $businessPic.parent().parent().addClass("hidden");
            $workPic.parent().parent().addClass("hidden");
            $drivingLicenceType.parent().parent().addClass("hidden");
        }
    })

    $("#form-create").submit(function () {
        if ($("#identityCardPic").val() == "") {
            layer.msg("身份证照片不能为空");
            return false;
        }
        if ($("#drivingLicencePic").val() == "") {
            layer.msg("驾驶证照片不能为空");
            return false;
        }
        if ($("#driverType").val() == "LIMOUSINE") {
            if ($travelPic.val() == "") {
                layer.msg("行驶证照片不能为空");
                return false;
            }
        } else if ($("#driverType").val() == "TAXI") {
            if ($travelPic.val() == "") {
                layer.msg("行驶证照片不能为空");
                return false;
            }
            if ($businessPic.val() == "") {
                layer.msg("营业证照片不能为空");
                return false;
            }
            if ($workPic.val() == "") {
                layer.msg("从业证照片不能为空");
                return false;
            }
        } else if ($("#driverType").val() == "GENERATION") {
            if ($("#drivingLicenceType").val() == "") {
                layer.msg("驾照类型不能为空");
                return false;
            }
        }
        return true;
    })
</script>
[/@override]


[@extends name="/decorator.ftl"/]