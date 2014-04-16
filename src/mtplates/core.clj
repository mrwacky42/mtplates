(ns mtplates.core
  (:gen-class)
  (:use
   [compojure.core :only [defroutes GET]]
   [compojure.handler :only [site]]
   org.httpkit.server)
  (:require [compojure.route :as route]))



(defn header [str]
  (clojure.string/join " " ["<h1>" str "</h1>"]))

(defroutes app-routes
  (GET "/" [] (header "Nothing to see here... YET."))
  (route/resources "/")
  (route/not-found (header "404 Sucka!")))

(def app
  (site app-routes))

(defn -main [port]
  (println (str "Starting server at: http://localhost:" port))
  (run-server (site #'app-routes) {:port (Integer. port)}))
