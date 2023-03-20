import {get, patch} from "../fetchFunctions.js";
import {
    authorsSelectFormatter,
    authorsTableFormatter,
    displaySavedBookInfo,
    genresSelectFormatter,
    genresTableFormatter
} from '../displayFunctions.js';
import {getSelectedOptionsArray, getUrlParameterValue} from '../utilityFunctions.js';


const bookId = getUrlParameterValue('id');

const titleField = document.getElementById('title');
const tableAuthors = document.getElementById('authorsTable');
const tableGenres = document.getElementById('genresTable');

const listAuthorsField = document.getElementById('authors');
const listGenresField = document.getElementById('genres');

document.getElementById('updateBookButton').addEventListener('click', updateBook, false);


get('/api/books', {'id': bookId})
    .then(book => {
        titleField.value = book[0].title;
        tableAuthors.innerHTML = authorsTableFormatter(book[0].authors);
        tableGenres.innerHTML = genresTableFormatter(book[0].genres);
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
        authors: [],
        genres: []
    }
    let authorIdsArray = getSelectedOptionsArray(listAuthorsField);
    let genreIdsArray = getSelectedOptionsArray(listGenresField);

    let authorPromiseArray = [];
    let genrePromiseArray = [];
    for (let i = 0; i < authorIdsArray.length; i++) {
        authorPromiseArray.push(get('/api/authors/' + authorIdsArray[i]));
    }
    for (let i = 0; i < genreIdsArray.length; i++) {
        genrePromiseArray.push(get('/api/genres/' + genreIdsArray[i]));
    }

    Promise.all(authorPromiseArray).then(
        authors => {
            params.authors = authors;
            Promise.all(genrePromiseArray).then(
                genres => {
                    params.genres = genres;
                    patch('/api/books', params)
                        .then(bookSaved => displaySavedBookInfo(bookSaved));
                }
            )
        }
    )
}







