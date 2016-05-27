[@override name="title"]资金流向管理-资金流向列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active" xmlns="http://www.w3.org/1999/html">资金流向列表</li>
[/@override]

[@override name="pageHeaderTitle"]
资金流向列表
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="table-responsive">
            <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
                <!-- 查询条件 -->
                <div class="row">
                    <form>
                        <div class="col-sm-6">
                            <div id="sample-table-2_length" class="dataTables_length">
                                <label>
                                    用户名<input type="text" value="${command.userName!}" name="userName"/>
                                </label>
                                <label>
                                    开始<input type="date" value="${command.startTime!}" name="startTime"/>
                                </label>
                                <label>
                                    结束<input type="date" value="${command.endTime!}" name="endTime"/>
                                </label>

                                <label>
                                    <button type="submit" class="btn btn-app btn-sm btn-success">查询</button>
                                </label>
                                <label>
                                    <a href="/money_detailed/export_excel?userName=${command.userName!}&startTime=${command.startTime!}&endTime=${command.endTime!}"
                                       class="btn-sm btn-success">导出表格</a>
                                </label>
                            </div>
                        </div>
                </div>
                </form>
            </div>
            <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                <thead>
                <tr role="row">
                    <th>用户名</th>
                    <th>资金流向</th>
                    <th>金额</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>


                <tbody role="alert" aria-live="polite" aria-relevant="all">
                    [#if pagination.data??]
                        [#list pagination.data as moneyDetailed ]
                        <tr class="even">
                            <td>${moneyDetailed.baseUser.userName!}</td>
                            <td>${(moneyDetailed.flowType.getName())!}</td>
                            <td>${moneyDetailed.money!}</td>
                            <td>${moneyDetailed.createDate!}</td>
                            <td>
                                <div class="btn-group">
                                    <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                        操作
                                        <i class="icon-angle-down icon-on-right"></i>
                                    </button>

                                    <ul class="dropdown-menu">
                                        <li>
                                            <a class="blue"
                                               href="[@spring.url '/money_detailed/show/${moneyDetailed.id!}'/]">查看</a>
                                        </li>
                                    [#--<li>--]
                                    [#--<a class="green" href="[@spring.url '/role/edit/${role.id}'/]">编辑</a>--]
                                    [#--</li>--]
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        [/#list]
                    [/#if]
                </tbody>
            </table>

            [#if pagination??]
                [@mc.showPagination '/money_detailed/list?userName=${command.userName!}&startTime=${command.startTime!}&endTime=${command.endTime!}' /]
            [/#if]

        </div>
    </div>
</div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]