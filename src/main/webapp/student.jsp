<%@ page import="org.example.demo6.repository.StudentRepo" %>
<%@ page import="org.example.demo6.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.demo6.repository.GroupRepo" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <title>Student</title>
</head>
<body>
<form action="">
    <div class="form-group">
        <input type="text" placeholder="Search..." name="search">
        <button class="btn btn-primary">Search</button>
    </div>
</form>
<%
    String search = Objects.requireNonNullElse(request.getParameter("search"),"");
    Integer pages = Integer.parseInt(Objects.requireNonNullElse(request.getParameter("page"),"1"));


    Integer groupId = Integer.parseInt(request.getParameter("groupId"));
    List<Student> students = StudentRepo.getStudentsByGroupId(groupId,search,pages);
    Integer moduleId = GroupRepo.getModuleId(groupId);

%>
<form action="/groups.jsp">
    <input type="hidden" value="<%=moduleId%>" name="moduleId">
    <button class="btn btn-dark">
        Back
    </button>
</form>
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
        <%
            Long count = StudentRepo.count(groupId);
            int pageCount = (int) Math.ceil(count / 10.0);

            for (int i = 1; i <= pageCount; i++) {
        %>
        <a href="?page=<%=i%>&search=<%=search%>" class="btn btn-dark"><%=i%></a>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
