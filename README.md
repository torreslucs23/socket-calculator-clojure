# **Clojure Multi-Service Server**

This is a simple Clojure project that implements a server providing two main services:  
1. A **calculator** for performing basic mathematical operations.  
2. A **temperature converter** between Celsius and Fahrenheit.  

The client can connect to the server and request either of these services by sending the necessary parameters.

---

## **Project Structure**
- `server.clj`: Contains the server code, including routing and request handling.
- `client.clj`: Contains the client code that connects to the server to consume the services.

---

## **Prerequisites**
- Clojure installed on your system.
- Java Development Kit (JDK) installed.
- `deps.edn` file configured to manage dependencies (already included in the project).

---

## **How to Run**

1. Navigate to the project directory in the terminal.
2. Run the following command to start the server:
   ```bash
   clj -M -m main-server
   ```

3. To run the client, you can execute the following commands:
    ```bash
    clj -M -m main-client localhost 12345 "calc sum 10 20"
    clj -M -m main-client localhost 12345 "convert c-to-f 25"
## **Project Structure**

The project has been refactored so that the communication logic is separated from the service logic. This allows the TCPClient and TCPServer to be reused for other types of messages.
