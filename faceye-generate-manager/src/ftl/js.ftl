/**
*说明:${component.name}->${component.packageName} -> ${entity.name} ${entity.className}  js 脚本
*作者:${project.author}
*/
var ${entity.className}={
  init:function(){
       /**
       *全选、全不选
       */
       ${r'$('}'input[name="check-all"]').click(function(){
	     Check.onCheck(${r'$('}'input[name="check-all"]'),${r'$('}'input[name="check-single"]'));
	    });
	    /**
	    *执行批量删除
	    */
	    ${r'$('}'.multi-remove').click(function(){
	        var modal = new Modal({
	           id:'multi-remove-confirm-modal',
			   title : '确认删除',
			   body : '您确认要删除本条数据吗?',
			   footer : true
		    });
		    modal.show();
		    ${r'$('}'#multi-remove-confirm-modal .modal-footer').delegate("#default-window-btn-yes", "click", function() {
			   ${entity.className}.multiRemove();
	     	});
	       
	    });
	    /**
	    *单条记录删除
	    */
	    ${r'$('}'.record-remove').click(function(){
	       var id=$(this).parent().parent().attr('id');
	        var modal = new Modal({
	           id:'remove-confirm-modal',
			   title : '确认删除',
			   body : '您确认要删除本条数据吗?',
			   footer : true
		    });
		    modal.show();
		    ${r'$('}'#remove-confirm-modal .modal-footer').delegate("#default-window-btn-yes", "click", function() {
			  ${entity.className}.recordRemove(id);
	     	});
	     });
  },
  /**
   * 批量删除
   */
  multiRemove:function(){
	  var checkedIds=Check.getCheckedIds(${r'$('}'input[name="check-single"]'));
	  if(checkedIds!=''){
		  ${r'$'}.ajax({
			  url:'/${component.packageName}/${entity.className?uncap_first}/multiRemove',
			  type:'post',
			  dataType:'json',
			  data:{
				  ids:checkedIds
			  },
			  success:function(data,textStatux,xhr){
				  var msg=new Msg({msg:'数据删除成功'});
				  var idArray=checkedIds.split(',');
				  for(var i=0;i<idArray.length;i++){
				     var id=idArray[i];
				     ${r'$('}'#'+id).remove();
				  }
				  $('#multi-remove-confirm-modal').modal('hide');
				  msg.show();
			  }
		  });
	  }else{
		  var msg=new Msg({msg:'请选择要删除的数据',type:'warning'});
		  msg.show();
	   }
    },
    /**
    *删除一条数据
    */
    recordRemove:function(id){
       ${r'$'}.ajax({
			  url:'/${component.packageName}/${entity.className?uncap_first}/remove/'+id,
			  type:'post',
			  dataType:'json',
			  success:function(data,textStatux,xhr){
				  var msg=new Msg({msg:data.msg});
				  ${r'$('}'#'+id).remove();
				  $('#remove-confirm-modal').modal('hide');
				  msg.show();
			  }
		  });
    }
};

$(document).ready(function(){
	${entity.className}.init();
});
