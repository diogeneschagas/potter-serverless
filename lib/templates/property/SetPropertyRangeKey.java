
/**
* Sets the <%= property.name %> classifield as RangeKey.
*
* 
*/
public void set<%= property.name[0].toUpperCase() %><%= property.name.substring(1) %>(<%- $.invoke('property-type', property) %> <%= property.name %>) {
    this.<%= property.name %> = <%= property.name %>;
}
