/**
 *  @fileoverview To control input focus in a HTML form
 *  @author Adrian Mclaughlin
 *  @version 1
 */

/**
 * Moves focus to the next input statement after the carriage return key is
 * pressed
 */
function addFocusListenerToTextInput() {
    // Get all Input Elements
    var inputs = document.getElementsByTagName('input');
    for (var i = 0; i < inputs.length; i++) {
        // add Listener to change focus
        inputs[i].addEventListener('keypress', function(event, element) {
            nextInput(event, this);
        });
        // console.log('adding listener to '+inputs[i]);
    }
}
/**
 * adds Listener to change page on elements with the class='end'
 *
 * @param {Array}
 *            tabs an array of HTML divs' ids
 */
function addChangePageListenerInput(tabs) {
    var elements = document.getElementsByClassName('end');
    console
            .log('addChangePageListenerInput called: Elements' +
                    'with the class "end":' + elements.length + ' found');
    for (var i = 0; i < elements.length; i++) {
        elements[i].addEventListener('keypress', function(event, element) {
            checkEnterNextPage(event, this, tabs);
        });
    }
}

/**
 * Changes focus to the next Input element
 *
 * @param {Event}
 *            event object
 * @param {Object}
 *            element HTML DOM object
 * @return {Boolean} false if the carriage return key is pressed
 */
function nextInput(event, element) {
    var code = (event.keyCode ? event.keyCode : event.which);
    if (code == 13) {
        console.log(element);
        var inputs = document.getElementsByTagName('input');
        var index = 0;
        for (var i = 0; i < inputs.length; i++) {
            if (inputs[i] == element) {
                index = i;
                break;
            }
        }
        index++;
        // If index is a valid index of the inputs array
        if (index < inputs.length) {
            inputs[index].focus();
        }
        return false;
    }
}

/**
 * function is executed when script is loaded
 */
$(document).ready(function() {
    // Stop the enter button from submitting the form
    $(document).keypress(function stopEnter(event) {
        var code = (event.keyCode ? event.keyCode : event.which);
        if (code == 13) {
            event.stopPropagation();
            return false;
        }

    });

    // Add listener to Input elements to control focus
    addFocusListenerToTextInput();
});
