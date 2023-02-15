function hideForm(formID) {
    document.getElementById(formID).classList.add("hidden");
}

function showForm(formID) {
    document.getElementById(formID).classList.remove("hidden");
}

function showSearchForm() {
    showForm("searchForm");
    showForm("searchTable");
    
    hideForm("addForm");
    hideForm("editForm");
    hideForm("deleteForm");
}

function showAddForm() {
    showForm("addForm");
    
    hideForm("searchForm");
    hideForm("searchTable");
    hideForm("editForm");
    hideForm("deleteForm");
}

function showEditForm() {
    showForm("editForm");
    
    hideForm("searchForm");
    hideForm("searchTable");
    hideForm("addForm");
    hideForm("deleteForm");
}

function showDeleteForm() {
    showForm("deleteForm");
    
    hideForm("searchForm");
    hideForm("searchTable");
    hideForm("addForm");
    hideForm("editForm");
}
