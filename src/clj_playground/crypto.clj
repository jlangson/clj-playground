(ns clj-playground.crypto
  (:import java.io.ByteArrayOutputStream
           javax.crypto.spec.SecretKeySpec
           javax.crypto.Mac
           java.util.Base64
           java.net.URLEncoder
           java.nio.charset.StandardCharsets))

(set! *warn-on-reflection* true)

(defn get-bytes [^String s]
  (.getBytes s (StandardCharsets/UTF_8)))

(defn create-spec [secret]
  (SecretKeySpec. (get-bytes secret) "HmacSHA256"))

(defn init-mac [spec]
  (doto (Mac/getInstance "HmacSHA256")
  (.init spec)))

(defn compute-hmac [^Mac mac canonical]
  (.doFinal mac (get-bytes canonical)))

(defn encode [hmac]
  (URLEncoder/encode
    (.encodeToString (Base64/getEncoder) hmac)))

(defn sign [canonical secret]
  (-> secret
    create-spec
    init-mac
    (compute-hmac canonical)
    encode))

(defn sign-request [url]
  (let [signature (sign url "secret-password")]
    (format "%s?signature=%s" url signature)))

(comment
  (sign-request "http://example.com/tx/1")
  ;350 ms before type hinting
  (time (dotimes [i 10000]
          (sign-request (str "http://example.com/tx" i))))
  ;47 ms after type hinting


  )