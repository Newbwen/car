[@override name="title"]提现管理-提现列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">提现管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
提现列表
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
                                    开始<input type="date" value="${command.startTime!}" name="startTime" />
                                </label>
                                <label>
                                    结束<input type="date" value="${command.endTime!}" name="endTime" />
                                </label>
                                <label>申请人<input type="text" value="${command.baseUser!}" name="baseUser"/></label>
                                <label>状态
                                    <select name="status">
                                        [#assign status = (command.status!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="PENDING" [@mc.selected status "PENDING"/]>待处理</option>
                                        <option value="FINISH" [@mc.selected status "FINISH"/]>处理完成</option>
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
                        <th>申请时间</th>
                        <th>金额</th>
                        <th>状态</th>
                        <th>完成时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as withdraw ]
                            <tr class="even">
                                <td>${withdraw.username!}</td>
                                <td>${withdraw.createTime!}</td>
                                <td>${withdraw.money!}</td>
                                <td>${withdraw.status.getName()!}</td>
                                <td>${withdraw.finishTime!}</td>
                                <td>
                                    <div class="btn-group">
                                        [#if withdraw.status.getValue() == 0]
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue" href="[@spring.url '/withdraw/finish?id=${withdraw.id!}&version=${withdraw.version}'/]">处理完成</a>
                                            </li>
                                        </ul>
                                        [/#if]
                                    </div>
                                </td>
                            </tr>
                            [/#list]
                        [/#if]
                    </tbody>
                </table>

                [#if pagination??]
                    [@mc.showPagination '/withdraw/list?startTime=${command.startTime!}&endTime=${command.endTime!}&baseUser=${command.baseUser!}&status=${command.status!}' /]
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