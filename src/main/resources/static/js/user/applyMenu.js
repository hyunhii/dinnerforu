window.onload = function() {
    buildCalendar(menuList);

    // $(".yearMonth td:nth-child(2)").attr('onclick', '').unbind('click');
    // $(".yearMonth td:nth-child(2)").addClass('off');

    $(".add").on("click", function () {
        let $cnt = $(".cnt");
        let $price = $(".price");

        let quantity = parseInt($cnt.text()) + 1;
        $cnt.text(quantity);

        applyAmount += price;
        $price.text(comma(quantity * price));

        let cnt = 0;
        $.each(applyDay, function (i) {
           if(applyDay[i].day === $("#calYear").text() + "-" + leftPad($("#calMonth").text()) +"-" + leftPad(selectedDate[0].innerText)) {
               cnt++;
               applyDay[i].cnt += 1;
           }
        })

        if(cnt === 0) {
            let item = {};
            item.id = $("#menuId").val();
            item.day = $("#calYear").text() + "-" + leftPad($("#calMonth").text()) +"-" + leftPad(selectedDate[0].innerText);
            item.cnt = quantity;
            item.price = price;

            applyDay.push(item);
            $(".apply").addClass("on");
            $(".subtract").addClass("on");
            selectedDate.next().addClass("on");
        }
    });

    $(".subtract").on("click", function (){
        let $cnt = $(".cnt");
        let $price = $(".price");

        if(parseInt($cnt.text())===0) return;

        let quantity = parseInt($cnt.text()) - 1;
        $cnt.text(quantity);

        applyAmount -= price;
        $price.text(comma(quantity * price));


        $.each(applyDay, function (i) {
            if(applyDay[i].day === $("#calYear").text() + "-" + leftPad($("#calMonth").text()) +"-" + leftPad(selectedDate[0].innerText)) {

                if(quantity === 0) {
                    $(".subtract").removeClass("on");
                    applyDay.splice(i, 1);
                    selectedDate.next().removeClass("on");
                } else {
                    applyDay[i].cnt -= 1;
                }
                return false;
            }
        });
    });


    $(".cntControl button").on("click", function (){
        if(applyDay.length === 0) {
            $(".apply").removeClass("on");
        }
        $(".apply").text(applyDay.length + "일 | " + comma(applyAmount) + "원 구독");
    });

}

let nowMonth = new Date();
let today = new Date();
today.setHours(0,0,0,0);
let price = 0;
let applyDay = [];
let applyAmount = 0;
let selectedDate = null;
// let menuList2 = null; //다음달 메뉴
// let nonShow = "off";

//달력생성
function buildCalendar(list) {
    let sDate = false;

    let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1);
    let lastDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, 0);
    let nowColumn = null;

    let tbody_Calendar = document.querySelector(".calendar > tbody");
    document.getElementById("calYear").innerText = "" + (nowMonth.getFullYear());
    document.getElementById("calMonth").innerText = "" + (nowMonth.getMonth() + 1);

    while (tbody_Calendar.rows.length > 0) { //초기화
        tbody_Calendar.deleteRow(tbody_Calendar.rows.length - 1);
    }

    let nowRow = tbody_Calendar.insertRow();

    for (let j = 0; j < firstDate.getDay(); j++) {
        nowColumn = nowRow.insertCell();
    }

    for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {

        nowColumn = nowRow.insertCell();

        let newDIV = document.createElement("div");
        nowColumn.appendChild(newDIV);

        let newSpan = document.createElement("span");
        newSpan.innerHTML = "" +  nowDay.getDate();
        nowColumn.firstChild.appendChild(newSpan);

        nowColumn.appendChild(document.createElement("div")); //underscore

        if(nowDay.getDay()===0 || nowDay.getDay()===6) {
            newDIV.className = "holiday ";
        }

        if (nowDay.getDay() === 6) {
            nowRow = tbody_Calendar.insertRow();
        }

        if(applyDay.length > 0) {
            $.each(applyDay, function (idx){
               if(nowDay.getTime() == new Date(applyDay[idx].day +' 00:00:00').getTime()) {

                   $(nowColumn).children('div').eq(1).addClass('on')

                   return false;
               }
            });
        }


        if (nowDay <= today) {
            newDIV.className += "pastDay";
        } else {
            newDIV.className += "futureDay";

            if(!sDate && nowDay.getDay() > 0 && nowDay.getDay() < 6) {
                sDate = nowDay;
                newDIV.className += " focus";
                selectedDate = $(newDIV);
                matchMenuList(nowDay.getDate(), list);
            }
        }




        if(!newDIV.classList.contains('pastDay') && !newDIV.classList.contains('holiday')) {
            //Click 이벤트
            newDIV.onclick = function () {
                selectedDate.removeClass('focus');
                selectedDate = $(this);
                selectedDate.addClass("focus");

                matchMenuList(this.innerText, list);
            }
        }

    }
}

function moveMonth(order) {

    let month;
    order === "prev" ? month = -1 :  month = 1;

    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + month, 1)

    buildCalendar(menuList);

    if(order === "next") {
        $(".yearMonth td:nth-child(2)").attr('onclick', 'moveMonth("prev")').unbind('click');
        $(".yearMonth td:nth-child(2)").removeClass('off');

        $(".yearMonth td:nth-child(4)").attr('onclick', '').unbind('click');
        $(".yearMonth td:nth-child(4)").addClass('off');
    } else if (order === "prev") {
        $(".yearMonth td:nth-child(4)").attr('onclick', 'moveMonth("next")').unbind('click');
        $(".yearMonth td:nth-child(4)").removeClass('off');

        $(".yearMonth td:nth-child(2)").attr('onclick', '').unbind('click');
        $(".yearMonth td:nth-child(2)").addClass('off');
    }

    /*let month;
    order === "prev" ? month = -1 :  month = 1;

    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + month, 1)

    if(order === "next") {
        if(menuList2 == null  && nonShow === "off") {
            $.ajax({
                type : "post",
                url : "nextMonthMenu",
                data : {
                    "year" : nowMonth.getFullYear(),
                    "month" : nowMonth.getMonth() + month
                },
                success : function (data) {
                    $("body div").append(data);

                    if(menuList2 != null && menuList2.length > 0 && nonShow === "off") {
                        buildCalendar(menuList2);

                    }
                }
            });
        } else {
            buildCalendar(menuList2);
        }

        $(".yearMonth td:nth-child(2)").attr('onclick', 'moveMonth("prev")').unbind('click');
        $(".yearMonth td:nth-child(2)").removeClass('off');

        $(".yearMonth td:nth-child(4)").attr('onclick', '').unbind('click');
        $(".yearMonth td:nth-child(4)").addClass('off');

    } else if (order === "prev") {

        buildCalendar(menuList);

        $(".yearMonth td:nth-child(4)").attr('onclick', 'moveMonth("next")').unbind('click');
        $(".yearMonth td:nth-child(4)").removeClass('off');

        $(".yearMonth td:nth-child(2)").attr('onclick', '').unbind('click');
        $(".yearMonth td:nth-child(2)").addClass('off');
    }*/

}


function matchMenuList(day, list) {
    // let $imgWrap = $(".img-wrap");
    // let $menuName = $(".menuName");
    let $cnt = $(".cnt");
    let $price = $(".price");
    let $subtract = $(".subtract");


    //초기화
    $(".menuImgAndName").children().remove();
    // $imgWrap.children().remove();
    // $menuName.children().empty();
    $cnt.text(0);
    $price.text(0);
    $subtract.removeClass("on");


    $(".detailMenu article p").text(nowMonth.getMonth() + 1 + "월 "+ day + "일");

    let clickedDate = nowMonth.getFullYear() + "-"+ leftPad(nowMonth.getMonth() + 1 )+"-" + leftPad(day);

    let flag = 0;

    for(let i=0; i < list.length; i++) {

        if(list[i].date === clickedDate) {
            flag++;
            /*if(list[i].foodImg != null) {
                $imgWrap.append(" <img src=\"/images/food/"+ list[i].foodImg  +"\" alt=\"\">")
                price = list[i].price;
                $("#menuId").val(list[i].menuId)
            }
            $menuName.append("<div><span>"+ list[i].foodName +"</span></div>")*/

            $(".menuImgAndName").append(
                "<div class=\"food-detail\">\n" +
                // "   <img class=\"foodImg\" src=\"/images/food/"+ list[i].foodImg  +"\" alt=\"\">\n" +
                "   <img class=\"foodImg\" src=\"https://storage.googleapis.com/food-image-bucket-1/"+ list[i].foodImg  +"\" alt=\"\">\n" +
                "   <div class=\"foodName\"><span>"+ list[i].foodName +"</span></div>\n" +
                "</div>"
            );

            $("#menuId").val(list[i].menuId);
            price = list[i].price;


            $.each(applyDay, function (idx){

                if(applyDay[idx].day === $("#calYear").text() + "-" + leftPad($("#calMonth").text()) +"-" + leftPad(day)) {

                    $cnt.text(applyDay[idx].cnt);
                    $price.text(comma(list[i].price * applyDay[idx].cnt));
                    $subtract.addClass('on');

                    return false;
                }
            });
            if(flag == 2) {
                flag = 0;
                return false;
            }
        }
    }
}

function attachData() {
    applyDay.sort(function(a,b) {
        return (a.day) > (b.day) ? 1 : (a.day) < (b.day) ? -1 : 0;
    })

    $("#applyList").val(JSON.stringify(applyDay));
}

