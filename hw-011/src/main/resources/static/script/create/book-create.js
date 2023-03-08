import {get, post} from "../fetchFunctions.js";
import {authorsSelectFormatter, genresSelectFormatter, displaySavedBookInfo} from "../displayFunctions.js";
import{getSelectedOptionsArray} from '../utilityFunctions.js';

const titleField = document.getElementById('title');

const listAuthorsField = document.getElementById('authors');
const listGenresField = document.getElementById('genres');

document.getElementById('createBookButton').addEventListener('click', createBook, false);

get('/api/authors')
    .then(authors => {
        listAuthorsField.innerHTML = authorsSelectFormatter(authors);
    })

get('/api/genres')
    .then(genres => {
        listGenresField.innerHTML = genresSelectFormatter(genres);
    })

function createBook(){
    let params = {
        id: '',
        title: titleField.value,
        authorsIds: getSelectedOptionsArray(listAuthorsField),
        genresIds: getSelectedOptionsArray(listGenresField)
    }

    post('/api/books', params)
        .then(bookSaved=>displaySavedBookInfo(bookSaved));
}