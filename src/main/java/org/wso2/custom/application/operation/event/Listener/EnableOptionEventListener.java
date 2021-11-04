package org.wso2.custom.application.operation.event.Listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.IdentityApplicationManagementException;
import org.wso2.carbon.identity.application.common.model.LocalAndOutboundAuthenticationConfig;
import org.wso2.carbon.identity.application.common.model.ServiceProvider;
import org.wso2.carbon.identity.application.mgt.ApplicationManagementService;
import org.wso2.carbon.identity.application.mgt.listener.AbstractApplicationMgtListener;
import org.wso2.carbon.identity.core.util.IdentityCoreConstants;

public class EnableOptionEventListener extends AbstractApplicationMgtListener {

//Please note that this is only a sample and WSO2 cannot guarantee that this implementation will work for all APIM products. This is only a sample implementation for APIM 3.2.0. 

  private static final Log log = LogFactory.getLog(
    EnableOptionEventListener.class
  );

  @Override
  public int getExecutionOrderId() {
    int orderId = getDefaultOrderId();
    if (orderId != IdentityCoreConstants.EVENT_LISTENER_ORDER_ID) {
      return orderId;
    }
    //Please provide a higher number to make sure there are no other listeners with the same execution order ID.
    return 3455;
  }

  @Override
  public int getDefaultOrderId() {
    return 1;
  }

  //Override any listners here as you like and implement a logic as per your requirenment. Here, for example I have overriden two listners and implemented my logic to it.

  @Override
  public boolean doPreCreateApplication(
    ServiceProvider serviceProvider,
    String tenantDomain,
    String userName
  )
    throws IdentityApplicationManagementException {
    log.info(
      "============= doPreCreateApplication method started!! ================="
    );
    //You can access the service provider object here and implement the logic here to enable needed options prior to creating application.
    //I am editing few options in the LocalAndOutboundAuthenticationConfig of this service provider below.
    LocalAndOutboundAuthenticationConfig localAndOutboundAuthenticationConfig = new LocalAndOutboundAuthenticationConfig();
    localAndOutboundAuthenticationConfig.setUseUserstoreDomainInLocalSubjectIdentifier(true);
    serviceProvider.setLocalAndOutBoundAuthenticationConfig(
      localAndOutboundAuthenticationConfig
    );
    return true;
  }

/* Since according to the current implementation, Each application is updated right after it is created, 
If we send the changed service provider object in the doPreCreateApplication listener it wil get overriden when it reaches the update method. 
Hence according to the requirenment, The options must be set in this doPreUpdateApplication method. 
Implementing the doPreCreateApplication is not needed in this requirenment. I have only added it as a sample.
*/
  @Override
  public boolean doPreUpdateApplication(
    ServiceProvider serviceProvider,
    String tenantDomain,
    String userName
  )
    throws IdentityApplicationManagementException {
    log.info(
      "============= doPreUpdateApplication method started!! ================="
    );
    LocalAndOutboundAuthenticationConfig localAndOutboundAuthenticationConfig = new LocalAndOutboundAuthenticationConfig();
    localAndOutboundAuthenticationConfig.setUseUserstoreDomainInLocalSubjectIdentifier(true);
    serviceProvider.setLocalAndOutBoundAuthenticationConfig(
      localAndOutboundAuthenticationConfig
    );
    return true;
  }
}
