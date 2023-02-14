<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>atVenu Tax Rate API - Admin</title>
        <link rel="stylesheet" href="./assets/styles/admin.css" />
    </head>

    <body>
        <div class="header">
            <h1>atVenu Tax Rate API</h1>
            <img src="./assets/images/LogoOnBlue.png" />
        </div>

        <div id="message">${message}</div>
        <br />

        <input type="button" onclick="revealAdd();" value="Add new tax rate" />
        <br />
        <input type="button" onclick="revealSearch();" value="Search for tax rate" /><br />

        <!-- search by location code -->
        <div class="hidden" id="search">
            <form action="admin">
                <input type="text" name="searchField" value="" placeholder="Postal or Zip Code" />
                <input type="submit" value="Search" />
            </form>
        </div>

        <!-- form to add new tax rate to DB -->
        <div class="hidden" id="add">
            <form method="post" action="admin">
                <select name="country">
                    <option value="CAN">Canada</option>
                    <option value="USA">USA</option>
                </select>
                <br />
                <input type="text" name="region" placeholder="Region" />
                <input type="text" name="locationcode" placeholder="LocationCode" />
                <input type="text" name="taxRate1" placeholder="GST" />
                <input type="text" name="taxRate2" placeholder="PST" />
                <input type="text" name="taxRate3" placeholder="HST" />
                <br />

                <input type="hidden" name="action" value="add" />
                <input type="submit" value="Add Tax Rate To Database" />
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
                    <td contenteditable name="country">${taxRate.getLocationCode().getCountry()}</td>
                    <td contenteditable name="region">${taxRate.getLocationCode().getRegion()}</td>
                    <td contenteditable name="locationCode">${taxRate.getLocationCode().getLocationCode()}</td>
                    <td contenteditable name="taxRate1">${taxRate.getGst()}</td>
                    <td contenteditable name="taxRate2">${taxRate.getPst()}</td>
                    <td contenteditable name="taxRate3">${taxRate.getHst()}</td>
                </tr>
            </table>

            <!-- make changes and update button -->
            <form method="post" action="admin">
                <input type="hidden" name="action" value="update" />
                <input type="submit" value="Save Changes" />
            </form>

            <!-- cancel changes button -->
            <form method="post" action="admin">
                <input type="hidden" name="action" value="cancel" />
                <input type="submit" value="Cancel Edit" />
            </form>
        </div>

        <!-- delete tax rate button -->
        <form method="post" action="admin">
            <input type="hidden" name="action" value="delete" />
            <input type="submit" value="Delete Tax Rate" />
        </form>

        <script type="text/javascript" src="./assets/scripts/admin.js"></script>
    </body>
</html>