<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <title>Task3</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="/static/script.js"></script>


    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>

<form action="panel" method="post" id="options">
    <nav class="navbar navbar-expand-lg navbar-light bg-primary">

        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-primary" name="activateUser" type="submit">Activate user</button>
                </li>
                <li class="nav-item">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-primary" name="deleteUser" type="submit">Delete user</button>

                </li>
                <li class="nav-item active">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-primary" value="blockUser" name="blockUser" type="submit">Block user
                    </button>

                </li>
                <li class="nav-item">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>


                </li>
            </ul>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary " name="logout"
                    type="submit">Sign out
            </button>

        </div>
    </nav>

    <table class="table table-striped " >
        <thead>
        <tr>
            <th scope="col"><input type="checkbox" id="chek"></th>
            <th scope="col">NickName</th>
            <th scope="col">Email</th>
            <th scope="col">Active</th>

        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <th scope="row"><input type="checkbox" value="${user.getId()}" name="box"></th>
                <td>${user.getUsername()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.isActive()?c}</td>

            </tr>
        </#list>
        </tbody>
    </table>
</form>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
        crossorigin="anonymous"></script>
</body>
</html>
