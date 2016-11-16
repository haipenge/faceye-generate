<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/component/component.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/component/component.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="generate.component.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/generate/component/input?projectId=${project.id}"/>"> <fmt:message key="generate.component.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<div class="content">
			<c:if test="${not empty page.content }">
				<ol class="breadcrumb">
					<li><a href="<c:url value="/generate/project/home"/>"><fmt:message key="generate.project" /></a></li>
					<li><a href="<c:url value="/generate/project/detail/${page.content[0].project.id}"/>">${page.content[0].project.name }</a></li>
				</ol>
			</c:if>
		</div>
		<div class="content">
			<form action="<c:url value="/generate/component/home?EQ|project.$id=${project.id}"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-2">
							<input type="text" name="like|name" value="${searchParams.name}" placeholder="<fmt:message key="generate.component.name"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-2">
							<input type="text" name="like|packageName" value="${searchParams.packageName}" placeholder="<fmt:message key="generate.component.packageName"></fmt:message>"
								class="form-control input-sm">
						</div>
						<!--@generate-entity-jsp-query-detail@-->
						<div class="col-md-1">
							<button type="submit" class="btn btn-sm btn-primary">
								<fmt:message key="global.search"></fmt:message>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="content">
			<div id="msg"></div>
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='generate.component.name'></fmt:message></th>
							<th><fmt:message key='generate.component.projectId'></fmt:message></th>
							<th><fmt:message key='generate.component.packageName'></fmt:message></th>
							<th><fmt:message key="generate.entity" /></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="component">
							<tr id="${component.id}">
								<td><input type="checkbox" name="check-single" value="${component.id}"></td>
								<td>${component.name}</td>
								<td>${component.project.name}</td>
								<td>${component.packageName}</td>
								<td><a href="<c:url value="/generate/entity/home?EQ|component.$id=${component.id}"/>"><fmt:message key="generate.entity" /></a> <!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/generate/component/edit/${component.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/generate/component/remove/${component.id}"/>"> <fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/generate/component/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
