	
	<div class="panel-body" ng-controller="professorCtrl" ng-cloak>
		<div class="row">

			<div class="col-md-6">

				<a id="idform" role="form" novalidate="" name="professorForm" class="text-left">
					<div class="form-group ">
						<label for="Name">Nombre:</label>
						<div>
							<input type="text" name="Name" class="form-control" ng-change="clearMsg()"
								placeholder="Nombre" ng-model="professor.name" ng-model-options="{ updateOn: 'blur' }"></input>
						</div>
					</div>
					
					<div class="form-group">
						<label for="Name">Apellido:</label>
						<div>
							<input type="text" name="Name" class="form-control" ng-change="clearMsg()"
								placeholder="Apellido" ng-model="professor.lastName" ng-model-options="{ updateOn: 'blur' }"></input>
						</div>
					</div>

					<div class="form-group">
						<label for="Email">Correo Electrónico</label> <input type="email" class="form-control"  ng-change="clearMsg()"
							id="email" placeholder="Email" ng-model="professor.email" ng-model-options="{ updateOn: 'blur' }">
					</div>

					<div class="form-group">
						<label for="Photo">Imagen</label>
						
						
							
						<form action="api/upload" class="my-drop-zone drop-zone dropzone" dropzone="" id="dropzone">
							<div class="dz-default dz-message"></div>
							<div class="dz-progress">
								<span class="dz-upload" data-dz-uploadprogress></span>
							</div>
						</form>
						
						<div class="btn-dropzone">
							<button class="btn btn-success btn-sm" ng-click="uploadFile()">Subir Archivo</button>
							<button class="btn btn-danger btn-sm" ng-click="reset()">Limpiar</button>
						</div>
						
						
						
						
					</div>
					
					<div class="form-group text-right">
						<button class="btn btn-success " name="Add" value="Submit" ng-click="addProfessor()" ng-disabled="professorForm.$invalid && addButton"  ng-show="!editButton">Agregar</button>
						<button class="btn btn-md btn-warning" ng-click="addProfessor()" ng-show="editButton">Modificar</button>
						<button type="reset" class="btn btn-primary" name="reset" value="Clear" ng-click="reset(professorForm)">Limpiar</button>
					</div>
					
			</a>
			
			


			</div>
			<!-- FIN FORM -->

			<div class="col-md-6">
				<table st-table="professorsDisplayed" st-safe-src="professors" class="table table-striped table-hover">
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
						<tr ng-repeat="row in professorsDisplayed" st-select-row="row" st-select-mode="single"  class="list-row text-left"
							ng-click="rowSelect(row)">
							<td><img class="media-object media-user-list media-user-medium" src="resources/img/nophoto.png"
								alt="..."></td>
							<td>{{row.name | capitalize}}</td>
							<td>{{row.lastName | capitalize}}</td>
							<td>{{row.email | capitalize}}</td>
							<td>
								<button type="button" confirmed-click="deleteProfessor(row.oid)" class="btn btn-danger" ng-confirm-click="Esta seguro que desea eliminar este profesor?">
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



