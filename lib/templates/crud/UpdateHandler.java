package<%-$.invoke('make-package')%>.crud;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import<%-$.invoke('make-package')%>.ApiGatewayResponse;import<%-$.invoke('make-package')%>.Response;import<%-$.invoke('make-package')%>.dao.<%=entity.name%>DAO;import<%-$.invoke('make-package')%>.model.<%=entity.name%>;

public class Editar<%=entity.name%>Handler implements RequestHandler<Map<String,Object>,ApiGatewayResponse> {

	private<%=entity.name%>DAO<%=entity.name.toLowerCase()%>DAO;
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		try {
			<%= entity.name.toLowerCase() %>DAO = new <%= entity.name %>DAO();
			JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
			Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
			String <%= entity.name.toLowerCase() %>Id = pathParameters.get("id");

			// update <%= entity.name.toLowerCase() %>
			<%= entity.name %> <%= entity.name.toLowerCase() %> = <%= entity.name.toLowerCase() %>DAO.get(<%= entity.name.toLowerCase() %>Id);

			<%= $.invokeLoop ('set-properties-entities', entity.properties) %>

			<%= entity.name.toLowerCase() %>DAO.save(<%= entity.name.toLowerCase() %>);

			<%- $.invoke('success-response-service') %>

		} catch (Exception ex) {
			<%- $.invoke('error-response-service') %>
		}
	}

}