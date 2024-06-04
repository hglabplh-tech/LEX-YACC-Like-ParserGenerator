(ns hgp.lawrence-add-on.yacc-reader-test
  (:require  [hgp.lawrence-add-on.test-defs :refer :all]
    [hgp.lawrence-add-on.lex-yacc-combined :refer :all]
            [active.lawrence.grammar   :refer :all]
            [active.ephemerol.regexp   :refer :all]
            [active.ephemerol.scanner  :refer :all]
            [active.ephemerol.scanner-run :refer :all]))

(read-lex-yacc-eval "input.lang" calculator
                    lang-scan :lr 'hgp.lawrence-add-on)

