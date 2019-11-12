$(function() {
	var initUrl = '/d2d/shopadmin/getshopinitinfo';
	var registShopUrl = '/d2d/shopadmin/registershop';
	alert(initUrl);
	getShopInitInfo();
	function getShopInitInfo() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.shopCategoryId
							+ '">' + item.shopCategoryName + '</option>';
				});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
		$('#submit').click(function() {
			var shop = {};
			shop.shopName = $('#shop-name').val();
			shop.shopAddr = $('#shop-addr').val();
			shop.phone = $('#shop-addr').val();
			$("#shop-category").val();
			var shopCategoryOptions=$("#shop-category option:selected");
			shop.shopCategory=shopCategoryOptions.val();
			$("#area").val();
			var areaOptions=$("#area option:selected");
			shop.area=areaOptions.val();
			var shopImg = $('#shop-img')[0].files[0];
			var formData = new FormData();
			formData.append('shopImg',shopImg);
			formData.append('shopStr',JSON.stringify(shop));
			$.ajax({
				url: registerShopUrl,
				type:'POST',
				data:formData,
				contentType:false,
				processData:false,
				cache:false,
				//data是后台返回的数据
				success:function(data){
					if(data.success){
						$.toast('提交成功！');
					}else{
						$.toast('提交失败！'+data.errMsg);
					}
				}
			})
		});

	}
})