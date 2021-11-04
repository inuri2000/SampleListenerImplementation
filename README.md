# SampleListenerImplementation to edit an option in the localAndOutboundAuthenticationConfig of an application service provider.
This is a sample implementation of a listener to edit options of a service provider in WSO2 APIM 3.2.0. 
In this example listner implementation, We have used two readily available listners in AbstractApplicationMgtListener class and implemented our logic into it.
 
### Steps to deploy
- Clone the repository and Build the component by running "mvn clean install"
- Copy the created jar file which can be found in target directory into <APIM_HOME>/repository/components/dropins/ folder.
- Configure the User Operation Event Listener by adding following lines into <APIM_HOME>/repository/conf/deployment.toml file.
    ```
   [[event_listener]]
    id = "EnableOptionEventListener"
    type = "org.wso2.carbon.identity.application.mgt.listener.ApplicationMgtListener"
    name = "org.wso2.custom.application.operation.event.Listener.EnableOptionEventListener"
    order = 3455
    ```
- Restart WSO2 APIM.

### Testing
- Login to the devportal and create an application.
- Generate keys for the application.
- Navigate to the management console and view the service provider created for the application and you will see that the setUseUserstoreDomainInLocalSubjectIdentifier option is enabled under the localAndOutboundAuthenticationconfigurations.

**Note: Please note that this is a sample implementation and WSO2 team is not responsible for any code quality or does not gurantee that this implementation will work for all APIM products as the implementation could change overtime. 
Hence you are responsible to refer to this as a sample and implement the logic as per your requirenment and maintaining the listner.