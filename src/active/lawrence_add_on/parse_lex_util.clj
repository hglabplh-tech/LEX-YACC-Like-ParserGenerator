(ns active.lawrence-add-on.parse-lex-util
  (:require [active.ephemerol.char-set :refer :all]
            [active.lawrence.grammar   :as gr]
            [active.lawrence.runtime   :as rt]
            [active.lawrence.direct-lr :as direct]
            [active.ephemerol.regexp   :refer :all]
            [active.ephemerol.scanner  :refer :all]
            [active.ephemerol.scanner-run :refer :all]))

(defn input
  "Convert list of [token attribute-value] vectors to input."
  [g vs]
  (map (fn [[t av]]
         (rt/make-pair (gr/grammar-name->symbol t  g)
         av))
       vs))


(defn eval-scanner
  [scanner sym-name-space]
  (binding [*ns* (find-ns sym-name-space)]
    (eval (scanner->expression scanner))))

(def method-lr :lr)
(def method-slr :slr)

(defn parse
  [g m vs]
  (let [cooked-input (input g vs)]
    (println cooked-input)
  (direct/parse g 1 m cooked-input)))



(defn build-scan-result [sym-name-space this-scanner-spec
                         input]

  (let [scanner (compute-scanner this-scanner-spec)
        scan-one (make-scan-one (eval-scanner scanner
                                              sym-name-space))


        scan-result

         (scan-to-list
        scan-one
        (string->list input)
        (make-position 1 0))
        the-enc  (apply list (scan-result-data scan-result))
        the-rest (scan-result-input scan-result)
        the-pos (scan-result-input-position scan-result)
        real-scan-result [the-enc the-rest the-pos]]

    (println  "Scan Result output: ")
    (println real-scan-result)
    real-scan-result))


(defn execute-scan-parse [sym-name-space this-scanner-spec
                      yacc-result input method]


  (let [[the-enc the-rest the-pos] (build-scan-result sym-name-space
                                       this-scanner-spec
                                       input)]



    (println  "Scan Result: ")
    (println  the-enc)
    (parse yacc-result method  the-enc))
  )
