(ns main-server
  (:require [tcp-server :refer [start-server]]
            [service :refer [process-message]]))

(defn -main []
  (let [port 12345]
    (start-server port process-message)))
