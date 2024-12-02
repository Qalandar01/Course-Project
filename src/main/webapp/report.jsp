<%@ page import="org.example.demo6.repository.CourseRepo" %>
<%@ page import="org.example.demo6.entity.ReportData" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <title>Report</title>
</head>
<body>
<%
    List<ReportData> reportDatas = CourseRepo.getReportDatas();
%>

<div class="row">
    <div class="col-7">
        <table class="table">
            <thead>
            <tr>
                <th>Course Name</th>
                <th>Number of Students</th>
                <th>Total payment</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (ReportData reportData : reportDatas) {
            %>
                <tr>
                    <td><%=reportData.getCourseName()%></td>
                    <td><%=reportData.getStudentCount()%></td>
                    <td><%=reportData.getTotalAmount()%></td>
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
