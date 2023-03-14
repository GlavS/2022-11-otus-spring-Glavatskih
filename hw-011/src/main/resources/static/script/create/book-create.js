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
        id: undefined,
        title: titleField.value,
        authors:[],
        genres:[]
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
                    post('/api/books', params)
                        .then(bookSaved=>displaySavedBookInfo(bookSaved));
                }
            )
        }
    )
}