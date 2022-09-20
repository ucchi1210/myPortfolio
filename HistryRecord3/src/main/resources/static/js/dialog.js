/**
 * 
 */
 // dialog.js
const DELETE_MESSAGE = "削除してもよろしいですか？"
$('.mail-action').click(function() {
	if(!confirm(DELETE_MESSAGE)){
		return false;
	}
});