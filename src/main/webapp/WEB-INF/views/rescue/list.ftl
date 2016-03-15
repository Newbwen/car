[@override name="title"]救援管理-救援列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">救援管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
救援信息列表
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
                                <label>申请人<input type="text" value="${rescue.applyUser!}" name="applyUser" /></label>
                                <label>救援司机<input type="text" value="${rescue.driver!}" name="driver" /></label>
                                <label>救援状态
                                    <select name="status">
                                        [#assign status = (command.status!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="WAIT_RESCUE" [@mc.selected status "WAIT_RESCUE"/]>待救援</option>
                                        <option value="IN_RESCUE" [@mc.selected status "IN_RESCUE"/]>救援中</option>
                                        <option value="SUCCESS_RESCUE" [@mc.selected status "SUCCESS_RESCUE"/]>救援完成</option>
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
                        <th>申请人</th>
                        <th>救援司机</th>
                        <th>救援状态</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as rescue ]
                            <tr class="even">
                                <td>${rescue.applyUser!}</td>
                                <td>${rescue.driver!}</td>
                                <td>${(rescue.status.getName())!}</td>
                                <td>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                                        <a class="blue" href="[@spring.url '/rescue/show/${rescue.id!}'/]"
                                           title="查看"><i class="icon-zoom-in bigger-130"></i></a>
                                        <a class="green" href="[@spring.url '/rescue/edit/${rescue.id}'/]"
                                           title="编辑"><i class="icon-pencil bigger-130"></i></a>
                                    </div>
                                </td>
                            </tr>
                            [/#list]
                        [/#if]
                    </tbody>
                </table>

                [#if pagination??]
                    [@mc.showPagination '/rescue/list?applyUser=${command.applyUser!}&driver=${command.driver!}&status=${command.status!}' /]
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