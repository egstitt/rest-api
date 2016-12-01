## Requirements
[Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)   
[Maven](https://maven.apache.org/download.cgi)   
[Git](https://git-scm.com/)   
[PostgreSQL](https://www.postgresql.org/)   
[Eclipse Neon](https://eclipse.org/downloads/eclipse-packages/) (Recommended)

If you plan to use Eclipse, use the `Java EE` one. It is also highly recommended that you use a clean workspace.   

## How to set up the project locally

You will need to download PostgreSQL and create a local database named `ride_recorder`. Alternatively, you can just leave the application pointed at the database instance running on AWS. NOTE: Currently, every time the app restarts, it will automatically re-build the table structure and any existing data will be wiped out.

Clone the repo with this command:   
`git clone https://github.com/SpecializedBicycles/ride-recorder-api.git`

If you plan to use Eclipse, fire it up and import the project with:   
`File -> Import... -> Maven -> Existing Maven Projects`

Navigate to the directory into which you cloned the repo and click 'Finish'.   

## How to run the project locally

If you are using Eclipse, expand the `com.specialized` package, right-click on `Application.java` and choose `Run as...Java application`

If you are not using Eclipse, you can run the project with `mvn spring-boot:run`.

## Documentation

The documentation is automatically generated and is located at `/api/v1/swagger-ui.html`. A running demo version is up on AWS at http://ec2-35-163-102-162.us-west-2.compute.amazonaws.com:8080/api/v1/swagger-ui.html

Any questions? Cool story bro. Errr, I mean ask @egstitt.
