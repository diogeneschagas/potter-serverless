/**
* Sets the properties throught POST
*
* @param name <%= entity.name %>.<%= property.name %>.
*/

<%= entity.name.toLowerCase() %>.set<%= property.name[0].toUpperCase() %><%= property.name.substring(1) %>(<%- $.invoke('property-type', property) %>)(body.get("<%= property.name.toLowerCase() %>").as<%- $.invoke('property-post-type', property) %>());