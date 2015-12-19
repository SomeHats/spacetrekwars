(ns spacetrekwars.core
  (:gen-class)
  (:require [spacetrekwars.generator :as generator]
            [spacetrekwars.twitter :as twitter]))

(defn spoiler []
  (->> (repeatedly generator/spoiler)
       (filter #(> 140 (count %1)))
       (first)))

(defn tweet-spoiler []
  (twitter/tweet (spoiler)))

(defn -main
  [& args]
  (println (tweet-spoiler))
  (System/exit 0))
