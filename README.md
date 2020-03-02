# Async-api-InternalDSL
The AsyncAPI internal DSL utilizes the [internal DSL for JSON Schema](https://github.com/jonaslagoni/Java-to-JSON-Schema) and is needed to compile this library. It is used for the payload and parameters. 

By default the AsyncAPI version is `2.0.0`.


The following is an example usecase of the builder:
```java
AsyncAPI api = new AsyncAPIBuilder().
    info().
        title("Streetlights API").
        version("1.0.0").
        license().
            name("Apache 2.0").
            url("https://www.apache.org/licenses/LICENSE-2.0").parent().
        description("The Smartylighting Streetlights API allows you to remotely manage the city lights.").parent().
    server("production").
        url("mqtt://test.mosquitto.org").
        protocol("mqtt").
        protocolVersion("1.0.0").
        description("MQTT production server").parent().
    channel("light/measured").
        description("Channel which is used to notify when light is measured.").
        publish().
            summary("Inform about lumens measured").
            description("Inform about environmental lighting conditions for a particular streetlight.").
            operationId("onLightMeasured").
                message().
                    description("Message contains lighting measurement from a specific light with its lumen with the sentAt timestamp").
                    name("onLightMeasured").
                    title("On Light Measured").
                    summary("Contain lightning measurement").
                    payload().
                        object().
                            property("id", SimpleType.INTEGER).
                                minimum(0).
                                description("Id of the streetlight.").parent().
                            property("lumens", SimpleType.INTEGER).
                                minimum(0).
                                description("Light intensity measured in lumens.").parent().
                            property("sentAt", SimpleType.STRING).
                                format(StringFormat.DATE_TIME).
                                description("Date and time when the message was sent.").finish();
```

Which will generate the following AsyncAPI JSON:

```json
{
   "asyncapi":"2.0.0",
   "info":{
      "title":"Streetlights API",
      "version":"1.0.0",
      "description":"The Smartylighting Streetlights API allows you to remotely manage the city lights.",
      "license":{
         "name":"Apache 2.0",
         "url":"https://www.apache.org/licenses/LICENSE-2.0"
      }
   },
   "channels":{
      "light/measured":{
         "description":"Channel which is used to notify when light is measured.",
         "publish":{
            "message":{
               "payload":{
                  "type":[
                     "object"
                  ],
                  "properties":{
                     "lumens":{
                        "type":[
                           "integer"
                        ],
                        "description":"Light intensity measured in lumens.",
                        "minimum":0.0
                     },
                     "id":{
                        "type":[
                           "integer"
                        ],
                        "description":"Id of the streetlight.",
                        "minimum":0.0
                     },
                     "sentAt":{
                        "type":[
                           "string"
                        ],
                        "description":"Date and time when the message was sent.",
                        "format":"date-time"
                     }
                  }
               },
               "name":"onLightMeasured",
               "title":"On Light Measured",
               "summary":"Contain lightning measurement",
               "description":"Message contains lighting measurement from a specific light with its lumen with the sentAt timestamp"
            },
            "operationId":"onLightMeasured",
            "summary":"Inform about lumens measured",
            "description":"Inform about environmental lighting conditions for a particular streetlight."
         }
      }
   },
   "servers":{
      "production":{
         "protocol":"mqtt",
         "protocolVersion":"1.0.0",
         "description":"MQTT production server",
         "url":"mqtt://test.mosquitto.org"
      }
   }
}
```



## AsyncAPI Builder
A wide range of AsyncAPI fields are not currently supported in this builder. Below is a list of supported methods and information which can be used.

The builder is structured largely the same way you would define your AsyncAPI document manually. When you are done building call the method `finish()` to return the root `AsyncAPI` object. 

These are the following methods that can be called in the `AsyncAPIBuilder`:
```java
new AsyncAPIBuilder().
    asyncapi(Version.v2_0_0).
    info().//Continues with InfoBuilder
    server("mosquitto").//Continues with ServerBuilder
    channel("light/measured").//Continues with ChannelBuilder
```
### Parent
The `parent()` method are used to move the pointer of the active builder 1 up. Meaning that if you are currently defining the info section, when you are finished and want to continue with the parrent `AsyncAPIBuilder` call `.parent()`.

### To JSON
Through the AsyncAPI object you can call `toJson()` to return the JSON string representation of the AsyncAPI document.

### Info Builder
Used to build all the information and license information.

These are the following methods that can be called in the `InfoBuilder`:
```java
new AsyncAPIBuilder().
    info().
        title("Streetlights API").
        version("1.0.0").
        description("The Smartylighting Streetlights API allows you to remotely manage the city lights.").
        license().//Continues with LicenseBuilder

```

### License Builder
License builder used to setup all the license information.

These are the following methods that can be called in the `LicenseBuilder`:
```java
new AsyncAPIBuilder().
    info().
        license().
            name("Apache 2.0").
            url("https://www.apache.org/licenses/LICENSE-2.0");
```

### Server Builder
Server builder used to add servers.

These are the following methods that can be called in the `ServerBuilder`:
```java
new AsyncAPIBuilder().
    server("mosquitto").
        url("mqtt://test.mosquitto.org").
        protocol("mqtt").
        protocolVersion("1.0.0").
        description("MQTT production server").parent().
```
### Channel Builder
Channel builder used to add channels.

These are the following methods that can be called in the `ChannelBuilder`:
```java
new AsyncAPIBuilder().
    channel("light/measured").
        description("Channel which is used to notify when light is measured.").
        publish().//Continues with Operation Builder
        subscribe().//Continues with Operation Builder

```
### Message Builder
Message builder used to add message information about a given operation for a given channel.

These are the following methods that can be called in the `MessageBuilder`:
```java
new AsyncAPIBuilder().
    channel("light/measured").
        publish().
            message().
                description("Message contains lighting measurement from a specific light with its lumen with the sentAt timestamp").
                name("onLightMeasured").
                title("On Light Measured").
                summary("Contain lightning measurement").
                payload().//Continues internal DSL for JSON Schema
```
### Operation Builder
Operation builder used to add information about a given operation.

These are the following methods that can be called in the `MessageBuilder`:
```java
new AsyncAPIBuilder().
    channel("light/measured").
        publish().
            summary("Inform about lumens measured").
            description("Inform about environmental lighting conditions for a particular streetlight.").
            operationId("onLightMeasured").
            message().//Continues with message Builder
```

## Abstractions
Certain abstractions are implemented to ease the use of the builder.

### CRUD
Using the abstract CRUD builder you are able to create an AsyncAPI file using very few lines of code. The CRUD builder is taking the "server" point of view as default, and the method `reverse(true)` can be used to take the "client" point of view.

Using the following example defines CRUD operations for users:
```java
AsyncAPI api = new AsyncAPIBuilder().
        info().
            title("Streetlights API").
            version("1.0.0").
            description("The Smartylighting Streetlights API allows you to remotely manage the city lights.").parent().
        license().
            name("Apache 2.0").
            url("https://www.apache.org/licenses/LICENSE-2.0").parent().
        server("mosquitto").
            url("mqtt://test.mosquitto.org").
            protocol("mqtt").parent().
        crud("user").parameter("id").integer().
            minimum(0).
            description("Id of the user.").crudParent().
        body().
            object().
            title("user").
            property("name", SimpleType.STRING).
                description("Name of the user").
            property("joined_date", SimpleType.STRING).
                format(StringFormat.DATE_TIME).
                description("The date-time the user joined.").crudParent().
        .finish();
```
which would generate the following JSON:

```json
{
   "asyncapi":"2.0.0",
   "info":{
      "title":"Streetlights API",
      "version":"1.0.0",
      "description":"The Smartylighting Streetlights API allows you to remotely manage the city lights."
   },
   "license":{
      "name":"Apache 2.0",
      "url":"https://www.apache.org/licenses/LICENSE-2.0"
   },
   "channels":{
      "user/{id}/read":{
         "parameters":{
            "id":{
               "schema":{
                  "type":[
                     "integer"
                  ],
                  "description":"Id of the user.",
                  "minimum":0.0
               }
            }
         },
         "subscribe":{
            "message":{
               "payload":{
                  "type":[
                     "null"
                  ]
               }
            }
         },
         "publish":{
            "message":{
               "payload":{
                  "type":[
                     "object"
                  ],
                  "title":"user",
                  "properties":{
                     "name":{
                        "type":[
                           "string"
                        ],
                        "description":"The date-time the user joined.",
                        "format":"date-time"
                     }
                  }
               }
            }
         }
      },
      "user/{id}/removed":{
         "parameters":{
            "id":{
               "schema":{
                  "type":[
                     "integer"
                  ],
                  "description":"Id of the user.",
                  "minimum":0.0
               }
            }
         },
         "publish":{
            "message":{
               "payload":{
                  "type":[
                     "null"
                  ]
               }
            }
         }
      },
      "user/{id}/created":{
         "parameters":{
            "id":{
               "schema":{
                  "type":[
                     "integer"
                  ],
                  "description":"Id of the user.",
                  "minimum":0.0
               }
            }
         },
         "publish":{
            "message":{
               "payload":{
                  "type":[
                     "null"
                  ]
               }
            }
         }
      },
      "user/{id}/updated":{
         "parameters":{
            "id":{
               "schema":{
                  "type":[
                     "integer"
                  ],
                  "description":"Id of the user.",
                  "minimum":0.0
               }
            }
         },
         "publish":{
            "message":{
               "payload":{
                  "type":[
                     "null"
                  ]
               }
            }
         }
      }
   },
   "servers":{
      "mosquitto":{
         "protocol":"mqtt",
         "url":"mqtt://test.mosquitto.org"
      }
   }
}
```

### Reversed CRUD
Using the `reverse(true)` method you can easily create the reversed CRUD operations for any client.

```java
AsyncAPI api = new AsyncAPIBuilder().
        info().
            title("Streetlights API").
            version("1.0.0").
            description("The Smartylighting Streetlights API allows you to remotely manage the city lights.").parent().
        license().
            name("Apache 2.0").
            url("https://www.apache.org/licenses/LICENSE-2.0").parent().
        server("mosquitto").
            url("mqtt://test.mosquitto.org").
            protocol("mqtt").parent().
        crud("user").parameter("id").integer().
            minimum(0).
            description("Id of the user.").crudParent().
        body().
            object().
            title("user").
            property("name", SimpleType.STRING).
                description("Name of the user").
            property("joined_date", SimpleType.STRING).
                format(StringFormat.DATE_TIME).
                description("The date-time the user joined.").crudParent().
        reverse(true).finish();
```

Which would generate the following JSON:
```json
{
   "asyncapi":"2.0.0",
   "info":{
      "title":"Streetlights API",
      "version":"1.0.0",
      "description":"The Smartylighting Streetlights API allows you to remotely manage the city lights."
   },
   "license":{
      "name":"Apache 2.0",
      "url":"https://www.apache.org/licenses/LICENSE-2.0"
   },
   "channels":{
      "user/{id}/read":{
         "parameters":{
            "id":{
               "schema":{
                  "type":[
                     "integer"
                  ],
                  "description":"Id of the user.",
                  "minimum":0.0
               }
            }
         },
         "subscribe":{
            "message":{
               "payload":{
                  "type":[
                     "null"
                  ]
               }
            }
         },
         "publish":{
            "message":{
               "payload":{
                  "type":[
                     "object"
                  ],
                  "title":"user",
                  "properties":{
                     "name":{
                        "type":[
                           "string"
                        ],
                        "description":"The date-time the user joined.",
                        "format":"date-time"
                     }
                  }
               }
            }
         }
      },
      "user/{id}/removed":{
         "parameters":{
            "id":{
               "schema":{
                  "type":[
                     "integer"
                  ],
                  "description":"Id of the user.",
                  "minimum":0.0
               }
            }
         },
         "publish":{
            "message":{
               "payload":{
                  "type":[
                     "null"
                  ]
               }
            }
         }
      },
      "user/{id}/created":{
         "parameters":{
            "id":{
               "schema":{
                  "type":[
                     "integer"
                  ],
                  "description":"Id of the user.",
                  "minimum":0.0
               }
            }
         },
         "publish":{
            "message":{
               "payload":{
                  "type":[
                     "null"
                  ]
               }
            }
         }
      },
      "user/{id}/updated":{
         "parameters":{
            "id":{
               "schema":{
                  "type":[
                     "integer"
                  ],
                  "description":"Id of the user.",
                  "minimum":0.0
               }
            }
         },
         "publish":{
            "message":{
               "payload":{
                  "type":[
                     "null"
                  ]
               }
            }
         }
      }
   },
   "servers":{
      "mosquitto":{
         "protocol":"mqtt",
         "url":"mqtt://test.mosquitto.org"
      }
   }
}
```
