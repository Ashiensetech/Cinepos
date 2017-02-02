<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 2/2/17
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<form id="createTicketForm">
    <%--Developer Hidden Inputs--%>
        <input id="ticketId" type="hidden" value="${ticket.id}" />
        <input id="filmTimeId" type="hidden" value="${filmTime}" />
        <input id="seatId" type="hidden" value="${seatId}" />
        <input id="vatId" type="hidden" value="${vat.id}" />
    <div class="form-group">
        <label>Current Status : </label>
        <label>
            <d:if test="${ticket.currentState==null}" >
                TICKET NO YET CREATED
            </d:if>
            <d:if test="${ticket.currentState!=null}" >
                ${ticket.currentState}
            </d:if>
        </label>
    </div>
    <div class="form-group">
        <label>Description</label>
        <input id="description"  value="${ticket.description}" class="form-control">
        <p class="help-block error" id="errorMsg_description"></p>
    </div>
    <div class="form-group">
        <label>Annotation</label>
        <input id="annotation"  value="${ticket.annotation}" class="form-control">
        <p class="help-block error" id="errorMsg_annotation"></p>
    </div>
  <div class="form-group">
    <label>Vat</label>
    <label>${vat.amount}%</label>
    <%--<select class="form-control" id="vatId" >
      <d:forEach var="vat" items="${vats}" >
        <option value="${vat.id}" data-price = ${vat.amount}>${vat.name}</option>
      </d:forEach>
    </select>--%>
    <p class="help-block error" id="errorMsg_vatId"></p>
  </div>

    <div class="form-group">
        <label>Is Child</label><br>
        <label class="checkbox-inline">
            <input type="checkbox"
            <d:if test="${ticket.child}" >
                   checked
            </d:if>
                   id="isChild">Yes
        </label>
        <p class="help-block error" id="errorMsg_isChild"></p>
    </div>

    <div class="form-group">
        <label>Printed Price</label>
        <input id="printedPrice"  value="${ticket.printedPrice}" class="form-control">
        <p class="help-block error" id="errorMsg_printedPrice"></p>
    </div>

  <%--<div class="form-group">
    <label>Start Date</label>
    <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
      <input id="startDate" type='text' class="form-control" name="date" placeholder="MM/DD/YYY" />

    </div>
    <p class="help-block error" id="errorMsg_startDate"></p>
  </div>
  <div class="form-group">
    <label>End Date</label>
    <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
      <input type='text' class="form-control" id="endDate" name="date" placeholder="MM/DD/YYY" />

    </div>
    <p class="help-block error" id="errorMsg_endDate"></p>
  </div>--%>

  <br>
  <p class="help-block" id="statusMsg"></p>
  <button class="btn btn-primary" onclick="return submitTicketData()">Create Ticket</button>
</form>
