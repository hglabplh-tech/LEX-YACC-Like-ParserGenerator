(ns active.lawrence-add-on.lex-yacc-combined
  (:require
    [active.lawrence-add-on.parse-lex-util :refer :all]
    [active.ephemerol.char-set :refer :all]
    [active.ephemerol.regexp :refer :all]
    [active.ephemerol.scanner :refer :all]
    [active.ephemerol.scanner-run :refer :all]
    [active.lawrence.direct-lr :as direct]
    [active.lawrence.grammar :refer :all]
    [active.lawrence.lr :as lr])
  (:import (java.lang ClassLoader))
  )

(defn eval-scanner
  [scanner sym-name-space]
  (binding [*ns* (find-ns sym-name-space)]
    (eval (scanner->expression scanner))))

(def method-lr :lr)
(def method-slr :slr)

(defn parse
  [g m vs]
  (direct/parse g 1 m vs))


(defn execute-direct [sym-name-space this-scanner-spec
                      yacc-result input method]
(execute-scan-parse sym-name-space this-scanner-spec
                    yacc-result input method)
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

(defn read-lex-yacc-eval                                    ;; change it to the real interfaces
  ;; have to look which calls are available
  "Read AC from a file."
  [filename grammar this-scanner-spec method sym-name-space]
    (with-open [rdr (clojure.java.io/reader filename)
                content (read-full rdr)]
      (execute-scan-parse sym-name-space this-scanner-spec
      grammar content method)))




