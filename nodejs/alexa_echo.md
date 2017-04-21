## Setting up Your Alexa Skill with Serverless

Skills are managed through the Amazon Developer Portal. You’ll link the Lambda function you created to a skill defined in the [Developer Portal](https://developer.amazon.com/).

**Step 1** Navigate to the [Amazon Developer Portal](https://developer.amazon.com/) and Sign In. 
 
 2. Once signed in, navigate to Alexa and select **"Getting Started"** under Alexa Skills Kit.

  ![](https://raw.githubusercontent.com/snippet-java/snippet-marketplace/master/nodejs/images/alexa1.png)

**Step 2** Here is where you will define and manage your skill. Select **"Add a New Skill"**

  ![](https://raw.githubusercontent.com/snippet-java/snippet-marketplace/master/nodejs/images/alexa2.png)

**Step 3**. In **Skill Information** Section

 
 &nbsp;&nbsp;&nbsp;&nbsp;Make **sure** the **custom interaction model** is selected for **Skill Type**”. 
 
 &nbsp;&nbsp;&nbsp;&nbsp;**Language** will be English (U.S)
 
&nbsp;&nbsp;&nbsp;&nbsp;Enter **Name**   You can use value **DW Helper** for this example. 

&nbsp;&nbsp;&nbsp;&nbsp;`Remember, when you create a skill that you will publish, you will use a name that you define for your skill. That name will be the one that shows up in the Alexa App. Add the invocation name.`

&nbsp;&nbsp;&nbsp;&nbsp;Enter the **Invocation Name**  You can use **say hello** for this example. 

&nbsp;&nbsp;&nbsp;&nbsp;`You will issue command` **Alexa, say hello** ` to trigger the skill for this name`.

&nbsp;&nbsp;&nbsp;&nbsp;Click Save > Next 


**Step 4** Now, notice you are in the Interaction Model section.


Next, we need to define our skill’s Interaction Model. Let us begin with the intent schema.
     ![](https://raw.githubusercontent.com/snippet-java/snippet-marketplace/master/nodejs/images/alexa3.png)
```JSON
{
  "intents": [
    {
      "slots": [
        {
          "name": "Text",
          "type": "AMAZON.LITERAL"
        }
      ],
      "intent": "RawText"
    }
  ]
}
```
We will not do any changes or additions to "Add Slot Type”
     ![](https://raw.githubusercontent.com/snippet-java/snippet-marketplace/master/nodejs/images/alexa4.png)

 
The next step is to build the utterance list.
     ![](https://raw.githubusercontent.com/snippet-java/snippet-marketplace/master/nodejs/images/alexa5.png)
```JSON
RawText {maybe|Text}
RawText {probably|Text}
RawText {dont know|Text}
RawText {I think so|Text}
RawText {maybe next time|Text}
```
    


Select **Save**. You should see the interaction model being built (this might a take a minute or two). If you select next, your changes will be saved and you will go directly to the Configuration screen. After selecting Save, it should now look like this:

**Step 5** In the **Configuration** Section

&nbsp;&nbsp;&nbsp;&nbsp;a) For **Service Endpoint Type**, select **HTTPS** 

&nbsp;&nbsp;&nbsp;&nbsp;b) Select checkbox **North America** 

&nbsp;&nbsp;&nbsp;&nbsp;c) Enter  your **URL** from Deployment. 

  ![](https://raw.githubusercontent.com/snippet-java/snippet-marketplace/master/nodejs/images/alexa6.png)

`
url will look like this
 https://openwhisk.ng.bluemix.net/api/v1/web/<ow_space_name>default/<action_name>.json
`

&nbsp;&nbsp;&nbsp;&nbsp;Click Next

**Step 6** SSL Certificate

&nbsp;&nbsp;&nbsp;&nbsp;Select the **2nd checkbox**,
`
My development endpoint is a sub-domain of a domain that has a wildcard certificate from a certificate authority
`
     ![](https://raw.githubusercontent.com/snippet-java/snippet-marketplace/master/nodejs/images/alexa7.png)
	 
Click Next

** YOU ARE PRETTY MUCH DONE**
  
  **Step 7** Testing instructions

&nbsp;&nbsp;&nbsp;&nbsp;a) Using Amazon Developer console

&nbsp;&nbsp;&nbsp;&nbsp;b) Using Alexa devices (Echo dot, Alexa app, echosim.io)

&nbsp;&nbsp;&nbsp;&nbsp;c) Issue command **Alexa, tell <invocation_name> to <any_name>**

&nbsp;&nbsp;&nbsp;&nbsp;Expected audio output "Hello World.. etc"
