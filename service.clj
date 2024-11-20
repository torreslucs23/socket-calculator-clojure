(ns service)

(defn process-message [message]
  (let [[service & args] (clojure.string/split message #" ")]
    (case service
      "calc" (let [[op n1 n2] args
                   n1 (Double/parseDouble n1)
                   n2 (Double/parseDouble n2)]
               (case op
                 "sum" (+ n1 n2)
                 "sub" (- n1 n2)
                 "mult" (* n1 n2)
                 "div" (if (not= n2 0)
                         (/ n1 n2)
                         "Error: Division by zero")
                 "Invalid operation"))
      "convert" (let [[conversion value] args
                      value (Double/parseDouble value)]
                  (case conversion
                    "c-to-f" (+ (* value 9/5) 32)
                    "f-to-c" (/ (- value 32) 9/5)
                    "Invalid conversion"))
      "Invalid service")))
