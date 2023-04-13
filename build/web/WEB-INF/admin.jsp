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
            <img src="./assets/images/LogoOnBlue.png" />4
            <h1>atVenu Tax Rate API</h1>

        </div>

        <div id="message"><b>${message}</b></div>
        <br />

        <div id="formButtons">
            <input type="button" onclick="showSearchForm();" value="Search for tax rate" />
            <input type="button" onclick="showAddForm()" value="Add new tax rate" />
            <input type="button" onclick="showDeleteForm();" value="Delete a tax rate" />
        </div>

        <!-- search by location code -->
        <div class="hidden" id="searchForm">
            <form method="get" action="admin">
                <input type="text" name="searchField" value="" placeholder="Postal or Zip Code" />
                <input type="submit" onclick="showEditForm();" value="Search" />
                <br>
                <input type="hidden" name="action" value="cancel" />
                <input type="submit" value="Cancel" />
            </form>
        </div>


        <c:choose>
            <%-- search table --%>
            <%--CANADA--%>
            <c:when test="${searchedLoc eq 'canada'}">
                <div class="" id="searchTable">
                    <table>
                        <tr>
                            <th>Country</th>
                            <th>Province</th>
                            <th>Postal Code</th>
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
                        <input type="button" id="editButton" onclick="showEditForm();" value="Edit" />
                        <br />
                    </table>

                    <div class="hidden" id="editForm">
                        <h2>Edit Existing Tax Rate</h2> 
                        <form method="post" action="admin">
                            <label for="editCountry">Country</label>
                            <input type="text" id="editCountry" name="editCountry" value="${taxRate.getLocation().getCountry()}"><br>
                            <label for="editRegion">Province</label>
                            <input type="text" id="editRegion" name="editRegion"  value="${taxRate.getLocation().getRegion()}"><br>
                            <label for="editLocationCode">Postal Code</label>
                            <input type="text" id="editLocationCode" name="editLocationCode" readonly value="${taxRate.getLocation().getLocationCode()}"><br>
                            <label for="editTaxRate1">GST</label>
                            <input type="text" id="editTaxRate1" name="editTaxRate1"  value="${taxRate.getGst()}"><br>
                            <label for="editTaxRate2">PST</label>
                            <input type="text" id="editTaxRate2" name="editTaxRate2"  value="${taxRate.getPst()}"><br>
                            <label for="editTaxRate3">HST</label><br>
                            <input type="text" id="editTaxRate3" name="editTaxRate3"  value="${taxRate.getHst()}"><br>
                            <input type="hidden" name="action" value="edit" />
                            <input type="submit" value="Save Changes" />
                            <input type="hidden" name="action" value="cancel" />
                            <input type="submit" value="Cancel" />
                        </form>
                    </div>
                </div>


            </c:when>

            <%--USA--%>
            <c:when test="${searchedLoc eq 'usa'}">
                <div class="" id="searchTable">
                    <table>
                        <tr>
                            <th>Country</th>
                            <th>State</th>
                            <th>Zip Code</th>
                            <th>State Tax Rate</th>
                        </tr>

                        <tr>
                            <td name="searchCountry">${usTaxRate.getLocation().getCountry()}</td>
                            <td name="searchRegion">${usTaxRate.getLocation().getRegion()}</td>
                            <td name="searchLocationCode">${usTaxRate.getLocation().getLocationCode()}</td>
                            <td name="searchTaxRate1">${usTaxRate.getStateTax()}</td>
                        </tr>
                        <input type="button" id="editButton" onclick="showEditForm();" value="Edit" />
                        <br />
                    </table>

                    <div class="hidden" id="editForm">
                        <h2>Edit Existing Tax Rate</h2> 
                        <form method="post" action="admin">

                            <label for="editCountry">Country</label>
                            <input type="text" id="editCountry" name="editCountry" value="${usTaxRate.getLocation().getCountry()}"><br>
                            <label for="editRegion">State</label>
                            <input type="text" id="editRegion" name="editRegion"  value="${usTaxRate.getLocation().getRegion()}"><br>
                            <label for="editLocationCode">Zip Code</label>
                            <input type="text" id="editLocationCode" name="editLocationCode" readonly value="${usTaxRate.getLocation().getLocationCode()}"><br>
                            <label for="editTaxRate1">State Tax</label>
                            <input type="text" id="editTaxRate1" name="editTaxRate1"  value="${usTaxRate.getStateTax()}"><br>
                            <input type="hidden" name="action" value="editUs" />
                            <input type="submit" value="Save Changes" />
                            <input type="hidden" name="action" value="cancel" />
                            <input type="submit" value="Cancel" />
                        </form>
                    </div>
                </div>
            </div>
        </c:when> 
    </c:choose>

    <div class="hidden" id="deleteForm">
        <%-- delete tax rate button --%>
        <form method="post" action="admin">
            <input type="hidden" name="action" value="delete" />
            <input type="text" name="deleteField" placeholder="Postal or Zip Code" />
            <input type="submit" value="Delete Tax Rate" />
            <input type="hidden" name="action" value="cancel" />
            <input type="submit" value="Cancel" />
        </form>
    </div>

    <%-- form to add new tax rate to DB --%>
    <div class="hidden" id="addForm">
        <select id="addSelect" name="addCountry">
            <option value="">---</option>
            <option value="CAN">Canada</option>
            <option value="USA">USA</option>
        </select>
        <br />
        <!--Where JS will write the ADD FORM depending on what the user selects-->
        <div id="addForm"></div>
    </div>

    <script type="text/javascript" src="./assets/scripts/admin.js"></script>
</body>
</html>
