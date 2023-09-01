function setList(list) {


    let html = "";
    let previous = "";
    let previous2 = "";
    let foodName = [];
    let foodSpan ="";

    if(list.length === 0) {
        $(".container").eq(1).children().not(".title-wrap, .none-content").remove();
        return;
    }

    for(let i=0; i<list.length; i++) {

        let date2 = new Date(list[i].subscribeDate);
        // let day2 = getDayOfWeek(date2.getDay());
        let day2 = list[i].sdDayOfWeek;

        if(previous !== list[i].id) {

            if(i!==0) {

                for(let j=0; j<foodName.length; j++) {
                    if(j !== 0) foodSpan += " / ";
                    foodSpan += "<span>" + foodName[j] + "</span>";
                }
                foodSpan += "                </div>\n" +
                    "           </div>\n" +
                    "   </div>\n";

                html += foodSpan;

                foodSpan = "";
                foodName = [];


                html += "   </div>\n" +
                        "</div>\n";

            }

            previous = list[i].id;

            let date = list[i].orderDate.substr(0,10);
            // let day = getDayOfWeek(new Date(date).getDay());
            let day = list[i].dayOfWeek;



            html += "<div class=\"content-item\">\n" +
                    "   <div class=\"gray-line\">\n" +
                    "       <div>결제일 : "+ date +" ("+ day +")</div>\n" +
                    "       <div><a href=\"/mypage/cancel/"+ list[i].id +"\"><div class=\"cancel-btn\"><div class=\"dot\"></div>취소</div></a></div>\n" +
                    "   </div>\n" +
                    "   <div class=\"subscription-menu-detail-wrap\">" +
                    "       <div class=\"subscription-menu-detail\" onclick=\"location.href='/mypage/history/detail/"+  list[i].id +"'\">\n" +
                    "           <div style=\"font-weight: bold\">구독일 : "+ (date2.getMonth() + 1 )+"월 "+ date2.getDate() +"일 ("+ day2 +")</div>\n" +
                    "           <div class=\"flex-space-between\">\n" +
                    "               <div>구매수량</div>\n" +
                    "               <div>"+ list[i].cnt +"개</div>\n" +
                    "           </div>\n" +
                    "           <div class=\"flex-space-between\">\n" +
                    "               <div>메뉴</div>\n" +
                    "               <div>";


            previous2 = list[i].subscribeDate;
            foodName.push(list[i].foodName);

        }else {
            if(previous2 !== list[i].subscribeDate) {

                for(let j=0; j<foodName.length; j++) {
                    if(j !== 0) foodSpan += " / ";
                    foodSpan += "<span>" + foodName[j] + "</span>";
                }
                foodSpan += "                </div>\n" +
                            "           </div>\n" +
                            "   </div>\n";


                html += foodSpan;

                foodSpan = "";
                foodName = [];
                previous2 = list[i].subscribeDate;
                foodName.push(list[i].foodName);

                html += "       <div class=\"subscription-menu-detail\" onclick=\"location.href='/mypage/history/detail/"+  list[i].id +"'\">\n" +
                        "           <div style=\"font-weight: bold\">구독일 : "+ (date2.getMonth() + 1 )+"월 "+ date2.getDate() +"일 ("+ day2 +")</div>\n" +
                        "           <div class=\"flex-space-between\">\n" +
                        "               <div>구매수량</div>\n" +
                        "               <div>"+ list[i].cnt +"개</div>\n" +
                        "           </div>\n" +
                        "           <div class=\"flex-space-between\">\n" +
                        "               <div>메뉴</div>\n" +
                        "               <div>";
            } else {
                foodName.push(list[i].foodName);
            }
        }

    } //for문

    for(let j=0; j<foodName.length; j++) {
        if(j !== 0) foodSpan += " / ";
        foodSpan += "<span>" + foodName[j] + "</span>";
    }
    foodSpan += "                </div>\n" +
        "           </div>\n" +
        "   </div>\n";


    html += foodSpan;

    html += "   </div>\n" +
            "</div>\n";


    $(".container").eq(1).children().not(".title-wrap").remove();
    $(".container").eq(1).append(html);


}