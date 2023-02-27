import {get, patch} from "../fetchFunctions.js";


const bookId = document.getElementById('bookId').value;

const titleField = document.getElementById('title');
const tableAuthors = document.getElementById('authorsTable');
const tableGenres = document.getElementById('genresTable');

const listAuthorsField = document.getElementById('authors');
const listGenresField = document.getElementById('genres');

document.getElementById('updateBookButton').addEventListener('click', updateBook, false);


get('/api/books', {'id': bookId})
    .then(book => {
        titleField.value = book.title;
        tableAuthors.innerHTML = authorsTableFormatter(book.authors);
        tableGenres.innerHTML = genresTableFormatter(book.genres);
    })

get('/api/authors')
    .then(authors => {
        listAuthorsField.innerHTML = authorsSelectFormatter(authors);
    })

get('/api/genres')
    .then(genres => {
        listGenresField.innerHTML = genresSelectFormatter(genres);
    })

function updateBook() {
    let params = {
        id: bookId,
        title: titleField.value,
        authorsIds: getSelectedOptionsArray(listAuthorsField),
        genresIds: getSelectedOptionsArray(listGenresField)
    }
    patch('/api/books', params)
        .then(bookUpdated => {
            document.getElementById('savedBookInfo').innerHTML =
                `
<fieldset>
<legend>Updated book</legend>
<p><strong>Book Id: </strong>${bookUpdated.id}</p>
<p><strong>Book title: </strong>${bookUpdated.title}</p>
<p><strong>Book authors: </strong></p>
<table>
${authorsTableFormatter(bookUpdated.authors)}
</table>
<p><strong>Book genres: </strong></p>
<table>
${genresTableFormatter(bookUpdated.genres)}
</table>
</fieldset>
                `;
        })


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

function getSelectedOptionsArray(optionList) {

    let result = [];
    let options = optionList.options;

    Array.prototype.map.call(options, (opt) => {
        if (opt.selected) {
            result.push(opt.value);
        }
    });
    return result;
}
