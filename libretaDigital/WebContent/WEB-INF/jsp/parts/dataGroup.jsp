	<div class="panel-body" ng-controller="groupCtrl">
		<div class="row">

			<div class="col-md-6">

				<form id="idform" role="form" novalidate="" name="groupForm" class="text-left">
					<div class="form-group ">
						<label for="Name">Nombre:</label>
						<div>
							<input type="text" name="Name" class="form-control" ng-change="clearMsg()"
								placeholder="Nombre" ng-model="group.name" ng-model-options="{ updateOn: 'blur' }"></input>
						</div>
					</div>
					<div class="form-group">
						<label for="Name">Año:</label>
						<div>
							<input type="text" name="Name" class="form-control" ng-change="clearMsg()"
								placeholder="Año" ng-model="group.year" ng-model-options="{ updateOn: 'blur' }"></input>
						</div>
					</div>
					
					<div class="form-group text-right">
						<button class="btn btn-success " name="Add" value="Submit" ng-click="addGroup()" ng-disabled="groupForm.$invalid && addButton" ng-show="!editButton">Agregar</button>
						<button class="btn btn-md btn-warning" ng-click="addGroup()" ng-show="editButton">Modificar</button>
						<button type="reset" class="btn btn-primary" name="reset" value="Clear" ng-click="reset(groupForm)">Limpiar</button>
					</div>
				</form>


			</div>
			<!-- FIN FORM -->

			<div class="col-md-6">
				<table st-table="groupsDisplayed" st-safe-src="groups" class="table table-striped table-hover">
					<thead >
						<tr>
							<th colspan="6"><input st-search="" class="form-control" placeholder="búsqueda rápida ..." type="text" /></th>
						</tr>
						<tr>
<!-- 							<th></th> -->
							<th class="sort-header text-left" st-sort="name" class="text-left">Nombre</th>
							<th class="sort-header text-left" st-sort="year" class="text-left">Año</th>
							<th></th>
						</tr>

					</thead>
					<tbody>
						<tr ng-repeat="row in groupsDisplayed" st-select-row="row" st-select-mode="single"  class="list-row text-left"
							ng-click="rowSelect(row)">
							<td>{{row.name | capitalize}}</td>
							<td>{{row.year}}</td>
							<td>
								<button type="button" confirmed-click="deleteGroup(row.oid)" class="btn btn-danger" ng-confirm-click="Esta seguro que desea eliminar este grupo?">
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

