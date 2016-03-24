[@override name="title"]举报管理-举报列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">举报管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
举报信息列表
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
                                <label>开始<input type="date" value="${command.beginTime!}" name="beginTime" /></label>
                                <label>结束<input type="date" value="${command.endTime!}" name="endTime" /></label>
                                <label>处理状态
                                    <select name="status">
                                        [#assign status = (command.status!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="PENDING" [@mc.selected status "PENDING"/]>待处理</option>
                                        <option value="IN_PROCESS" [@mc.selected status "IN_PROCESS"/]>正在处理</option>
                                        <option value="FIGURE_OUT" [@mc.selected status "FIGURE_OUT"/]>处理完成</option>
                                    </select>
                                </label>
                                <label><button type="submit" class="btn btn-app btn-sm btn-success">查询</button></label>
                            </div>
                        </div>
                    </form>
                </div>
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                    <thead>
                    <tr role="row">
                        <th>举报人</th>
                        <th>举报订单</th>
                        <th>举报时间</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as report ]
                            <tr class="even">
                                <td>${report.reportUser.userName!}</td>
                                <td>${report.order.orderNumber!}</td>
                                <td>${report.reportTime!}</td>
                                <td>${(report.status.getName())!}</td>
                                <td>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                                        <a class="blue" href="[@spring.url '/report/show/${report.id!}'/]"
                                           title="查看"><i class="icon-zoom-in bigger-130"></i></a>
                                    </div>
                                </td>
                            </tr>
                            [/#list]
                        [/#if]
                    </tbody>
                </table>

                [#if pagination??]
                    [@mc.showPagination '/report/list?beginTime=${command.beginTime!}&endTime=${command.endTime}&status=${command.status!}' /]
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