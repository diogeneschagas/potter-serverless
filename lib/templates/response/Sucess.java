/**
 * 
 *  SUCCESS RESPONSE SERVICE
 * @return success msg.(200)
*/
return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(<%= entity.name %>.toLowerCase())
						.setHeaders(
								Collections.singletonMap("X-Powered-By:", "AWS Lambda & Serverless"))
						.build();