(ns server
  (:require [clojure.string :as str])
  (:import [java.net ServerSocket Socket]
           [java.io BufferedReader InputStreamReader PrintWriter]))

(defn calcular [operador n1 n2]
  (case operador
    "soma" (+ n1 n2)
    "subtracao" (- n1 n2)
    "multiplicacao" (* n1 n2)
    "divisao" (if (not= n2 0)
                (/ n1 n2)
                "Erro: Divisão por zero")
    "Operação inválida"))

(defn tratar-conexao [socket]
  (with-open [in (BufferedReader. (InputStreamReader. (.getInputStream socket)))
              out (PrintWriter. (.getOutputStream socket) true)]
    (let [input (str/split (str/trim (.readLine in)) #" ")
          operador (nth input 0)
          n1 (Double/parseDouble (nth input 1))
          n2 (Double/parseDouble (nth input 2))
          resultado (calcular operador n1 n2)]
      (.println out resultado))))

(defn iniciar-servidor [porta]
  (with-open [server-socket (ServerSocket. porta)]
    (println "Servidor rodando na porta" porta)
    (while true
      (let [client-socket (.accept server-socket)]
        (println "Conexão recebida")
        (future (tratar-conexao client-socket))))))

