(ns clj-playground.destructuring)


(def sample-person
  {:person_id         1234567
   :person_name       "John Doe"
   :image             {:url "http://focus.on/me.jpg"
                       :preview "http://corporate.com/me.png"}
   :person_short_name "John"})


;here
(def cleanup                                      ; ❶
  {:person_id     [:id str]
   :person_name   [:name (memfn toLowerCase)]
   :image         [:avatar :url]})

(defn transform [orig mapping]
  (apply merge
         (map (fn [[k [k' f]]]
                (println "k= " k)
                (println "k'= " k')
                (println "f= " f)
                {k' (f (k orig))})      ; ❷
              mapping)))

;k is :person_id, :person_name, :image
;k' is :id, :name :avatar
;f is str, toLowerCasse, :url

(transform sample-person cleanup)