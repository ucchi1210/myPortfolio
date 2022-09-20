'use strict';

/**画面ロード時の処理 */
jQuery( function($){


	/**更新ボタンを押した時の処理 */
	$("#btn-update").click(function(event) {
		//データ更新
		updataData();
	});

	/**削除ボタンを押した時の処理 */
	$('#btn-delete').click(function(event) {

		deleteData();
	});

});

/**データ更新処理 */
function updataData() {
	//フォームの値を取得
	var formData = $('#data-detail-form').serializeArray();

	//ajax通信
	$.ajax({
		type: "PUT",
		cache: false,
		url: '/update',
		data: formData,
		dataType: 'json',
	}).done(function(data) {
		//ajax成功時の処理
		alert('データを更新しました');
		//データ一覧画面にリダイレクト
		window.location.href = '/datalist';
	}).fail(function(jqXHR, textStatus, errorThrown) {
		//ajax失敗の時の処理
		alert('ユーザー更新に失敗しました');

	}).always(function() {
		//常に実行する処理
	});

}

/**ユーザー削除処理 */
function deleteData(){
	
	//フォームの値を取得
	var formData = $('#data-detail-form').serializeArray();
	
	//ajax通信
	$.ajax({
		type:"DELETE",
		cache:false,
		url:'/delete',
		data:formData,
		datatype:'json',
	}).done(function(data){
		//ajax成功時の処理
		alert('ユーザーを削除しました');
		window.location.href='/datalist';
	}).fail(function(jqXHR, textStatus, errorThrown){
		//ajax失敗時の処理
		alert('ユーザー削除に失敗しました');
		
	}).always(function() {
		//常に実行する処理
	});
}