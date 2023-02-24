export {testDisplay};

console.log('working');

const surnameField = document.getElementById('surname');
const nameField = document.getElementById('name');
const initialsField = document.getElementById('initials');
const inf = document.getElementById('info');

document.getElementById('saveButton').addEventListener("click", testDisplay, false);

function testDisplay(){
  let author = `
  <table>
    <tr>
        <td>Name: </td>
        <td>${nameField.value}</td>
    </tr>
    <tr>
        <td>Surname: </td>
        <td>${surnameField.value}</td>
    </tr>
    <tr>
        <td>Initials: </td>
        <td>${initialsField.value}</td>
    </tr>
</table>
  `;
  inf.innerHTML = author;
}
