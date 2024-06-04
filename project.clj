(defproject de.active-group/LEX-YACC-LawEphem "0.1.2-SNAPSHOT" ; version "1.0.0-SNAPSHOT
  :description "This generator is based on lawrence and ephemerol and simply simplifies and extends the functionality by connecting the project"
  :min-lein-version "2.0.0"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies
  [[org.clojure/clojure "1.10.1"]
   [org.clojure/core.async "1.6.681"]
   [de.active-group/active-clojure "0.42.2"]
   [de.active-group/active-data "0.2.0-SNAPSHOT"]
   [de.active-group/lawrence-parse-gen "0.12.0-SNAPSHOT"]
   [de.active-group/ephemerol-brain-scan "0.6.0-SNAPSHOT"]
   [clojure-interop/apache-commons-io "1.0.0"]
   [com.ibm.icu/icu4j "75.1"]
   [com.ibm.icu/icu4j-charset "75.1"]]
  :source-paths ["src"]
  :java-source-paths ["src/main/java"]                      ; Java source is stored separately.
  :test-paths ["test"]
  :resource-paths ["resource"]
  :dev {:resource-paths "test/resource"}


  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "resources/public/cljs-out" :target-path]

  :jar-exclusions [#"(?:^|/).svn/.idea"]
  :jar-inclusions [#"(?:^|/)\.ebextensions"]

  )
