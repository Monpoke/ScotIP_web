/**
 * Created by svevia on 23/04/2016.
 */
$(document).ready(function () {
    $("#signIn").click(function (e) {
        login(e);
    });

    $("#loginForm").keypress(function (e) {
        if (e.keyCode == 13) {
            login(e);
        }
    });

    function login(e) {
        e.preventDefault();
        var mail = $('#mail').val();
        var pass = $('#pass').val();
        checkLogin(mail, pass);
    }
})


function checkLogin(mail, pass) {
    $.ajax({
        type: 'POST',
        url: "/login",
        dataType: "html",
        data: 'mail=' + mail + '&pass=' + pass,
        success: function (data) {
            if (data === "ok") {
                location.reload();
            } else {
                alert("Login error.");
            }
        },
        fail: function () {
            alert("Oooops. Something goes wrong.")
        }
    });
}