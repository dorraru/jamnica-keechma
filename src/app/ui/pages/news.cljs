(ns app.ui.pages.news
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.classified :refer [defclassified]]
            [clojure.string :as str]))

(defclassified ProductImage :img "p-4 rounded-full h-60 w-60 object-contain "
  (fn [props]
    (let [variant (get props :background/variant)]
      (case variant
        "grey" "bg-gray-100 "
        "bg-green-100 "))))

(defnc NewsRenderer [props]
  (let [news (ffirst (use-sub props :news))
        {:keys [subpage]} (use-sub props :router)
        current-article (nth news (int subpage))]
    (if subpage
      (d/div {:class ""}
             (d/div {:class "mt-6 text-xl font-bold text-center pb-4"}
                    (:articleName current-article))
             (d/div (:articleContent current-article)))

      (d/div {:class "bg-green-50 w-screen h-screen p-4 text-green-900 overflow-y-auto"}
             (d/p {:class "font-semibold text-2xl flex items-center justify-center"} "Naši brendovi")
             (d/img {:src "images/brendovi.png"
                     :class "flex w-3/5 h-1/2 items-center justify-center block m-auto "})
             (d/p {:class "font-semibold text-2xl flex items-center justify-center pt-16"} "Jamnica nudi široku paletu vlastitih brendova")
             (d/div {:class "w-full flex flex-wrap justify-around px-36"}

                    (map-indexed
                     (fn [idx item]
                       (d/div {:key idx
                               :class ""}
                              (d/div {:class "mt-6 text-xl font-bold text-center pb-4"}
                                     (:articleName item))
                              (d/div (:summary item))

                              (d/a {:href (router/get-url props :router {:page "news" :subpage idx})}
                                   "Više...")))
                     news))))))

(def News (with-keechma NewsRenderer))