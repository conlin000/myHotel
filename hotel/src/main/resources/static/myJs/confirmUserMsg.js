// 预约开始时间校验
function checkBookOn(roomId){
    $.ajax({
        type:"POST",
        url:"/checkRoom/"+roomId+"/bookStart",
        data:{'date':$("#bookOn").val()},
        success:function (data) {
            // console.log(data);
            $("#bookOnDiv").html(data);
        }
    })
}

// 预约结束时间校验
function checkBookEnd(roomId){
    $.ajax({
        type:"POST",
        url:"/checkRoom/"+roomId+"/bookEnd",
        data:{'date':$("#bookEnd").val()},
        success:function (data) {
            // console.log(data);
            $("#bookEndDiv").html(data);
        }
    })
}

// 表单校验
function checkForm(){
    let flag = $("#bookOnDiv").text() + $("#bookEndDiv").text();
    return flag;
}
$(function () {
    $("#bookOn").click(function () {
       $("#bookOnDiv").html("");
    });
    $("#bookEnd").click(function () {
        $("#bookEndDiv").html("");
    });
});


// 用户信息确认
document.getElementById('zhezhao').style.display="none";
function confirm(){
    let flag = checkForm();
    if (flag != "") {
        alert("您输入的信息有误,请检查!");
    } else {
        document.getElementById('zhezhao').style.display="";
        let sex = $("#sex").val()==1? '男':'女';
        // sex = val;
        let userMsg = {
            userName:$("#userName").val(),
            phone:$("#phone").val(),
            idCard:$("#idCard").val(),
            sex:sex,
            room:$("#room").val(),
            bookOn:$("#bookOn").val(),
            bookEnd:$("#bookEnd").val(),
            // bookOn:$("#bookOn").val()+" "+$("#bookOn-time").val(),
            // bookEnd:$("#bookEnd").val()+" "+$("#bookEnd-time").val(),
            comment:$("#comment").val()
        };
        let html = template(document.getElementById('confirmMsgTempl').innerHTML,userMsg);
        document.getElementById('confirmMsg').innerHTML = html;
    }
}

// 关闭“用户信息确认框”弹窗
function hidder(){
    document.getElementById('zhezhao').style.display="none";
}
// (普通客户)确认预约,提交预约订单
function doBook(room) {
    // 表单信息
    let userMsg = {
        userName:$("#userName").val().trim(),
        phone:$("#phone").val(),
        idCard:$("#idCard").val(),
        sex:$("#sex").val(),
        room:$("#room").val(),
        isVip:0,
        bookTime:$("#bookOn").val(),
        endTime:$("#bookEnd").val(),
        // bookOn:$("#bookOn").val()+" "+$("#bookOn-time").val(),
        // bookEnd:$("#bookEnd").val()+" "+$("#bookEnd-time").val(),
        comment:$("#comment").val(),
        deposit:room.deposit,
        roomName:room.roomName,
        roomLevel:room.roomLevel,
        roomType:room.roomType,
        version:room.version
    };
    let userMsgJson = JSON.stringify(userMsg);
    let url = "/payDeposit/"+room.roomId;
    sessionStorage.setItem("userMsg", userMsgJson);
    window.location=url;
}


