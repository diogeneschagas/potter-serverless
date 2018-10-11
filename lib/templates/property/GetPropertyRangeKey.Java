
/**
* Get the <%= property.name %> classifield as RangeKey.
*
* 
*/
@DynamoDBRangeKey(attributeName = "<%= property.name.toLowerCase() %>")
public <%- $.invoke('property-type', property) %> get<%= property.name[0].toUpperCase() %><%= property.name.substring(1) %>() {
    return <%= property.name %>;
}
