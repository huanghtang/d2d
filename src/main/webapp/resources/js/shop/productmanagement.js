$(function() {
	// 获取此店铺下的商品列表的URL
	var listUrl = '/d2d/shopadmin/getproductlistbyshop?pageIndex=1&pageSize=999';
	// 商品下架URL
	var statusUrl = '/d2d/shopadmin/modifyproduct';
	getList();
	/**
	 * 获取此商店下的商品列表
	 */
	function getList() {
		// 从后台获取此店铺的商品列表
		$.getJSON(listUrl, function(data) {
			if (data.success) {
				var productList = data.productList;
				var tempHtml = '';
				// 遍历每条商品信息，拼接成一行显示，列信息包括：
				// 商品名称，优先级，上架、下架（含productId），编辑按钮（含productId）
				// 预览（含productId）
				productList.map(function(item, index) {
					var textOp="下架";
					var contrayStatus=0;
					if(item.enableStatus==0){
						
					}
				})
			}
		})

	}

})