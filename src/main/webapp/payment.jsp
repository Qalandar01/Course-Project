
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <title>Add payment</title>
</head>
<body>
<%
    String studentId = request.getParameter("studentId");
%>

<div class="card">
    <div class="card-header">
        <h4>Add payment</h4>
    </div>
    <div class="card-body">
        <form action="/payment/save" method="post">
            <label>
                <input type="hidden" value="<%=studentId%>" name="studentId">
                <input type="number" name="amount" placeholder="Enter amount">
            </label>
            <button class="btn btn-success">Save</button>
        </form>

    </div>
</div>

</body>
</html>
