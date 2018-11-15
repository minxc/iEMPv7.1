/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.minxc.emp.activiti.rest.editor.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.minxc.emp.bpm.core.manager.BpmnDefinitionManager;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tijs Rademakers
 */
@RestController
public class ModelSaveRestResource implements ModelDataJsonConstants {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelSaveRestResource.class);

    @Autowired
    private BpmnDefinitionManager bpmnDefinitionManager;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Resource
    RepositoryService repositoryService;


    @RequestMapping(value = "/model/{modelId}/save", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorCatching
    public void saveModel(HttpServletResponse response,@PathVariable String modelId, @RequestBody MultiValueMap<String, String> values) throws Exception {
        Model model = repositoryService.getModel(modelId);
        ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
        modelJson.put("name", values.getFirst("name"));
        modelJson.put("description", values.getFirst("description"));
        bpmnDefinitionManager.updateBpmnModel(model, values);
    }
}
