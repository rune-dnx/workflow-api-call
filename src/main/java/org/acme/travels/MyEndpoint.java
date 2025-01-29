package org.acme.travels;

import java.util.HashMap;
import java.util.Map;

import org.kie.kogito.Model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;


@ApplicationScoped
@Path("/demo")
public class MyEndpoint {

    @Inject
    @Named("demo1")
    Process<? extends Model> scriptsProcess;

    @POST
    public String startMyProcessCustomWay() {
        Model m = scriptsProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "Alex");
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = scriptsProcess.createInstance(m);
        processInstance.start();

        Model result = (Model) processInstance.variables();
        
        return result.toMap().get("message").toString();
    }

}
