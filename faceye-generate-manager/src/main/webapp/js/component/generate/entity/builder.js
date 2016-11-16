/**
 * 说明:FormBuilder js 脚本 作者:@haipenge
 */
var FormBuilder = {
	entityId : '',
	init : function() {
		FormBuilder.addElement();
		FormBuilder.removeElement();
		// 获取实体ID
		var url = location.href;
		FormBuilder.entityId = url.substring(url.lastIndexOf('\/') + 1);

		// $('.edit-property').click(function() {
		// var el = $(this);
		// FormBuilder.editProperty(el);
		// });
		$('#form-container').delegate('.edit-property', 'click', function() {
			var el = $(this);
			FormBuilder.editProperty(el);
		});
		$('#element-properties').delegate('input[type="text"],textarea', 'blur', function() {
			FormBuilder.updateProperty(this);
		});
		$('#element-properties').delegate('input[type="radio"]', 'change', function() {
			// alert($(this).attr('name') + ':' + $(this).val());
			FormBuilder.updateProperty(this);
		});
		$('#element-properties').delegate('select', 'change', function() {
			// alert($(this).attr('name') + ':' + $(this).val());
			FormBuilder.updateProperty(this);
		});
	},
	/**
	 * 向Form中添加元素
	 */
	addElement : function() {
		// var elType=$(this).attr('data-ele');
		// alert(elType);
		$('[data-ele="text"]').click(function() {
			FormBuilder.saveDefaultProperty('text', FormBuilder.addText);
		});
		$('[data-ele="textarea"]').click(function() {
			FormBuilder.saveDefaultProperty('textarea', FormBuilder.addTextArea);
		});
		$('[data-ele="radio"]').click(function() {
			FormBuilder.saveDefaultProperty('radio', FormBuilder.addRadio);
		});
		$('[data-ele="checkbox"]').click(function() {
			FormBuilder.saveDefaultProperty('checkbox', FormBuilder.addCheckBox);
		});
		$('[data-ele="select"]').click(function() {
			FormBuilder.saveDefaultProperty('select', FormBuilder.addSelect);
		});
	},
	/**
	 * 保存 el
	 */
	saveDefaultProperty : function(elType, callback) {
		$.ajax({
			url : '/generate/property/saveDefaultProperty',
			type : 'post',
			dataType : 'json',
			data : {
				entityId : FormBuilder.entityId,
				inputTypeText:elType
			},
			success : function(data, textStatus, xhr) {
				callback(data.id);
			}
		});
	},
	/**
	 * 添加输入框
	 */
	addText : function(propertyId) {
		var html = '<input type="text" class="form-control" placeholder="请输入...">';
		FormBuilder.add2Form(propertyId, html);
	},
	/**
	 * 添加文本框
	 */
	addTextArea : function(propertyId) {
		var html = '<textarea class="form-control" rows="3" placeholder="请输入文本内容..."></textarea>';
		FormBuilder.add2Form(propertyId, html);
	},
	/**
	 * 添加单选按钮
	 */
	addRadio : function(propertyId) {
		var html = '<label class="radio-inline">';
		html += '<input type="radio" name="radio" id="radio1" value="1">选项1';
		html += '</label>';
		html += '<label class="radio-inline">';
		html += '<input type="radio" name="radio" id="radio2" value="2">选荐2';
		html += '</label>';
		html += '<label class="radio-inline">';
		html += '<input type="radio" name="radio" id="radio3" value="2">选项3';
		html += '</label>';
		FormBuilder.add2Form(propertyId, html);
	},
	/**
	 * 添加复选框
	 */
	addCheckBox : function(propertyId) {
		var html = '<label class="checkbox-inline">';
		html += '<input type="checkbox" name="radio" id="radio1" value="1">选项1';
		html += '</label>';
		html += '<label class="checkbox-inline">';
		html += '<input type="checkbox" name="radio" id="radio2" value="2">选项2';
		html += '</label>';
		html += '<label class="checkbox-inline">';
		html += '<input type="checkbox" name="radio" id="radio3" value="2">选项3';
		html += '</label>';
		FormBuilder.add2Form(propertyId, html);
	},
	/**
	 * 选择下拉选择框
	 */
	addSelect : function(propertyId) {
		var html = '<select class="form-control">';
		html += '<option value="1">选项1</option>';
		html += '<option value="2">选项2</option>';
		html += '<option value="3">选项3</option>';
		html += '</select>';
		FormBuilder.add2Form(propertyId, html);
	},
	/**
	 * 将Html添加到表单
	 * 
	 * @param html
	 */
	add2Form : function(propertyId, ele) {
		var html = '<div class="form-group" id="' + propertyId + '">';
		html += '<label class="col-sm-2 control-label">Field</label>';
		html += '<div class="col-sm-8">';
		html += ele;
		html += '</div>';
		html += '<div class="col-sm-2">';
//		html += '<span class="ele-reduce">-</span><span class="edit-property">...</span>';
		html+='<img src="/images/remove.png" class="img-responsive ele-reduce"/> <img src="/images/settings.png" class="img-responsive edit-property"/>';
		html += '</div>';
		html += '</div>';
		$('#form-container form').append(html);
	},
	/**
	 * 删除Form组件
	 */
	removeElement : function() {
		$('#form-container').delegate('.ele-reduce', 'click', function() {
			var propertyId = $(this).parent().parent().attr('id');
			$.ajax({
				url : '/generate/property/removeProperty',
				type : 'post',
				dataType : 'json',
				data : {
					id : propertyId
				},
				success : function(data, textStatux, xhr) {
					$('#' + propertyId).remove();
				}
			});
		});
	},
	/**
	 * 设置表单元素的属性
	 */
	setElementProperties : function(elementType) {

	},
	/**
	 * 编辑指定指属性
	 */
	editProperty : function(el) {
		// 属性ID property id
		var id = $(el).parent().parent().attr('id');
		var url = location.href;
		var entityId = FormBuilder.entityId;
		// alert(id);
		$.ajax({
			url : '/generate/property/ajaxDetail/' + id,
			type : 'post',
			dataType : 'json',
			success : function(data, textStatus, xhr) {
				var containerElId = 'element-properties';
				// alert(containerElId);
				var html = '';
				html = '<form role="form" class="form-horizontal">';
				html += '<input type="hidden" name="id" value="' + id + '">';
				html += '<input type="hidden" name="entity.id" value="' + entityId + '">';
				// 实休ID
				html += '<input type="hidden" name="entity.id" value="">';
				html += '<fieldset>';
				// 名称
				html += '<div class="form-group">';
				html += '<label class="col-md-2 control-label" for="name">名称</label>';
				html += '<div class="col-md-10">';
				html += '<input type="text" name="name" class="form-control" value="' + data.name + '">';
				html += '</div>';
				html += '</div>';

				// 属性名
				html += '<div class="form-group">';
				html += '<label class="col-md-2 control-label" for="propertyName">属性</label>';
				html += '<div class="col-md-10">';
				html += '<input type="text" name="propertyName" class="form-control" value="' + data.propertyName
						+ '">';
				html += '</div>';
				html += '</div>';

				// element type -> text,radio,checkbox,select,area
				html += '<div class="form-group" id="input-type-select">';
				html += '<label class="col-md-2 control-label" for="columnName">类型</label>';
				html += '<div class="col-md-10">';
				// html += '<input type="text" name="columnName" class="form-control" value="' + data.columnName + '">';
				html += $('#input-types').html();
				html += '</div>';
				html += '</div>';

				// 数据类型
				html += '<div class="form-group" id="data-type-select">';
				html += '<label class="col-md-2 control-label" for="dataType.id">数据类型</label>';
				html += '<div class="col-md-10">';
				html += $('#data-types').html();
				html += '</div>';
				html += '</div>';

				// 引用对像
				html += '<div class="form-group" id="reference-entity-select">';
				html += '<label class="col-md-2 control-label" for="referenceEntity.id">引用</label>';
				html += '<div class="col-md-10">';
				html += $('#referency-enties').html();
				html += '</div>';
				html += '</div>';

				// 单选/多选
				html += '<div class="form-group">';
				html += '<label class="col-md-2 control-label" for="dataType.id">多选</label>';
				html += '<div class="col-md-10">';
				html += '<label class="radio-inline"><input type="radio" name="isMulti" value="true"';
				if (data.isMulti) {
					html += ' checked="checked"';
				}
				// else {
				// html += ' checked="false"';
				// }
				html += '>是</label>';
				html += '<label class="radio-inline"><input type="radio" name="isMulti" value="false"';

				if (!data.isMulti) {
					html += ' checked="checked"';
				}
				// else {
				// html += ' checked="true"';
				// }
				html += '>否</label>';
				html += '</div>';
				html += '</div>';

				// 是否查询属性
				html += '<div class="form-group">';
				html += '<label class="col-md-2 control-label" for="isQuery">查询</label>';
				html += '<div class="col-md-10">';
				html += '<label class="radio-inline"><input type="radio" name="isQuery" value="true"';
				if (data.isQuery) {
					html += ' checked="checked"';
				}
				html += '>是</label>';
				html += '<label class="radio-inline"><input type="radio" name="isQuery" value="false"';
				if (!data.isQuery) {
					html += ' checked="checked"';
				}
				html += '>否</label>';
				html += '</div>';
				html += '</div>';

				// 是否索引

				html += '<div class="form-group">';
				html += '<label class="col-md-2 control-label" for="isIndex">索引</label>';
				html += '<div class="col-md-10">';
				html += '<label class="radio-inline"><input type="radio" name="isIndex" value="true"';
				if (data.isIndex) {
					html += ' checked="checked"';
				}
				html += '>是</label>';
				html += '<label class="radio-inline"><input type="radio" name="isIndex" value="false"';
				if (!data.isIndex) {
					html += 'checked="checked"';
				}
				html += '>否</label>';
				html += '</div>';
				html += '</div>';

				html += '<fieldset>';
				html += '</form>';
				// alert(html);
				$('#' + containerElId).empty().append(html);
				// FormBuilder.init();
				FormBuilder.setPropertyValuesAfterBuilderDom(data);
			}
		});
	},
	/**
	 * 设置属性值
	 */
	setPropertyValuesAfterBuilderDom : function(data) {
		var dataTypeId = data.dataType.id;
		$('#data-type-select input[type="radio"]').each(function(i, el) {
			var value = $(el).val();
			if (value == dataTypeId) {
				$(el).attr('checked', 'true');
			}
		});
		if (data.referenceEntity) {
			// alert('r:'+data.referenceEntity.id);
			$('#reference-entity-select select').val(data.referenceEntity.id);
		}
		if(data.inputType){
			$('#input-type-select select').val(data.inputType);
		}

	},
	/**
	 * 更新property value
	 */
	updateProperty : function(el) {
		var propertyId = $('#element-properties input[name="id"]').val();
		// alert('property id is:' + propertyId);
		var value = $(el).val();
		var name = $(el).attr('name');
		// alert('name is:' + name + ",value is:" + value);
		FormBuilder.doUpdate(propertyId, name, value);
	},
	/**
	 * 更新Property id: property id name:property 指定属性，如name,isMulti,isQuery等 value:属性值, callback:回调函数
	 */
	doUpdate : function(id, name, value, callback) {
		$.ajax({
			url : '/generate/property/updateProperty',
			type : 'post',
			dataType : 'json',
			data : {
				id : id,
				propertyName : name,
				propertyValue : value
			},
			success : function(data, textStatux, xhr) {
				if (callback) {
					callback();
				}
				if(name=='inputType'){
					$('#'+id).remove();
					if(value==1){
						FormBuilder.addText(id);
					}else if(value ==2){
						FormBuilder.addTextArea(id);
					}else if(value==3){
						FormBuilder.addRadio(id);
					}else if(value==4){
						FormBuilder.addSelect(id);
					}else if(value==5){
						FormBuilder.addCheckbox(id);
					}
				}
			}
		});
	}
};

$(document).ready(function() {
	FormBuilder.init();
});
