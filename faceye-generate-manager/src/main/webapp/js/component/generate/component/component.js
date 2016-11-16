/**
 * 说明:Component js 脚本 作者:@haipenge
 */
var Component = {
	init : function() {
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="check-single"]'));
		});
		$('.multi-remove').click(function() {
			Component.multiRemove();
		});
	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"'));
		if (checkedIds != '') {
			$.ajax({
				url : '/generate/component/multiRemove',
				type : 'post',
				dataType : 'json',
				data : {
					ids : checkedIds
				},
				success : function(data, textStatux, xhr) {
					var ids=checkedIds.split(',');
					for(var i=0;i<ids.length;i++){
						$('#'+ids[i]).remove();
					}
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
	}
};

$(document).ready(function() {
	Component.init();
});
