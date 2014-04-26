(ns brainiac.plugins.video
  (:require [brainiac.plugin :as brainiac]))

(defn update-video [program-name videos]
  (brainiac/send-message {:name "video" :videos videos} program-name)
)

(defn configure [{:keys [videos program-name]}]
    (brainiac/schedule
        20000
        (update-video program-name videos)))
