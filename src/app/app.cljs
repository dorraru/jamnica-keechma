(ns app.app
  (:require [keechma.next.controllers.router]
            [keechma.next.controllers.dataloader]
            [keechma.next.controllers.subscription]
            [app.controllers.products]
            [app.controllers.news]
            [app.controllers.news-article]
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
                 :keechma.controller/type :keechma/dataloader}

    :products #:keechma.controller{:deps [:router]
                                   :params (fn [{:keys [router] :as deps}]
                                             (= "proizvodi" (:page router)))}
    :news #:keechma.controller{:deps [:router]
                               :params (fn [{:keys [router] :as deps}]
                                         (= "news" (:page router)))}
    :news-article #:keechma.controller{:deps [:router]
                                       :params (fn [{:keys [router] :as deps}]
                                                 (and (= "news" (:page router))
                                                      (:subpage router)))}}})

