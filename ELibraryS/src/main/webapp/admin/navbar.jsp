<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<div class="container-fluid p-3" style="background-color: #edebeb">
	<div class="row">
		<div class="col-md-3">
			<h3><i class="fa-solid fa-book-open-reader"></i>E-Library</h3>
		</div>
		<div class="col-md-6">
			
		</div>
		<div class="col-md-2 mx-5">
		<c:if test="${not empty User}">
			<div class="d-flex align-items-center justify-content-end">
				<h4 class="mb-0 me-3">
					<i class="fa-solid fa-user"></i>&nbsp;${User.name}
				</h4>
				<a href="logout.jsp" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">Logout</a>
			</div>
		</c:if>

		<c:if test="${empty User}">
			<a href="login.jsp" class="btn btn-success">Login</a>
			<a href="register.jsp" class="btn btn-success">Register</a>
		</c:if>
		</div>
	</div>
</div>

<!--logout-->
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Do you want to Logout
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <a href="Logoutcontroller" class="btn btn-success">Logout</a>
      </div>
    </div>
  </div>
</div>

<nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
  <div class="container-fluid">
   <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
     <span class="navbar-toggler-icon"></span>
   </button>
   
   
   <div class="collapse navbar-collapse" id="navbarSupportedContent">
     <ul class="navbar-nav me-auto mb-2 mb-lg-0">
       <li class="nav-item">
         <a class="nav-link active" aria-current="page" href="index2.jsp"><i class="fa-solid fa-house"></i>Home</a>
       </li>
       <li class="nav-item">
         <a class="nav-link active" aria-current="page" href="addBookForm.jsp"><i class="fa-solid fa-book"></i>AddBook</a>
       </li>
       <li class="nav-item">
         <a class="nav-link active" aria-current="page" href="admin.jsp"><i class="fa-solid fa-bookmark"></i>Books</a>
       </li>
     </ul>
     
    </div>
  </div>
</nav>