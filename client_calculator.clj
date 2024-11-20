(ns client
  (:require [clojure.string :as str])
  (:import [java.net Socket]
           [java.io BufferedReader InputStreamReader PrintWriter]))

(defn connect-server [host port operator n1 n2]
  (with-open [socket (Socket. host port)
              in (BufferedReader. (InputStreamReader. (.getInputStream socket)))
              out (PrintWriter. (.getOutputStream socket) true)]
    (let [message (str operator " " n1 " " n2)]
      (.println out message)
      (println "Received result:" (.readLine in)))))

