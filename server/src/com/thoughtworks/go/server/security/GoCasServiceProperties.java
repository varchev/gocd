/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.server.security;

import com.thoughtworks.go.server.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.ui.cas.ServiceProperties;

public class GoCasServiceProperties extends ServiceProperties{
    private final SecurityService securityService;
    private final String casCallbackPath;

    @Autowired
    public GoCasServiceProperties(SecurityService securityService, String casCallbackPath) {
        this.securityService = securityService;
        this.casCallbackPath = casCallbackPath;
    }

    public void initialize() throws Exception {
        setService(getService());
        super.afterPropertiesSet();
    }

    //  Service is not set when afterPropertiesSet is invoked, hence overriding it.
    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public String getService() {
        return String.format("%s/go%s", securityService.casServiceBaseUrl(), casCallbackPath);
    }

    @Override
    public boolean isSendRenew() {
        return false;
    }

}
