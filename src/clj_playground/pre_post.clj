(ns clj-playground.pre-post
  (:require [clojure.test :refer [are]]))

(defn save! [item]
  {:pre [(are [x] x
           (map? item)
           (integer? (:multi item))
           (#{:double :triple} (:width item)))]
   :post [(clojure.test/is (= 10 (:id %)))]}
  (assoc item :id (* (:multi item) 2)))



(save! {:multi 4 :width #{:double :triple}}) ;fails

(save! {:multi 5 :width :double}) ;passes