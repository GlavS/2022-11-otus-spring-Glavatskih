import {del, get} from './fetchFunctions.js';
import {authorsListFormatter, genresListFormatter} from './displayFunctions.js';
import {getUrlParameterValue} from './utilityFunctions.js';

const bookId = getUrlParameterValue('id');
const pageTitle = document.getElementById('pageTitle');
const id = document.getElementById('id');
const authors = document.getElementById('authors');
const title = document.getElementById('title');
const genres = document.getElementById('genres');
const comments = document.getElementById('comments');

document.getElementById('delete-book').addEventListener('click', deleteBook, false);
addEventListener('load', loadPage, false);


function commentsListFormatter(commentsArray) {
    return `${commentsArray.map((elem) =>
        `<li><span>[${elem.authorNick}] on ${new Date(elem.date).toLocaleDateString("ru-RU")}: ${elem.text}</span>
<br>
<span> <a href="/comment-edit?commentId=${elem.id}&bookId=${bookId}" >EDIT COMMENT</a> </span> |
<span> <a href="#" id="delete-comment" onclick="{
     fetch('/api/comments?id=${elem.id}', {method: 'DELETE'}) ; alert('Comment deleted, refresh page') 
}return false;">DELETE COMMENT</a></span></li>`
    ).join('')}`
}


function loadPage() {
    $('#edit-book-link').attr('href', 'edit/book-edit.html?id=' + bookId);
    get('/api/books', {id: bookId})
        .then(book => {
            pageTitle.innerHTML = book[0].title;
            title.innerHTML = book[0].title;
            id.innerHTML = book[0].id;
            authors.innerHTML = authorsListFormatter(book[0].authors);
            genres.innerHTML = genresListFormatter(book[0].genres);
            comments.innerHTML = commentsListFormatter(book[0].comments);
        })
}


function deleteBook(event) {
    event.preventDefault();
    event.returnValue = false;
    del('/api/books/' + bookId);
    document.body.setAttribute('style', 'color:red;text-decoration: line-through');
    alert('Book deleted');
}


