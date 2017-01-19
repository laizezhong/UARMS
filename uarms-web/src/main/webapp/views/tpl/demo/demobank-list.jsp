<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<div class="bg-light lter b-b wrapper">
  <h1 class="m-n font-thin h3">银行管理(非弹窗)</h1>
</div>

<div class="wrapper" ng-controller="BankController">
	<div class="row">
		<div class="col col-md-12">
        <form name="form" class="form-validation">
          <div class="panel panel-default">
            <div class="panel-body bg-light" style="padding:5px;padding-left:0px;">
              	<div class="col-sm-2">
	               <label>银行名称：</label> 
	               <input type="text" class="form-control"  ng-model="searcher.bankName" >
	             </div>
	             <div class="col-md-1">
	               <label>&nbsp;</label> 
                   <button class="btn btn-primary form-control" ng-disabled="form.$invalid" ng-click="search()">检   索</button>
                 </div>
                 <div class="col-md-1 col-md-offset-7 pull-right">
	               <label>&nbsp;</label>
	               <shiro:hasPermission name="bank:create"> 
                   		<button class="btn btn-success form-control" ng-click="create()">新  建</button>
                   </shiro:hasPermission>
                 </div>
            </div>
          </div>
        </form>
		</div>
	</div>

<!-- 
	<div class="row">
		<div class="col col-md-8">
          <div class="panel panel-default">
            <div class="panel-body bg-light" style="padding:5px;padding-left:0px;">
	             <div class="col-md-2">
                   <button class="btn btn-primary form-control" ng-click="create()">新 建</button>
                 </div>
            </div>
          </div>
		</div>
	</div>
 -->
	<div class="row">
	  <div class="col col-md-12">
      <!-- <div class="panel panel-default"> -->

	  <table  class="bg-white table table-striped left-table-header" ng-table="tableParamsBank" show-filter="false">
          <thead ng-if="$data.length>0" class="bg-info">
            <tr>
			  <th>No.</th>
			  <th>银行编码</th>
              <th>银行名称</th>
			  <th>银行类型</th>
			  <th>地址</th>
			  <th>联系人</th>
			  <th>联系人电话</th>
			<!--   <th>状态</th> -->
			  <th>已删</th>
			  <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr ng-repeat="bank in $data">
			  <td>{{$index + 1}}</td>
              <td>{{bank.bankCode}}</td>
			  <td>{{bank.bankName}}</td>
			  <td>{{bankTypes[bank.bankType]}}</td>
			  <td>{{bank.bankAddress}}</td>
			  <td>{{bank.contactName}}</td>
			  <td>{{bank.contactPhone}}</td>
			  <!--  <td>{{bank.status}}</td> -->
			  <td>{{bank.deleteFlag}}</td>
			  <td>
			  <shiro:hasPermission name="bank:update">
			  	<a href="" ng-click="edit(bank.id)"><em class="text-primary">更新</em></a>&nbsp;
			  	<a href="" ng-click="logicalDel(bank.id)"><em class="text-primary">删除</em></a>&nbsp;
			  </shiro:hasPermission>	
			  </td>

            </tr>
          </tbody>
          
        </table>
      <!-- </div> -->
    </div>


</div>