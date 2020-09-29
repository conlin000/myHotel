
// 用户信息确认
document.getElementById('zhezhao').style.display="none";
function confirm(){
    document.getElementById('zhezhao').style.display="";
    let sex = $("#sex").val()==1? '男':'女';
    // sex = val;
    let userMsg = {
        userName:$("#userName").val(),
        phone:$("#phone").val(),
        idCard:$("#idCard").val(),
        sex:sex,
        room:$("#room").val(),
        bookOn:$("#bookOn").val()+" "+$("#bookOn-time").val(),
        bookEnd:$("#bookEnd").val()+" "+$("#bookEnd-time").val(),
        comment:$("#comment").val()
    };
    let html = template(document.getElementById('confirmMsgTempl').innerHTML,userMsg);
    document.getElementById('confirmMsg').innerHTML = html;
}
// 关闭“用户信息确认框”弹窗
function hidder(){
    document.getElementById('zhezhao').style.display="none";
}
// 确认预约
function doBook(room) {
    // 表单信息
    let userMsg = {
        userName:$("#userName").val(),
        phone:$("#phone").val(),
        idCard:$("#idCard").val(),
        sex:$("#sex").val(),
        room:$("#room").val(),
        bookOn:$("#bookOn").val()+" "+$("#bookOn-time").val(),
        bookEnd:$("#bookEnd").val()+" "+$("#bookEnd-time").val(),
        comment:$("#comment").val(),
        deposit:room.deposit,
        roomName:room.roomName,
        roomLevel:room.roomLevel,
        roomType:room.roomType,
        version:room.version
    };
    var userMsgJson = JSON.stringify(userMsg);
    var url = "/payDeposit/"+room.roomId;
    sessionStorage.setItem("userMsg", userMsgJson);
    window.location=url;

}


