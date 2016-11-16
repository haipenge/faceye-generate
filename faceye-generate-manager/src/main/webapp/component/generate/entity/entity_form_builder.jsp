<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/entity/entity.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/entity/builder.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/jquery-ui/jquery-ui.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/jquery-ui/jquery-ui.theme.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/jquery-ui/jquery-ui.structure.min.css"/>" />
<script type="text/javascript" src="<c:url value="/js/lib/jquery-ui/jquery-ui.min.js"/>"></script>
<style>
.ele-text {
	padding-left: 2px;
	font-size: 9px;
}

.add-plus {
	margin-right: 2px;
	float: right;
	font: 12px;
	font-weight: bold;
	color: blue;
	cursor: pointer;
}

.ele-reduce {
	text-align: center;
	vertical-align: middle;
	font: 12px;
	color: red;
	margin-top:5px;
	cursor: pointer;
	width:20px;
	height:20px;
	display:bolck;
	float:left;
}

.edit-property {
	text-align: center;
	vertical-align: middle;
	margin-top:5px;
	font: 12px;
	font-weight: bold;
	color: red;
	cursor: pointer;
	width:20px;
	height:20px;
	display:block;
	float:right;
}
</style>
<div class="page-head">
	<h2>
		<fmt:message key="generate.entity.form.builder"></fmt:message>
	</h2>
</div>
<div class="cl-mcont" style="padding: 0px; margin: 0px;">
	<div class="header">
		<ol class="breadcrumb">
			<li><a href="<c:url value="/generate/project/home"/>"><fmt:message key="generate.project"></fmt:message></a></li>
			<li><a href="<c:url value="/generate/component/home"/>"><fmt:message key="generate.component" /></a></li>
			<li><fmt:message key="generate.entity" /></li>
		</ol>
	</div>
	<div class="block-flat" style="padding-left: 15px;padding-right:15px;">
		<div class="row" style="margin-top:0px;">
			<div class="col-md-2" style="padding: 0px;height:100%;">
				<h5>工具箱</h5>
				<div class="table-responsive" id="form-elements">
					<table class="table" style="border: 1px solid red;">
						<tr>
							<td style="width: 50%; padding-left: 1px;"><img src="<c:url value="/js/component/generate/entity/builder/images/textbox.png"/>"><span class="ele-text">Text</span><span
								class="add-plus" data-ele="text">+</span></td>
							<td style="width: 50%; padding-left: 1px;"><img src="<c:url value="/js/component/generate/entity/builder/images/textarea.png"/>"><span class="ele-text">TextArea</span><span
								class="add-plus" data-ele="textarea">+</span></td>
						</tr>
						<tr>
							<td style="width: 50%; padding-left: 1px;"><img src="<c:url value="/js/component/generate/entity/builder/images/radio.png"/>"><span class="ele-text">Radio</span><span
								class="add-plus" data-ele="radio">+</span></td>
							<td style="width: 50%; padding-left: 1px;"><img src="<c:url value="/js/component/generate/entity/builder/images/checkbox.png"/>"><span class="ele-text">checkbox</span><span
								class="add-plus" data-ele="checkbox">+</span></td>
						</tr>
						<tr>
							<td style="width: 50%; padding-left: 1px;" colspan="2"><img src="<c:url value="/js/component/generate/entity/builder/images/dropdown.png"/>"><span
								class="ele-text">Select</span><span class="add-plus" data-ele="select">+</span></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="col-md-6">
				<h5>工作面版</h5>
				<div class="content" id="form-container" style="margin:0px;">
					<form class="form-horizontal">
						<c:forEach items="${properties.content}" var="property" varStatus="status">
							<div class="form-group" id="${property.id}">
								<label class="col-sm-2 control-label">${property.name}</label>
								<div class="col-sm-8">
									<c:choose>
										<c:when test="${property.inputTypeText eq 'text'}">
											<input type="text" class="form-control" placeholder="请输入...">
										</c:when>
										<c:when test="${property.inputTypeText eq 'textarea' }">
											<textarea class="form-control" rows="3" placeholder="请输入文本内容..."></textarea>
										</c:when>
										<c:when test="${property.inputTypeText eq 'radio' }">
											<label class="radio-inline"> <input type="radio" name="radio" id="radio1" value="1">选项1
											</label>
											<label class="radio-inline"> <input type="radio" name="radio" id="radio1" value="2">选项2
											</label>
											<label class="radio-inline"> <input type="radio" name="radio" id="radio1" value="3">选项3
											</label>
										</c:when>
										<c:when test="${property.inputTypeText eq 'select' }">
											<select class="form-control">
												<option value="1">选项1</option>
												<option value="2">选项2</option>
												<option value="3">选项3</option>
											</select>
										</c:when>
										<c:when test="${property.inputTypeText eq 'checkbox' }">
											<label class="checkbox-inline"> <input type="checkbox" name="radio" id="radio1" value="1">选项1
											</label>
											<label class="checkbox-inline"> <input type="checkbox" name="radio" id="radio2" value="2">选项2
											</label>
											<label class="checkbox-inline"> <input type="checkbox" name="radio" id="radio3" value="3">选项3
											</label>
										</c:when>
										<c:otherwise>
										  未知输入类型
										</c:otherwise>
									</c:choose>
								</div>
								<div class="col-md-2">
									<img src="<c:url value="/images/remove.png"/>" class="img-responsive ele-reduce"/> <img src="<c:url value="/images/settings.png"/>" class="img-responsive edit-property"/>
								</div>
							</div>
						</c:forEach>
					</form>
				</div>
			</div>
			<div class="col-md-4">
				<h5>属性设置</h5>
				<div class="content" id="element-properties" style="padding: 0px;"></div>
			</div>
		</div>

	</div>
</div>

<div id="data-types" style="display: none;">
	<c:forEach items="${dataTypes}" var="dataType">
		<label class="radio-inline"><input type="radio" name="dataType.id" value="${dataType.id}">${dataType.name}</label>
	</c:forEach>
</div>
<div id="referency-enties" style="display: none;">
	<select name="referenceEntity.id" class="form-control">
		<option value="0">引用对像</option>
		<c:forEach items="${entities}" var="entity">
			<option value="${entity.id}">${entity.name}</option>
		</c:forEach>
	</select>
</div>
<div id="input-types" style="display:none;">
	<select name="inputType" class="form-control">
		<c:forEach items="${inputTypes}" var="pair">
			<option value="${pair.key}">${pair.value}</option>
		</c:forEach>
	</select>
</div>