/**
 * Created by svevia on 23/04/2016.
 */


function checkLogin(mail, pass) {
    $.ajax({
        type : 'POST',
        url : url,
        dataType : "html",
        data :'mail=' + mail + '&pass=' + pass
        });
}