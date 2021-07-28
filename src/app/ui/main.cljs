(ns app.ui.main
  (:require [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.controllers.router :as router]
            [helix.core :as hx :refer [$]]
            [helix.dom :as d]
            [app.ui.pages.home :refer [Home]]
            [app.ui.pages.proizvodi :refer [Proizvodi]]
            [keechma.next.helix.classified :refer [defclassified]]))

(defclassified NavigationLink :a "mx-4 text-green-900 hover:text-red-600 flex items-center justify-center")

(defnc Header [props]
  (d/div
   {:className "w-full fixed top-0 left-0 right-0 h-16 bg-green-200 flex p-2"}
   (d/a {:class "h-full relative"
         :href (router/get-url props :router {:page "home"})}
           ;; router/get-url je unaprijed definirana funkcija za 
           ;; kreiranje linkova koje konzumira naš router controller
        (d/img {:class "h-full"
                :src "images/jamnica-logo.png"
                :alt "Logotip Jamnice"}))

   ($ NavigationLink {:href (router/get-url props :router {:page "home"})}
      "Home")
   ($ NavigationLink {:href (router/get-url props :router {:page "proizvodi"})}
      "Proizvodi")
   ($ NavigationLink {:href (router/get-url props :router {:page "about"})}
      "O nama")
   ($ NavigationLink {:href (router/get-url props :router {:page "vijesti"})}
      "Vijesti")
   ($ NavigationLink {:href (router/get-url props :router {:page "kontakt"})}
      "Kontakt")))

(defnc Footer [props]
  (d/div  {:className "w-full bg-green-200 flex justify-between p-2 z-50"}
          (d/div {:class "flex"}
                 ($ NavigationLink {:href (router/get-url props :router {:page "zastita_privatnosti"})}
                    "Zaštita privatnosti")
                 ($ NavigationLink {:href (router/get-url props :router {:page "uvjeti_koristenja"})}
                    "Uvjeti korištenja"))
          (d/div {:class "flex items-center flex-col"}
                 (d/p {:class "mx-4 text-green-900 text-xs italic "} "Expert in mineral waters since 1828")
                 (d/img {:class "h-8 py-2"
                         :src "images/jamnica-logo.png"
                         :alt "Logotip Jamnice"})
                 (d/p {:class "mx-4 text-green-900 text-xs italic mt-2 "} "© 2020. JAMNICA"))))

(defn get-breadcrumb-urls [router]
  (reduce
   (fn [coll route]
     (conj coll (distinct (concat (flatten coll) route))))
   []
   router))

(defnc Breadcrumbs [props]
  (let [router (use-sub props :router)
        breadcrumbs (get-breadcrumb-urls router)]
    (d/div {:class "bg-red-500"}
           (map
            (fn [url]
              (let [breadcrumb (apply hash-map url)
                    new-route (router/get-url props :router breadcrumb)]
                (d/a {:href new-route}
                     "/"
                     (last url))))
            breadcrumbs))))

(defnc MainRenderer [props]
  (let [{:keys [page]} (use-sub props :router)]
    (d/div {:class "pt-16 overflow-x-hidden"}
           ($ Breadcrumbs {& props})
           ($ Header {& props})
           (case page
             "home" ($ Home)
             "proizvodi" ($ Proizvodi)
             (d/div "404"))
           ($ Footer {& props}))))

(def Main (with-keechma MainRenderer))