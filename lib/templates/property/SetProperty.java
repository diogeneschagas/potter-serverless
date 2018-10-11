
/**
* Sets the <%= property.name %>.
*
* @param name <%= property.name %>.
*/
public void set<%= property.name[0].toUpperCase() %><%= property.name.substring(1) %>(<%- $.invoke('property-type', property) %> <%= property.name %>) {
    this.<%= property.name %> = <%= property.name %>;
}
