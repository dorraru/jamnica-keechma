(ns app.ui.pages.proizvodi
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.classified :refer [defclassified]]
            [clojure.string :as str]
            ["react-slick" :default Slider]))

(defclassified ProductImage :img "p-4 rounded-full h-60 w-60 object-contain "
  (fn [props]
    (let [variant (get props :background/variant)]
      (case variant
        "grey" "bg-gray-100 "
        "bg-green-100 "))))

(def slick-settings
  {:dots true
   :centerMode true
   :centerPadding "30px"
   :slidesToShow 3
   :slidesToScroll 4
   :autoplay true
   :autoplaySpeed 2000
   :responsive [{:breakpoint 768
                 :settings {:arrows false
                            :centerMode true
                            :centerPadding "40px"
                            :slidesToShow 3}}
                {:breakpoint 480
                 :settings {:arrows false
                            :centerMode true
                            :centerPadding "40px"
                            :slidesToShow 1}}]})

(defnc ProizvodiRenderer [props]
  (let [products (ffirst (use-sub props :products))]
    (d/div {:class "bg-green-50 w-screen h-screen p-4 text-green-900 overflow-y-auto"}
           (d/p {:class "font-semibold text-2xl flex items-center justify-center"} "Naši brendovi")
           (d/img {:src "images/brendovi.png"
                   :class "flex w-3/5 h-1/2 items-center justify-center block m-auto "})
           (d/p {:class "font-semibold text-2xl flex items-center justify-center pt-16"} "Jamnica nudi široku paletu vlastitih brendova")
           (d/div {:class "w-full flex flex-wrap justify-around px-36 bg-gray-300"}
                  (d/div {:class "w-full h-full"}
                         ($ Slider {& (clj->js slick-settings)}
                            (map-indexed
                             (fn [idx [k item]]
                               (d/div {:key idx
                                       :class "text-center py-6"}
                                      (:name item)
                                      ($ ProductImage
                                         {:key idx
                                          :class "mx-auto"
                                          :background/variant (if (= 0 (mod idx 2)) "grey" "")
                                          :src (str "images/flaša" (:id item) ".png")})))
                             products)))))))

(def Proizvodi (with-keechma ProizvodiRenderer))