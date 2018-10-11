package<%-$.invoke('make-package')%>.crud;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import<%-$.invoke('make-package')%>.ApiGatewayResponse;import<%-$.invoke('make-package')%>.Response;import<%-$.invoke('make-package')%>.dao.<%=entity.name%>DAO;

public class DeletarLivroHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private<%=entity.name%>DAO<%=entity.name.toLowerCase()%>DAO;
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		try {
			<%= entity.name.toLowerCase() %>DAO = new <%= entity.name %>DAO();
			// obtem 'pathParameters' da entrada informada
			Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
			String <%= entity.name.toLowerCase() %>Id = pathParameters.get("id");

			// pega o livro pelo ID
			Boolean success = <%= entity.name.toLowerCase() %>DAO.delete(<%= entity.name.toLowerCase() %>Id);

			// envia resposta de volta
			if (success) {
				return ApiGatewayResponse.builder().setStatusCode(204).setHeaders(
						Collections.singletonMap("X-Powered-By:", "AWS Lambda & Serverless"))
						.build();

			} else {
				<%- $.invoke('not-found-response-service') %>

			}
		} catch (Exception ex) {
			// logger.error("Erro ao deletar <%= entity.name.toLowerCase() %>: " + ex);

			<%- $.invoke('error-response-service') %>

		}
	}

}