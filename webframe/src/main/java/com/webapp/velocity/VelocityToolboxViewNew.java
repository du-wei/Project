package com.webapp.velocity;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;

public class VelocityToolboxViewNew extends VelocityToolboxView  {
	@Override
	protected Context createVelocityContext(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		ViewToolContext ctx = new ViewToolContext(this.getVelocityEngine(),request, response, this.getServletContext());

		if (this.getToolboxConfigLocation() != null) {
			ToolManager tm = new ToolManager();
			tm.setVelocityEngine(this.getVelocityEngine());
			tm.configure(this.getServletContext().getRealPath(this.getToolboxConfigLocation()));

			if (tm.getToolboxFactory().hasTools(Scope.REQUEST)) {
                ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.REQUEST));
            }
            if (tm.getToolboxFactory().hasTools(Scope.APPLICATION)) {
                ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.APPLICATION));
            }
            if (tm.getToolboxFactory().hasTools(Scope.SESSION)) {
                ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.SESSION));
            }
		}

		if (model != null && !model.isEmpty()) {
			ctx.putAll(model);
		}
		ctx.put("velocity_keys", ctx.keySet());
		return ctx;
	}
}
