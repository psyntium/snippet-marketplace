Alexa Skill-Set Configuration

 1. Sign in to https://developer.amazon.com
 
 2. Select ALEXA tab => Alexa Skills Kit
  Click ****Add a New Skill** button located at top right
  
 3. In **Skill Information** Section
 Enter **Name** and **Invocation Name** 
Click Save > Next
 
 4. In **Interaction Model** Section
 **a)** Under **Intent Schema panel**, enter the following  
  {  &nbsp;&nbsp;&nbsp;&nbsp;"intents": [ 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{ 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"slots": [ 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "Text", 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"type": "AMAZON.LITERAL" 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;],
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"intent": "RawText" 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;} 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;] 
 &nbsp;&nbsp;&nbsp;&nbsp;}
**b)** Under **Sample Utterances** panel, enter the following 
 &nbsp;&nbsp;&nbsp;&nbsp;RawText {maybe|Text}
 &nbsp;&nbsp;&nbsp;&nbsp;RawText {probably|Text}
 &nbsp;&nbsp;&nbsp;&nbsp;RawText {dont know|Text}
 &nbsp;&nbsp;&nbsp;&nbsp;RawText {I think so|Text}
 &nbsp;&nbsp;&nbsp;&nbsp;RawText {maybe next time|Text}
Click Next

5. In the **Configuration** Section
a) For **Service Endpoint Type**, select **HTTPS** 
b) Select checkbox **North America** 
c) Enter  your **URL** from Deployment. 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;It will look like this
 https://openwhisk.ng.bluemix.net/api/v1/web/<ow_space_name>default/<_action_name_\>.json
Click Next
6. SSL Certificate
Select the **2nd checkbox**, 
&nbsp;&nbsp;&nbsp;&nbsp;**My development endpoint is a sub-domain of a domain that has a wildcard certificate from a certificate authority**
Click Next
7. Testing instructions
a) Using Amazon Developer console
b) Using Alexa devices (Echo dot, Alexa app, echosim.io)
c) Issue command Alexa, tell <invocation_name> to <any_name>
&nbsp;&nbsp;&nbsp;&nbsp;Expected audio output "Hello World. ."