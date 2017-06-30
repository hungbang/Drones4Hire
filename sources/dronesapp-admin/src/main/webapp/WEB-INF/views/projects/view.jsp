<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="ProjectDetailsController" class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><spring:message code="drones.admin.pages.projects.view.title"/>
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 tab_block">
			<p style="color: grey">
				<a href="" class="tab2" data-ng-click="tabs[0].active = true" data-ng-class="{active: tabs[0].active}">Summary</a>
				<a href="" class="tab2" data-ng-click="tabs[1].active = true" data-ng-class="{active: tabs[1].active}">Comments <small data-ng-if="comments.length">({{comments.length}})</small></a>
				<a href="" class="tab2" data-ng-click="tabs[2].active = true" data-ng-class="{active: tabs[2].active}">Bids <small data-ng-if="bids.length">({{bids.length}})</small></a>
				<a href="" class="tab2" data-ng-click="tabs[3].active = true" data-ng-class="{active: tabs[3].active}">Attachments <small data-ng-if="project.attachments.length">({{project.attachments.length}})</small></a>
			</p>
			<tabset justified="true">
				<tab active="tabs[0].active">
					<div class="row">
						<div class="col-lg-6">
							<form name="summaryForm">
								<fieldset>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.project.photo.label"/></label><br/>
										<img height="90%" width="90%" alt="<spring:message code="drones.admin.pages.common.project.photo.label"/>" data-ng-src="{{project.attachments[0].attachmentURL}}">
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.column.status.caption"/></label>
										<input class="form-control" type="text" data-ng-model="project.status" disabled/>
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.project.form.clientId.label"/></label>
										<input class="form-control" data-ng-model="project.clientId" type="text" disabled/>
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.project.form.pilotId.label"/></label>
										<input class="form-control" data-ng-model="project.pilotId" type="text" disabled/>
									</div>
								</fieldset>
							</form>
						</div>
						<div class="col-lg-6">
							<form name="summaryForm">
								<fieldset>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.project.form.title.label"/></label>
										<input class="form-control" data-ng-model="project.title" type="text"/>
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.project.form.summary.label"/></label>
										<input class="form-control" data-ng-model="project.summary" type="text"/>
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.project.form.category.label"/></label>
										<select class="form-control validation" data-ng-model="project.service.category"
												data-ng-options="category.name for category in categories track by category.id"
												data-ng-change="loadServices()"></select>
									</div>
									<div class="form-group" data-ng-if="services.length">
										<label><spring:message code="drones.admin.pages.project.form.service.label"/></label>
										<select class="form-control validation" data-ng-model="project.service.id">
											<option data-ng-value="service.id" data-ng-repeat="service in services">{{service.name}}</option>
										</select>
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.project.form.duration.label"/></label>
										<select class="form-control validation" data-ng-model="project.duration" data-ng-options="duration.title for duration in durations track by duration.id"></select>
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.project.form.address.label"/></label>
										<input class="form-control" type="text" data-ng-model="project.location.address" />
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.project.form.budget.label"/></label>
										<select class="form-control validation" data-ng-model="project.budget" data-ng-options="budget.title for budget in budgets track by budget.id"></select>
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.column.startDate.caption"/></label>
										<input class="form-control" type="text" data-ng-model="startDate"/>
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.column.finishDate.caption"/></label>
										<input class="form-control" type="text" data-ng-model="finishDate"/>
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.project.form.postProdaction.label"/></label>
										<input type="checkbox" data-ng-model="project.postProductionRequired" />
									</div>
									<button data-ng-if="project.status != 'BLOCKED'" class="btn btn-primary btn-warning" data-ng-click="blockProject()">Block up</button>
									<button data-ng-if="project.status == 'BLOCKED'" class="btn btn-primary btn-primary" data-ng-click="unblockProject()">Unblock</button>
									<button class="btn btn-primary btn-danger" data-ng-click="deleteProject(project.id)" disabled>Delete</button>
									<button class="btn btn-primary action pull-right" data-ng-click="editProject()"><spring:message code="drones.admin.pages.common.button.save"/></button>
								</fieldset>
							</form>
						</div>
					</div>
				</tab>
				<tab active="tabs[1].active">
					<div class="row">
						<div class="col-lg-6">
							<form name="commentForm">
								<fieldset>
									<div class="form-group" data-ng-if="comments.length">
										<table class="details-table">
											<th>
											</th>
											<tr data-ng-repeat="comment in comments">
												<td>
													<span class="comment-author-text"><a data-ng-href="/admin/#/users/{{comment.user.id}}/view">{{comment.user.firstName}} {{comment.user.lastName}}</a></span>
													<span class="comment-text">: {{comment.comment}}</span>
												</td>
												<td>
													<button class="btn btn-sm btn-danger" data-ng-click="deleteComment(comment.id)"><spring:message code="drones.admin.pages.common.button.delete"/></button>
												</td>
											</tr>
										</table>
										<hr/>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</tab>
				<tab active="tabs[2].active">
					<div class="row">
						<div class="col-lg-6">
							<form name="bidForm">
								<fieldset>
									<div class="form-group" data-ng-if="bids.length">
										<table class="details-table">
											<th>
											</th>
											<tr data-ng-repeat="bid in bids">
												<td>
													<span class="comment-author-text"><a data-ng-href="/admin/#/users/{{bid.user.id}}/view">{{bid.user.firstName}} {{bid.user.lastName}}</a></span>
													<span class="comment-text" data-ng-if="bid.comment">: {{bid.comment}}</span>
													<span> ({{bid.amount}} {{bid.currency}}) </span>
												</td>
												<td data-ng-if="bidsPreparedToDelete">
													<button class="btn btn-sm btn-danger" data-ng-click="deleteBid(bid.id)"><spring:message code="drones.admin.pages.common.button.delete"/></button>
												</td>
											</tr>
										</table>
										<hr/>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</tab>
				<tab active="tabs[3].active">
					<div class="row">
						<div class="col-lg-6">
							<form name="attachmentForm">
								<fieldset>
									<div class="row">
										<div class="col-lg-6" data-ng-repeat="attachment in project.attachments" class="form-group">
											<label>{{ attachment.title }}</label><br/>
											<img height="100%" width="100%" alt="<spring:message code="drones.admin.pages.common.project.photo.label"/>" data-ng-src="{{attachment.attachmentURL}}">
										</div>
									</div>
									<div class="form-group">
										<div data-ng-if="project.attachments.length">
											<table class="details-table">
												<tr data-ng-repeat="attachment in project.attachments">
													<td>
														<a href="{{attachment.attachmentURL}}">{{attachment.title}}</a>
													</td>
													<td>
														<button class="btn btn-sm btn-danger" data-ng-click="deleteAttachment(attachment.id)"><spring:message code="drones.admin.pages.common.button.delete"/></button>
													</td>
												</tr>
											</table>
											<hr/>
										</div>
										<div class="row">
											<div class="form-group">
												<input class="btn btn-default" type="file" file-model="fileToUpload" data-ng-model="file"/>
											</div>
											<button data-ng-if="fileToUpload" class="btn btn-default btn-primary" data-ng-click="uploadAttachment(fileToUpload)" value="Load file">Upload {{fileToUpload.name}}</button>
										</div>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</tab>
			</tabset>
		</div>
	</div>
</div>