**Alexa Skill-Set Configuration**

**Step 1**

&nbsp;&nbsp;&nbsp;&nbsp;Sign in to https://developer.amazon.com
 
**Step 2**

&nbsp;&nbsp;&nbsp;&nbsp;Select ALEXA tab => Alexa Skills Kit

&nbsp;&nbsp;&nbsp;&nbsp;Click ****Add a New Skill** button located at top right
  
**Step 3**
 
&nbsp;&nbsp;&nbsp;&nbsp;In **Skill Information** Section

&nbsp;&nbsp;&nbsp;&nbsp;Enter **Name** and **Invocation Name** 

&nbsp;&nbsp;&nbsp;&nbsp;Click Save > Next
 
**Step 4** In **Interaction Model** Section

&nbsp;&nbsp;&nbsp;&nbsp;**a)** Under **Intent Schema panel**, enter the following

&nbsp;&nbsp;&nbsp;&nbsp;{

&nbsp;&nbsp;&nbsp;&nbsp;"intents": [

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

&nbsp;&nbsp;&nbsp;&nbsp;**b)** Under **Sample Utterances** panel, enter the following

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RawText {maybe|Text}

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RawText {probably|Text}

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RawText {dont know|Text}

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RawText {I think so|Text}

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RawText {maybe next time|Text}

&nbsp;&nbsp;&nbsp;&nbsp;Click Next

**Step 5** In the **Configuration** Section

&nbsp;&nbsp;&nbsp;&nbsp;a) For **Service Endpoint Type**, select **HTTPS** 

&nbsp;&nbsp;&nbsp;&nbsp;b) Select checkbox **North America** 

&nbsp;&nbsp;&nbsp;&nbsp;c) Enter  your **URL** from Deployment. 

```
It will look like this
 https://openwhisk.ng.bluemix.net/api/v1/web/<ow_space_name>default/<action_name>.json
``` 
&nbsp;&nbsp;&nbsp;&nbsp;Click Next

**Step 6** SSL Certificate

&nbsp;&nbsp;&nbsp;&nbsp;Select the **2nd checkbox**, 
``` 
My development endpoint is a sub-domain of a domain that has a wildcard certificate from a certificate authority
``` 

Click Next

**Step 7** Testing instructions

&nbsp;&nbsp;&nbsp;&nbsp;a) Using Amazon Developer console

&nbsp;&nbsp;&nbsp;&nbsp;b) Using Alexa devices (Echo dot, Alexa app, echosim.io)

&nbsp;&nbsp;&nbsp;&nbsp;c) Issue command **Alexa, tell <invocation_name> to <any_name>**

&nbsp;&nbsp;&nbsp;&nbsp;Expected audio output "Hello World.. etc"