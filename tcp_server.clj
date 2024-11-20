(ns tcp-server
  (:import [java.net ServerSocket]
           [java.io PrintWriter BufferedReader InputStreamReader]))

(defn handle-connection [socket process-message]
  (with-open [in (BufferedReader. (InputStreamReader. (.getInputStream socket)))
              out (PrintWriter. (.getOutputStream socket) true)]
    (let [message (.readLine in)
          response (process-message message)]
      (.println out response))))

(defn start-server [port process-message]
  (with-open [server-socket (ServerSocket. port)]
    (println "Server running on port" port)
    (while true
      (let [client-socket (.accept server-socket)]
        (future (handle-connection client-socket process-message))))))
