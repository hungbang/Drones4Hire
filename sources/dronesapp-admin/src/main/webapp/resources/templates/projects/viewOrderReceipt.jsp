<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div class="modal-header">
	<h3>
		<spring:message code="copper.admin.pages.common.order.label"/> # {{order.id}} <small style="float: right;">{{order.createdAt | date:'dd.MM.yyyy'}}</small>
	</h3>
</div>
<div class="modal-body receipt-details">	
	<div  data-ng-if="order.to.longitude != 0 && order.to.latitude != 0">
		<img alt="" src="http://maps.googleapis.com/maps/api/staticmap?size=540x250&maptype=roadmap&markers=color:green%7Clabel:A%7C{{order.from.latitude}},{{order.from.longitude}}&markers=color:red%7Clabel:B%7C{{order.to.latitude}},{{order.to.longitude}}&language=en" />
	</div>
	<div  data-ng-if="order.to.longitude == 0 && order.to.latitude == 0">
		<img alt="" src="http://maps.googleapis.com/maps/api/staticmap?size=540x250&maptype=roadmap&markers=color:green%7Clabel:A%7C{{order.from.latitude}},{{order.from.longitude}}&language=en" />
	</div> 
	<br/>
	<div>
		<p data-ng-if="order.addressFrom"><b>A:</b> {{order.addressFrom}}</p>
		<p data-ng-if="order.addressTo"><b>B:</b> {{order.addressTo}}</p>
	</div>
	<br/>
	<div>
		<table style="width: 100%;">
			<tr>
				<th style="width: 50%;"><h4><spring:message code="copper.admin.pages.common.order_participants.label"/></h4></th>
				<th style="width: 50%;"><h4><spring:message code="copper.admin.pages.common.order_status.label"/></h4></th>
			</tr>
			<tr>
				<td>
					<p data-ng-if="order.passengerId"><spring:message code="copper.admin.pages.common.form.field.role.PASSENGER"/>: <a href="#/users/{{order.passengerId}}/view" class="pointer">{{order.passengerId}}</a></p>
					<p data-ng-if="order.driverId"><spring:message code="copper.admin.pages.common.form.field.role.DRIVER"/>: <a href="#/users/{{order.driverId}}/view" class="pointer">{{order.driverId}}</a></p>
					<p data-ng-if="order.carType"><spring:message code="copper.admin.pages.common.column.car.type.caption"/>:
						{{
							(order.carType.name == 'BUDGET') ? ('EasyFly') :
							((order.carType.name == 'STANDARD') ? ('GoodFly') :
							((order.carType.name == 'BUSINESS') ? ('BizFly') :
							((order.carType.name == 'PREMIUM') ? ('Premium') :
							('BigFly'))))
						}}
					</p>
				</td>
				<td>
					<p><spring:message code="copper.admin.pages.common.column.status.caption"/>:
						<span class="minw100" data-ng-switch on="order.status">
		                	<span data-ng-switch-when="NEW"><spring:message code="copper.admin.orders.NEW"/></span>
		                	<span data-ng-switch-when="IN_PROGRESS"><spring:message code="copper.admin.orders.IN_PROGRESS"/></span>
		                	<span data-ng-switch-when="CAR_NOT_FOUND" class="error"><spring:message code="copper.admin.orders.CAR_NOT_FOUND"/></span>
		                	<span data-ng-switch-when="COMPLETED" class="success"><spring:message code="copper.admin.orders.COMPLETED"/></span>
		                	<span data-ng-switch-when="CANCELED_BY_DRIVER"><spring:message code="copper.admin.orders.CANCELED_BY_DRIVER"/></span>
		                	<span data-ng-switch-when="CANCELED_BY_PASSENGER"><spring:message code="copper.admin.orders.CANCELED_BY_PASSENGER"/></span>
		                	<span data-ng-if="order.status == 'CANCELED_BY_PASSENGER' && order.driverId == null"> (<spring:message code="copper.admin.orders.during_search"/>)</span>
		                	<span data-ng-switch-when="FAILED"><spring:message code="copper.admin.orders.FAILED"/></span>
		                	<span data-ng-switch-when="PAID"><spring:message code="copper.admin.orders.PAID"/></span>
		                	<span data-ng-switch-when="OFFLINE_PROCESSING"><spring:message code="copper.admin.orders.OFFLINE_PROCESSING"/></span>
		                	<span data-ng-switch-when="EXTERNAL_SERVICE"><spring:message code="copper.admin.orders.EXTERNAL_SERVICE"/></span>
		                	<span data-ng-switch-when="PREORDER"><spring:message code="copper.admin.orders.PREORDER"/></span>
		                </span>
	                </p>
					<p><spring:message code="copper.admin.pages.common.column.created.caption"/>: {{order.createdAt | date:'HH:mm'}}</p>
					<p><spring:message code="copper.admin.pages.common.column.modified.caption"/>: {{order.modifiedAt | date:'HH:mm'}}</p>
				</td>
			</tr>
		</table>
	</div>
	<br/>
	<div data-ng-if="order.status == 'COMPLETED' || order.status == 'PAID'">
		<h4><spring:message code="copper.admin.pages.orders.receipt.modal.trip"/></h4>
		<p data-ng-if="order.pickupDistance != 0"><spring:message code="copper.admin.pages.orders.receipt.modal.pickup_distance"/> {{order.pickupDistance}} <spring:message code="copper.admin.pages.orders.receipt.modal.km"/></p>
		<p data-ng-if="order.distance != 0"><spring:message code="copper.admin.pages.orders.receipt.modal.d"/> {{order.distance}} <spring:message code="copper.admin.pages.orders.receipt.modal.km"/></p>
		<p data-ng-if="order.distanceOutside != 0"><spring:message code="copper.admin.pages.orders.receipt.modal.do"/> {{order.distanceOutside}} <spring:message code="copper.admin.pages.orders.receipt.modal.km"/></p>
		<p data-ng-if="order.time != 0"><spring:message code="copper.admin.pages.orders.receipt.modal.dur"/> {{order.time}} <spring:message code="copper.admin.pages.orders.receipt.modal.mins"/></p> 
		<p data-ng-if="order.timeOutside != 0"><spring:message code="copper.admin.pages.orders.receipt.modal.durO"/> {{order.timeOutside}} <spring:message code="copper.admin.pages.orders.receipt.modal.mins"/></p>
		<p data-ng-if="order.downtime != 0"><spring:message code="copper.admin.pages.orders.receipt.modal.dt"/> {{order.downtime}} <spring:message code="copper.admin.pages.orders.receipt.modal.mins"/></p>
		<br/>
		<p data-ng-if="order.orderRate.promoCodeDiscount > 0"><spring:message code="copper.admin.pages.orders.receipt.modal.discount"/> {{order.orderRate.promoCodeDiscount}}{{order.orderRate.currency.code}}</p>
		<p data-ng-if="order.totalPrice - order.orderRate.promoCodeDiscount > 0"><b><spring:message code="copper.admin.pages.orders.receipt.modal.total"/></b> {{(order.totalPrice - order.orderRate.promoCodeDiscount) | number : 2}}{{order.orderRate.currency.code}}</p>
		<p data-ng-if="order.totalPrice - order.orderRate.promoCodeDiscount < 0"><b><spring:message code="copper.admin.pages.orders.receipt.modal.total"/></b> 0{{order.orderRate.currency.code}}</p>
	</div>
	<br/>
	<div data-ng-if="order.operatorComment">
		<h4><spring:message code="copper.admin.pages.common.form.field.operator_comment.label"/></h4>
		<p>{{order.operatorComment}}</p>
	</div>
	<!-- div>
		<h4><spring:message code="copper.admin.pages.orders.receipt.modal.fare"/></h4>
		<span data-ng-if="order.orderRate.rateDetails.attributes.LP"><spring:message code="copper.admin.pages.orders.receipt.modal.baseFee"/> {{order.orderRate.rateDetails.attributes.LP}}{{order.orderRate.currency.code}}</p>
		<span data-ng-if="order.orderRate.rateDetails.attributes.D"><spring:message code="copper.admin.pages.orders.receipt.modal.d"/> {{order.orderRate.rateDetails.attributes.D}}{{order.orderRate.currency.code}}</p>
		<span data-ng-if="order.orderRate.rateDetails.attributes.DO"><spring:message code="copper.admin.pages.orders.receipt.modal.do"/> {{order.orderRate.rateDetails.attributes.DO}}{{order.orderRate.currency.code}}</p>
		<span data-ng-if="order.orderRate.rateDetails.attributes.T"><spring:message code="copper.admin.pages.orders.receipt.modal.time"/> {{order.orderRate.rateDetails.attributes.T}}{{order.orderRate.currency.code}}</p>
		<span data-ng-if="order.orderRate.rateDetails.attributes.TO"><spring:message code="copper.admin.pages.orders.receipt.modal.timeO"/> {{order.orderRate.rateDetails.attributes.TO}}{{order.orderRate.currency.code}}</p>
		<span data-ng-if="order.orderRate.promoCodeDiscount"><spring:message code="copper.admin.pages.orders.receipt.modal.discount"/> {{order.orderRate.promoCodeDiscount}}{{order.orderRate.currency.code}}</p>
		<span data-ng-if="order.totalPrice - order.orderRate.promoCodeDiscount > 0"><b><spring:message code="copper.admin.pages.orders.receipt.modal.total"/></b> {{(order.totalPrice - order.orderRate.promoCodeDiscount) | number : 2}}{{order.orderRate.currency.code}}</span>
		<span data-ng-if="order.totalPrice - order.orderRate.promoCodeDiscount < 0"><b><spring:message code="copper.admin.pages.orders.receipt.modal.total"/></b> 0{{order.orderRate.currency.code}}</span>
	</div-->
</div>
<div class="modal-footer">
    <button class="btn btn-primary" data-ng-click="close()">
    	<spring:message code="copper.admin.pages.common.button.close"/>
    </button>
</div>