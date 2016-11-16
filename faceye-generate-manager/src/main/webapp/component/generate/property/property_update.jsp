<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/property/property.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/property/property.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty property.id}">
					<fmt:message key="generate.property.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="generate.property.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/generate/property/save" method="post" role="form" cssClass="form-horizontal" commandName="property">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <fmt:message key="generate.property.name"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="propertyName"> <fmt:message key="generate.property.propertyName"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:input path="propertyName" cssClass="form-control" />
						<form:errors path="propertyName" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="columnName"> <fmt:message key="generate.property.columnName"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:input path="columnName" cssClass="form-control" />
						<form:errors path="columnName" class="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="entity.id"> <fmt:message key="generate.property.entityId"></fmt:message>
					</label>
					<div class="col-md-6">
						<c:choose>
							<c:when test="${not empty property.entity }">
								<form:hidden path="entity.id" />
							  ${property.entity.name }
							</c:when>
							<c:otherwise>
								<form:select path="entity.id" cssClass="form-control">
									<form:option value="0" label="--Please Select Entity" />
									<form:options items="${entities}" itemValue="id" itemLabel="name" />
								</form:select>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="dataTypeId"> <fmt:message key="generate.property.dataTypeId"></fmt:message>
					</label>
					<div class="col-md-6">
						<c:forEach items="${dataTypes}" var="dataType">
							<c:set var="isCheck" value="false" />
							<c:if test="${dataType.id eq property.dataType.id }">
								<c:set var="isCheck" value="true" />
							</c:if>
							<label class="radio-inline"><input type="radio" name="dataType.id" value="${dataType.id}" <c:if test="${isCheck eq 'true' }">checked</c:if>>${dataType.name }</label>
						</c:forEach>
					</div>
				</div>
				<!-- 
				<div class="form-group">
					<label class="col-md-2 control-label" for="validateIds"> <fmt:message key="generate.property.validateIds"></fmt:message>
					</label>
					<div class="col-md-6">
						<c:forEach items="${validates}" var="validate">
							<c:set var="isCheck" value="false" />
							<c:if test="${not empty property.validates}">
								<c:forEach items="${property.validates}" var="pValidate">
									<c:if test="${pValidate.id eq validate.id}">
										<c:set var="isCheck" value="true" />
									</c:if>
								</c:forEach>
							</c:if>
							<input name="validate.id" type="checkbox" value="${validate.id}" <c:if test="${isCheck eq 'true' }">checked</c:if>>${validate.name }
						</c:forEach>
					</div>
				</div>
				 -->
				<!-- Length validator
				<div class="form-group" id="validator-length">
					<label class="col-md-2 control-label" for="validateIds"> Length </label>
					<div class="col-md-6">
						<div class="col-md-5">
							<input name="validator-length-min" placeholder="Min" class="form-control">
						</div>
						<div class="col-md-2 text-center">-</div>
						<div class="col-md-5">
							<input name="validator-length-max" placeholder="Max" class="form-control">
						</div>
					</div>
				</div>
				 -->
				<!-- Max validator 
				<div class="form-group" id="validator-max">
				<label class="col-md-2 control-label" for="validateIds"> Max </label>
					<div class="col-md-6">
						<input name="validator-max" placeholder="Max" class="form-control">
					</div>
				</div>
				-->
				<!-- Min validator 
				<div class="form-group" id="validator-min">
				<label class="col-md-2 control-label" for="validateIds"> Min </label>
					<div class="col-md-6">
						<input name="validator-min" placeholder="Min" class="form-control">
					</div>
				</div>
				-->
				<!-- Regexp validator 
				<div class="form-group" id="validator-regexp">
				<label class="col-md-2 control-label" for="validateIds"> Regexp </label>
					<div class="col-md-6">
						<input name="validator-regexp" placeholder="Regexp" class="form-control">
					</div>
				</div>
				-->

				<div class="form-group">
					<label class="col-md-2 control-label" for="referenceEntity.id"> <fmt:message key="generate.property.referenceObjectId"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:select path="referenceEntity.id" cssClass="form-control">
							<form:option value="0" label="select entity" />
							<form:options items="${entities}" itemValue="id" itemLabel="name" />
						</form:select>
						<form:errors path="referenceEntity" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="isMulti"> <fmt:message key="generate.property.isMulti"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:radiobutton path="isMulti" value="true" />
						&nbsp;&nbsp;
						<f:boolean value="true" />
						&nbsp;&nbsp;
						<form:radiobutton path="isMulti" value="false" checked="false" />
						&nbsp;&nbsp;
						<f:boolean value="false" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="isQuery"> <fmt:message key="generate.property.isQuery"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:radiobutton path="isQuery" value="true" />
						&nbsp;&nbsp;
						<f:boolean value="true" />
						&nbsp;&nbsp;
						<form:radiobutton path="isQuery" value="false" />
						&nbsp;&nbsp;
						<f:boolean value="false" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="isIndex"> <fmt:message key="generate.property.isIndex"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:radiobutton path="isIndex" value="true" />
						&nbsp;&nbsp;
						<f:boolean value="true" />
						&nbsp;&nbsp;
						<form:radiobutton path="isIndex" value="false" />
						&nbsp;&nbsp;
						<f:boolean value="false" />
					</div>
				</div>
				<!--@generate-entity-jsp-property-update@-->

				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>
