package com.spring.hateoas.learn.controller

import com.spring.hateoas.learn.domain.PersonOwner
import com.spring.hateoas.learn.domain.resource.PersonOwnerResource
import com.spring.hateoas.learn.domain.resource.assembler.PersonOwnerResourceAssembler
import com.spring.hateoas.learn.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/PersonOwner"])
class PersonOwnerController {


    @Autowired
    lateinit var personRepository: PersonRepository

    @PostMapping(path = ["/add-person"])
    fun addPersonOwner(@RequestBody(required = true) person: PersonOwner): ResponseEntity<Resource<PersonOwnerResource>> {

        person.let {
            personRepository.addPersonOwner(it)
            val dogResource = PersonOwnerResourceAssembler().toResource(it)
            val myReturn = Resource(dogResource)
            return ResponseEntity(Resource(dogResource), HttpStatus.OK)
        }
    }

    @GetMapping(path = ["/get-all"])
    fun getAllPersons(): ResponseEntity<Resources<PersonOwnerResource>> {
        var listDogueiras = PersonOwnerResourceAssembler().toResources(personRepository.listOfPersonOwners)
        var listOfResourcesDogs = Resources<PersonOwnerResource>(listDogueiras)

        listOfResourcesDogs.add(
                linkTo(methodOn(DogController::class.java).getAllDogs()).withRel("all-dogs")
        )
        return ResponseEntity(listOfResourcesDogs, HttpStatus.OK)
    }

    @GetMapping(path = ["/find/{id}"])
    fun findPerson(@PathVariable(name = "id", required = true) idPerson: Long): ResponseEntity<Resource<PersonOwnerResource>> {
        val result = personRepository.listOfPersonOwners.filter { personOwner -> personOwner.id.equals(idPerson) }

        result.getOrNull(0)?.let {

            var personResource = PersonOwnerResourceAssembler().toResource(it)
            val resourceToReturn = Resource(personResource)
            return ResponseEntity(resourceToReturn, HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)

    }

}