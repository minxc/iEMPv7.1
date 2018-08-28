package org.minxc.emp.bpm.plugin.usercalc.user.def;

public class ExecutorVar {
	public static final String ad = "BO";
	public static final String ae = "flowVar";
	public static final String af = "user";
	public static final String ag = "group";
	private String source = "";
	private String name = "";
	private String ah = "";
	private String ai = "";
	private String aj = "";
	private String dimension = "";
	private String value;

	public ExecutorVar() {
	}

	public ExecutorVar(String source, String name, String executorType, String userValType, String groupValType,
			String dimension) {
		this.source = source;
		this.name = name;
		this.ah = executorType;
		this.ai = userValType;
		this.aj = groupValType;
		this.dimension = dimension;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExecutorType() {
		return this.ah;
	}

	public void setExecutorType(String executorType) {
		this.ah = executorType;
	}

	public String getUserValType() {
		return this.ai;
	}

	public void setUserValType(String userValType) {
		this.ai = userValType;
	}

	public String getGroupValType() {
		return this.aj;
	}

	public void setGroupValType(String groupValType) {
		this.aj = groupValType;
	}

	public String getDimension() {
		return this.dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}