<%@ page import="org.example.demo6.repository.CourseRepo" %>
<%@ page import="org.example.demo6.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Courses</title>
</head>
<body>
<%
    List<Course> courses = CourseRepo.getAllCourses();
%>
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
                    <td><%=course.getId()%></td>
                    <td><%=course.getName()%></td>
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
    </div>
</div>

</body>
</html>
