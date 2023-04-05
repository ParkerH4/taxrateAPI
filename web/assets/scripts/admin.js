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

const addSelect = document.getElementById('addSelect');
const addForm = document.getElementById('addForm');

addSelect.addEventListener('change', (event) => {
    const formType = event.target.value;
    addForm.innerHTML = '';

    if (formType === 'CAN') {
        addForm.innerHTML = `
        <form method="post" action="admin">

       <div id="CAN">
                <label for="region">Province</label>
                <input type="text" name="addRegion" id=region placeholder="Region" />
                <label for="locationCode">Postal Code</label>
                <input type="text" name="addLocationCode" id=locationCode placeholder="LocationCode" />
                <label for="taxRate1">GST</label>
                <input type="text" name="addTaxRate1" id = taxRate1 placeholder="GST" />
                <label for="taxRate2">PST</label>
                <input type="text" name="addTaxRate2" id = taxRate2 placeholder="PST" />
                <label for="taxRate3">HST</label>
                <input type="text" name="addTaxRate3" id = taxRate3 placeholder="HST" />
                <br />
            </div>
            <input type="hidden" name="action" value="add" />
            <input type="submit" value="Add Tax Rate To Database" />
            <input type="hidden" name="addCountry" value="CAN" />
            <input type="hidden" name="action" value="cancel" />
            <input type="submit" value="Cancel" />
      </form>
    `;
    } else if (formType === 'USA') {
        addForm.innerHTML = `
              <form method="post" action="admin">

       <div id="USA">
                    <label for="region">State</label>
                    <input type="text" name="addRegion" id=region placeholder="Region" />
                    <label for="locationCode">Zip Code</label>
                    <input type="text" name="addLocationCode" id=locationCode placeholder="LocationCode" />
                    <label for="taxRate1">State Tax</label>
                    <input type="text" name="addTaxRate1" id = taxRate1 placeholder="State Tax" />
                    <br />
                    <input type="hidden" name="action" value="addUs" />
                    <input type="submit" value="Add Tax Rate To Database" />
                    <input type="hidden" name="addCountry" value="USA">
                    <input type="hidden" name="action" value="cancel" />
                    <input type="submit" value="Cancel" />
                </div>
      </form>
    `;
    }
});