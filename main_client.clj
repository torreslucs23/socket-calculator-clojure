(ns main-client
  (:require [tcp-client :refer [send-and-receive]]))

(defn -main [& args]
  (let [[host port message] args
        port (Integer/parseInt port)]
    (println "Response from server:" (send-and-receive host port message))))
