(ns app.ui.pages.proizvodi
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.classified :refer [defclassified]]))

(defnc Proizvodi [props]
  (d/div {:class "bg-green-100 w-screen h-screen p-4 text-green-900"}
         (d/p {:class "font-semibold text-2xl flex items-center justify-center"} "Naši brendovi")
         (d/img {:src "images/brendovi.png"
                 :class "flex w-3/5 h-1/2 items-center justify-center block m-auto "})
         (d/p {:class "font-semibold text-2xl flex items-center justify-center pt-16"} "Jamnica nudi široku paletu vlastitih brendova")))