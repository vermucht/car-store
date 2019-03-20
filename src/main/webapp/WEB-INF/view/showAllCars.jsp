<%--@elvariable id="roles" type="java.util.List<Role>"--%>
<%--@elvariable id="loggedUser" type="ru.job4j.crud.model.User"--%>
<%--@elvariable id="users" type="java.util.List<ru.job4j.crud.model.User>"--%>
<%--@elvariable id="error" type="java.lang.String"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="context" scope="request" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <!-- Bootstrap CSS -->
    <link crossorigin="anonymous" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" rel="stylesheet">
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script crossorigin="anonymous"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script crossorigin="anonymous" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script crossorigin="anonymous" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <!-- Jquery-UI -->
    <link href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <!-- Custom components -->
    <link href="components/Item.css" rel="stylesheet" type="text/css">
    <script src="components/Item.js" type="text/javascript"></script>
    <script src="components/get-url-parameter.js" type="text/javascript"></script>

    <script>
        function showItems() {
            $.ajax({
                type: 'GET',
                async: false,
                datatype: "application/json",
                url: '${context}' + '/' + 'getAllCarItems',
                success: function (response) {
                    if (response.error != null) {
                        alert(response.error);
                    } else {
                        let items = JSON.parse(response);
                        drawItems(items);
                    }
                }
            });
        }

        /**
         *
         * @param items
         */
        function drawItems(items) {
            let res = '';
            items.forEach(function (item) {
                Object.setPrototypeOf(item, Item.prototype);
                res += '<div class="item-div col col-12 col-md-6 col-lg-4" style="background-color: bisque;">'
                    + item.toHtml()
                    + '</div>';
            });
            $("#cars-table").html(res);
        }
    </script>

    <script>
        $(function () {
            showResponseIdIfPresent();
            showItems();
        });
    </script>

    <script>
        function showResponseIdIfPresent() {
            let returnedId = getUrlParameter("id");
            if (returnedId !== undefined) {
                $("#alert-message").text('Car saved with id: ' + returnedId);
            }
        }
    </script>

    <script>
        $(function () {
            $("#button_add_item").click(function () {
                location.href = "addCar";
            });
        })
    </script>

    <title>Item store</title>
</head>
<body>

<div align="center" class="container-fluid">
    <h3>Item store</h3>
</div>

<div class="container">
    <p id="alert-message" style="color: green"></p>

    <div class="row">
        <button class="btn btn-primary" id="button_add_item">Add car</button>
    </div>

    <div class="row row-centered" id="cars-table">
        Loading items...
    </div>
</div>

</body>
</html>