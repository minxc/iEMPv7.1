package org.activiti.engine.impl.bpmn.helper;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;


public class SkipExpressionUtil {
  
  public static boolean isSkipExpressionEnabled(DelegateExecution execution, Expression skipExpression) {
    
    if (skipExpression == null) {
      return false;
    }
    
    final String skipExpressionEnabledVariable = "_ACTIVITI_SKIP_EXPRESSION_ENABLED";
    Object isSkipExpressionEnabled = execution.getVariable(skipExpressionEnabledVariable);
    
    if (isSkipExpressionEnabled == null) {
      return false;
      
    } else if (isSkipExpressionEnabled instanceof Boolean) {
      return ((Boolean) isSkipExpressionEnabled).booleanValue();
      
    } else {
      throw new ActivitiIllegalArgumentException(skipExpressionEnabledVariable + " variable does not resolve to a boolean. " + isSkipExpressionEnabled);
    } 
  }
  
  public static boolean shouldSkipFlowElement(DelegateExecution execution, Expression skipExpression) {
    Object value = skipExpression.getValue(execution);
    
    if (value instanceof Boolean) {
      return ((Boolean)value).booleanValue();
      
    } else {
      throw new ActivitiIllegalArgumentException("Skip expression does not resolve to a boolean: " + skipExpression.getExpressionText());
    }
  }
}
