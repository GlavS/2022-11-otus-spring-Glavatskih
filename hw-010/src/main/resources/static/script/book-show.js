import {get, del} from './fetchFunctions.js';
import {authorsListFormatter, genresListFormatter} from './displayFunctions.js';

const bookId = document.getElementById('bookId').value;
const pageTitle = document.getElementById('pageTitle');
const id = document.getElementById('id');
const authors = document.getElementById('authors');
const title = document.getElementById('title');
const genres = document.getElementById('genres');
const comments = document.getElementById('comments');

document.getElementById('delete-book').addEventListener('click', deleteBook, false);


function commentsListFormatter(commentsArray) {
        return `${commentsArray.map((elem) => 
`<li><span>[${elem.authorNick}] on ${new Date(elem.date).toLocaleDateString("ru-RU")}: ${elem.text}</span>
<br>
<span> <a href="/comment-edit?commentId=${elem.id}&bookId=${bookId}" >EDIT COMMENT</a> </span> |
<span> <a href="#" >DELETE COMMENT</a></span></li>`
        ).join('')}`;
}

get('/api/books', {id: bookId})
    .then(book=>{
        pageTitle.innerHTML = book.title;
        title.innerHTML = book.title;
        id.innerHTML = book.id;
        authors.innerHTML = authorsListFormatter(book.authors);
        genres.innerHTML = genresListFormatter(book.genres);
        comments.innerHTML = commentsListFormatter(book.comments);
    })

function deleteBook(event){
        event.preventDefault();
        event.returnValue = false;
        del('/api/books', {id: bookId});
        document.body.setAttribute('style', 'color:red;text-decoration: line-through');
        alert('Book deleted');
}
