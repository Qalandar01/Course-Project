<%@ page import="org.example.demo6.repository.StudentRepo" %>
<%@ page import="org.example.demo6.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <title>Student</title>
</head>
<body>
<%
    Integer groupId = Integer.parseInt(request.getParameter("groupId"));
    List<Student> students = StudentRepo.getStudentsByGroupId(groupId);

%>
<hr>
<div class="row">
    <div class="col-6">
        <table class="table">
            <thead>
            <tr>
                <th>Student Id</th>
                <th>Student Name</th>
                <th>Total payments</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Student student : students) {
            %>
                <tr>
                    <td><%=student.getId()%></td>
                    <td><%=student.getFirstName()%></td>
                    <td>
                        <%=StudentRepo.getStudentTotalPayments(student)%>
                    </td>
                    <td>
                        <form action="/payment.jsp">
                            <input type="hidden" value="<%=student.getId()%>" name="studentId">
                            <button class="btn btn-success">
                                Add Payment
                            </button>
                        </form>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
