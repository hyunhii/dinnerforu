var element_wrap;

$(document).ready(function (){

    element_wrap = document.getElementById('wrap');

    if($("#phone").val() !== '') $("#phone").val(autoHyphenPhone($("#phone").val().trim()));
    checkInputFill();


    $("#phone").on('keydown', function () {
        $(this).val(autoHyphenPhone($(this).val().trim()));
    });

    $(".checkbox-wrap").on('click', function (){
       if($(".fa-circle-check").hasClass('fa-solid')) {
           $(".fa-circle-check").removeClass('fa-solid')
           $("#mainYN").val("N");
       } else {
           $(".fa-circle-check").addClass('fa-solid');
           $("#mainYN").val("Y");
       }
    });

    $("#address1, #searchAddress").on('click focus', function () {
        getPost();
        $(".add-address-wrap").hide();
        $(".search-post-wrap").show();
    });

    $(".fa-x").on('click', function () {
        $(".add-address-wrap").show();
        $(".search-post-wrap").hide();
    })

    $("input").on('input', function (){
       checkInputFill();
    })




})

function checkInputFill() {
    if ($("#addressName").val().trim() === '' || $("#receiver").val().trim() === '' ||
        $("#phone").val().trim() === '' || $("#phone").val().trim().length < 13 ||
        $("#address1").val().trim() === '' || $("#address2").val().trim() === '') {
        $(".add-address").removeClass('on')
    } else {
        $(".add-address").addClass('on')
    }
}




function getPost() {
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
        oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            addr = data.roadAddress;

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                // document.getElementById("sample3_extraAddress").value = extraAddr;

            } else {
                // document.getElementById("sample3_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            // document.getElementById('sample3_postcode').value = data.zonecode;
            document.getElementById("address1").value = addr;
            // 커서를 상세주소 필드로 이동한다.c
            document.getElementById("address2").focus();

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_wrap.style.display = 'none';

            // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
            document.body.scrollTop = currentScroll;

            $(".add-address-wrap").show();
            $(".search-post-wrap").hide();

            checkInputFill();
        },
        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
        onresize : function(size) {
            element_wrap.style.height = size.height+'px';
        },
        width : '100%',
        height : '1000px',
        outlineColor : "rgb(237, 237, 237)"
    }).embed(element_wrap);

    // iframe을 넣은 element를 보이게 한다.
    element_wrap.style.display = 'block';
}
