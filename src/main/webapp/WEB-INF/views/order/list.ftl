[@override name="title"]订单管理-订单列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">订单列表</li>
[/@override]

[@override name="pageHeaderTitle"]
订单列表
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
                                <label>订单号<input type="text" value="${command.orderNumber!}" name="orderNumber" /></label>
                                <label>订单状态
                                    <select name="orderStatus">
                                        [#assign status = (command.orderStatus!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="WAIT_ORDER" [@mc.selected status "WAIT_ORDER"/]>待接单</option>
                                        <option value="HAS_ORDER" [@mc.selected status "HAS_ORDER"/]>已接单</option>
                                        <option value="START_ORDER" [@mc.selected status "START_ORDER"/]>已开始</option>
                                        <option value="WAIT_PAY" [@mc.selected status "WAIT_PAY"/]>待支付</option>
                                        <option value="SUCCESS" [@mc.selected status "SUCCESS"/]>完成</option>
                                        <option value="INVALID" [@mc.selected status "INVALID"/]>作废</option>
                                    </select>
                                </label>
                                <label>司机类型
                                    <select name="orderStatus">
                                        [#assign status = (command.orderStatus!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="GENERATION" [@mc.selected status "GENERATION"/]>代驾</option>
                                        <option value="LIMOUSINE" [@mc.selected status "LIMOUSINE"/]>专车</option>
                                        <option value="TAXI" [@mc.selected status "TAXI"/]>出租车</option>
                                    </select>
                                </label>
                                <label>车辆类型
                                    <select name="orderStatus">
                                        [#assign status = (command.orderStatus!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="ECONOMY" [@mc.selected status "ECONOMY"/]>经济型</option>
                                        <option value="COMFORT" [@mc.selected status "COMFORT"/]>舒适型</option>
                                        <option value="BUSINESS" [@mc.selected status "BUSINESS"/]>商务型</option>
                                        <option value="LUXURY" [@mc.selected status "LUXURY"/]>豪华型</option>
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
                        <th>订单号</th>
                        <th>下单人</th>
                        <th>接单人</th>
                        <th>订单金额</th>
                        <th>下单时间</th>
                        <th>司机类型</th>
                        <th>车辆类型</th>
                        <th>订单状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as order ]
                            <tr class="even">
                                <td>${order.orderNumber!}</td>
                                <td>${order.orderUser.userName!}</td>
                                <td>${order.receiveUser.userName!}</td>
                                <td>${order.shouldMoney!}</td>
                                <td>${order.createDate!}</td>
                                <td>${(order.driverType.getName())!}</td>
                                <td>${(order.carType.getName())!}</td>
                                <td>${(order.orderStatus.getName())!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue" href="[@spring.url '/order/show/${order.id!}'/]">查看</a>
                                            </li>
                                            [#if order.orderStatus.getValue()==4 || order.orderStatus.getValue()==5]
                                                <li>
                                                    <a class="blue" href="[@spring.url '/order/way/${order.id!}'/]">线路</a>
                                                </li>
                                            [/#if]
                                            [#--<li>--]
                                                [#--<a class="green" href="[@spring.url '/order/edit/${order.id}'/]">编辑</a>--]
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
                    [@mc.showPagination '/order/list?orderNumber=${command.orderNumber!}&orderStatus=${command.orderStatus!}' /]
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