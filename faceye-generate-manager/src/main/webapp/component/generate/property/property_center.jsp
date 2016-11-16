<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/property/property.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/property/property.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="generate.property.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/generate/property/input?EQ|entity.$id=${entity.id}"/>"> <fmt:message key="generate.property.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<div class="content">
			<c:if test="${not empty page.content }">
				<ol class="breadcrumb">
					<li><a href="<c:url value="/generate/project/home"/>"><fmt:message key="generate.project" /></a></li>
					<li><a href="<c:url value="/generate/component/home?EQ|project.$id=${page.content[0].entity.component.project.id}"/>">${page.content[0].entity.component.project.name }</a></li>
					<li><a href="<c:url value="/generate/entity/home?EQ|component.$id=${page.content[0].entity.component.id}"/>">${page.content[0].entity.component.name }</a></li>
					<li><a href="<c:url value="/generate/property/home?EQ|entity.$id=${page.content[0].entity.id}"/>">${page.content[0].entity.name }</a></li>
				</ol>
			</c:if>
		</div>
		<div class="content">
			<form action="<c:url value="/generate/property/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-2">
							<input type="text" name="like|name" value="${searchParams.name}" placeholder="<fmt:message key="generate.property.name"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-2">
							<input type="text" name="like|propertyName" value="${searchParams.propertyName}" placeholder="<fmt:message key="generate.property.propertyName"></fmt:message>"
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
			<button class="btn btn-primary btn-sm">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='generate.property.name'></fmt:message></th>
							<th><fmt:message key='generate.property.propertyName'></fmt:message></th>
							<th><fmt:message key='generate.property.entityId'></fmt:message></th>
							<th><fmt:message key='generate.property.dataTypeId'></fmt:message></th>
							<th><fmt:message key='generate.property.validateIds'></fmt:message></th>
							<th><fmt:message key='generate.property.columnName'></fmt:message></th>
							<th><fmt:message key='generate.property.referenceObjectId'></fmt:message></th>
							<th><fmt:message key='generate.property.isMulti'></fmt:message></th>
							<th><fmt:message key='generate.property.isQuery'></fmt:message></th>
							<th><fmt:message key='generate.property.isIndex'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="property">
							<tr>
								<td><input type="checkbox" name="check-single" value="${property.id}"></td>
								<td>${property.name}</td>
								<td>${property.propertyName}</td>
								<td>${property.entity.name}</td>
								<td>${property.dataType.name}</td>
								<td><c:forEach items="${property.validates}" var="validate" varStatus="status">${validate.name}<c:if test="${not status.last}">,</c:if>
									</c:forEach></td>
								<td>${property.columnName}</td>
								<td>${property.referenceEntity.name}</td>
								<td><f:boolean value="${property.isMulti}" /></td>
								<td><f:boolean value="${property.isQuery}" /></td>
								<td><f:boolean value="${property.isIndex}" /></td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/generate/property/edit/${property.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/generate/property/remove/${property.id}"/>"> <fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/generate/property/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
