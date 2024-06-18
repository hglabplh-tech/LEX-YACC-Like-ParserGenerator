(ns active.lawrence-add-on.lex-yacc-combined
  (:require
    [active.ephemerol.char-set :refer :all]
    [active.ephemerol.regexp :refer :all]
    [active.ephemerol.scanner :refer :all]
    [active.ephemerol.scanner-run :refer :all]
    [active.lawrence.direct-lr :as direct]
    [active.lawrence.grammar :refer :all]
    [active.lawrence.lr :as lr])
  (:import (java.lang ClassLoader))
  )

(def custom-classloader (ClassLoader/getSystemClassLoader))
(.setContextClassLoader (Thread/currentThread) custom-classloader)
(defn eval-scanner
  [scanner sym-name-space]
  (binding [*ns* (find-ns sym-name-space)]
    (eval (scanner->expression scanner))))
(def method-lr :lr)
(def method-slr :slr)
(defn parse
  [g m vs]
  (direct/parse g 1 m vs))

(defn def-complete-scan [sym-name-space this-scanner-spec
                         input]

  (let [scanner (compute-scanner this-scanner-spec)
        scan-one (make-scan-one (eval-scanner scanner
                                              sym-name-space))

        scan-result
        (scan-to-list
          scan-one
          (string->list input)
          (make-position 1 0))]
    scan-result))


(defn execute-direct [sym-name-space this-scanner-spec
                      yacc-result input method]


  (let [scan-result (def-complete-scan sym-name-space
                                       this-scanner-spec
                                       input)]


    (println "Scan Result: ")
    (println scan-result)
    (println (first scan-result))
    (println (second scan-result))
    (println (rest scan-result))
    (parse yacc-result method (first scan-result))
    )
  )

(defn write-specialized-defs
  [scanner ns-name grammar out-dir pkg name method]
  (write-scanner-ns scanner ns-name :append false)          ;; TODO: change this here (option)
  (lr/write-ds-parse-ns grammar 1 method
                        (symbol (str pkg "." name "-parser"))
                        '([active.lawrence.parser-runtime :refer :all])
                        (str out-dir "/" name "_parser.clj")))



(defn read-full [rdr]
  (let [the-line (line-seq rdr)]
    the-line))

(defn parse-whole-input [grammar method sc]
  (loop [intern-sc sc
         result '()]
    (if (empty? intern-sc)
    result
    (let [step-result (parse grammar method (first intern-sc))]
      (recur (rest intern-sc) (conj result step-result)))))
  )

(defn read-lex-yacc-eval                                    ;; change it to the real interfaces
  ;; have to look which calls are available
  "Read AC from a file."
  [filename grammar this-scan-spec method sym-name-space]
  (let [scanner (compute-scanner this-scan-spec)
        scan-one (make-scan-one (eval-scanner scanner
                                              sym-name-space))]
    (with-open [rdr (clojure.java.io/reader filename)
                content (read-full rdr)]
      (let [sc (scan-to-list scan-one content
                             (make-position 1 0))]
        (println sc)
        (let [ps (parse-whole-input grammar method (first sc))]
          ps)))))




