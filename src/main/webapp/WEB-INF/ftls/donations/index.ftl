<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="viewport" content="width=1024px, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <title>捐赠选择</title>

    <!-- Stylesheets -->
    <link rel="stylesheet" href="${requestContext.contextPath}/css/reset.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/icons.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/formalize.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/checkboxes.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/sourcerer.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/jqueryui.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/tipsy.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/calendar.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/tags.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/selectboxes.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/960.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/main.css"/>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/style.css"/>
    <link rel="stylesheet" media="all and (orientation:portrait)" href="${requestContext.contextPath}/css/portrait.css"/>
    <link rel="apple-touch-icon" href="apple-touch-icon-precomposed.png"/>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>

    <!--[if lt IE 9]>
    <script src="${requestContext.contextPath}/js/html5shiv.js"></script>
    <script src="${requestContext.contextPath}/js/excanvas.js"></script>
    <![endif]-->

    <!-- JavaScript -->
    <script src="${requestContext.contextPath}/js/jquery.min.js"></script>
    <script src="${requestContext.contextPath}/js/jqueryui.min.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.cookies.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.pjax.js"></script>
    <script src="${requestContext.contextPath}/js/formalize.min.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.metadata.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.validate.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.checkboxes.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.chosen.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.fileinput.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.datatables.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.sourcerer.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.tipsy.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.calendar.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.inputtags.min.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.wymeditor.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.livequery.js"></script>
    <script src="${requestContext.contextPath}/js/jquery.flot.min.js"></script>
    <script src="${requestContext.contextPath}/js/application.js"></script>
</head>

<body>

<!-- Primary navigation -->
<nav id="primary">
    <ul>
        <li class="active">
            <a href="index.html">
                <span class="icon32 love"></span>
                捐赠
            </a>
        </li>
        <li>
            <a href="history.html">
                <span class="icon32 listicon"></span>
                历史
            </a>
        </li>
        <li>
            <a href="manage.html">
                <span class="icon32 tools"></span>
                管理
            </a>
        </li>
        <li class="bottom">
            <a href="#">
                <span class="icon32 quit"></span>
                退出
            </a>
        </li>
    </ul>
</nav>

<!-- Secondary navigation -->
<nav id="secondary">
    <ul class="book-order">
        <#list donations as donation>
            <li><a href="#">${donation.createdDate}<label class="number">${donation.bookAmount}</label></a></li>
        </#list>
</ul>
</nav>

<section id="maincontainer">
    <div id="main" class="container_12">
        <!--<div class="box">-->
            <!--<div class="box-header">-->
                <!--<h1>12345</h1>-->
            <!--</div>-->
            <!--<table>-->
                <!--<thead>-->
                <!--<tr>-->
                    <!--<th width="10%">ISBN号</th>-->
                    <!--<th width="70%">书名</th>-->
                    <!--<th width="20%">动作</th>-->
                <!--</tr>-->
                <!--</thead>-->
                <!--<tbody>-->
                <!--<tr>-->
                    <!--<td>9787536474543</td>-->
                    <!--<td>关妖精的瓶子</td>-->
                    <!--<td>-->
                        <!--<a href="#" class="button plain green">通过</a>-->
                        <!--<a href="#" class="button plain red active">拒绝</a>-->
                    <!--</td>-->
                <!--</tr>-->
                <!--<tr>-->
                    <!--<td>1287536214543</td>-->
                    <!--<td>圣母悼歌</td>-->
                    <!--<td>-->
                        <!--<a href="#" class="button plain green active">通过</a>-->
                        <!--<a href="#" class="button plain red">拒绝</a>-->
                    <!--</td>-->
                <!--</tr>-->
                <!--</tbody>-->
            <!--</table>-->
        <!--</div>-->

        <!--<div class="box ">-->
            <!--<div class="box-header">-->
                <!--<h1>选择寄送的图书馆</h1>-->
            <!--</div>-->
            <!--<form action="/" method="post">-->
                <!--<p>-->
                    <!--<select name="city" id="city" placeholder="City" class="{validate:{required:true}}">-->
                        <!--<option>h黄侃图书馆</option>-->
                        <!--<option>z张国栋图书馆</option>-->
                        <!--<option>z张国栋图书馆二分馆</option>-->
                        <!--<option>t唐仲容图书馆</option>-->
                        <!--<option>y晏阳初图书馆</option>-->
                        <!--<option>y晏阳初图书馆二分馆</option>-->
                        <!--<option>s孙世祥图书馆</option>-->
                        <!--<option>g甘泉图书馆</option>-->
                        <!--<option>x熊培云图书馆</option>-->
                        <!--<option>z志翔图书馆</option>-->
                        <!--<option>t陶行知图书馆</option>-->
                        <!--<option>z卓英图书馆</option>-->
                    <!--</select>-->
                <!--</p>-->
            <!--</form>-->
        <!--</div>-->
        <!--<input type="submit" class="button blue" value="确认捐助"/>-->

    </div>
</section>
</body>
        </html>