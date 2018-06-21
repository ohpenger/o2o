$(function() {
	function getQueryString(name) {
		var reg=new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
		var r=window.location.search.substr(1).match(reg);
		if(r!=null) {
			return decodeURIComponent(r[2]);
		}
		return'';
	}
	
	var productId = getQueryString('productId');
	var infoUrl = '/oto/shopadmin/getproductbyid?productId=' + productId;
	var categoryUrl = '/oto/shopadmin/getproductcategorylist';
	var productPostUrl = '/oto/shopadmin/modifyproduct';
	var isEdit = false;
	var shopId =1;
	if (productId) {
		getInfo(productId);
		isEdit = true;
	} else {
		getCategory(shopId);
		productPostUrl = '/oto/shopadmin/addproduct';
	}

	function getInfo(id) {
		$.getJSON(
					infoUrl,
					function(data) {
						if (data.success) {
							var product = data.product;
							$('#product-name').val(product.productName);
							$('#product-desc').val(product.productDesc);
							$('#priority').val(product.priority);
							$('#normal-price').val(product.normalPrice);
							$('#promotion-price').val(
									product.promotionPrice);

							var optionHtml = '';
							var optionArr = data.productCategoryList;
							var optionSelected = product.productCategory.productCategoryId;
							optionArr
									.map(function(item, index) {
										var isSelect = optionSelected === item.productCategoryId ? 'selected'
												: '';
										optionHtml += '<option data-value="'
												+ item.productCategoryId
												+ '"'
												+ isSelect
												+ '>'
												+ item.productCategoryName
												+ '</option>';
									});
							$('#category').html(optionHtml);
						}
					});
	}

	function getCategory() {
		$.getJSON(categoryUrl, function(data) {
			if (data.success) {
				var productCategoryList = data.data;
				var optionHtml = '';
				productCategoryList.map(function(item, index) {
					optionHtml += '<option data-value="'
							+ item.productCategoryId + '">'
							+ item.productCategoryName + '</option>';
				});
				$('#category').html(optionHtml);
			}
		});
	}

	$('#submit').click(
			function() {
				// var product = {};
				// product.productName = $('#product-name').val();
				// product.productDesc = $('#product-desc').val();
				// product.priority = $('#priority').val();
				// product.normalPrice = $('#normal-price').val();
				// product.promotionPrice = $('#promotion-price').val();
				var productCategory = {
					productCategoryId : '1'

				};
				// product.productId = productId;

				// var thumbnail = $('#small-img')[0].files[0];
				// var formData = new FormData();
				// formData.append('thumbnail', thumbnail);
				// $('.detail-img').map(
				// 		function(index, item) {
				// 			if ($('.detail-img')[index].files.length > 0) {
				// 				formData.append('productImg' + index,
				// 						$('.detail-img')[index].files[0]);
				// 			}
				// 		});
				// formData.append('productStr', JSON.stringify(product));
				var formData = {
					'product-name' : $('#product-name').val(),
					'product-desc' : $('#product-desc').val(),
					'priority' : $('#priority').val(),
					'normal-price' : $('#normal-price').val(),
					'promotion-price' : $('#promotion-price').val(),
					'productCategoryId' : productCategory.productCategoryId
				}
				//var verifyCodeActual = $('#j_captcha').val();
				//if (!verifyCodeActual) {
					//$.toast('请输入验证码！');
					//return;
				//}
				// formData.append("verifyCodeActual", verifyCodeActual);
				$.ajax({
					url : productPostUrl,
					type : 'POST',
					data : JSON.stringify(formData),
					dataType:'json',
				  contentType: "application/json; charset=utf-8",
				  processData: false,
					cache : false,
					success : function(data) {
						if (data.success) {
							$.toast('提交成功！');
							$('#captcha_img').click();
						} else {
							$.toast('提交失败！');
							$('#captcha_img').click();
						}
					}
				});
			});

});