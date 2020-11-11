# Miro Assignment: Widget REST API

## Summary
A web service to work with widgets via HTTP REST API. The service stores only widgets,
assuming that all clients work with the same board.

## Constraints
* Creating a widget. 
  * Having a set of coordinates, Z-index, width, and height, we get a complete widget description in the response. 
  * The server generates the identifier. 
  * If aZ-index is not specified, the widget moves to the foreground (becomes maximum). 
  * If the existing Z-index is specified, then the new widget shifts widget with the same (and greater if needed) upwards.
  
* Changing widget data by Id. 
  * In response, we get an updated full description of the widget. 
  * We cannot change the widget id. 
  * All changes to widgets must occur atomically.
  
* Deleting a widget. 
  *We can delete the widget by its identifier.

* Getting a widget by Id. 
  * In response, we get a complete description of the widget.

* Getting a list of widgets.
  * In response, we get a list of all widgets sorted by Z-index, from smallest to largest.
