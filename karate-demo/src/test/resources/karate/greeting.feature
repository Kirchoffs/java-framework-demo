Feature: Greeting API test
  Background:
    * def port = karate.properties['demo.server.port'] || '8080'
    * url 'http://localhost:' + port

  Scenario: Get default greeting
    Given path 'greeting'
    When method get
    Then status 200
    And match response == { id: 1, content: 'Hello, World! (GET)' }

  Scenario: Get custom greeting
    Given path 'greeting'
    And param name = 'Karate'
    When method get
    Then status 200
    And match response == { id: 1, content: 'Hello, Karate! (GET)' }

  Scenario: Post greeting
    Given path 'greeting'
    And request { name: 'Karate User' }
    When method post
    Then status 200
    And match response == { id: 1, content: 'Hello, Karate User! (POST)' }
