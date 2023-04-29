function leftPad(num) {
    if (num < 10) {
        num  = "0" + num
    }
    return num;
}

function comma(num) {
    return  String(num).replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function getDayOfWeek(date){

    const week = ['일', '월', '화', '수', '목', '금', '토'];

    return week[new Date(date).getDay()];

}

function autoHyphenPhone(str){
    return str.replace(/[^0-9]/g, '')
        .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
}

