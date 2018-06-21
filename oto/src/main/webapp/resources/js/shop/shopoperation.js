/**
 * 
 */



$(function() {
		function getQueryString(name) {
		var reg=new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
		var r=window.location.search.substr(1).match(reg);
		if(r!=null) {
			return decodeURIComponent(r[2]);
		}
		return'';
		}
		var shopId = getQueryString('shopId');
		var isEdit = shopId? true:false;
		var initUrI = '/oto/shopadmin/getshopinitinfo';
		var registerShopUrI = '/oto/shopadmin/registershop';
		var shopInfoUrI ='/oto/shopadmin/getshopbyid?shopId=' + shopId;
		var editShopUrI ='/oto/shopadmin/modifyshop';
		//店铺注册
		if(!isEdit){
			getShopInitInfo();
		}
		//店铺修改
		else{
			getShopInfo(shopId);
		}
		
		function getShopInfo(shopId) {
			$.getJSON(shopInfoUrI, function(data) {
				if(data.success) {
					var shop = data.shop;
					$('#shop-name').val(shop.shopName);
					$('#shop-addr').val(shop.shopAddr);
					$('#shop-phone').val(shop.phone);
					$('#shop-desc').val(shop.shopDesc);
					var shopCategory = '<option data-id="' + shop.shopCategory.shopCategoryId + '"selected>' + shop.shopCategory.shopCategoryName + '</option>';
					var tempAreaHtml = '';
					data.areaList.map(function(item, index) {
						tempAreaHtml +='<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
					});
					$('#shop-category').html(shopCategory);
					$('#shop-category').attr('disabled', 'disabled');
					$('#area').html(tempAreaHtml);
					$("#area option[data-id='" + shop.area.areaId + "']").attr("selected", "selected");
				}
			});
		}

		
		getShopInitInfo(initUrI)
		function getShopInitInfo() {
			$.getJSON(initUrI, function(data) {
				if(data.success) {
					var tempHtml = '';
					var tempAreaHtml = '';
					data.shopCategoryList.map(function(item, index) {
						tempHtml += '<option data-id="' + item.shopCategoryId + '">' + item.shopCategoryName + '</option>';
					});
					data.areaList.map(function(item, index) {
						tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
					});
					$('#shop-category').html(tempHtml);
					$('#area').html(tempAreaHtml);
				}
			});
		}
		$('#submit').click(function() {
			// var shop = {};
			// shop.shopName = $('#shop-name').val();
			// shop.shopAddr = $('#shop-addr').val();
			// shop.phone = $('#shop-phone').val();
			// shop.shopDesc = $('#shop-desc').val();
			// shop.shopCategory = {
			// 	shopCategoryId: $('#shop-category').find('option').not(function() {
			// 		return !this.selected;
			// 	}).data('id')
			// };
			var shopCategory = {
				"shopCategoryId":$('#shop-category').find('option').not(function() {
					return !this.selected;
				}).data('id')
			}
			// shop.area = {
			// 	areaId: $('#area').find('option').not(function(){
			// 		return !this.selected;
			// 	}).data('id')
			// };
			var area = {
					"areaId": $('#area').find('option').not(function(){
						return !this.selected;
					}).data('id')
			}
			//var shopImg = $('#shop-img')[0].files[0];
			// var formData = new FormData();
			//formData.append('shopImg',shopImg);
			// formData.append('shopStr',JSON.stringify(shop));
			// var shopstr = JSON.stringify(shop);
			var verifyCodeActual = $('#j_captcha').val();
			if(!verifyCodeActual){
				$.toast('请输入验证码！');
				return;
			}
			
			//formData.append('verifyCodeActual',verifyCodeActual)
			var formData1={
					"shopName" : $('#shop-name').val(),
					"shopAddr" : $('#shop-addr').val(),
					"phone" : $('#shop-phone').val(),
					"shopDesc" : $('#shop-desc').val(),
					"areaId" : area.areaId,
					"shopCategoryId":shopCategory.shopCategoryId,
					"shopId": shopId
			}			
			$.ajax({
				url: (isEdit?editShopUrI:registerShopUrI),
				type: 'POST',
				data: JSON.stringify(formData1),
				dataType:'json',
				contentType: "application/json; charset=utf-8",
				processData: false,
				cache: false,
				success: function(data) {
					if(data.success) {
						$.toast('提交成功！');
					} else{
						$.toast('提交失败！' + data.errMsg);
					}
					$('#captcha_img').click();
				}
			})
		});
	})
