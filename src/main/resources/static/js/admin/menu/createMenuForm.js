window.onload = function() {
    buildCalendar();
}

let nowMonth = new Date();
let today = new Date();
today.setHours(0,0,0,0);

//달력생성
function buildCalendar() {
    console.log('Create Calendar');

    let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1);
    let lastDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, 0);

    let tbody_Calendar = document.querySelector(".Calendar > tbody");
    document.getElementById("calYear").innerText = "" + nowMonth.getFullYear();             // 연도 숫자 갱신
    document.getElementById("calMonth").innerText = "" + (nowMonth.getMonth() + 1);  // 월 숫자 갱신

    while (tbody_Calendar.rows.length > 0) { //초기화
        tbody_Calendar.deleteRow(tbody_Calendar.rows.length - 1);
    }

    let nowRow = tbody_Calendar.insertRow();        // 첫번째 행 추가

    for (let j = 0; j < firstDate.getDay(); j++) {  // 이번달 1일의 요일만큼
        let nowColumn = nowRow.insertCell();        // 열 추가
    }

    for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {   // day는 날짜를 저장하는 변수, 이번달 마지막날까지 증가시키며 반복

        let nowColumn = nowRow.insertCell();        // 새 열을 추가하고


        let newDIV = document.createElement("p");
        newDIV.innerHTML = "" +  nowDay.getDate();        // 추가한 열에 날짜 입력
        nowColumn.appendChild(newDIV);

        if (nowDay.getDay() == 6) {                 // 토요일인 경우
            nowRow = tbody_Calendar.insertRow();    // 새로운 행 추가
        }

        if (nowDay < today) {                       // 지난날인 경우
            newDIV.className = "pastDay";
        } else if (nowDay.getFullYear() == today.getFullYear() && nowDay.getMonth() == today.getMonth() && nowDay.getDate() == today.getDate()) { // 오늘인 경우
            newDIV.className = "today";
        } else {                                      // 미래인 경우
            newDIV.className = "futureDay";
        }

        newDIV.onclick = function () { choiceDate(this, nowMonth.getMonth() + 1); }
    }
}

function choiceDate(newDiv, month) {
    //초기화
    $("#menuOfDay").children().remove();



    if (document.getElementsByClassName("choiceDay")[0]) {                              // 기존에 선택한 날짜가 있으면
        document.getElementsByClassName("choiceDay")[0].classList.remove("choiceDay");  // 해당 날짜의 "choiceDay" class 제거
    }


    document.getElementById("choiceDate").innerText = month + "월 " + newDiv.innerHTML +"일";

    $("#choiceYear").val(nowMonth.getFullYear());
    $("#choiceMonth").val(month);
    $("#choiceDay").val(newDiv.innerHTML);
    newDiv.classList.add("choiceDay");

    let dateFormat = nowMonth.getFullYear() + "-" + leftPad(month) + "-" + leftPad(newDiv.innerHTML);

    if(menuList.length > 0) {
        var result = menuList.filter(menu => menu.date == dateFormat);

        for(var i=0; i<result.length; i++) {
            $("#menuOfDay").append("<div>" + result[i].foodName + "</div>");
        }

        $("#menuOfDay").append("<div> 가격: " + result[0].price + "원</div>");
        $("#menuOfDay").append("<div> 수량: " + result[0].stockQuantity + "</div>");


    }
}

function moveMonth(order) {

    let month;
    order == "prev" ? month = -1 :  month = 1;

    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + month, nowMonth.getDate())
    buildCalendar();
}

function selectMenu(type, id, tr) {

    let _name = $(tr).find('td').eq(1).html();

    if (type == "m") {
        $("#choiceMainFood").val(id)
    } else if (type == "d") {
        $("#choiceDessert").val(id)
    }
    if(name != null) {
        $(".choiceFoodList").append("<span>"+ _name +"</span>")
    }
}

function leftPad(num) {
    if (num < 10) {
        num  = "0" + num
    }
    return num;
}

