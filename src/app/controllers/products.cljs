(ns app.controllers.products
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.next.controllers.dataloader :as dl]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [keechma.next.toolbox.ajax :refer [GET POST DELETE PUT]]
            [app.settings :as settings]))

(derive :products ::pipelines/controller)

(def default-request-config
  {:response-format :json, :keywords? true, :format :json})

(def pipelines
  {:keechma.on/start (pipeline! [value {:keys [state* deps-state* meta-state*] :as ctrl}]
                                (GET "https://jamnica-b0065-default-rtdb.europe-west1.firebasedatabase.app/.json"
                                  default-request-config)
                                (pp/swap! state* assoc (:proizvodi value)))

   :keechma.on/stop (pipeline! [_ ctrl]
                               ;;(println "controller stopping!")
                               )})

(defmethod ctrl/prep :products [ctrl] (pipelines/register ctrl pipelines))

(defmethod ctrl/derive-state :products
  [_ state _]
  state)