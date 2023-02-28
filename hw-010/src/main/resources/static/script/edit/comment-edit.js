import {get, patch} from '../fetchFunctions.js';

const commentId = document.getElementById('commentId').value;
const commentedBookId = document.getElementById('bookId').value;

const commentTextField = document.getElementById('text');
const commentAuthorNickField = document.getElementById('authorNick');
const commentDateField = document.getElementById('date');
const saveCommentButton = document.getElementById('saveCommentButton');
const savedCommentInfo = document.getElementById('info');

saveCommentButton.addEventListener('click', updateComment, false);





get('/api/comments', {commentId: commentId})
    .then(comment=>{
        commentTextField.innerHTML = comment.text;
        commentAuthorNickField.value = comment.authorNick;
        commentDateField.value = comment.date.substring(0,10);
    })


function updateComment(){
    let params = {
        id: commentId,
        text: commentTextField.value,
        authorNick: commentAuthorNickField.value,
        date: commentDateField.value,
        commentedBookId: commentedBookId
    }
    patch('/api/comments', params)
        .then(comment=>{
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
        })

}