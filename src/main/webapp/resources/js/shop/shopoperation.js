$(function() {
	var initUrl = '/d2d/shopadmin/getshopinitinfo';
	var registShopUrl = '/d2d/shopadmin/registershop';
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
			shop.phone = $('#shop-phone').val();
			shop.shopDesc = $('#shop-desc').val();
			$("#shop-category").val();
			var shopCategoryOptions=$("#shop-category option:selected").attr("data-id");
			shop.shopCategory={shopCategoryId:shopCategoryOptions}
			$("#area").val();
			var areaOptions=$("#area option:selected").attr("data-id");
			shop.area={areaId : areaOptions};
			var shopImg = $('#shop-img')[0].files[0];
			var formData = new FormData();
			formData.append('shopImg',shopImg);
			formData.append('shopStr',JSON.stringify(shop));
			var verifyCodeActual=$('#j_captcha').val();
		
			//如果为空
			if(!verifyCodeActual){
				$.toast(verifyCodeActual);
				$.toast('请输入验证码!');
				return;
			}
			formData.append('verifyCodeActual',verifyCodeActual);
			$.ajax({
				url: registShopUrl,
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
					$('#captcha_img').click();
				}
			})
		});

	}
})