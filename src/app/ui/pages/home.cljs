(ns app.ui.pages.home
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma]]
            [keechma.next.helix.lib :refer [defnc]]
            ;;[keechma.next.helix.template :refer [defnt fill-slot configure]]
            [keechma.next.helix.classified :refer [defclassified]]
            [app.ui.components.main :refer [Main]]
            [app.ui.components.hello :refer [Hello]]))

(defclassified HomepageWrapper :div "w-screen flex bg-green-50")

(defnc HomeRenderer [_]
  ($ HomepageWrapper

     (d/div {:class "w-32 bg-flase-left min-h-screen bg-contain"})
     (d/div {:class "w-1/2 mx-auto my-16"}

            (d/h1 {:class "text-2xl text-center py-2 w-full mb-16 border-b border-red-600 text-red-600"}
                  "Jamnica Dobrodošli")
            (d/p "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi consectetur, urna lacinia ullamcorper vehicula, magna enim porttitor turpis, nec tincidunt elit sem vel tortor. Pellentesque porttitor lobortis nisi ac pellentesque. Vivamus ac sem sit amet leo consequat interdum nec quis lorem. Donec sagittis elit et purus blandit imperdiet eget in metus. Nullam ac justo pellentesque, egestas odio eu, sodales nisl. Vivamus sed neque vitae sapien tincidunt aliquam. Quisque sodales sed nisl quis luctus. Curabitur et fermentum sapien. Sed eu ex eu leo facilisis consequat. Fusce aliquam turpis sed ullamcorper malesuada. Vestibulum pretium est ac magna mollis, ut aliquam ante fringilla. Duis sit amet leo at metus imperdiet pellentesque. Maecenas consectetur ut magna eget mollis. Cras risus odio, consectetur ultricies rhoncus eu, sagittis vel sem.")

            (d/p "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi consectetur, urna lacinia ullamcorper vehicula, magna enim porttitor turpis, nec tincidunt elit sem vel tortor. Pellentesque porttitor lobortis nisi ac pellentesque. Vivamus ac sem sit amet leo consequat interdum nec quis lorem. Donec sagittis elit et purus blandit imperdiet eget in metus. Nullam ac justo pellentesque, egestas odio eu, sodales nisl. Vivamus sed neque vitae sapien tincidunt aliquam. Quisque sodales sed nisl quis luctus. Curabitur et fermentum sapien. Sed eu ex eu leo facilisis consequat. Fusce aliquam turpis sed ullamcorper malesuada. Vestibulum pretium est ac magna mollis, ut aliquam ante fringilla. Duis sit amet leo at metus imperdiet pellentesque. Maecenas consectetur ut magna eget mollis. Cras risus odio, consectetur ultricies rhoncus eu, sagittis vel sem.")

            (d/p "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi consectetur, urna lacinia ullamcorper vehicula, magna enim porttitor turpis, nec tincidunt elit sem vel tortor. Pellentesque porttitor lobortis nisi ac pellentesque. Vivamus ac sem sit amet leo consequat interdum nec quis lorem. Donec sagittis elit et purus blandit imperdiet eget in metus. Nullam ac justo pellentesque, egestas odio eu, sodales nisl. Vivamus sed neque vitae sapien tincidunt aliquam. Quisque sodales sed nisl quis luctus. Curabitur et fermentum sapien. Sed eu ex eu leo facilisis consequat. Fusce aliquam turpis sed ullamcorper malesuada. Vestibulum pretium est ac magna mollis, ut aliquam ante fringilla. Duis sit amet leo at metus imperdiet pellentesque. Maecenas consectetur ut magna eget mollis. Cras risus odio, consectetur ultricies rhoncus eu, sagittis vel sem."))
     (d/div {:class "w-32 bg-flase-right min-h-screen bg-contain"})))

(def Home (with-keechma HomeRenderer))