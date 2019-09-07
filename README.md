# Spring-Hateoas Studies
>With HATEOAS the resources returned from an API contain links to other related resources. This enables clients to navigate an API with minimal understanding of the URLs.

HATEOAS principles: “The API should guide the client through the application by returning relevant information about the next potential steps, along with each response.”

Spring HATEOAS provides two primary types that represent hyperlinked resources: Resource and Resources. The first one represents a single resource whereas the second if a collection of resources.

## ControllerLinkBuilder - Avoid Hardcoding URLs

To avoid hardcoding URL Spring HATEOAS provides a link builder called ControllerLinkBuilder 

```sh
ControllerLinkBuilder.linkTo(methodOn(MyClass.class).myMethod())withRel(“name-of-the-relation”));

```

Above, ControllerLinkBuilder uses the MyClass.class base path as the foundation of the Link object we are creating. The `methodOn(...)` takes some class and allows us to make calls to the methods defined inside the class passed as parameter of `methodOn(...)`. In the example, the call the class called `myMethod()`.

## Creating Resource Assemblers

We create those new EntityResource because of the creation of the links among the objects that are going to be returned from the API.

```
EntityResource extends ResourceSupport {
    # [Java] The constructor will receive the X entity and copies its values to the variables in XResource
}
```

## Converting Normal Entities to XEntitiesResource
To aid in converting Entity to EntityResource is necessary to create a resource assembler.

```
EntityResourceAssembles extends ResourceAssemblerSupport <Entity, EntityResource> 
	#Constructor that involks 'super'
	# instantiateResource(Entity en)
	# toResource(Entity en)
} 

```

The default constructor of EntityResourceAssembler informs its superclass that it will be using some class to determine the base path for any URL in links it creates when creating an EntityResource.
*	`instantiateResource()` instantiate an EntityResource given an Entity. Is optional override this method when the EntityResource that we want to great has a default constructor.
*	Mandatory when extends `ResourceAssemblerSupport`. What this method does is create an EntityResource from an Entity and gives it a self-link. Under the cover, this method will call `instantiateResource()`  to create the EntityReosurce.

## Naming Embedded Relationships
With the `@Relation` annotation, we can specify how spring Hateos should name the field in the resulting JSON.

```
@Relation(value = “name of single unity in json”, collectionRelation=”name of a collection of unities in json”)
Class EntityResource (): ResourceSupport
```

