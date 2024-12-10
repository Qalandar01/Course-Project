<%@ page import="org.example.demo6.repository.GroupRepo" %>
<%@ page import="org.example.demo6.entity.Groups" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.demo6.repository.ModuleRepo" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Groups</title>
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

    Integer moduleId = Integer.parseInt(request.getParameter("moduleId"));
    List<Groups> groups = GroupRepo.getGroupsByModuleId(moduleId,search,pages);
    Integer courseId = ModuleRepo.getCourseId(moduleId);
  %>
  <form action="/module.jsp">
    <input type="hidden" value="<%=courseId%>" name="courseId">
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
      <%
        Long count = GroupRepo.count(moduleId);
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
