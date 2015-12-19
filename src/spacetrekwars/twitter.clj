(ns spacetrekwars.twitter
  (:require [twitter.api.restful :refer [statuses-update]]
            [twitter.oauth :refer [make-oauth-creds]]))

(def credentials
  (let [{:keys [app-consumer-key app-consumer-secret user-access-token user-access-secret]}
        (read-string (slurp "credentials.clj"))]
    (make-oauth-creds app-consumer-key app-consumer-secret user-access-token user-access-secret)))

(defn tweet
  "Sends a tweet on the account authorized by credentials.clj"
  [status]
  (statuses-update :oauth-creds credentials
                   :params {:status status}))
