package<%-$.invoke('make-package')%>.crud;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import<%-$.invoke('make-package')%>.ApiGatewayResponse;import<%-$.invoke('make-package')%>.Response;import<%-$.invoke('make-package')%>.dao.<%=entity.name%>DAO;import<%-$.invoke('make-package')%>.<%=entity.name%>;

public class Create<%=entity.name%>Handler implements RequestHandler<Map<String,Object>,ApiGatewayResponse> {

	private<%=entity.name%>DAO<%=entity.name%>DAO;
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		try {
			// does 'get' in the informed input in 'body'
			JsonNode body = new ObjectMapper().readTree((String) input.get("body"));

			// create <%= entity.name %> throught POST
			<%= entity.name %> <%= entity.name.toLowerCase() %> = new <%= entity.name %>();
			<%= entity.name.toLowerCase() %>DAO = new <%= entity.name %>DAO();
            
			
			<%- $.invokeLoop ('set-properties-entities', entity.properties) %>
            
			<%= entity.name.toLowerCase() %>DAO.save(<%= entity.name.toLowerCase() %>);

			<%- $.invoke('success-response-service') %>

		} catch (Exception ex) {
			//logger.error("Erro ao salvar <%= entity.name.toLowerCase() %>: " + ex);
			
			<%- $.invoke('error-response-service') %>

		}
	}
}