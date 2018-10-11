package <%-$.invoke('make-package')%>.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import <%-$.invoke('make-package')%>.model.<%=entity.name%>;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

public class <%=entity.name%>DAO{

// does get in the variable with the table name in the 'serverless.yml' file
private static final String<%=metadata.project.name.toUpperCase()%>_<%=entity.name.toUpperCase()%>_TABLE_NAME=System.getenv("<%= metadata.project.name.toUpperCase() %>_<%=entity.name.toUpperCase()%>_TABLE_NAME");

private static DynamoDBAdapter db_adapter;private final AmazonDynamoDB client;private final DynamoDBMapper mapper;

private Logger logger=Logger.getLogger(this.getClass());

public <%=entity.name%>DAO(){

// build in mapper configurations
DynamoDBMapperConfig mapperConfig=DynamoDBMapperConfig.builder().withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(<%=metadata.project.name.toUpperCase()%>_<%=entity.name.toUpperCase()%>_TABLE_NAME)).build();

// Does 'get' no db_adapter

this.db_adapter=DynamoDBAdapter.getInstance();
this.client=this.db_adapter.getDbClient();

// Create DynamoDB mapper configurations
this.mapper=this.db_adapter.createDbMapper(mapperConfig);}

// CRUD methods
public Boolean ifTableExists(){return this.client.describeTable(<%=metadata.project.name.toUpperCase()%>_<%=entity.name.toUpperCase()%>_TABLE_NAME).getTable().getTableStatus().equals("ACTIVE");}

public List <<%=entity.name%>> list() throws IOException{
    DynamoDBScanExpression scanExp = new DynamoDBScanExpression();
    List<<%=entity.name%>> resultados = this.mapper.scan(<%=entity.name%>.class,scanExp);
    for(<%=entity.name%>l:resultados){
        logger.info("<%= entity.name %> - list(): "+l.toString());
    }
    return resultados;
}

public <%=entity.name%>get(String id)throws IOException{
    <%=entity.name%><%=entity.name.toLowerCase()%>=null;

HashMap<String,AttributeValue>av=new HashMap<String,AttributeValue>();av.put(":v1",new AttributeValue().withS(id));

DynamoDBQueryExpression<<%=entity.name%>>queryExp=new DynamoDBQueryExpression<<%=entity.name%>>().withKeyConditionExpression("id = :v1").withExpressionAttributeValues(av);

PaginatedQueryList<<%=entity.name%>>resultado=this.mapper.query(<%=entity.name%>.class,queryExp);if(resultado.size()>0){<%=entity.name.toLowerCase()%>=resultado.get(0);logger.info("<%= entity.name %> - get(): <%= entity.name.toLowerCase() %> - "+<%=entity.name.toLowerCase()%>.toString());}else{logger.info("<%= entity.name %> - get(): <%= entity.name.toLowerCase() %> - Não encontrado.");}return<%=entity.name.toLowerCase()%>;}

public void save(<%=entity.name%><%=entity.name.toLowerCase()%>)throws IOException{logger.info("<%= entity.name %> - save(): "+<%=entity.name.toLowerCase()%>.toString());this.mapper.save(<%=entity.name.toLowerCase()%>);}

public Boolean delete(String id)throws IOException{<%=entity.name%><%=entity.name.toLowerCase()%>=null;

// Check if <%= entity.name.toLowerCase() %> exists, before deleting
<%=entity.name.toLowerCase()%>=get(id);if(<%=entity.name.toLowerCase()%>!=null){logger.info("<%= entity.name %> - delete(): "+<%=entity.name.toLowerCase()%>.toString());this.mapper.delete(<%=entity.name.toLowerCase()%>);}else{logger.info("<%= entity.name %> - delete(): <%= entity.name.toLowerCase() %> - não existe.");return false;}return true;}}