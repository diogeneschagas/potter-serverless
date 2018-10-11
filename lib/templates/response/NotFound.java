/**
 *  NOT FOUND RESPONSE SERVICE
 * @return NOT FOUND <%= entity.name %> msg. (404)
*/
return ApiGatewayResponse.builder().setStatusCode(404)
		.setObjectBody("<%= entity.name %> com o id: '" + <%= entity.name %>.toLowerCase()Id + "' não encontrado.").setHeaders(Collections
				.singletonMap("X-Powered-By:", "AWS Lambda & Serverless"))
		.build();