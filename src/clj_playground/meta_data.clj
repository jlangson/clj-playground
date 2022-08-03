(ns clj-playground.meta-data
  (:require [clj-playground.profilable :as profilable]))


(defn- wrap [f]
  (fn [& args]
    (time (apply f args))))

(defn- make-profilable [v]
  (alter-var-root v (constantly (wrap @v))))

(defn- tagged-by [tag nsname]
  (->> (ns-publics nsname)
    vals
    (filter #(get (meta %) tag))))

(defn prepare-bench [nsname]
  (->> (tagged-by :bench nsname)
    (map make-profilable)
    dorun))

(profilable/profile-me 500)

(prepare-bench 'profilable)                                 ;todo bug here?

(profilable/profile-me 500)

(profilable/dont-profile-me 0)


(comment

  )