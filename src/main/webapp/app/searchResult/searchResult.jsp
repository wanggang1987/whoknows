<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="container topic-page"> 
      <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
       	 	<topic-list></topic-list>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
          <hot-topic-siderbar topic="ttttopic"></hot-topic-siderbar>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

 </div>