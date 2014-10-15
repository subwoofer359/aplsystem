/**
 * @fileoverview User related functions
 * @author Adrian McLaughlin
 */

/**
 * Global variable
 */
var validUsername=false;

/**
 * The function uses a ajax request to the server to check if the username is valid.
 * if false the function changes the userName input element's style and sets text
 * in the '#userNameMsg' element
 * 
 * @param event
 */
function isUsernameAvailable(event){
    var inputUserName=$('#userName');
    var userName=inputUserName.val();
    var formData="username="+userName;
    
    $.post('./isUserNameAvailable',formData,function(data,status){
        if(data.trim()==='true'){
            data=Boolean(data);
        }else{
            data=false;
        }
        validUsername=data;
        var inputParent=inputUserName.parent().parent('.form-group');
        if(data){
            
            if(!inputParent.hasClass('has-success')){
                inputParent.addClass('has-success');
            }
            if(inputParent.hasClass('has-error')){
                inputParent.removeClass('has-error');
            }
            inputParent.find('#userNameMsg').text("Username is available");
        } else {
            if(!inputParent.hasClass('has-error')){
                inputParent.addClass('has-error');
            }
            if(inputParent.hasClass('has-success')){
                inputParent.removeClass('has-success');
            }
            inputParent.find('#userNameMsg').text("Username taken");
        }
    });
}

/**
 * Checks that the requirements of submission have been met
 * before submitting the form.
 * Requirements:
 * 1. The passwords supplied by the user are the same
 * 2. The username is a valid username.
 * 
 * @param event
 * @returns {Boolean}
 */
function canSubmit(event){
    if(!checkPassword('passwordOne','passwordTwo')){
        return false;
    }
    if(!validUsername){
        showPanelMessage("#alert","Username is not valid.",true);
        return false;
    }
    
    return true;
    
}

$(document).ready(function(){
    $('#userName').keyup(isUsernameAvailable);
    $('#userForm').submit(canSubmit);
});