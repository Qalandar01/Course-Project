<%@ page import="org.example.demo6.repository.CourseRepo" %>
<%@ page import="org.example.demo6.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Courses</title>
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
    List<Course> courses = CourseRepo.getAllCourses(pages,search);
%>
<form action="/report.jsp">
    <button class="btn btn-warning">
        Report
    </button>
</form>
<hr>
<div class="row">
    <div class="col-6">
        <table class="table">
            <thead>
            <tr>
                <th>Course Id</th>
                <th>Course Name</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Course course : courses) {
            %>
            <tr>
                <td><%=course.getId()%>
                </td>
                <td><%=course.getName()%>
                </td>
                <td>
                    <form action="/module.jsp" method="post">
                        <input type="hidden" value="<%=course.getId()%>" name="courseId">
                        <button class="btn btn-primary">
                            Go
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
            Long count = CourseRepo.count();
            int pageCount = (int) Math.ceil(count / 10.0);

            for (int i = 1; i <=pageCount; i++) {
        %>
        <a href="?page=<%=i%>&search=<%=search%>" class="btn btn-dark"><%=i%></a>
        <%
            }
        %>
    </div>
</div>

</body>
</html>
