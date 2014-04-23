(ns mtplates.core
  (:gen-class)
  (:require
   [compojure.core :refer [defroutes GET]]
   [compojure.handler :refer [site]]
   [compojure.route :as route]
   [org.httpkit.server :refer [run-server]]
   [mtplates.reload :as reload] ))


(defn header [str]
  (clojure.string/join " " ["<h1>" str "</h1>"]))

(defroutes app-routes
  (GET "/" [] (header "Nothing to see here... YET."))
  (GET "/lookup/:number" [number] (header (str "There's a purple YETI, with tag number: " number)))
  (route/resources "/")
  (route/not-found (header "404 Sucka!")))

(def app
  (site app-routes))

(defn -main [port & dev]
  (println (str "Starting server at: http://localhost:" port))
  (if dev
    (reload/start-nstracker))
  (run-server (site #'app-routes) {:port (Integer. port)}))
