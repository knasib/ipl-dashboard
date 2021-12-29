### IPL Dashboard ###
It is a simple dashboard to show IPL match information from year 2010 to 2020.

This repository serves the different REST endpoints to provide different IPL related data to Web UI.

### Technology Used ###
1. Spring boot
2. Cassandra Database
3. Spring boot starter Hateoas to frame to Hateoas links
4. Swagger for Documentation

### How to run ###
1. Get the secure-connect zip file for Astra DB to your local machine 
2. Run the below command

   ```
   java -jar .\target\ipl-data-provider.jar --datastax.astra.secure-connection-bundle=<Path to Secure connect.zip>
   ```
   
   #### Example ####
   ```
   java -jar .\ipl-data-provider.jar --datastax.astra.secure-connection-bundle=C:\MyWork\ipl-dashboard\common\src\main\resources\secure-connect.zip

   ```  
   
### API Documentation ###
Once application is started, the documentation about the different REST endpoints can be found using the below link
``
http://localhost:8082/ipl/swagger-ui/index.html
``

![img.png](Documentation.png)

###### Web UI code will be present in different git repository. ######