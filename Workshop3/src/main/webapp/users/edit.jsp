<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ include file="/header.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>
        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i> Dodaj użytkownika</a>
    </div>

    <!-- Basic Card Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Edycja użytkownika</h6>
        </div>
        <div class="card-body">
            <form action="/workshop/user/edit" method="post">
                <div class="form-group">
                    <input type="hidden" value="${user.id}" name="id"/>
                    Nazwa
                    <input value="${user.userName}" type="text" class="form-control form-control-user"
                           name="name" aria-describedby="nameHelp"/>
                </div>
                <div class="form-group">
                    Email
                    <input value="${user.email}" type="email" class="form-control form-control-user"
                           name="email" aria-describedby="emailHelp"/>
                </div>
                <div class="form-group">
                    Hasło
                    <input type="password" class="form-control form-control-user"
                           name="password" placeholder="Hasło użytkownika"/>
                </div>

                <div>
                    <button type="submit" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Zapisz</button>
                </div>

            </form>
        </div>
    </div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@ include file="/footer.jsp" %>
