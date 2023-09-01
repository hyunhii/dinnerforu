$(document).ready(function (){

    $("#phone").val(autoHyphenPhone($("#phone").val()))

    $("#phone").on('keydown', function () {
        $(this).val(autoHyphenPhone($(this).val().trim()));
    });

    $("#pw, #new-pw, #new-pw-check").on('input', function (){
        checkValidation_pw();
    });

});

function openModal() {
    $(".modal").show()
}
function closeModal() {
    $(".modal").hide()
}


function openBox() {
    $(".open-btn").hide();
    $(".show-and-hide").show();
}

function closeBox() {
    $(".open-btn").show();
    $(".show-and-hide").hide();
}

function validationCheck() {
    if($("#name").val().trim() === "") {
        alert("이름을 작성해주세요.")
        return false
    } else if($("#phone").val().trim() === "") {
        alert("핸드폰 번호를 입력해주세요.")
        return false;
    } else if ($("#phone").val().trim().length < 13) {
        alert("핸드폰 번호를 정확히 입력해주세요")
        return false;
    } else if($("#email").val().trim() === "") {
        alert("이메일을 입력해주세요")
        return false;
    }

    return true;
}

function checkValidation_pw () {

    let pw = $("#pw").val();
    let new_pw = $("#new-pw").val();
    let pw_check = $("#new-pw-check").val();

    let regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;

    let checkFalse = 0;

    if(regExp.test(pw)) {
        $(".check-fail-pw").hide();
    } else {
        checkFalse++;
        $(".check-fail-pw").show()
    }

    if(new_pw === '') return;

    if(regExp.test(new_pw)) {
        $(".check-fail-new-pw").hide();
    } else {
        checkFalse++;
        $(".check-fail-new-pw").show()
    }

    if(new_pw === pw_check) {
        $(".check-fail-pwCheck").hide();
    } else {
        checkFalse++;
        $(".check-fail-pwCheck").show();
    }

    return checkFalse === 0;

}

function changePW(){
    if(!checkValidation_pw()) {
        return;
    }

    if($("#pw").val() === $("#new-pw").val()) {
        alert("현재 비밀번호와 신규비밀번호가 같습니다.")
        return;
    }

    $.ajax({
        type: "post",
        url :"/mypage/member/changePW",
        data : {
            "pw" : $("#pw").val(),
            "newPW" : $("#new-pw").val()
        },
        success : function (data) {
            if(data) {
                closeBox();
                alert("비밀번호가 변경되었습니다.");

                $("#pw, #new-pw, #new-pw-check").val('');

            } else {
                alert("현재 비밀번호가 일치하지 않습니다.");
            }
        }
    })
}

function withdrawal() {

}