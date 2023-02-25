import {post} from "./fetchFunctions.js";

let savedAuthor = post('http://localhost:8080/api/authors',{
    name: 'NemName',
    surname: 'NewSurname',
    initials: 'New.'
}).then(result=>{
    return result;
});
console.log(savedAuthor);