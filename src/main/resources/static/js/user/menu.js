let today = new Date();
today.setHours(0,0,0);

$(document).ready(function (){

    $(".menu-block").on('click', function (){

        $(".menu-block").removeClass('on')

        $(this).addClass('on');

        switch ($(this).index()) {
            case 0:
                $(".menu-detail-wrap").show();
                break;

            case 1:
                $(".menu-detail-wrap").hide();
                $(".today").eq(0).parent().show();
                break;

            default:
                $(".menu-detail-wrap").hide();

                for(let i= 5 * ($(this).index() -2); i< 5 * ($(this).index() -1); i++) {
                 $(".menu-detail-wrap").eq(i).show();
                }
        }

    });

    setMenu(menuList)

});


function setMenu(menu) {
    let markDate = "";
    let html ="";
    let className = "";
    let someday = "";

    for(let i=0; i<menu.length; i++) {
        className = "";

        someday = new Date(menu[i].date +' 00:00:00');

        if(today.getFullYear() === someday.getFullYear() && today.getMonth()=== someday.getMonth() && today.getDate() === someday.getDate()) {
            className = "today";
        } else if (today.getTime() > someday.getTime()) {
            className = "pastDay";
        } else {
            className ="";
        }

        if(markDate === "" || markDate !== menu[i].date) {
            markDate = menu[i].date;

            if(i!==0) {
                html += "</div></div></div>";
            }

            html += "<div class=\"menu-detail-wrap\">\n" +
                "   <div class=\"detail-date "+ className +"\">"+ menu[i].date.slice(5,7) +"/"+ menu[i].date.slice(8,10) + " (" + getDayOfWeek(menu[i].date)  +")</div>\n" +
                "       <div style=\"display: flex\">\n";
        }

        html += "           <div class=\"detail-list "+ className +"\">\n" +
            // "               <img src=\"/images/food/"+ menu[i].foodImg +"\" alt=\"\">\n" +
            "               <img src=\"https://storage.googleapis.com/food-image-bucket-1/"+ menu[i].foodImg +"\" alt=\"\">\n" +
            "               <div>"+ menu[i].foodName +"</div>\n" +
            "           </div>\n";

        if(i=== menu.length -1) {
            html += "</div></div></div>";
        }
    }

    $(".menu-detail").append(html);
}
