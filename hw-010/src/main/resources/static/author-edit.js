export {saveAuthor};
import {post} from "./fetchFunctions.js";

console.log('working');

const surnameField = document.getElementById('surname');
const nameField = document.getElementById('name');
const initialsField = document.getElementById('initials');
const savedAuthorInfo = document.getElementById('info');


document.getElementById('saveButton').addEventListener("click", saveAuthor, false);

function saveAuthor() {
    let authorFromForm = {
        surname: surnameField.value,
        name: nameField.value,
        initials: initialsField.value
    };
    post("/api/authors", authorFromForm)
        .then(savedAuthor => {
            savedAuthorInfo.innerHTML = `
<p><strong>Saved author:</strong></p>
  <table>
  <tr>
        <td>Id: </td>
        <td>${savedAuthor.id}</td>
    </tr>
    <tr>
        <td>Name: </td>
        <td>${savedAuthor.name}</td>
    </tr>
    <tr>
        <td>Surname: </td>
        <td>${savedAuthor.surname}</td>
    </tr>
    <tr>
        <td>Initials: </td>
        <td>${savedAuthor.initials}</td>
    </tr>
</table>
  `;
        })
}
