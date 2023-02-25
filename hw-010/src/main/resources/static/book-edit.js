import {get} from "./fetchFunctions.js";


const bookId = document.getElementById('bookId').value;

const formTitle = document.getElementById('title');
const tableAuthors = document.getElementById('authorsTable');
const tableGenres = document.getElementById('genresTable');

const listAuthors = document.getElementById('authors');
const listGenres = document.getElementById('genres');


//get('http://localhost:8080/api/book', {'id': '63deae757901a39c59a726b6'})
get('/api/books', {'id': bookId})
    .then(book => {
        formTitle.value = book.title;
        tableAuthors.innerHTML = authorsTableFormatter(book.authors);
        tableGenres.innerHTML = genresTableFormatter(book.genres);
    })

get('/api/authors')
    .then(authors=>{
        listAuthors.innerHTML = authorsSelectFormatter(authors);
    })

get('/api/genres')
    .then(genres=>{
        listGenres.innerHTML = genresSelectFormatter(genres);
    })

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
        `<option>${elem.name} ${elem.surname}</option>`
    ).join('')}`;
}

function genresSelectFormatter(genresArray) {
    return `${genresArray.map((elem) =>
        `<option>${elem.name}</option>`
    ).join('')}`;
}
