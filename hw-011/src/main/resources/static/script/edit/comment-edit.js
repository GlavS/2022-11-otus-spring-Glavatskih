import {get, patch, post} from '../fetchFunctions.js';
import {getUrlParameterValue} from '../utilityFunctions.js';

const commentId = getUrlParameterValue('commentId');
const commentedBookId = getUrlParameterValue('id');

const commentTextField = document.getElementById('text');
const commentAuthorNickField = document.getElementById('authorNick');
const commentDateField = document.getElementById('date');
const saveCommentButton = document.getElementById('saveCommentButton');
const savedCommentInfo = document.getElementById('info');

saveCommentButton.addEventListener('click', saveComment, false);
addEventListener('load', loadComment, false);


function loadComment() {
    if (commentId === '') {
        commentTextField.innerHTML = 'Enter your comment';
        commentAuthorNickField.value = 'Enter your nick';
        commentDateField.value = new Date().toISOString().substring(0, 10);
    } else {
        get('/api/comments', {commentId: commentId})
            .then(comment => {
                commentTextField.innerHTML = comment.text;
                commentAuthorNickField.value = comment.authorNick;
                commentDateField.value = comment.date.substring(0, 10);
            })
    }
}

function saveComment() {
    if (commentId === '') {
        createComment();
    } else {
        updateComment();
    }
}


function updateComment() {
    get('/api/books', {'id': commentedBookId})
        .then(book => {
            let params = {
                id: commentId,
                text: commentTextField.value,
                authorNick: commentAuthorNickField.value,
                date: commentDateField.value,
                commentedBook: book[0]
            }
            patch('/api/comments', params)
                .then(comment => commentInfoFormatter(comment)
                )
        })
}

function createComment() {
    get('/api/books', {'id': commentedBookId})
        .then(book => {
            let params = {
                id: undefined,
                text: commentTextField.value,
                authorNick: commentAuthorNickField.value,
                date: commentDateField.value,
                commentedBook: book[0]
            }
            post('/api/comments', params)
                .then(comment => commentInfoFormatter(comment)
                )
        })
}

function commentInfoFormatter(comment) {
    savedCommentInfo.innerHTML =
        `
<p><strong>Saved comment:</strong></p>
<table>
  <tr>
        <td>Id: </td>
        <td>${comment.id}</td>
    </tr>
    <tr>
        <td>Text: </td>
        <td>${comment.text}</td>
    </tr>
    <tr>
        <td>Author's nick: </td>
        <td>${comment.authorNick}</td>
    </tr>
    <tr>
        <td>Date: </td>
        <td>${comment.date}</td>
    </tr>
</table>
                `;
}


