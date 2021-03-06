package <%-$.invoke('make-package')%>.crud;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import <%-$.invoke('make-package')%>.ApiGatewayResponse;
import <%-$.invoke('make-package')%>.Response;
import <%-$.invoke('make-package')%>.dao.<%=entity.name%>DAO;
import <%-$.invoke('make-package')%>.model.<%=entity.name%>;

public class Get<%=entity.name%>Handler implements RequestHandler<Map<String,Object>,ApiGatewayResponse> {

	private <%=entity.name%>DAO <%=entity.name.toLowerCase()%>DAO;
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		try {
			<%= entity.name.toLowerCase() %>DAO = new <%= entity.name %>DAO();
			// get input 'pathParameters'
			Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
			String <%= entity.name.toLowerCase() %>Id = pathParameters.get("id");

			// get <%= entity.name %> to ID
			<%= entity.name %> <%= entity.name.toLowerCase() %> = <%= entity.name.toLowerCase() %>DAO.get(<%= entity.name.toLowerCase() %>Id);

			
			if (<%= entity.name.toLowerCase() %> != null) {
				/** 
 				*  SUCCESS RESPONSE SERVICE
 				* @return success msg.(200)
				*/
				return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(<%= entity.name.toLowerCase() %>)
				.setHeaders(
						Collections.singletonMap("X-Powered-By:", "AWS Lambda & Serverless"))
				.build();
				

			} else {
				/**
				 *  NOT FOUND RESPONSE SERVICE
				 * @return NOT FOUND <%= entity.name %> msg. (404)
				*/
				return ApiGatewayResponse.builder().setStatusCode(404)
				.setObjectBody("<%= entity.name %> com o id: '" + <%= entity.name.toLowerCase() %>Id + "' não encontrado.").setHeaders(Collections
						.singletonMap("X-Powered-By:", "AWS Lambda & Serverless"))
				.build();
			}
		} catch (Exception ex) {
			<%- $.invoke('error-response-service') %>
		}
	}

}