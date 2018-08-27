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

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;
import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.core.manager.BpmDefinitionManager;
import org.minxc.emp.bpm.core.model.BpmDefinition;
import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Tijs Rademakers
 */
@RestController
public class ModelEditorJsonRestResource {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);

    @Autowired
    private RepositoryService repositoryService;

    @Resource
    private BpmDefinitionManager bpmDefinitionManager;

    @Autowired
    private BpmProcessDefService bpmProcessDefService;

    @RequestMapping(value = "/model/{modelId}/json", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getEditorJson(@PathVariable String modelId) {
        JSONObject json = null;

        Model model = repositoryService.getModel(modelId);
        if (model != null) {
            try {
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    json = JSONObject.parseObject(model.getMetaInfo());
                } else {
                    json = new JSONObject();
                    json.put("name", model.getName());
                }
                json.put("modelId", model.getId());
                String editorJson = new String(repositoryService.getModelEditorSource(model.getId()), "utf-8");

                JSONObject editorModelJson = JSONObject.parseObject(editorJson);
                BpmDefinition def = bpmDefinitionManager.getMainDefByActModelId(modelId);

                JSONObject bpmDefSetting = new JSONObject();
                if (StringUtil.isNotEmpty(def.getActDefId())) {
                	DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) bpmProcessDefService.getBpmProcessDef(def.getId());
                    bpmDefSetting = processDef.getJson();
                }

                bpmDefSetting.put("bpmDefinition", def);
                json.put("bpmDefSetting", bpmDefSetting);

                if (!editorModelJson.containsKey("properties")) {
                    JSONObject initJson = new JSONObject();
                    initJson.put("process_id", model.getKey());
                    initJson.put("name", model.getName());
                    editorModelJson.put("properties", initJson);
                }

                json.put("model", editorModelJson);

            } catch (Exception e) {
                LOGGER.error("Error creating model JSON", e);
                throw new ActivitiException("Error creating model JSON", e);
            }
        }
        return json;
    }
}
