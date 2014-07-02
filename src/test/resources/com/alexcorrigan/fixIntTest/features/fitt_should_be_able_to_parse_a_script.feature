Feature: FITT Should be able to Parse a Script

  As a FIX interface tester I want the tool to read a script that specifies a scenario of message submissions and expected message responses because I want the tool to validate the scenario for me.

  Scenario: Script with single message submission
    Given a script
    """
      {
        "scenario": {
          "id": "scenario_101",
          "name": "bla",
          "description": "bla bla bla",
          "steps": [
            {
              "type": "submit",
              "description": "Entering a simple new order",
              "template": "simpleNewOrder"
              "fields": {
                "member": "AMT",
                "quantity": "100",
                "price": "56.2",
                "symbol": "VOD.L"
              }
            }
          ]
        }
      }
      """
    When FITT loads the script
    Then FITT outputs
    """
    Scenario: scenario_101 - bla
    Description: bla bla bla
    
    - Submit Message: simpleNewOrder
      - Description: Entering a simple new order
        - member = AMT
        - price = 56.2
        - quantity = 100
        - symbol = VOD.L
    """

  Scenario: Script with single message submission and expected message response
    Given a script
    """
      {
        "scenario": {
          "id": "scenario_101",
          "name": "bla",
          "description": "bla bla bla",
          "steps": [
            {
              "type": "submit",
              "description": "Entering a simple new order",
              "template": "simpleNewOrder",
              "fields": {
                "member": "AMT",
                "quantity": "100",
                "price": "56.2",
                "symbol": "VOD.L"
              }
            },
            {
              "type": "expect",
              "description": "Message received in response",
              "fields": {
                "35": "8"
              }
            }
          ]
        }
      }
    """
    When FITT loads the script
    Then FITT outputs
    """
    Scenario: scenario_101 - bla
    Description: bla bla bla
    
    - Submit Message: simpleNewOrder
      - Description: Entering a simple new order
        - member = AMT
        - price = 56.2
        - quantity = 100
        - symbol = VOD.L

    - Expect Message:
      - Description: Message received in response
        - 35 = 8
    """