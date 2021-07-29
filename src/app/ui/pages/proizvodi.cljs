(ns app.ui.pages.proizvodi
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.classified :refer [defclassified]]
            [clojure.string :as str]))

(defclassified ProductImage :img "p-4 rounded-full h-60 w-60 object-contain "
  (fn [props]
    (let [variant (get props :background/variant)]
      (case variant
        "grey" "bg-gray-100 "
        "bg-green-100 "))))

(defnc ProizvodiRenderer [props]
  (let [products (ffirst (use-sub props :products))
        _ (cljs.pprint/pprint products)]
    (d/div {:class "bg-green-50 w-screen h-screen p-4 text-green-900 overflow-y-auto"}
           (d/p {:class "font-semibold text-2xl flex items-center justify-center"} "Naši brendovi")
           (d/img {:src "images/brendovi.png"
                   :class "flex w-3/5 h-1/2 items-center justify-center block m-auto "})
           (d/p {:class "font-semibold text-2xl flex items-center justify-center pt-16"} "Jamnica nudi široku paletu vlastitih brendova")
           (d/div {:class "w-full flex flex-wrap justify-around px-36"}

                  (map-indexed
                   (fn [idx [k item]]
                     (d/div {:class "flex items-center flex-col justify-center m-10"}
                            (:name item)
                            ($ ProductImage
                               {:key idx
                                :background/variant (if (= 0 (mod idx 2)) "grey" "")
                                :src (str "images/flaša" (:id item) ".png")})))
                   products)))))

(def Proizvodi (with-keechma ProizvodiRenderer))