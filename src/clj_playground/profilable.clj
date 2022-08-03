(ns clj-playground.profilable)

;playing with metadata
(defn ^{:t1 1} foo
  "docstring"
  {:t2 2} (^{:t3 3} [a b] {:t4 4}
           (let [x 5]
             (* x a b)))
  {:t5 5})

(defn ^:bench profile-me [ms]
  (println "Crunching bits for" ms "ms")
  (Thread/sleep ms))

(defn dont-profile-me [ms]
  (println "not expecting profiling"))
