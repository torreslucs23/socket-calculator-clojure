(ns server
  (:require [clojure.string :as str])
  (:import [java.net ServerSocket Socket]
           [java.io BufferedReader InputStreamReader PrintWriter]))

(defn calculate [operator n1 n2]
  (case operator
    "sum" (+ n1 n2)
    "sub" (- n1 n2)
    "mult" (* n1 n2)
    "div" (if (not= n2 0)
                (/ n1 n2)
                "Error: Divide by 0")
    "Invalid operation"))

(defn treat-connection [socket]
  (with-open [in (BufferedReader. (InputStreamReader. (.getInputStream socket)))
              out (PrintWriter. (.getOutputStream socket) true)]
    (let [input (str/split (str/trim (.readLine in)) #" ")
          operator (nth input 0)
          n1 (Double/parseDouble (nth input 1))
          n2 (Double/parseDouble (nth input 2))
          result (calculate operator n1 n2)]
      (.println out result))))

(defn start-server [port]
  (with-open [server-socket (ServerSocket. port)]
    (println "Server running on port" port)
    (while true
      (let [client-socket (.accept server-socket)]
        (println "Received result")
        (future (treat-connection client-socket))))))

