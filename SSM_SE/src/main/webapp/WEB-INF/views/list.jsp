<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		
 -->
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>



<!-- 员工添加的模态框 -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" method="post">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">empName</label>
		    <div class="col-sm-10">
		      <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="empName">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">email</label>
		    <div class="col-sm-10"> 
		      <input type="text" name="email" class="form-control" id="email_add_input" placeholder="email" autocomplete='email'>
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">gender</label>
		    <div class="col-sm-10">
		      <label class="radio-inline">
				  <input type="radio" name="gender" id="gender1_add_input" value="M" checked="checked"> 男
				</label>
				<label class="radio-inline">
				  <input type="radio" name="gender" id="gender2_add_input" value="F"> 女
				</label>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-4">
		    	<!-- 部门提交部门id即可 -->
		      <select class="form-control" name="dId">
		      </select>
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- 员工修改的模态框 -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">员工修改</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">empName</label>
		    <div class="col-sm-10">
		      	<p class="form-control-static" id="empName_update_static"></p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">email</label>
		    <div class="col-sm-10">
		      <input type="text" name="email" class="form-control" id="email_update_input" placeholder="email@atguigu.com">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">gender</label>
		    <div class="col-sm-10">
		      <label class="radio-inline">
				  <input type="radio" name="gender" id="gender1_update_input" value="M" checked="checked"> 男
				</label>
				<label class="radio-inline">
				  <input type="radio" name="gender" id="gender2_update_input" value="F"> 女
				</label>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-4">
		    	<!-- 部门提交部门id即可 -->
		      <select class="form-control" name="dId">
		      </select>
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
      </div>
    </div>
  </div>
</div>

			

	<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="add_emp_btn"  >新增数据</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageInfo.list }" var="emp">
						<tr>
							<th>${emp.empId }</th>
							<th>${emp.empName }</th>
							<th>${emp.gender=="M"?"男":"女" }</th>
							<th>${emp.email }</th>
							<th>${emp.department.deptName }</th>
							<th>
								<button class="btn btn-primary btn-sm  edit_btn" id="edit_emp_btn" value="${emp.empId }" >
									<span class="glyphicon glyphicon-pencil "  aria-hidden="true"></span>
									编辑
								</button>
								<button class="btn btn-danger btn-sm delete_btn" id="delete_emp_btn" value="${emp.empId }">
									<span class="glyphicon glyphicon-trash"   aria-hidden="true"></span>
									删除
								</button>
							</th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<!-- 显示分页信息 -->
		<div class="row">
			<!--分页文字信息  -->
			<div class="col-md-6">当前 ${pageInfo.pageNum }页,总${pageInfo.pages }
				页,总 ${pageInfo.total } 条记录</div>
			<!-- 分页条信息 -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				<ul class="pagination">
					<li><a href="${APP_PATH }/emps?pn=1">首页</a></li>
					<c:if test="${pageInfo.hasPreviousPage }">
						<li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum-1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>


					<c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
						<c:if test="${page_Num == pageInfo.pageNum }">
							<li class="active"><a href="#">${page_Num }</a></li>
						</c:if>
						<c:if test="${page_Num != pageInfo.pageNum }">
							<li><a href="${APP_PATH }/emps?pn=${page_Num }">${page_Num }</a></li>
						</c:if>

					</c:forEach>
					<c:if test="${pageInfo.hasNextPage }">
						<li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1 }"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
					<li><a href="${APP_PATH }/emps?pn=${pageInfo.pages}">末页</a></li>
				</ul>
				</nav>
			</div>
		</div>
		
	</div>
	
	<script type="text/javascript">
	
	
	
	
	/*====================================== 弹出模态框并查询部门操作  ========================*/
			//在进入模态框前将下拉列表的数据查询出来
			//也就是在执行点击   新增按钮 事件时完成部门查询
						
					$("#add_emp_btn").click(function(){
						
						console.log("执行了");
						//发送ajax请求
						getDepts("#empAddModal select");
						//弹出模态框
						$("#empAddModal").modal({
							backdrop:"static"
						});
					});
		
					//查出所有信息并显示在下拉列表中
					function getDepts(ele){
						//清空之前下拉列表的值
						$(ele).empty();
						$.ajax({
							url:"${APP_PATH}/depts",
							type:"GET",
							success:function(result){
								$.each(result,function(){
									var optionEle = $("<option></option>").append(this.deptName).attr("value",this.deptId);
									optionEle.appendTo(ele);
								});
							}
						});
						
					}
		
		/*====================================== 添加数据操作  ========================*/
			//模态框保存按钮的事件
				$("#emp_save_btn").click(function() {
					$.ajax({
						type : "post",
						url : "${APP_PATH}/emp",
						data : $("#empAddModal form").serialize(),
						// async : false,
						success : function(result) {
							window.location.href="${APP_PATH}/emps?pn="+${pageInfo.pages};
						}
					});
					//关闭模态框 
					$("#empAddModal").modal('hide');
				});
			
			
/*====================================== 删除操作 ========================*/
			
			$(document).on("click",".delete_btn",function(){
				
				//1、弹出是否确认删除对话框
				if(confirm("确认删除吗？")){
				var id = $(this).attr("value");
				//alert(id);
					$.ajax({
						url:"${APP_PATH}/emp/"+id,
						type:"delete",
						 success:function(result){
							 window.location.href="${APP_PATH}/emps?pn="+${pageInfo.pages};
						} 
					});
				}
			});
			
			/*====================================== 修改操作  ========================*/	
			//根据id查数据 
			function getEmp(id){
			$.ajax({
				url:"${APP_PATH}/emp/"+id,
				type:"GET",
				success:function(result){
					console.log(result);
					$("#empName_update_static").text(result.empName);
					$("#email_update_input").val(result.email);
					$("#empUpdateModal input[name=gender]").val([result.gender]);
					$("#empUpdateModal select").val([result.dId]);
				}
			});
		}
			//给编辑按钮添加事件  并回显数据 
			var id;
			$(document).on("click",".edit_btn",function(){
				//修改前回显数据
				 id = $("#edit_emp_btn").attr("value");
				//alert(id);
				getEmp(id);
				getDepts("#empUpdateModal select");
				//弹出模态框
				$("#empUpdateModal").modal({
					backdrop:"static"
				});
			});
			
			//给模态框更新按钮添加事件 
			 $("#emp_update_btn").click(function() {
				// alert(id);
					$.ajax({
						type : "put",
						url : "${APP_PATH}/emp/"+id,
						data : $("#empUpdateModal form").serialize(),
						// async : false,
						success : function(result) {
							window.location.href="${APP_PATH}/emps?pn="+${pageInfo.pageNum};
						}
					});
					//关闭模态框 
					$("#empUpdateModal").modal('hide');
				});
			
			
			</script>
			
			
			
	
	
</body>
</html>