(ns app.app
  (:require [keechma.next.controllers.router]
            [keechma.next.controllers.dataloader]
            [keechma.next.controllers.subscription]
            ["react-dom" :as rdom]))

(defn page-eq? [page] (fn [{:keys [router]}] (= page (:page router))))

(defn role-eq? [role] (fn [deps] (= role (:role deps))))

(def homepage? (page-eq? "home"))

(defn slug [{:keys [router]}] (:slug router))

(def app
  {:keechma.subscriptions/batcher rdom/unstable_batchedUpdates
   :keechma/controllers
   {:router {:keechma.controller/params true
             :keechma.controller/type :keechma/router
             :keechma/routes [["" {:page "home"}]
                              ":page"
                              ":page/:subpage/"
                              ":page/:subpage/:detail"
                              ":page/:subpage/:detail/:id"]}
    :dataloader {:keechma.controller/params true
                 :keechma.controller/type :keechma/dataloader}}})