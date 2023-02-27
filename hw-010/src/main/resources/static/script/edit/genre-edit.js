export {saveGenre};
import {post} from "../fetchFunctions.js";

console.log('working');

const nameField = document.getElementById('name');
const savedGenreInfo = document.getElementById('info');


document.getElementById('saveButton').addEventListener("click", saveGenre, false);

function saveGenre() {
    let genreFromForm = {
        name: nameField.value,
    };
    post("/api/genres", genreFromForm)
        .then(savedGenre => {
            savedGenreInfo.innerHTML = `
<p><strong>Saved genre:</strong></p>
  <table>
  <tr>
        <td>Id: </td>
        <td>${savedGenre.id}</td>
    </tr>
    <tr>
        <td>Name: </td>
        <td>${savedGenre.name}</td>
    </tr>
</table>
  `;
        })
}
