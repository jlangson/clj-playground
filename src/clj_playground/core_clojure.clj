(ns clj-playground.core-clojure)

(def db {:user {:id 123
                :email "eric@lispcast.com"}
         :shopping-cart {:items []}
         :checkout {:coupon-code "434fdsfs"
                    :shipping-preference {}
                    :address {}
                    :billing-information {}}
         :products {1256 {:id 1256
                          :name "Car"
                          :description "..."
                          :price 200}
                    434 {:id 434
                         :name "Truck"}}})

(comment
  (assoc-in db [:a [:b :c] :d] [1 2])
  (update-in db [:products 424 :name] "test")

  (assoc-in db [:products 564] {:id 564 :name "Truck"})

  (update-in db [:products 1256 :name] str " for sale")

  (assoc-in db [:products 1256 :name] nil)

  (update-in db [:products 1256 :id] * 999)
  (assoc-in db [:products 1256 :id] 0)

  (update-in db [:products 1256 :price] + 300)

  (update-in db [:products 434 :price] 1)

  )