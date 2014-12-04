$(document).ready(function(){
	//Dropdown menu
	$(".panel-heading").click(function(){
		if ($(this).find("span").hasClass("glyphicon glyphicon-chevron-up")) {
			$(this).find("span").removeClass("glyphicon glyphicon-chevron-up");
			$(this).find("span").addClass("glyphicon glyphicon-chevron-down");
			$(this).parent().find("div").fadeOut("fast");
		} else {
			$(this).find("span").removeClass("glyphicon glyphicon-chevron-down");
			$(this).find("span").addClass("glyphicon glyphicon-chevron-up");
			$(this).parent().find("div").fadeIn("fast");
		}
	});
});