# Creating an application with a Spring Boot code sample

This application runs as:

* Application
  * Spring Boot 3.3
    * Spring Boot Web:
      * Apache Tomcat 10.1 with virtual threads enabled
      * REST controller
    * Spring Boot Web Security
* Runtime
  * Java 21
    * Container aware
* Cloud
  * Openshift / Kubernetes

This application builds with:

* Maven 3.9.9
* Java 21

This application can be deployed to OpenShift Dev Sandbox Kubernetes Cluster:

* Use S2I Import from Git
* Url https://github.com/brunostuessy/SpringBootApp2.git
* Application e.g. spring-boot-app-2-app
* Name e.g. spring-boot-app-2

See https://developers.redhat.com/articles/2024/03/14/how-use-java-container-awareness-openshift-4

**Note:** The Spring Boot code sample uses the **8081** HTTP port.

Before you begin creating an application with this `devfile` code sample, it's helpful to understand the relationship between the `devfile` and `Dockerfile` and how they contribute to your build. You can find these files at the following URLs:

* [Spring Boot `devfile.yaml`](https://github.com/brunostuessy/SpringBootApp2/blob/master/devfile.yaml)
* [Spring Boot `Dockerfile`](https://github.com/brunostuessy/SpringBootApp2/blob/master/docker/Dockerfile)

1. The `devfile.yaml` file has an [`image-build` component](https://github.com/brunostuessy/SpringBootApp2/blob/master/devfile.yaml#L22-L28) that points to your `Dockerfile`.
2. The [`docker/Dockerfile`](https://github.com/brunostuessy/SpringBootApp2/blob/master/docker/Dockerfile) contains the instructions you need to build the code sample as a container image.
3. The `devfile.yaml` [`kubernetes-deploy` component](https://github.com/brunostuessy/SpringBootApp2/blob/master/devfile.yaml#L29-L41) points to a `deploy.yaml` file that contains instructions for deploying the built container image.
4. The `devfile.yaml` [`deploy` command](https://github.com/brunostuessy/SpringBootApp2/blob/master/devfile.yaml#L49-L56) completes the [outerloop](https://devfile.io/docs/2.2.0/innerloop-vs-outerloop) deployment phase by pointing to the `image-build` and `kubernetes-deploy` components to create your application.

### Additional resources
* For more information about Spring Boot, see [Spring Boot](https://spring.io/projects/spring-boot).
* For more information about devfiles, see [Devfile.io](https://devfile.io/).
* For more information about the deployment outerloop, see [Devfile.io: Innerloop versus outerloop](https://devfile.io/docs/2.2.0/innerloop-vs-outerloop).
* For more information about Dockerfiles, see [Dockerfile reference](https://docs.docker.com/engine/reference/builder/).
