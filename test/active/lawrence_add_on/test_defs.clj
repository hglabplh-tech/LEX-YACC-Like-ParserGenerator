(ns active.lawrence-add-on.test-defs
  (:require [active.ephemerol.char-set :refer :all]
            [active.ephemerol.regexp :refer :all]
            [active.ephemerol.scanner :refer :all]
            [active.ephemerol.scanner-run :refer :all]
            [active.lawrence.grammar :as gr]
            [clojure.test :refer :all]))

(def lang-scan (scanner-spec
                 ((+ char-set:digit)
                  (fn [lexeme position input input-position]
                    (make-scan-result [:decimal-symbol (Integer/parseInt lexeme)]
                                      input input-position)))
                 (char-set:whitespace
                   (fn [lexeme position input input-position]
                     (make-scan-result :whitespace
                                       input input-position)))
                 ("*"
                   (fn [lexeme position input input-position]
                     (make-scan-result [:mul nil]
                                       input input-position)))
                 ("/"
                   (fn [lexeme position input input-position]
                     (make-scan-result [:div nil]
                                       input input-position)))
                 ("-"
                   (fn [lexeme position input input-position]
                     (make-scan-result [:minus nil]
                                       input input-position)))
                 ("+"
                   (fn [lexeme position input input-position]
                     (make-scan-result [:plus nil]
                                       input input-position)))
                 ("**"
                   (fn [lexeme position input input-position]
                     (make-scan-result [:power nil]
                                       input input-position)))
                 ("("
                   (fn [lexeme position input input-position]
                     (make-scan-result [:lparen nil]
                                       input input-position)))
                 (")"
                   (fn [lexeme position input input-position]
                     (make-scan-result [:rparen nil]
                                       input input-position)))
                 ("^"
                   (fn [lexeme position input input-position]
                     (make-scan-result [:not nil]
                                       input input-position)))))
(def lang-grammar (gr/define-grammar
                    calculator
                    (:decimal-symbol :plus :minus :mul :div :power :not
                      :lparen :rparen :whitespace)
                    expression
                    ((expression ((:whitespace) $1)
                                 ((term) $1)
                                 ((term :plus expression) (+ $1 $3))
                                 ((term :minus expression) (- $1 $3)))
                     (term ((product) $1)
                           ((:decimal-symbol) $1)
                           ((term :mul term) (* $1 $3))
                           ((term :power expression) (+ $1 $3))
                           ((product :div term) (/ $1 $3)))
                     (product ((:not) $1)
                              ((:lparen
                                 expression :rparen) $2))
                     )))

