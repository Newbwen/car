[@override name="title"]评价管理-评价列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">评价管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
评价信息列表
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
                                <label>评价人<input type="text" value="${command.evaluateUser!}" name="evaluateUser" /></label>
                                <label>订单<input type="text" value="${command.order!}" name="orderId" /></label>
                                <label><button type="submit" class="btn btn-app btn-sm btn-success">查询</button></label>
                            </div>
                        </div>
                    </form>
                </div>
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                    <thead>
                    <tr role="row">
                        <th>评价人</th>
                        <th>订单</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as evaluate ]
                            <tr class="even">
                                <td>${evaluate.evaluateUser!}</td>
                                <td>${evaluate.orderId!}</td>
                                <td>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                                        <a class="blue" href="[@spring.url '/car/show/${evaluate.id!}'/]"
                                           title="查看"><i class="icon-zoom-in bigger-130"></i></a>
                                        <a class="green" href="[@spring.url '/car/edit/${evaluate.id}'/]"
                                           title="编辑"><i class="icon-pencil bigger-130"></i></a>
                                    </div>
                                </td>
                            </tr>
                            [/#list]
                        [/#if]
                    </tbody>
                </table>

                [#if pagination??]
                    [@mc.showPagination '/evaluate/list?evaluateUserId=${command.evaluateUser!}&orderId=${command.order!}' /]
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