<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>atVenu Tax Rate API - Admin</title>
        <link rel="stylesheet" href="./assets/styles/admin.css" />
    </head>

    <body onload="">
        <div class="header">
            <h1>atVenu Tax Rate API</h1>
            <img src="./assets/images/LogoOnBlue.png" />
        </div>

        <div id="message"><b>${message}</b></div>
        <br />

        <input type="button" onclick="showSearchForm();" value="Search for tax rate" />
        <br />
        <input type="button" onclick="showAddForm()" value="Add new tax rate" />
        <br />
        <input type="button" onclick="showDeleteForm();" value="Delete a tax rate" />
        <br />

        <!-- search by location code -->
        <div class="hidden" id="searchForm">
            <form method="get" action="admin">
                <input type="text" name="searchField" value="" placeholder="Postal or Zip Code" />
                <input type="submit" onclick="showEditForm();" value="Search" />
            </form>
        </div>

        <!-- search table -->
        <div class="" id="searchTable">
            <table>
                <tr>
                    <th>Country</th>
                    <th>Province</th>
                    <th>Location Code</th>
                    <th>GST</th>
                    <th>PST</th>
                    <th>HST</th>
                </tr>

                <tr>
                    <td name="searchCountry">${taxRate.getLocation().getCountry()}</td>
                    <td name="searchRegion">${taxRate.getLocation().getRegion()}</td>
                    <td name="searchLocationCode">${taxRate.getLocation().getLocationCode()}</td>
                    <td name="searchTaxRate1">${taxRate.getGst()}</td>
                    <td name="searchTaxRate2">${taxRate.getPst()}</td>
                    <td name="searchTaxRate3">${taxRate.getHst()}</td>
                </tr>
                <input type="button" class="hidden" id="editButton" onclick="showEditForm();" value="Edit a tax rate" />
                <br />
            </table>

            <div class="hidden" id="editForm">
                <h2>Edit Existing Tax Rate</h2> 
                <form method="post" action="admin">
                    <input type="text" name="editCountry" value="${taxRate.getLocation().getCountry()}"><br>
                    <input type="text" name="editRegion"  value="${taxRate.getLocation().getRegion()}"><br>
                    <input type="text" name="editLocationCode" readonly value="${taxRate.getLocation().getLocationCode()}"><br>
                    <input type="text" name="editTaxRate1"  value="${taxRate.getGst()}"><br>
                    <input type="text" name="editTaxRate2"  value="${taxRate.getPst()}"><br>
                    <input type="text" name="editTaxRate3"  value="${taxRate.getHst()}"><br>
                    <input type="hidden" name="action" value="edit" />
                    <input type="submit" value="Save Changes" />
                </form>
            </div>
        </div>

        <div class="hidden" id="deleteForm">
            <!-- delete tax rate button -->
            <form method="post" action="admin">
                <input type="hidden" name="action" value="delete" />
                <input type="text" name="deleteField" placeholder="Postal or Zip Code" />
                <input type="submit" value="Delete Tax Rate" />
            </form>
        </div>

        <!-- form to add new tax rate to DB -->
        <div class="hidden" id="addForm">
            <form method="post" action="admin">
                <select name="addCountry">
                    <option value="CAN">Canada</option>
                    <option value="USA">USA</option>
                </select>
                <br />
                Region: <input type="text" name="addRegion" placeholder="Region" />
                Postal Code: <input type="text" name="addLocationCode" placeholder="LocationCode" />
                GST: <input type="text" name="addTaxRate1" placeholder="GST" />
                PST: <input type="text" name="addTaxRate2" placeholder="PST" />
                HST: <input type="text" name="addTaxRate3" placeholder="HST" />
                <br />

                <input type="hidden" name="action" value="add" />
                <input type="submit" value="Add Tax Rate To Database" />
            </form>
        </div>
        
        <!-- just for testing the JSON response of our servers -->
        <div>
            <form method="get" action="taxrate">
        
            <input type="submit" name="" value="TEST">
            <input type="hidden" name="rate" value="t3j"> 
        </form>
        </div>

        <script type="text/javascript" src="./assets/scripts/admin.js"></script>
    </body>
</html>
