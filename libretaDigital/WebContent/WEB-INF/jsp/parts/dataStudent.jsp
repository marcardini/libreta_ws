
	<div class="panel-body" ng-controller="studentCtrl">
		<div class="row">

			<div class="col-md-6">

				<form id="idform" role="form" novalidate="" name="studentForm" class="text-left">
					<div class="form-group ">
						<label for="Name">Nombre:</label>
						<div>
							<input type="text" name="Name" class="form-control" ng-change="clearMsg()"
								placeholder="Nombre" ng-model="student.name" ng-model-options="{ updateOn: 'blur' }"></input>
						</div>
					</div>
					<div class="form-group">
						<label for="Name">Apellido:</label>
						<div>
							<input type="text" name="Name" class="form-control" ng-change="clearMsg()"
								placeholder="Apellido" ng-model="student.lastName" ng-model-options="{ updateOn: 'blur' }"></input>
						</div>
					</div>

					<div class="form-group">
						<label for="Email">Correo Electrónico</label> <input type="email" class="form-control"  ng-change="clearMsg()"
							id="email" placeholder="Email" ng-model="student.email" ng-model-options="{ updateOn: 'blur' }">
					</div>

					
					<div class="form-group text-right">
						<button class="btn btn-success " name="Add" value="Submit" ng-click="addStudent()" ng-disabled="studentForm.$invalid && addButton"  ng-show="!editButton">Agregar</button>
						<button class="btn btn-md btn-warning" ng-click="addStudent()" ng-show="editButton">Modificar</button>
						<button type="reset" class="btn btn-primary" name="reset" value="Clear" ng-click="reset(studentForm)">Limpiar</button>
					</div>
				</form>


			</div>
			<!-- FIN FORM -->

			<div class="col-md-6">
				<table st-table="studentsDisplayed" st-safe-src="students" class="table table-striped table-hover">
					<thead >
						<tr>
							<th colspan="6"><input st-search="" class="form-control" placeholder="búsqueda rápida ..." type="text" /></th>
						</tr>
						<tr>
							<th></th>
							<th class="sort-header text-left" st-sort="name" class="text-left">Nombre</th>
							<th class="sort-header text-left" st-sort="lastName" class="text-left">Apellido</th>
							<th class="sort-header text-left" st-sort="email" class="text-left">Email</th>
							<th></th>
						</tr>

					</thead>
					<tbody>
						<tr ng-repeat="row in studentsDisplayed" st-select-row="row" st-select-mode="single"  class="list-row text-left"
							ng-click="rowSelect(row)">
							<td><img class="media-object media-user-list media-user-medium" src="resources/img/nophoto.png"
								alt="..."></td>
							<td>{{row.name | capitalize}}</td>
							<td>{{row.lastName | capitalize}}</td>
							<td>{{row.email | capitalize}}</td>
							<td>
								<button type="button" confirmed-click="deleteStudent(row.oid)" class="btn btn-danger" ng-confirm-click="Esta seguro que desea eliminar este estudiante?">
 									<i class="glyphicon glyphicon glyphicon-remove"></i>
								</button>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5" class="text-center">
								<div st-pagination="" st-items-by-page="10" st-displayed-pages="5"></div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- FIN TABLA -->

		</div>
		<!-- FIN ROW  -->
	</div>
	<!-- FIN PANEL-BODY  -->

