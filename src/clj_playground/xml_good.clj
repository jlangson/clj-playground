(ns clj-playground.xml-good)

(require '[clojure.java.io :as io])
(require '[clojure.xml :as xml])
(require '[clojure.string :refer [split capitalize join]])
(require '[clojure.pprint :refer [cl-format]])

(def balance
  "<balance>
    <accountId>3764882</accountId>
    <lastAccess>20120121</lastAccess>
    <currentBalance>80.12389</currentBalance>
  </balance>")

(defn- to-double [k m]
  (update-in m [k] #(Double/valueOf %)))

(defn parse [xml]
(with-open [xml-in (io/input-stream (.getBytes xml))]
  (->> (xml/parse xml-in)
    :content
    (map #(hash-map (:tag %) (first (:content %))))
    (into {})
    (to-double :currentBalance))))
(defn separate-words [s]
  (->> (split s #"(?=[A-Z])")
    (map capitalize)
    (join " ")))

(defn format-decimals [v]
  (if (float? v)
    (cl-format nil "~$" v)
    v))

(defn print-balance [xml]
  (let [balance (parse xml)
        ks (map (comp separate-words name) (keys balance))
        vs (map format-decimals (vals balance))]
    (zipmap ks vs)))

(comment
  (print-balance balance)
  )