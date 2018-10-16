package <%-$.invoke('make-package')%>.crud;

import java.util.Collections;
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

public class Create<%=entity.name%>Handler implements RequestHandler<Map<String,Object>,ApiGatewayResponse> {

	private <%=entity.name%>DAO <%=entity.name.toLowerCase()%>DAO;
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		try {
			// does 'get' in the informed input in 'body'
			JsonNode body = new ObjectMapper().readTree((String) input.get("body"));

			// create <%= entity.name %> throught POST
			<%= entity.name %> <%= entity.name.toLowerCase() %> = new <%= entity.name %>();
			<%= entity.name.toLowerCase() %>DAO = new <%= entity.name %>DAO();
            
			<%= entity.name.toLowerCase() %>.set<%= metadata.entities[0].properties[0].name[0].toUpperCase() %><%= metadata.entities[0].properties[0].name.substring(1) %>(body.get("<%= metadata.entities[0].properties[0].name.toLowerCase() %>").asText());
			/**
			 * Here the POSTS of the other properties of the entity
			 * for example:
			 * <%= entity.name.toLowerCase() %>.setProperty(body.get("property").asType());
			 *  */			
			
            
			<%= entity.name.toLowerCase() %>DAO.save(<%= entity.name.toLowerCase() %>);

			/**
			 * SUCCESS RESPONSE SERVICE
			 * @return success msg.(200)
			 */
			return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(<%= entity.name.toLowerCase() %>)
			.setHeaders(
					Collections.singletonMap("X-Powered-By:", "AWS Lambda & Serverless"))
			.build();

			} catch (Exception ex) {
				
				<%- $.invoke('error-response-service') %>

			}
	}
}