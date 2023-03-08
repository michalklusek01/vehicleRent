function validate(){
    var login = document.getElementById("login-field");
    var password = document.getElementById("password-field");
    var flag = true;

    if(login.value.length < 3)
    flag = false;

    if(password.value.length < 3)
    flag = false;

    return flag;
}