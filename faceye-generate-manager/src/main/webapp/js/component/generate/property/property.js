/**
 * 说明:Property js 脚本 作者:@haipenge
 */
var Property = {
	init : function() {
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="check-single"]'));
		});
		$('input[name="propertyName"]').blur(function(){
			Property.autoSetPropertyColumnValueByPropertyName();
		});
	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"'));
		if (checkIds != '') {
			$.ajax({
				url : '/generate/property/remove',
				type : 'post',
				dataType : 'json',
				data : {
					ids : checkedIds
				},
				success : function(data, textStatux, xhr) {
					var msg = new Msg({
						msg : '数据删除成功'
					});
					msg.show();
				}
			});
		} else {
			var msg = new Msg({
				msg : '请选择要删除的数据',
				type : 'warning'
			});
			msg.show();
		}
	},
	autoSetPropertyColumnValueByPropertyName:function(){
		var propertyName=$('input[name="propertyName"]').val();
		if(propertyName&&propertyName!=''){
			$.ajax({
				url:'/generate/property/autoSetPropertyColumnValueByPropertyName',
				type:'post',
				dataType:'json',
				data:{
					propertyName:propertyName
				},
				success:function(data){
					$('input[name="columnName"]').val(data.columnName);
				}
			});
		}
	}
};

$(document).ready(function() {
	Property.init();
});
