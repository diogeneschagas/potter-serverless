package <%-$.invoke('make-package')%>.crud;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import <%-$.invoke('make-package')%>.ApiGatewayResponse;
import <%-$.invoke('make-package')%>.Response;
import <%-$.invoke('make-package')%>.dao.<%=entity.name%>DAO;
import <%-$.invoke('make-package')%>.model.<%=entity.name%>;

public class List<%=entity.name%>Handler implements RequestHandler<Map<String,Object>,ApiGatewayResponse> {

	private <%=entity.name%>DAO <%=entity.name.toLowerCase()%>DAO;
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		try {
			<%= entity.name.toLowerCase() %>DAO = new <%= entity.name %>DAO();
			// List all elements <%= entity.name.toLowerCase() %>
			List<<%= entity.name %>> list<%= entity.name %> = <%= entity.name.toLowerCase() %>DAO.list();

			/**
 			* 
			*  SUCCESS RESPONSE SERVICE
			* @return success msg.(200)
			*/
			return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(list<%= entity.name %>)
			.setHeaders(
					Collections.singletonMap("X-Powered-By:", "AWS Lambda & Serverless"))
			.build();

		} catch (Exception ex) {
			<%- $.invoke('error-response-service') %>
		}
	}

}