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
  * We can delete the widget by its identifier.

* Getting a widget by Id. 
  * In response, we get a complete description of the widget.

* Getting a list of widgets.
  * In response, we get a list of all widgets sorted by Z-index, from smallest to largest.

# Configuration
Used spring profiles to separate SQL database implementation from the original in-memory implementation.
You can set the active profile in the *aplication.properties* file. You can choose between the default **memory** value or the **springdatajpa**

`spring.profiles.active=memory`

# REST API

## Create
Save a new widget

### Request

`POST /widget/`

```javascript   
{
	"coordinates":{
		"x": 100,
		"y": 100
	},
	"zindex": 2,
	"width": 100,
	"height": 100
}
```

### Response
```javascript
{
	"id": 1,
	"coordinates": {
		"x": 100,
		"y": 100
	},
	"zindex": 2,
	"width": 100,
	"height": 100,
	"modified": "2020-11-11T18:27:14.449+00:00"
}
```
	
## List

### Request
`GET /widget/all`

### Params

Request params

|Param|Description|Values|Required|
| ----- | ----- | ----- | ----- |
| pageNo | Page number | First page is 0 | no, default 0 |
| pageSize | Page size | Max 500 | no, default 10 |
| fpX | First point X | int | no |
| fpY | First point Y | int | no |
| spX | Second point X | int | no |
| spY | Second point Y | int | no |

### Response

```javascript
[
    {
        "id": 1,
        "coordinates": {
            "id": 1,
            "x": 50,
            "y": 50
        },
        "zindex": 1,
        "width": 100,
        "height": 100,
        "modified": "2020-11-11T17:34:58.136+00:00"
    }
]
```
## Find By ID

### Request
`GET /widget/:id`

### Response

```javascript
{
    "id": 1,
    "coordinates": {
        "x": 100,
        "y": 100
    },
    "zindex": 3,
    "width": 100,
    "height": 100,
    "modified": "2020-11-11T18:27:14.449+00:00"
}
```	
## Update

### Request
`PUT /widget/:id`
#### Request body
```javascript
{
    "coordinates":{
        "x": 5,
        "y": 5
    },
    "zindex": 2,
    "width": 5,
    "height": 5
}
```
### Response

```javascript
{
    "id": 1,
    "coordinates": {
        "x": 5,
        "y": 5
    },
    "zindex": 2,
    "width": 5,
    "height": 5,
    "modified": "2020-11-11T19:01:35.902+00:00"
}
```	

## Delete

### Request
`DELETE /widget/:id`

### Response

`status 200`


