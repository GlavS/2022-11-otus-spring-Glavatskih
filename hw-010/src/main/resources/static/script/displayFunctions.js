
function authorsListFormatter(authorsArray) {
    return `${authorsArray.map((elem) =>
        `<li>${elem.name} ${elem.surname}</li>`
    ).join('')}`;
}

function genresListFormatter(genresArray) {
    return `${genresArray.map((elem) =>
        `<li>${elem.name}</li>`
    ).join('')}`;
}

function authorsSelectFormatter(authorsArray) {
    return `${authorsArray.map((elem) =>
        `<option value="${elem.id}">${elem.name} ${elem.surname}</option>`
    ).join('')}`;
}

function genresSelectFormatter(genresArray) {
    return `${genresArray.map((elem) =>
        `<option value="${elem.id}">${elem.name}</option>`
    ).join('')}`;
}

function displaySavedBookInfo(bookSaved){
    document.getElementById('savedBookInfo').innerHTML =
        `
<fieldset>
<legend>Book is saved:</legend>
<p><strong>Book Id: </strong>${bookSaved.id}</p>
<p><strong>Book title: </strong>${bookSaved.title}</p>
<p><strong>Book authors: </strong></p>
<table>
${authorsTableFormatter(bookSaved.authors)}
</table>
<p><strong>Book genres: </strong></p>
<table>
${genresTableFormatter(bookSaved.genres)}
</table>
</fieldset>
                `;
}

function authorsTableFormatter(authorsArray) {
    return `${authorsArray.map((elem) =>
        `<tr><td>${elem.name} ${elem.surname}</td></tr>`
    ).join('')}`;
}

function genresTableFormatter(genresArray) {
    return `${genresArray.map((elem) =>
        `<tr><td>${elem.name}</td></tr>`
    ).join('')}`;
}

export {authorsListFormatter,
    genresListFormatter,
    authorsSelectFormatter,
    genresSelectFormatter,
    displaySavedBookInfo,
    authorsTableFormatter,
    genresTableFormatter};