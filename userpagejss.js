

function addExpense() {
    let addExpenseDetails = document.querySelector(".add-expense-details");
    let deleteExpenseDetails = document.querySelector(".delete-expense-details");
    if(addExpenseDetails.style.display == "block"){
        addExpenseDetails.style.display = "none";
    }else if(addExpenseDetails.style.display == "none"){
        addExpenseDetails.style.display = "block";
    }
    deleteExpenseDetails.style.display = "none";
}
function deleteExpense() {
    let addExpenseDetails = document.querySelector(".add-expense-details");
    let deleteExpenseDetails = document.querySelector(".delete-expense-details");
    if(deleteExpenseDetails.style.display == "block"){
        deleteExpenseDetails.style.display = "none";
    }else if(deleteExpenseDetails.style.display == "none"){
        deleteExpenseDetails.style.display = "block";
    }
    addExpenseDetails.style.display = "none";
}
/**
 * 
 */