# Key Java @Annotations to build full Spring Boot Rest API

Sample for the (www.jeevora.com) post about Key Java @Annotations to build full Spring Boot Rest API
 (https://www.jeevora.com/2019/09/16/key-java-annotations-to-build-spring-boot-rest-api/ and https://dzone.com/articles/key-java-annotations-to-build-full-spring-boot-res)

This post aims to demonstrate important Java @annotations used to build a functional Spring Boot REST API. The use of Java annotation gives developers the capability to reduce the code verbosity by a simple annotation.

As an example, we can refer to a transaction. By the standard pro-grammatically process with a transaction template, this requires a more complex config and boilerplate code to write, while this can be achieved with a simple@Transactional declarative annotation.

The API is a simple module to implement a CRUD operation of Business Entity from a more complex system with the intention to coordinate and harmonize economic information relating to enterprises, establishments, and groups of entities. For the sake of simplicity, the API uses the H2 in-memory database.

The project structure is constituted by three modules, but this post will focus on the module to manage entities. That module has a dependency from the Common module, where it shares things like the error handling and essential useful classes with the remaining part of the whole system.


# Microservices in Publish-Subscribe communication using Apache Kafka as a Messaging Systems and validated through Integration Test.

Sample for the (www.jeevora.com) post about Microservices in Publish-Subscribe communication using Apache Kafka as a Messaging Systems and validated through Integration Test (https://www.jeevora.com/2019/11/18/publish-subscribe-messaging-systems/ and https://dzone.com/articles/microservices-in-publish-subscribe-communication-u)

Messaging systems play an important role in any enterprise architecture as it enables reliable integration without tightly coupling the applications. It also allow the transfer of packets of data frequently, immediately and asynchronously, using customizable formats.

In this article, we will see how we can use the publish-subscribe pattern to share data in a responsive way between two distinct microservices and validate it with an integration test through different layers in an end to end scenario.
