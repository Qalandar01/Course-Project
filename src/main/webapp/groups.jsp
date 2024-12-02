<%@ page import="org.example.demo6.repository.GroupRepo" %>
<%@ page import="org.example.demo6.entity.Groups" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Groups</title>
  </head>

  <body>

  <%
    Integer moduleId = Integer.parseInt(request.getParameter("moduleId"));
    List<Groups> groups = GroupRepo.getGroupsByModuleId(moduleId);
  %>

  <hr>
  <div class="row">
    <div class="col-6">
      <table class="table">
        <thead>
        <tr>
          <th>Group Id</th>
          <th>Group Name</th>
          <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
          for (Groups group : groups) {

        %>
        <tr>
          <td><%=group.getId()%></td>
          <td><%=group.getName()%></td>
          <td>
            <form action="/student.jsp" method="post">
              <input type="hidden" value="<%=group.getId()%>" name="groupId">
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