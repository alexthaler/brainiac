(ns brainiac.plugins.metra-tracker
  (:require [brainiac.plugin :as brainiac]
            [clojure.contrib.json :as json :only (read-json)]
            [clojure.java.io :as io :only (reader)]
            [clojure.contrib.string :as string]))

(defn transform [stream]
  (let [json (json/read-json (io/reader stream))]
    (assoc {}
      :name "metra-tracker"
      :title "Upcoming Trains"
      :data json)))

(defn url [line orig dest]
  (format "http://horae.herokuapp.com/live/%s/%s/%s" line orig dest))

(defn configure [{:keys [line orig dest program-name]}]
  (brainiac/schedule
    20000
    (brainiac/simple-http-plugin
      {:method "GET" :url (url line orig dest)}
      transform program-name)))

