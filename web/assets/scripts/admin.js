function hideForm(formID) {
    document.getElementById(formID).classList.add("hidden");
}

function showForm(formID) {
    document.getElementById(formID).classList.remove("hidden");
}

function showSearchForm() {
    showForm("searchForm");
    showForm("editButton");
    
    hideForm("deleteForm");
    hideForm("addForm");
}

function showAddForm() {
    showForm("addForm");
    
    hideForm("searchForm");
    hideForm("editForm");
    hideForm("editButton");
    hideForm("deleteForm");
}

function showEditForm() {
    showForm("editForm");
    showForm("searchForm");

    hideForm("addForm");
    hideForm("deleteForm");
}

function showDeleteForm() {
    showForm("deleteForm");

    hideForm("searchForm");
    hideForm("addForm");
    hideForm("editForm");
    hideForm("editButton");
}
