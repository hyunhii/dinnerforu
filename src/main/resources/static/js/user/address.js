$(document).ready(function (){





});

function deleteAddress(id){

    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "/mypage/address/delete/" + id );
    document.body.appendChild(form);
    form.submit();
}

function modifyAddress(id){
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "/mypage/address/modify");

    var input1 = document.createElement('input');
    input1.setAttribute("type", "hidden");
    input1.setAttribute("name", "data");
    input1.setAttribute("value", id);

    form.appendChild(input1);
    document.body.appendChild(form);
    form.submit();
}