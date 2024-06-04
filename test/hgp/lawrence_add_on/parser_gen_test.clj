(ns hgp.lawrence-add-on.parser-gen-test
  (:require [hgp.lawrence-add-on.test-defs :refer :all]
    [active.ephemerol.char-set :refer :all]
            [active.ephemerol.regexp :refer :all]
            [active.ephemerol.scanner :refer :all]
            [active.ephemerol.scanner-run :refer :all]
            [hgp.lawrence-add-on.lex-yacc-combined :as ly-c]))


(ly-c/execute-direct 'hgp.lawrence-add-on.parser-gen-test
                  lang-scan calculator
                   "(9 * (10 ** (5 +4)))" ly-c/method-lr)