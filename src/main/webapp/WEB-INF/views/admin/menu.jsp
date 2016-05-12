<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="leftbar leftbar-close clearfix">
	<div class="admin-info clearfix">
		<div class="admin-thumb">
			<i class="icon-user"></i>
		</div>
		<div class="admin-meta">
			<ul>
				<li class="admin-username" style="margin-top: 10px;">欢迎你 ${sessionScope.adminuser.name}</li>
				<li><a href="${pageContext.request.contextPath}/admin/loginout">
				<i class="icon-lock"></i> 退出</a></li>
			</ul>
		</div>
	</div>

	<div class="left-nav clearfix">
		<div class="left-primary-nav">
			<ul id="myTab">
				<li  class="active"><a href="#dailyreport" class="icon-calendar" data-original-title="订单"></a></li>
			</ul>
		</div>
		<div class="responsive-leftbar">
			<i class="icon-list"></i>
		</div>
		<div class="left-secondary-nav tab-content" >
			<div class="tab-pane active dailyreport" id="dailyreport">
				<ul id="nav" class="accordion-nav" >
				<c:if test="${sessionScope.adminuser.username=='admin'}">
					<li><a href="${pageContext.request.contextPath}/admin/user/index"><i class="icon-pencil"></i>注册用户管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/project/create"><i class="icon-pencil"></i>教学计划发布</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/prescription/create"><i class="icon-pencil"></i>教学大纲发布</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/knowledge/create"><i class="icon-pencil"></i>课程介绍发布</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/jinji/create"><i class="icon-pencil"></i>教学日历发布</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/video/create"><i class="icon-pencil"></i>视频课程发布</a></li>
				   <li><a href="${pageContext.request.contextPath}/admin/work/create"><i class="icon-pencil"></i>课后作业发布</a></li>
					
					<li><a href="${pageContext.request.contextPath}/admin/jinji/index"><i class="icon-pencil"></i>教学日历管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/prescription/index"><i class="icon-pencil"></i>教学大纲管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/knowledge/index"><i class="icon-pencil"></i>课程介绍管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/project/index"><i class="icon-pencil"></i>教学计划管理</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/video/index"><i class="icon-pencil"></i>教学视频管理</a></li>
				
						<li><a href="${pageContext.request.contextPath}/admin/work/index"><i class="icon-pencil"></i>课后作业管理</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/submitwork/index"><i class="icon-pencil"></i>作业提交查询</a></li>
			
				</c:if>
				
				</ul>
			</div>
		</div>
	</div>
</div>