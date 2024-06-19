(ns active.lawrence-add-on.parser-gen-test
  (:require [active.lawrence-add-on.test-defs :refer :all]
    [active.ephemerol.char-set :refer :all]
            [active.ephemerol.regexp :refer :all]
            [active.ephemerol.scanner :refer :all]
            [active.ephemerol.scanner-run :refer :all]
            [active.lawrence-add-on.lex-yacc-combined :as ly-c]))


(ly-c/execute-direct 'active.lawrence-add-on.parser-gen-test
                  lang-scan calculator
                   "(9 * (10 ** (5 +4)))" ly-c/method-lr)