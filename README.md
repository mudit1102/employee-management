# Employee Management

A spring boot project application which helps in **saving**, **retrieving**, **bulk employee update** employee specific data.

* ## Prerequisites ##
    ```
   * Java version 8.
   * Intellij Idea or eclipse.
   * Mysql database.
   * Mvn.
    ```

* ## Features ##
    ```
  * New Employees can be created.
  * Retrieve employee details based on username.
  * Updating an employee detail.
  * Bulk employee update.
    ```
## Setting up the environment ##

 
Follow below steps in the order they are given.
   
* **Discovery service** 
    ```
  * Clone the github repository located at (https://github.com/mudit1102/service-discovery)
  * Open a terminal with this folder.
  * Then run "mvn install" inside service-discovery directory.
  * A target folder will be generated which will have the jar file of this project.
  * Enter into target folder and run "java -jar service-discovery-0.0.1-SNAPSHOT.jar", this will run the spirng boot application from the terminal with embedded tomcat server.
    ```

* **Configuration Service** 
    ```
  * clone the github repository located at (https://github.com/mudit1102/spring-config-server)
  * open a terminal with this folder.
  * Then run "mvn install" inside spring-config-server.
  * A target folder will nbe generated which will have the jar file of this project.
  * Enter into target folder and run "java -jar spring-config-server-0.0.1-SNAPSHOT.jar", this will run the spring boot application from the terminal with embedded tomcat server.
    ```
* **Employee Management** 
    ```
  * clone this github repository located at https://github.com/mudit1102/employee-management
  * Import this project in intellij or eclipse.
  * Before running employee-management service make sure that service-discovery and spring-config-server service are running on thier defined ports.
  * Run elastic-search version 6.8.7 and mysql instance on two other terminals.
  * Now you are good to go with employee-management service. 
    ```
* ## Rest End points exposed by employee-management service ##
  Go to url http://localhost:8093/swagger-ui.html 

* ## High Level View of how the user interacts with the service and rest of the world ##

 ![high_level_view](https://user-images.githubusercontent.com/29011354/86270372-c3ccb100-bbe8-11ea-9004-57fd9477649b.png)
 
* ## Brief overview about each service ##
  
  ![service_overview](https://user-images.githubusercontent.com/29011354/86270880-97656480-bbe9-11ea-8a14-3df370f08b14.png)
  
* ## Start up process ##

  ![start_up_process](https://user-images.githubusercontent.com/29011354/86270917-aa783480-bbe9-11ea-9373-068e456f0265.png)

* ## Requirements ##
  * https://docs.google.com/document/d/1BYSdi1IW_ig8dhVZkgg-vIPuACPOEJ4iNHWjWRLdmoU/edit?usp=sharing

* ## Design Docs links ##
  * Employee Api Design Doc link (https://docs.google.com/document/d/10I5F6AaOdremBQXueAhN2WrAaHS1HgayyDwtQu3zkhk/edit?usp=sharing)
  * Teams Api Design Doc link (https://docs.google.com/document/d/1hbVN2MnU2vpGe74wDw1YuNBfmGMn4u8x5tZVtk6hiO4/edit?usp=sharing)
  * Projects Api Design Doc link (https://docs.google.com/document/d/1dJ4kgfpxKKuuuX4_YfUEn5lwHOs-lNojYmijh-hN2bk/edit?usp=sharing)


* ## TODO ##
  * Integrating the service with elastic search for searching employees.
  * Attachments on projects.
  * Task assignment.
  * Comments on tasks.
  
* ## Important Notes  ##
  * https://docs.google.com/document/d/1BYSdi1IW_ig8dhVZkgg-vIPuACPOEJ4iNHWjWRLdmoU/edit?usp=sharing
   
* ## Disclaimer ##

  * Downloading copyrighted material may be illegal in your country. Use at your own risk.






 