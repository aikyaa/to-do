package todo;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //mark as spring application
// start autoconfig, component scanning, database connection, configures based on pom.xml
public class TodoApplication {
    public static void main(String[] args) {//terminal commands
        SpringApplication.run(TodoApplication.class, args) //ask JVM to run the .class file
        //args help spring configure, port details etc
}
