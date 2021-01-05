function save(correct) {
    $.ajax({
        type: 'POST',
        url: '/saveHistory',
        data: {
            theme: nameTheme,
            result: correct + " / " + arrayQuestions.length
        }
    })
}