(ns mtplates.core

  (:use
   [compojure.core :only [defroutes GET]]
   [compojure.handler :only [site]]
   org.httpkit.server)
  (:require [compojure.route :as route]))

(defn -main []
  "I suck."
  )

(defn header [str]
  (clojure.string/join " " ["<h1>" str "</h1>"]))

(defroutes app
  (GET "/" [] (header (-main)))
  (route/not-found (header "404 Sucka!")))
