package org.minxc.emp.basis.impl.groovy;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.minxc.emp.basis.api.groovy.IGroovyScriptEngine;
import org.minxc.emp.basis.api.groovy.IScript;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.minxc.emp.core.util.AppContextUtil;

import groovy.lang.GroovyShell;
import lombok.extern.slf4j.Slf4j;

/**
 * 脚本引擎用于执行groovy脚本。<br/>
 * 实现了IScript接口的类。 可以在脚本中使用。
 */
@Slf4j
@Component
public class GroovyScriptEngine implements IGroovyScriptEngine, ApplicationListener{

    private GroovyBinding groovyBinding = new GroovyBinding();
    
    @Override
    public void execute(String script) {
        executeObject(script, null);
    }

    @Override
    public void execute(String script, Map<String, Object> vars) {
        executeObject(script, vars);
    }

    @Override
    public boolean executeBoolean(String script, Map<String, Object> vars) {
        return (Boolean) executeObject(script, vars);
    }
 
    @Override
    public String executeString(String script, Map<String, Object> vars) {
        return (String) executeObject(script, vars);
    }

   
    @Override
    public int executeInt(String script, Map<String, Object> vars) {
        return (Integer) executeObject(script, vars);
    }
 
    @Override
    public float executeFloat(String script, Map<String, Object> vars) {
        return (Float) executeObject(script, vars);
    }

    @Override
    public Object executeObject(String script, Map<String, Object> vars) {
        //在执行时清除流程变量。
        groovyBinding.clearVariables();
        
        if(log.isDebugEnabled()) {
        	log.debug("执行:" + script);
        	log.debug("variables:" +vars+"");
        }
        GroovyShell shell = new GroovyShell(groovyBinding);
        this.setParameters(shell, vars);

        script = script.replace("&apos;", "'").replace("&quot;", "\"")
                .replace("&gt;", ">").replace("&lt;", "<")
                .replace("&nuot;", "\n").replace("&amp;", "&");

        Object rtn = shell.evaluate(script);
        return rtn;
    }

    /**
     * 设置执行参数。
     *
     * @param shell
     * @param vars
     */
    private void setParameters(GroovyShell shell, Map<String, Object> vars) {
        if (vars == null) return;
        Set<Map.Entry<String, Object>> set = vars.entrySet();
        for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it .hasNext();){
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it .next();
            shell.setVariable(entry.getKey(), entry.getValue());
        }
    }
    
    @Override
    public void onApplicationEvent(ApplicationEvent arg0) {
    	Map<String, IScript> scirptImpls =	AppContextUtil.getImplInstance(IScript.class);
    	for(Entry<String, IScript> scriptMap : scirptImpls.entrySet()) {
    		groovyBinding.setProperty(scriptMap.getKey(), scriptMap.getValue());
    	}
    }
}
