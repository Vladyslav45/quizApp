(function () {
    let quesCounter = 0;
    let selectOptions = [], shuffleArray = [], answer = [];
    let quizSpace = $('#quiz');

    nextQuestion();

    $('#next').click(function () {
        chooseOption();
        if (selectOptions[quesCounter] === null) {
            alert('Please select an option !');
        } else {
            quesCounter++;
            nextQuestion();
        }
    });

    function createElement(index) {
        let element = $('<div>', {id: 'question'});
        let header = $('<h2>Question No. ' + (index + 1) + ' :</h2>');
        element.append(header);

        let question = $('<p>').append(arrayQuestions[index].nameQuestion);
        element.append(question);

        let radio = radioButtons(index);
        element.append(radio);

        return element;
    }

    function radioButtons(index) {
        let arrayQ = [arrayQuestions[index].answer.correctAnswer, arrayQuestions[index].answer.inCorrectAnswer1, arrayQuestions[index].answer.inCorrectAnswer2, arrayQuestions[index].answer.inCorrectAnswer3];
        shuffleArray =  _.shuffle(arrayQ);

        let radioItems = $('<ul>');
        let item;
        let input;
        for (let i = 0; i < shuffleArray.length; i++){
            item = $('<li>');
            input = '<input type="radio" name="answer" value=' + i + ' />';
            input += shuffleArray[i];
            item.append(input);
            radioItems.append(item);
        }
        return radioItems;
    }

    function chooseOption() {
        selectOptions[quesCounter] = +$('input[name="answer"]:checked').val();
        answer[quesCounter] = shuffleArray[selectOptions[quesCounter]]
    }

    function nextQuestion() {
        quizSpace.fadeOut(function () {
            $('#question').remove();
            if (quesCounter < arrayQuestions.length) {
                let nextQuestion = createElement(quesCounter);
                quizSpace.append(nextQuestion).fadeIn();
                if (!(isNaN(selectOptions[quesCounter]))) {
                    $('input[value=' + selectOptions[quesCounter] + ']').prop('checked', true);
                }
                if (quesCounter === 0) {
                    $('#next').show();
                }
            } else {
                let scoreRslt = displayResult();
                quizSpace.append(scoreRslt).fadeIn();
                $('#next').hide();
            }
        });
    }

    function displayResult() {
        let result = $('<div>', {id: "result"}),  nameQuestion, correctAnswer, answers, describeMistake, imageAccept, imageWrong;
        let correct = 0;
        for (let i = 0; i < selectOptions.length; i++) {
            nameQuestion = $('<h2>').append(arrayQuestions[i].nameQuestion);
            if (answer[i] === arrayQuestions[i].answer.correctAnswer) {
                //imageAccept = $('<img src="https://cdn1.iconfinder.com/data/icons/user-interface-flat-5/32/Accept_agree_check_tick_web_app_user_interface-64.png" alt="icon accept">');
                answers = $('<span>', {id: "correct"}).append(answer[i]).append('<br>');
                correct++;
            } else {
               // imageWrong = $('<img src="https://cdn1.iconfinder.com/data/icons/nuvola2/48x48/actions/button_cancel.png" alt="icon accept">');
                correctAnswer = $('<h5>').append(arrayQuestions[i].answer.correctAnswer);
                describeMistake = $('<h4>', {id: "des"}).append("Right answer: " + correctAnswer.text() + ", because " + arrayQuestions[i].answer.describeAnswerIfChoiceWrong);
                answers = $('<span>').css({"color": "red"}).append(answer[i]).append(describeMistake);
            }
            result.append(nameQuestion);
            result.append(answers).append('<br>');
        }
        result.append('You scored ' + correct + ' out of ' + arrayQuestions.length);
        save(correct);
        return result;
    }

    // function save(correct) {
    //     $.ajax({
    //         type: 'POST',
    //         url: '/saveHistory',
    //         data: {
    //             theme: nameTheme,
    //             result: correct + " / " + arrayQuestions.length
    //         }
    //     })
    // }
})();