[@override name="title"]站内信息管理-信息列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">信息管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
站内信息列表
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        <div class="table-responsive">
            <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                    <thead>
                    <tr role="row">
                        <th>发送人</th>
                        <th>信息内容</th>
                        <th>发送时间</th>
                        <td>操作</td>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as message ]
                            <tr class="even">
                                <td>${message.sendBaseUser!}</td>
                                <td>${message.content!}</td>
                                <td>${message.sendDate!}</td>
                                <td>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                                        <a class="green" href="[@spring.url '/permission/delete/${message.id}'/]"
                                           title="删除"><i class="icon-pencil bigger-130"></i></a>
                                    </div>
                                </td>
                            </tr>
                            [/#list]
                        [/#if]
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]