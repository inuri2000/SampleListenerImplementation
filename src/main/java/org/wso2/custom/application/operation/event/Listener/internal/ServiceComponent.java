package org.wso2.custom.application.operation.event.Listener.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.carbon.identity.application.mgt.listener.ApplicationMgtListener;
import org.wso2.custom.application.operation.event.Listener.EnableOptionEventListener;

@Component(
        name = "org.wso2.custom.application.operation.event.Listener",
        immediate = true
)
public class ServiceComponent {

    private static Log log = LogFactory.getLog(ServiceComponent.class);

    protected void activate(ComponentContext context) {

        BundleContext bundleContext = context.getBundleContext();
        bundleContext.registerService(ApplicationMgtListener.class.getName(), new EnableOptionEventListener(), null);
        log.info("EnableOptionEventListener bundle is activated.");
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        log.info("EnableOptionEventListener bundle is deactivated.");
    }

}