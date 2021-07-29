(ns app.controllers.news-article
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.next.controllers.dataloader :as dl]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [keechma.next.toolbox.ajax :refer [GET POST DELETE PUT]]
            [app.settings :as settings]))

(derive :news-article ::pipelines/controller)

(def default-request-config
  {:response-format :json, :keywords? true, :format :json})

(def pipelines
  {:keechma.on/start (pipeline! [value {:keys [state* deps-state* meta-state*] :as ctrl}]

                                (get-in @deps-state* [:router :subpage])

                                (GET
                                  (str "https://jamnica-b0065-default-rtdb.europe-west1.firebasedatabase.app/news/"
                                       value ".json")
                                  default-request-config)

                                (pp/swap! state* assoc value))

   :keechma.on/deps-change (pipeline! [value {:keys [state* deps-state*]}]
                                      (println (get-in @deps-state* [:router :subpage])))

   :keechma.on/stop (pipeline! [_ ctrl]
                               ;;(println "controller stopping!")
                               )})

(defmethod ctrl/prep :news-article [ctrl] (pipelines/register ctrl pipelines))

(defmethod ctrl/derive-state :news-article
  [_ state _]
  state)
