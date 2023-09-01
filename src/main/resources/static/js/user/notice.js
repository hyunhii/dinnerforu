window.onload = function(){

    $(".title").on('click', function () {

        $(this).next('article').toggle();
    });
}