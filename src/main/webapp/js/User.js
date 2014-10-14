/**
 * @fileoverview User related functions
 * @author Adrian McLaughlin
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
        var inputParent=inputUserName.parent().parent('.form-group');
        if(data){
            
            if(!inputParent.hasClass('has-success')){
                inputParent.addClass('has-success');
            }
            if(inputParent.hasClass('has-error')){
                inputParent.removeClass('has-error');
            }
        } else {
            if(!inputParent.hasClass('has-error')){
                inputParent.addClass('has-error');
            }
            if(inputParent.hasClass('has-success')){
                inputParent.removeClass('has-success');
            }
        }
    });
}

$(document).ready(function(){
    $('#userName').keyup(isUsernameAvailable);
});