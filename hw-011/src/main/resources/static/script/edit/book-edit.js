import {get, patch} from "../fetchFunctions.js";
import{authorsSelectFormatter,
    genresSelectFormatter,
    displaySavedBookInfo,
    authorsTableFormatter,
    genresTableFormatter} from '../displayFunctions.js';
import{getSelectedOptionsArray} from '../utilityFunctions.js';


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
        .then(bookSaved => displaySavedBookInfo(bookSaved))
}






