import {get} from "./fetchFunctions.js";


const bookId = document.getElementById('bookId').value;

const formTitle = document.getElementById('title');
const tableAuthors = document.getElementById('authorsTable');
const tableGenres = document.getElementById('genresTable');


//get('http://localhost:8080/api/book', {'id': '63deae757901a39c59a726b6'})
get('/api/book', {'id': bookId})
    .then(book => {
        formTitle.value = book.title;

        tableAuthors.innerHTML = authorsSelectFormatter(book.authors);

        tableGenres.innerHTML = genresSelectFormatter(book.genres);

    })


function authorsSelectFormatter(authorsArray) {
    return `${authorsArray.map((elem) =>
        `<tr><td>${elem.name} ${elem.surname}</td></tr>`
    ).join('')}`;
}

function genresSelectFormatter(genresArray) {
    return `${genresArray.map((elem) =>
        `<tr><td>${elem.name}</td></tr>`
    ).join('')}`;
}