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

    <input type="button" onclick="revealAdd();" value="Add new tax rate" />
    <br />
    <input type="button" onclick="revealSearch();" value="Search for tax rate" /><br />

    <div class="hidden" id="search">
      <!-- search by location code -->
      <form action="">
        <input type="text" value="" placeholder="Postal or Zip Code" />
        <input type="submit" value="Search" />
      </form>
    </div>

    <div class="hidden" id="add">
      <!-- form to add new tax rate to DB -->
      <form method="post" action="inventory">
        <select name="country">
          <option value="canada">Canada</option>
          <option value="usa">USA</option>
        </select>
        <br />
        <input type="text" name="region" placeholder="Region" />
        <br />
        <input type="text" name="locationcode" placeholder="LocationCode" />
        <br />
        <input type="text" name="taxRate1" placeholder="GST" />
        <br />
        <input type="text" name="taxRate2" placeholder="PST" />
        <br />
        <input type="text" name="taxRate3" placeholder="HST" />
        <br />

        <input type="hidden" name="locationCode" value="0" />
        <input type="hidden" name="taxRateID" value="0" />
        <input type="hidden" name="action" value="add" />
        <input type="submit" value="Add" />
      </form>
    </div>

    <div class="hidden" id="table">
      <!-- search table -->
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
          <td contenteditable id="country"></td>
          <td contenteditable id="region"></td>
          <td contenteditable id="locationCode"></td>
          <td contenteditable id="taxRate1"></td>
          <td contenteditable id="taxRate2"></td>
          <td contenteditable id="taxRate3"></td>
        </tr>
      </table>

      <!-- submit button for changes -->
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

    <script type="text/javascript" src="./assets/scripts/admin.js"></script>
  </body>
</html>
