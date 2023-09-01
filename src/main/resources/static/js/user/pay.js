let applyScheduleArr = [];
let applyCnt = 0;
let applyDateCnt = 0;
let applyAmount = 0;

$( document ).ready(function() {

    $(".phone").text(autoHyphenPhone($(".phone").text()))

    let applyStartDate = applylist[0].day;
    let applyEndDate = applylist[0].day;

    //구독 수량
    $.each(applylist, function (idx){
        applyCnt += applylist[idx].cnt;
        applyDateCnt++;
        applyAmount += applylist[idx].price * applylist[idx].cnt

        if(applyStartDate > applylist[idx].day) {
            applyStartDate = applylist[idx].day
        }

        if(applyEndDate < applylist[idx].day) {
            applyEndDate = applylist[idx].day;
        }


        if(applyScheduleArr.indexOf(applylist[idx].day) < 0) {
            setWeek(applylist[idx].day)
        }
    })

    $(".apply-amount div:nth-child(2)").text(applyCnt + "개");
    $(".apply-period div:nth-child(2)").text(applyStartDate + " ~ " + applyEndDate + " ( 총 " +  applyDateCnt + "일)");
    $(".pay-price-wrap div:nth-child(2)").text(comma(applyAmount) +"원");
    $("#pay-amount").text(comma(applyAmount));
    $("#pay-amount").append("<span>원</span>");
    setScheduler();




    $(".confirm-wrap").on("click", function (){
        if($(".fa-circle-check").hasClass('on')) {
            $(".fa-circle-check").removeClass('on')
            $(".doPay").removeClass('on')
        } else {
            $(".fa-circle-check").addClass('on')
            $(".doPay").addClass('on')
        }
    })

    $("input[name='payment-method']").change(function(){
        $(".doPay").text($(this).next().text() + "로 결제하기");
    });

});


function setWeek(date) {

    let currentDay = new Date(date);

    let year  = currentDay.getFullYear() //선택된 년도
    let month = currentDay.getMonth() //선택된 월
    let day   = currentDay.getDate() //선택된 일자


    let theDayOfWeek = currentDay.getDay();

    for(let i=1; i<6; i++) {

        let resultDay = new Date(year, month, day + (i - theDayOfWeek));

        let yyyy = resultDay.getFullYear();
        let mm = leftPad(Number(resultDay.getMonth()) + 1);
        let dd = leftPad(resultDay.getDate());

        applyScheduleArr.push(yyyy + '-' + mm + '-' + dd)
    }
}

function setScheduler() {
    applyScheduleArr.sort();

    let _html = "";

    $.each(applyScheduleArr, function (idx){
        if(idx%5===0) {
            if(idx != 0) _html += "</div>\n";
            _html += '<div class="apply-card-line">';
        }

        _html +=
            "<div class=\"apply-card\">\n" +
                "<div class=\"apply-date\" data-date='"+ applyScheduleArr[idx] +"'>" +
                    applyScheduleArr[idx].slice(5,7) +"/"+ applyScheduleArr[idx].slice(8,10) + " (" + getDayOfWeek(applyScheduleArr[idx]) + ")" +
                "</div>\n" +
                "<div class=\"apply-cnt\"></div>\n" +
            "</div>\n";
    });

    _html += "</div>"

    $(".apply-schedule").append(_html)


    for(let i=0; i<applylist.length; i++) {
        $("[data-date='"+ applylist[i].day +"']").next().text(applylist[i].cnt + "개 / " + comma(applylist[i].cnt * applylist[i].price) + "원")
    }


}



function attachData() {

    $("#applyList").val(JSON.stringify(applylist));
    $("#payMethod").val($('input:radio[name=payment-method]:checked').val())
}