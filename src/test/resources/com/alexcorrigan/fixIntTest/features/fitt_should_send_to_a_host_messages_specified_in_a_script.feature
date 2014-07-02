Feature: FITT Should Send to a Host Messages Specified in a Script
As a FIX interface tester I want specified FIX messages to be sent to a host so I can validate the messages sent back in response.

  Scenario: Script with single message submission
    Given a message template
      """
      8=FIX.4.4|9=670|35=AE|49=CLIENT|56=EXEC|34=896|52=20140519-08:48:12.876|571=000018384200000021|17=2014051900000021|570=N|10010=1|20004=1|5475=171214|55={symbol}|7931=0|75=20140519|555=2|624=1|20005=1|20006=B|5474=1916.83|198=O10LHHqu_3|10003={quantity}|637={price}|5442=10000061|624=2|20005=1|20006=S|5474=1916.83|198=O10LHHqu_4|10003={quantity}|637={price}|5442=10000062|60=20140519-08:48:12.863|552=2|54=1|37=O10LHHqu_3|11=-349b19a8:14613aab2bf:-8000|453=1|448={member}|447=D|452=4|1={member}_H_1|581=2|5681=0|5477=BLACK BIRD|5179=094304|54=2|37=O10LHHqu_4|11=-349b19a8:14613aab2bf:-7fff|453=1|448={counterparty}|447=D|452=4|1={counterparty}_H_1|581=2|5681=0|5477=BLACK BIRD|5179=094304|856=0|1180=1|1181=183842|1350=180485|487=0|10=011|
      """
    And a script
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
    When FITT plays the script
    Then the following message is sent
    """
      8=FIX.4.4|9=670|35=AE|49=CLIENT|56=EXEC|34=896|52=20140519-08:48:12.876|571=000018384200000021|17=2014051900000021|570=N|10010=1|20004=1|5475=171214|55=VOD.L|7931=0|75=20140519|555=2|624=1|20005=1|20006=B|5474=1916.83|198=O10LHHqu_3|10003=100|637=56.2|5442=10000061|624=2|20005=1|20006=S|5474=1916.83|198=O10LHHqu_4|10003=100|637=56.2|5442=10000062|60=20140519-08:48:12.863|552=2|54=1|37=O10LHHqu_3|11=-349b19a8:14613aab2bf:-8000|453=1|448=AMT|447=D|452=4|1=AMT_H_1|581=2|5681=0|5477=BLACK BIRD|5179=094304|54=2|37=O10LHHqu_4|11=-349b19a8:14613aab2bf:-7fff|453=1|448=BMT|447=D|452=4|1=BMT_H_1|581=2|5681=0|5477=BLACK BIRD|5179=094304|856=0|1180=1|1181=183842|1350=180485|487=0|10=011|
    """
