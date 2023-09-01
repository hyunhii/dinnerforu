$(document).ready(function (){


    $("#password, #passwordCheck").on('input', function (){
        checkValidation_pw();
    });

    $("#phone").on('keydown', function () {
        $(this).val(autoHyphenPhone($(this).val().trim()));
    });


    $("#agreeAll").on('click', function () {
        if($(this).find('.fa-circle-check').hasClass('fa-solid')) {
            $(".checkbox-wrap").find('.fa-circle-check').removeClass('fa-solid')
        } else {
            $(".checkbox-wrap").find('.fa-circle-check').addClass('fa-solid')
        }
    })

    $(".agree").on('click', function (){
        checkValidation_agreement()
    });


});


function checkValidationForm () {

    if($("#idCheck").val()==="N") {
        alert("아이디를 입력해주세요");
        $("#userId").focus();
        return false;
    } else if(!checkValidation_pw()) {
        alert("비밀번호를 형식에 맞게 입력해주세요.")
        return false;
    } else if($("#name").val().trim() === "") {
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
    } else if(!checkValidation_agreement()) {
        alert("서비스 이용약관에 동의해주세요.")
        return false;
    } else {
    }


    return true;
}

function checkValidation_userId() {

    let userId = $("#userId").val().trim();

    if(userId === '') {
        alert("아이디를 입력해주세요.")
        return false;
    }

    $.ajax({
        type : "post",
        url : "/join/checkId",
        data: {
            "userId": userId
        },
        success : function (data) {
            if(data) {
                $("#idCheck").val("Y");
                $(".check-success-userId").show();
                $(".check-fail-userId").hide('on');
                $("#userId").val(userId)
            } else {
                $("#idCheck").val("N");
                $(".check-success-userId").hide();
                $(".check-fail-userId").show();
            }
        }
    })

}

function checkValidation_pw () {

    let pw = $("#password").val();
    let pw_check = $("#passwordCheck").val();

    let regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;

    let checkFalse = 0;

    if(regExp.test(pw)) {
        $(".check-fail-pw").hide();
    } else {
        checkFalse++;
        $(".check-fail-pw").show()
    }

    if(pw === pw_check) {
        $(".check-fail-pwCheck").hide();
    } else {
        checkFalse++;
        $(".check-fail-pwCheck").show();
    }

    return checkFalse === 0;

}


function checkValidation_agreement() {
    let cnt = 0;

    if($(this).find('.fa-circle-check').hasClass('fa-solid')) {
        $(this).find('.fa-circle-check').removeClass('fa-solid')
    } else {
        $(this).find('.fa-circle-check').addClass('fa-solid')
    }

    for(let i=0; i< $(".agree").length; i++) {
        if($(".agree").eq(i).find('.fa-circle-check').hasClass('fa-solid')) {
            cnt ++;
        }
    }

    if(cnt === 2) {
        $("#agreeAll").find('.fa-circle-check').addClass('fa-solid')
        return true;
    } else {
        $("#agreeAll").find('.fa-circle-check').removeClass('fa-solid')
        return false;
    }
}