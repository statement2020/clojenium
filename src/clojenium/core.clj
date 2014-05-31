(ns clojenium.core
  (:import (org.openqa.selenium.chrome ChromeDriver)
           (org.openqa.selenium.firefox FirefoxDriver)
           (org.openqa.selenium.ie InternetExplorerDriver)
           (org.openqa.selenium.remote RemoteWebDriver)
           (org.openqa.selenium WebElement By)))

(defn click
  [^WebElement element]
  (.click element))

(defn clear
  [^WebElement element]
  (.clear element))

(defn send-keys
  [^WebElement element keys]
  (.sendKeys element keys))

(def kw->by
  {:css #(By/cssSelector %)
   :id #(By/id %)
   :link-text #(By/id %)
   :class-name #(By/className %)
   :name #(By/name %)
   :partial-link-tet #(By/partialLinkText %)
   :tag-name #(By/tagName %)
   :x-path #(By/xpath %)})

(defn find-element
  "Takes a driver, a by and a selector, and returns
  a WebElement. Default is by css e.g.
  (find-element driver \"#input\") =>
  (WebElement id=\"input\")

  (find-element driver :id \"input\") =>
   (WebElement id=\"input\")"
  ([driver selector]
   (find-element driver :css selector))
  ([driver by selector]
   (.findElement driver (kw->by by) selector)))

(defn set-driver!
  [kw]
  {:chrome (ChromeDriver.)
   :firefox (FirefoxDriver.)
   :ie (InternetExplorerDriver.)})

(defn navigate-to
  [^RemoteWebDriver driver url]
  (.get driver url))

(defn open-driver
  "Takes a driver kw, and a url, navigates to the url,
  and returns the driver for future use."
  [kw url]
  (let [driver (set-driver! kw)]
    (-> driver
        (navigate-to url))
    driver))


