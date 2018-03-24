Test for IT Challenges 2018 Version 1.0.2 02/11/2018

GENERAL USAGE NOTES
--------------------
-support only Chrome browser 64+ versions
-support Selenium WebDriver 2.53.0 version. Other versions of Selenium WebDriver weren't tested
-support JUnit 4.12 version
-support Java 1.8 version

RUNNING THE TEST
----------------
For running this terst you should run CheckFirstURLAfterReturningFromImageTab in src/test/java package. In package PageObject you can find page objects for Google search page, Google search all results page and Google search images results page. This pages can help developing other tests for Google searching.
For change settings in the test please, update config.properties.

link.for.compare  - this link for comparing search results
web.searcher = http://google.com/ncr - on this web searcher you can use
string.for.search  - this parameter we search in web searcher
browser.environment - parameter for selecting test browser
os.environment - parameter for selecting operation system

Unfortunately, this test supports only Chrome, but supports it for MacOs, Linux and Windows/

If you have any questions you can contact with me:
email: a.n.yakubina@gmail.com
skype: alina_yakubina
